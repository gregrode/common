package com.gregrode.common.pagination;

import com.gregrode.common.util.StringUtil;

/**
 * The <code>Pagination</code> encapsulates different pagination objects.
 *
 * @author Gregroy Dennis
 *
 */
public interface Pagination {

	/**
	 * @return the rowCount
	 */
	int getRowCount();

	/**
	 * @param rowCount
	 *            the rowCount to set
	 */
	void setRowCount(int rowCount);

	/**
	 * @return offset
	 */
	int getOffset();

	/**
	 * @param offset
	 *            the offset to set
	 */
	default void setOffset(final String offset) {
		setOffset(Integer.parseInt(offset));
	}

	/**
	 * @param offset
	 *            the offset to set
	 */
	void setOffset(int offset);

	/**
	 * Reset the pagination offset to 0
	 */
	default void resetOffset() {
		setOffset(0);
	}

	/**
	 * @return limit
	 */
	int getLimit();

	/**
	 * @param limit
	 *            the limit
	 */
	void setLimit(final int limit);

	/**
	 * @param limit
	 *            the limit to set
	 */
	default void setLimit(final String limit) {
		if (StringUtil.isEmpty(limit)) return;
		setLimit(Integer.valueOf(limit));
	}

	
	/**
	 * @return a boolean that indicates if this is the first page.
	 */
	default boolean isFirst() {
		return getOffset() == 0;
	}
	
	
	/**
	 * @return a boolean that indicates if this is the last page.
	 */
	default boolean isLast() {
		return getOffset() == getLastOffset();
	}
	
	/**
	 * @return the start of the pagination.
	 */
	default int getStart() {
		return getOffset() + 1;
	}
	
	/**
	 * @return the end of the pagination.
	 */
	default int getEnd() {
		return getOffset() + getLimit();
	}

	
	/**
	 * @return the start of the pagination.
	 */
	default int getPageNumber() {
		final int limit = getLimit();
		final int offset = getOffset();
		if ((limit == 0) || (offset < limit)) return 1;	
		final int pageNumber =  (offset / limit);
		return pageNumber + 1;
	}

	/**
	 * @return the number of pages
	 */
	default int getTotalPages() {
		final int limit = getLimit();
		if (limit == 0) return 1;

		final int rowCount = getRowCount();
		final int remainder = rowCount % limit;
		
		if (remainder == 0) return (rowCount / limit);
		
		// If there is a remainder, then add one to the row count divide by limit.
		return (rowCount / limit) + 1;
	}


	/**
	 * @return the last offset
	 */
	default int getLastOffset() {
		final int limit = getLimit();
		final int rowCount = getRowCount();
		if (limit <= 0) return rowCount;
		if (rowCount < limit) return 0;
		
		final int remainder = rowCount % limit;
		if (remainder == 0) return rowCount - limit;
		return rowCount - remainder;
	}

	/**
	 * @return the next page index
	 */
	default int getNextPage() {
		final int next = getOffset() + getLimit();
		return (getRowCount() < next) ? getRowCount() : next;
	}

	
	/**
	 * @return the previous page
	 */
	default int getPreviousPage() {
		final int prev = getOffset() - getLimit();
		return (prev < 0) ? 1 : prev;
	}
}
