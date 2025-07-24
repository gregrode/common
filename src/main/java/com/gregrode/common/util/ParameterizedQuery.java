package com.gregrode.common.util;

import static com.gregrode.common.util.Util.toEntry;
import static com.gregrode.common.util.StringUtil.surroundWithSingleQuotes;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.gregrode.common.field.Field;

/**
 * @author Gregroy Dennis
 *
 */
public final class ParameterizedQuery {

	public static final String SQL_PLACEHOLDER = "?";
	public static final String EQUAL_SQL_PLACEHOLDER = " = ? ";

	private long startTime = 0L;
	private long endTime = 0L;
	private final StringBuilder query;
	private boolean hasWhereClause = false;
	private final LinkedList<List<Entry<Integer, Object>>> batch;

	/**
	 * Default Constructor
	 */
	public ParameterizedQuery() {
		this(false, "", new LinkedList<>());
	}

	/**
	 * Deep Copy Constructor
	 */
	public ParameterizedQuery(ParameterizedQuery query) {
		this.query = new StringBuilder(query.query);
		this.batch = new LinkedList<>();
		this.hasWhereClause = query.hasWhereClause;
		this.startTime = query.startTime;
		this.endTime = query.endTime;

		for (final List<Entry<Integer, Object>> queryBatch : query.batch) {
			final List<Entry<Integer, Object>> innerBatch = new LinkedList<>();
			for (Entry<Integer, Object> entry : queryBatch) {
				Entry<Integer, Object> param = new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue());
				innerBatch.add(param);
			}
			this.batch.add(innerBatch);
		}
	}

	/**
	 * Basic Constructor
	 *
	 * @param query the query
	 */
	public ParameterizedQuery(final CharSequence query) {
		this(false, query, new LinkedList<>());
	}


	/**
	 * Partial Constructor
	 *
	 * @param hasWhere a boolean that indicates if the query has a where clause.
	 * @param query    the query
	 * 
	 */
	public ParameterizedQuery(final boolean hasWhere, final CharSequence query) {
		this(hasWhere, query, new LinkedList<>());
	}

	/**
	 * Partial Constructor
	 *
	 * @param columnName the column name
	 * @param value      the column value
	 * @param dataType   the data type
	 */
	public ParameterizedQuery(final String columnName, final Object value, final Integer dataType) {
		this(false, "", new LinkedList<>());
		append(columnName, value, dataType);
	}

	/**
	 * Full Constructor
	 *
	 * @param hasWhere   a boolean that indicates if the query has a where clause.
	 * @param query      the query
	 * @param parameters the list of parameters associated with the query
	 */
	public ParameterizedQuery(final boolean hasWhere,final CharSequence query,
			final List<Entry<Integer, Object>> parameters) {
		this.hasWhereClause = hasWhere;
		this.query = new StringBuilder(query);
		this.batch = new LinkedList<>();
		if (parameters != null) {
			batch.add(parameters);
		}
	}

	/**
	 * @return the current query
	 */
	public String getQuery() {
		return query.toString();
	}

	/**
	 * @return {@link ParameterizedQuery}
	 */
	public ParameterizedQuery clear() {
		query.setLength(0);
		return this;
	}

	/**
	 * Add "FROM" clause to the query.
	 * 
	 * @param sql the sql to append to the current query
	 * @return {@link ParameterizedQuery}
	 */
	public ParameterizedQuery from(final CharSequence sql) {
		if (StringUtil.isEmpty(sql)) {
			return this;
		}
		return append(" FROM ").append(sql);
	}

	/**
	 * Add "FROM" clause to the query.
	 * 
	 * @param sql the sql to append to the current query
	 * @return {@link ParameterizedQuery}
	 */
	public ParameterizedQuery from(final ParameterizedQuery sql) {
		if ((sql == null) || StringUtil.isEmpty(sql.query)) {
			return this;
		}
		return append(" FROM ").append(expression(sql));
	}

	/**
	 * Add "WHERE" clause to the query.
	 * 
	 * @param sql the sql to append to the current query
	 * @return {@link ParameterizedQuery}
	 */
	public ParameterizedQuery where(final CharSequence sql) {
		if (StringUtil.isEmpty(sql)) {
			return this;
		}
		append(" WHERE ").append(sql);
		hasWhereClause = true;
		return this;
	}

	/**
	 * Add "WHERE" clause to the query.
	 * 
	 * @param sql the sql to append to the current query
	 * @return {@link ParameterizedQuery}
	 */
	public ParameterizedQuery where(final ParameterizedQuery sql) {
		if ((sql == null) || StringUtil.isEmpty(sql.query)) {
			return this;
		}
		append(" WHERE ").append(expression(sql));
		hasWhereClause = true;
		return this;
	}
	
	
	/**
	 * Add an entry to the parameters list
	 *
	 *@param field the name of the field
	 * @param dataType the data type
	 * @param value    the column value
	 * @return {@link ParameterizedQuery}
	 */
	public ParameterizedQuery where(final String field , final Integer dataType, final Object value) {
		Objects.requireNonNull(dataType);
		return where(is(field, value, dataType));
	}

	/**
	 * Add "AND" clause to the query.
	 * 
	 * @param sql the sql to append to the current query
	 * @return {@link ParameterizedQuery}
	 */
	public ParameterizedQuery and(final CharSequence sql) {
		if (StringUtil.isEmpty(sql)) {
			return this;
		}
		return append(" AND ").append(sql);
	}

	/**
	 * Add "AND" clause to the query.
	 * 
	 * @param sql the sql to append to the current query
	 * @return {@link ParameterizedQuery}
	 */
	public ParameterizedQuery and(final ParameterizedQuery sql) {
		if (sql == null || StringUtil.isEmpty(sql.query)) {
			return this;
		}
		return append(" AND ").append(sql);
	}

	/**
	 * Add "OR" clause to the query.
	 * 
	 * @param sql the sql to append to the current query
	 * @return {@link ParameterizedQuery}
	 */
	public ParameterizedQuery or(final CharSequence sql) {
		if (StringUtil.isEmpty(sql)) {
			return this;
		}
		return append(" OR ").append(sql);
	}

	/**
	 * Add "OR" clause to the query.
	 * 
	 * @param sql the sql to append to the current query
	 * @return {@link ParameterizedQuery}
	 */
	public ParameterizedQuery or(final ParameterizedQuery sql) {
		if (sql == null || StringUtil.isEmpty(sql.query)) {
			return this;
		}
		return append(" OR ").append(sql);
	}


	/**
	 * @param sql the sql to append to the current query
	 * @return {@link ParameterizedQuery}
	 */
	public ParameterizedQuery append(final ParameterizedQuery sql) {
		if (sql == null || StringUtil.isEmpty(sql.query)) {
			return this;
		}
		query.append(sql.query);
		batch.getLast().addAll(sql.getParameters());
		this.startTime = sql.startTime;
		this.endTime = sql.endTime;
		return this;
	}

	/**
	 *
	 * @param columnName the column name
	 * @param value      the column value
	 * @param dataType   the data type
	 * @return {@link ParameterizedQuery}
	 */
	public ParameterizedQuery append(final String columnName, final Object value, final Integer dataType) {
		if (value == null) {
			if (Field.isNumeric(dataType)) {
				return expression(append(columnName).append(" is NULL"));
			}
			addParameter(dataType, value);
			return expression(append(columnName).append(" is NULL").or(append(columnName).append(EQUAL_SQL_PLACEHOLDER)));
		}
		
		addParameter(dataType, value);
		return expression(append(columnName).append(EQUAL_SQL_PLACEHOLDER));
	}

	/**
	 * @param sql the sql to append to the current query
	 * @return {@link ParameterizedQuery}
	 */
	public ParameterizedQuery append(final CharSequence sql) {
		if (StringUtil.isEmpty(sql)) {
			return this;
		}

		query.append(sql);
		return this;
	}

	/**
	 * @param sql the sql to append to the current query
	 * @return {@link ParameterizedQuery}
	 */
	public ParameterizedQuery append(final Object sql) {
		query.append(String.valueOf(sql));
		return this;
	}

	/**
	 * Add an entry to the parameters {@link List}
	 *
	 * @param entry the entry to add
	 * @return {@link ParameterizedQuery}
	 */
	public ParameterizedQuery addParameter(final Entry<Integer, Object> entry) {
		List<Entry<Integer, Object>> parameters = batch.getLast();
		if (parameters == null) {
			parameters = new ArrayList<>();
			batch.add(parameters);
		}
		parameters.add(Objects.requireNonNull(entry));
		return this;
	}

	/**
	 * @return the parameters {@link List}
	 */
	public List<Entry<Integer, Object>> getParameters() {
		return new ArrayList<>(batch.getLast());
	}

	/**
	 * @return a boolean that indicates if the query is a batch query
	 */
	public boolean isBatch() {
		if (batch == null) {
			return false;
		}
		return (batch.size() > 1);
	}
	
	public boolean isEmpty() {
		return (query == null) || (query.length() == 0);
	}

	
	/**
	 * Add an entry to the parameters list
	 *
	 * @param dataType the data type
	 * @param value    the column value
	 * @return {@link ParameterizedQuery}
	 */
	public ParameterizedQuery addParameter(final Integer dataType, final Object value) {
		Objects.requireNonNull(dataType);
		return addParameter(toEntry(dataType, value));
	}

	/**
	 * Create the next parameters.
	 * 
	 * @return {@link ParameterizedQuery}
	 */
	public ParameterizedQuery nextBatch() {
		return addParameters(new ArrayList<>());
	}

	/**
	 * Add a given list of parameters to the batch
	 *
	 * @param parameters the list of parameters
	 * @return {@link ParameterizedQuery}
	 */
	public ParameterizedQuery addParameters(List<Entry<Integer, Object>> parameters) {
		if (parameters == null) {
			return this;
		}
		final List<Entry<Integer, Object>> last = batch.getLast();
		if (last != null && last.isEmpty()) {
			last.addAll(parameters);
			return this;
		}
		batch.add(parameters);
		return this;
	}

	/**
	 * Add the parameters to the the PrepareStatement
	 *
	 * @param pstmt The PreparedStatement object
	 * @return {@link ParameterizedQuery}
	 * @throws SQLException
	 * @throws UnsupportedEncodingException
	 */
	public ParameterizedQuery setValues(final PreparedStatement pstmt)
			throws SQLException, UnsupportedEncodingException {
		if (batch == null) {
			return this;
		}
		if (isBatch()) {
			return addBatch(pstmt);
		}
		return setValues(pstmt, getParameters());
	}

	/**
	 * Add the parameters to the the PrepareStatement
	 *
	 * @param pstmt The PreparedStatement object
	 * @return {@link ParameterizedQuery}
	 * @throws SQLException
	 * @throws UnsupportedEncodingException
	 */
	public ParameterizedQuery setValues(final PreparedStatement pstmt, final List<Entry<Integer, Object>> parameters)
			throws SQLException, UnsupportedEncodingException {

		for (final ListIterator<Entry<Integer, Object>> iter = parameters.listIterator(); iter.hasNext();) {
			final Entry<Integer, Object> entry = iter.next();
			final int index = iter.nextIndex();
			final Object value = entry.getValue();
			final int type = entry.getKey().intValue();
			final String valueAsString = String.valueOf(value);
			if (value == null || valueAsString.isEmpty()) {
				pstmt.setNull(index, type);
				continue;
			}

			switch (type) {
			case Types.BOOLEAN:
				if (value instanceof Boolean) {
					final Boolean bool = (Boolean) value;
					pstmt.setBoolean(index, bool);
					break;
				}
				pstmt.setBoolean(index, Boolean.valueOf(valueAsString));
				break;
			case Types.DOUBLE:
				if (value instanceof Double) {
					final Double dbl = (Double) value;
					pstmt.setDouble(index, dbl);
					break;
				}
				pstmt.setDouble(index, Double.valueOf(valueAsString));
				break;
			case Types.FLOAT:
				if (value instanceof Float) {
					final Float flt = (Float) value;
					pstmt.setFloat(index, flt);
					break;
				}
				pstmt.setFloat(index, Float.valueOf(valueAsString));
				break;
			case Types.INTEGER:
				if (value instanceof Integer) {
					final Integer intgr = (Integer) value;
					pstmt.setInt(index, intgr);
					break;
				}
				pstmt.setInt(index, Integer.valueOf(valueAsString));
				break;
			case Types.NUMERIC:
			case Types.BIGINT:
			case Types.DECIMAL:
				if (value instanceof BigDecimal) {
					final BigDecimal decimal = (BigDecimal) value;
					pstmt.setBigDecimal(index, decimal);
					break;
				}
				pstmt.setBigDecimal(index, new BigDecimal(valueAsString));
				break;
			case Types.LONGNVARCHAR:
			case Types.LONGVARBINARY:
			case Types.LONGVARCHAR:
				if (value instanceof Long) {
					final Long lng = (Long) value;
					pstmt.setLong(index, lng);
					break;
				}
				pstmt.setLong(index, Long.valueOf(valueAsString));
				break;
			case Types.DATE:
				if (value instanceof Date) {
					final Date date = (Date) value;
					pstmt.setDate(index, date);
					break;
				}
				pstmt.setDate(index, Date.valueOf(valueAsString));
				break;
			case Types.TIMESTAMP:
				if (value instanceof Timestamp) {
					final Timestamp timestamp = (Timestamp) value;
					pstmt.setTimestamp(index, timestamp);
					break;
				}
				pstmt.setTimestamp(index, Timestamp.valueOf(valueAsString));
				break;
			case Types.BLOB:
				if (value instanceof byte[]) {
					final byte[] bytes = (byte[]) value;
					pstmt.setBlob(index, new ByteArrayInputStream(bytes));
					break;
				}
				if (value instanceof Blob) {
					final Blob blob = (Blob) value;
					pstmt.setBlob(index, blob);
					break;
				}

				if (value instanceof byte[]) {
					InputStream stream = new ByteArrayInputStream((byte[]) value);
					pstmt.setBlob(index, stream);
					break;
				}

				pstmt.setNull(index, type);
				break;
			case Types.CLOB:
				if (value instanceof Clob) {
					final Clob clob = (Clob) value;
					pstmt.setClob(index, clob);
					break;
				}
				if (value instanceof Reader) {
					final Reader reader = (Reader) value;
					pstmt.setClob(index, reader);
					break;
				}

				if (value instanceof byte[]) {
					Reader reader = new InputStreamReader(new ByteArrayInputStream((byte[]) value));
					pstmt.setClob(index, reader);
					break;
				}

				pstmt.setNull(index, type);
				break;
			case Types.NULL:
				pstmt.setNull(index, type);
				break;
			default: {
				pstmt.setString(index, valueAsString);
				break;
			}
			}
		}
		return this;
	}

	/**
	 * Add value to {@link PreparedStatement} batch
	 * 
	 * @param pstmt {@link PreparedStatement} object
	 * @throws UnsupportedEncodingException
	 * @throws SQLException
	 */
	public ParameterizedQuery addBatch(final PreparedStatement pstmt)
			throws UnsupportedEncodingException, SQLException {

		if (batch == null) {
			return this;
		}
		for (final List<Entry<Integer, Object>> parameters : batch) {
			if (parameters.isEmpty()) {
				continue;
			}
			setValues(pstmt, parameters);
			pstmt.addBatch();
		}
		return this;
	}

	/**
	 * @return the run time in nanoseconds
	 */
	public long getRuntimeInNanoseconds() {
		return endTime - startTime;
	}

	/**
	 * Start the timer
	 * 
	 * @return {@link ParameterizedQuery} object
	 */
	public ParameterizedQuery startTimer() {
		this.startTime = System.nanoTime();
		return this;
	}

	/**
	 * End the timer
	 * 
	 * @return {@link ParameterizedQuery} object
	 */
	public ParameterizedQuery endTimer() {
		this.endTime = System.nanoTime();
		return this;
	}

	/**
	 * @return a boolean that indicates if the query contains a where clause.
	 */
	public boolean hasWhere() {
		return hasWhereClause;
	}

	/**
	 * Get a populated {@link PreparedStatement} object.
	 *
	 * @return PreparedStatement
	 * @throws Exception
	 */
	public PreparedStatement getPreparedStatement(Connection conn) throws Exception {
		Objects.requireNonNull(query, "query cannot be empty.");
		final PreparedStatement pstmt = conn.prepareStatement(getQuery(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		pstmt.clearParameters();
		pstmt.clearBatch();
		pstmt.clearWarnings();
		pstmt.setFetchSize(200);
		setValues(pstmt);
		return pstmt;
	}

	/**
	 * @param columns the list of columns
	 * @return Generates the beginning of "SELECT" query statement and its columns
	 *         (if supplied otherwise it uses the "*" wildcard)
	 */
	public static ParameterizedQuery select(final String... columns) {
		if (columns == null || columns.length == 0) {
			return new ParameterizedQuery("SELECT * ");
		}
		String columnsAsString = Stream.of(columns).filter(StringUtil::isNotEmpty).collect(Collectors.joining(", "));
		return new ParameterizedQuery("SELECT ").append(columnsAsString);
	}
	
	/**
	 * @param columns the list of columns
	 * @return Generates the beginning of "SELECT" query statement and its columns
	 *         (if supplied otherwise it uses the "*" wildcard)
	 */
	public static ParameterizedQuery select(final ParameterizedQuery sql) {
		if (sql == null || StringUtil.isEmpty(sql.query)) {
			return new ParameterizedQuery("SELECT * ");
		}
		return new ParameterizedQuery("SELECT ").append(expression(sql));
	}

	
	public static ParameterizedQuery selectAll(final CharSequence objectName) {
		if ( StringUtil.isEmpty(objectName)) {
			new ParameterizedQuery();
		}
		return new ParameterizedQuery("SELECT * ").from(objectName);
	}
	
	public static ParameterizedQuery selectAll(final ParameterizedQuery sql) {
		if (sql == null || StringUtil.isEmpty(sql.query)) {
			return new ParameterizedQuery();
		}
		return new ParameterizedQuery("SELECT * ").from(sql);
	}
	/**
	 * @param objectName the name of the database object to update
	 * @return Generates the beginning of the "UPDATE" statement.
	 */
	public static ParameterizedQuery update(final CharSequence objectName) {
		if (StringUtil.isEmpty(objectName)) {
			return new ParameterizedQuery();
		}
		return new ParameterizedQuery("UPDATE ").append(objectName);
	}

	/**
	 * @param from the object to delete from database.
	 * @return Generates the beginning of the "DELETE" statement.
	 */
	public static ParameterizedQuery delete(final CharSequence from) {
		if (StringUtil.isEmpty(from)) {
			return new ParameterizedQuery();
		}
		return new ParameterizedQuery("DELETE").from(from);
	}

	/**
	 * @param object the object to drop from the database.
	 * @return Generates the beginning of the "DELETE" statement.
	 */
	public static ParameterizedQuery drop(final CharSequence object) {
		if (StringUtil.isEmpty(object)) {
			return new ParameterizedQuery();
		}
		return new ParameterizedQuery("DROP").append(object);
	}

	/**
	 **
	 * Generates a search query based on the given parameters
	 * 
	 * @param columnName  the name of the column
	 * @param searchValue the search value
	 * @param castName    the name of the database type
	 * @return String
	 */
	public static ParameterizedQuery like(final String columnName, final String search, final String castName) {
		if (StringUtil.isEmpty(columnName) || StringUtil.isEmpty(search)) {
			return new ParameterizedQuery();
		}
		final boolean isCasted = StringUtil.isNotEmpty(castName);
		String searchFor = "%" + search.toLowerCase() + "%";
		
		if (isCasted) {
			return expression(lower(cast(columnName, castName)).append(" LIKE ? ").addParameter(Types.VARCHAR, searchFor));
		}
		return expression(lower(columnName).append(" LIKE ? ").addParameter(Types.VARCHAR, searchFor));
	}

	/**
	 *
	 * @param columnName the column name
	 * @param value      the column value
	 * @param dataType   the data type
	 * @return {@link ParameterizedQuery}
	 */
	public static ParameterizedQuery is(final String columnName, final Object value, final Integer dataType) {
		ParameterizedQuery valueExpression = expression(new ParameterizedQuery(columnName).append(EQUAL_SQL_PLACEHOLDER))
				.addParameter(dataType, value);
		if (value == null) {
			ParameterizedQuery nullExpression = expression(columnName + " is NULL");
			if (Field.isNumeric(dataType)) {
				return nullExpression;
			}
			return expression(nullExpression.or(valueExpression));
		}
		return valueExpression;
	}	

	public static ParameterizedQuery expression(final ParameterizedQuery sql) {
		if (sql == null || StringUtil.isEmpty(sql.query)) {
			return new ParameterizedQuery();
		}
		return new ParameterizedQuery(" ( ").append(sql).append(" ) ");
	}

	public static ParameterizedQuery expression(final CharSequence sql) {
		if (StringUtil.isEmpty(sql)) {
			return new ParameterizedQuery();
		}

		return new ParameterizedQuery(" ( ").append(sql).append(" ) ");
	}
	
	public static ParameterizedQuery lower(final CharSequence sql) {
		if (StringUtil.isEmpty(sql)) {
			return new ParameterizedQuery();
		}

		return new ParameterizedQuery(" LOWER( ").append(sql).append(" ) ");
	}
	
	public static ParameterizedQuery lower(final ParameterizedQuery sql) {
		if (sql == null || StringUtil.isEmpty(sql.query)) {
			return new ParameterizedQuery();
		}
		return new ParameterizedQuery(" LOWER( ").append(sql).append(" ) ");
	}
	
	public static ParameterizedQuery cast(final CharSequence sql, final CharSequence castType) {
		if (StringUtil.isEmpty(sql)) {
			return new ParameterizedQuery();
		}

		return new ParameterizedQuery(" CAST( ").append(sql).append(" AS ").append(castType).append(" ) ");
	}
	
	public static ParameterizedQuery cast(final ParameterizedQuery sql, final CharSequence castType) {
		if (sql == null || StringUtil.isEmpty(sql.query)) {
			return new ParameterizedQuery();
		}

		return new ParameterizedQuery(" CAST( ").append(sql).append(" AS ").append(castType).append(" ) ");
	}
	
	
	public static ParameterizedQuery orderBy(List<Entry<String, String>> sortedBy) {
		if (sortedBy == null || sortedBy.isEmpty()) {
			return new ParameterizedQuery();
		}
		String orderByClause = sortedBy.stream().map(entry -> {
			String sortId = entry.getKey();
			String direction = entry.getValue();
			return sortId + " " + direction;
		}).collect(Collectors.joining(", "));
		return new ParameterizedQuery(" ORDER BY ").append(orderByClause);
	}

	@Override
	public String toString() {
		final StringBuilder sQuery = new StringBuilder(query);
		for (final ListIterator<Entry<Integer, Object>> iter = getParameters().listIterator(); iter.hasNext();) {
			final Entry<Integer, Object> entry = iter.next();
			final Object value = entry.getValue();
			final int type = entry.getKey().intValue();
			final int start = sQuery.indexOf(SQL_PLACEHOLDER);
			if (start == -1) {
				continue;
			}
			final int end = start + 1;

			final String valueAsString = String.valueOf(value);
			if ((value == null) || (valueAsString.isEmpty() && (type != Types.VARCHAR))) {
				sQuery.replace(start, end, "NULL");
				continue;
			}

			if (valueAsString.isEmpty()) {
				sQuery.replace(start, end, "''");
				continue;
			}
			String sqlQuotedValue = surroundWithSingleQuotes(StringUtil.escapeSQLQuotes(valueAsString));

			switch (type) {
			case Types.BOOLEAN:
			case Types.DOUBLE:
			case Types.FLOAT:
			case Types.INTEGER:
			case Types.NUMERIC:
			case Types.LONGNVARCHAR:
			case Types.LONGVARBINARY:
			case Types.LONGVARCHAR:
				sQuery.replace(start, end, valueAsString);
				break;
			case Types.DATE:
			case Types.TIMESTAMP:
			case Types.TIMESTAMP_WITH_TIMEZONE:
				sQuery.replace(start, end, " timestamp " + sqlQuotedValue);
				break;
			default: {
				sQuery.replace(start, end, sqlQuotedValue);
				break;
			}
			}
		}
		return sQuery.toString();
	}
}
