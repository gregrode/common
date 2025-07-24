package com.gregrode.common.util.tree;

import java.util.Collection;

/**
 * The <code>Selectable</code> allows an object to set a boolean to indicate
 * that is was selected for some processing. Note, this implies that the objects
 * are part of Collections.
 *
 * @author Gregroy Dennis
 *
 * @param <T>
 */
public interface Selectable<T> {
	/**
	 * @return a boolean that indicates whether or not the object is selected.
	 */
	boolean isSelected();

	/**
	 * @param selected
	 *            boolean that indicates whether or not the object is selected.
	 */
	void setSelected(boolean selected);

	/**
	 * Set the selected boolean in the each of the object to false.
	 */
	void unselectAll();

	/**
	 * Get the first selected object represented by T.
	 *
	 * @return T
	 */
	default T getFirstSelectedObject() {
		return getSelectedObjects().stream().findFirst().get();
	}

	/**
	 * Get the collection of selected objects.
	 *
	 * @return Collection<T>
	 */
	Collection<T> getSelectedObjects();

	/**
	 * Find an object by it name and select it.
	 * 
	 * @param name
	 *            the name of the selection
	 * @return T
	 */
	T selectByName(String name);
}
