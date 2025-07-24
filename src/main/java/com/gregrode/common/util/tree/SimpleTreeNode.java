package com.gregrode.common.util.tree;

import static com.gregrode.common.util.Util.isEmpty;

import java.util.Collection;

/**
 * The <code>SimpleTreeNode</code> interface encapsulate classes that have a
 * parent object and children objects. Additional functionality to set, get both
 * parent and children objects, as well as traversing and manipulating the list
 * of children are also provided.
 * 
 * @author Gregroy Dennis
 * 
 * @param <P>
 *            The parent object
 * @param <C>
 *            The child object
 */
public interface SimpleTreeNode<P, C> {

	/**
	 * Set the parent object
	 * 
	 * @param parent
	 */
	void setParent(P parent);

	/**
	 * @return P, which represents the parent object.
	 */
	P getParent();

	/**
	 * @return get the collection of the children objects regardless of visibility.
	 */
	Collection<C> getChildren();

	/**
	 * Add a child object to the list of children.
	 * 
	 * @param child
	 *            The object represented by <code>C</code>, to add.
	 */
	boolean addChild(C child);

	/**
	 * Remove a child object from the list of children.
	 * 
	 * @param childName
	 *            the name of the child
	 * @return boolean that indicates if the item was removed.
	 */
	boolean removeChild(String childName);

	/**
	 * Find a child object based on the array of given parameters.
	 * 
	 * @param childName
	 *            the name of child object.
	 * @return C, which represents an instance of the child object
	 */
	C findChild(String childName);

	/**
	 * @return a integer indicating the number of child nodes.
	 */
	default int getChildCount() {
		final Collection<C> children = getChildren();
		if (isEmpty(children)) {
			return 0;
		}
		return children.size();
	}

	/**
	 * Clear list of child objects
	 */
	void clearChildren();

	/**
	 * @return a boolean that indicates if the current node is the root node.
	 */
	default boolean isRootNode() {
		return (getParent() == null);
	}

	/**
	 * @return a boolean that indicates if the current node has any children.
	 */
	default boolean isChildrenEmpty() {
		return (getChildCount() == 0);
	}

}
