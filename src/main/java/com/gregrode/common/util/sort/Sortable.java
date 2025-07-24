package com.gregrode.common.util.sort;

import java.util.SortedMap;

/**
 * The <code>Sortable</code> interface encapsulates a class that may be sorted.
 *
 * @author Gregroy Dennis
 *
 */
public interface Sortable {

	/**
	 * @return a boolean that indicates if sorting is allowed.
	 */
	boolean isSortable();

	/**
	 * @param sortable a boolean that indicates if sorting is allowed
	 */
	void setSortable(boolean sortable);

	/**
	 * @return a Id the represents the sorted object.
	 */
	String getSortId();

	/**
	 * @param sortId The sortId to set
	 */
	void setSortId(String sortId);

	/**
	 * @return sort direction.
	 */
	String getSortDirection();

	/**
	 * @param sortDirection a string that indicates sort direction.
	 */
	void setSortDirection(String sortDirection);

	/**
	 * @return the a map of columns to sort by.
	 */
	SortedMap<String, String> getSortedBy();

	/**
	 * @param sortedBy a map of columns to sort by.
	 */
	void setSortedBy(SortedMap<String, String> sortedBy);

	static String ACSENDING = "asc";
	static String DESCENDING = "desc";
}
