package com.gregrode.common.util.tree;

/**
 * Tree is a set of nodes and edges that connect them.<br/>
 * Exactly one path between any two nodes.<br/>
 * Path is a connected sequence of edges.<br/>
 * 
 * <pre>
 * 		 o o o
 * 		  \|/		
 * 		o--o--o
 * 		  /|\
 * 		 o o o
 * </pre>
 * 
 * <b>Rooted Tree</b> - one distinguished node is called the root.<br/>
 * Every node c, except root, has one parent p, the first node on path from c to
 * the root c is p's child.<br/>
 * 
 * Root has no parent. A node can have any number of children.<br/>
 * 
 * <ul>
 * <li>Leaf node is a node with no children.
 * <li>Siblings are node with same parent.
 * <li>Ancestors of node d: node on path form d to root, including d, d's
 * grandparent ... root. if a is an ancestor of d, then d is descendant of a
 * <li>length of path is the number of edges in path
 * <li>Depth of node n is the length of path form n to the root. eg the depth of
 * the root is 0 in every tree.
 * <li>the height of the node n is the length of the path from n to its deepest
 * descendant. eg. the height of any leaf is zero
 * 
 * <li>height of a tree = height of the root.
 * <li>Subtree rooted at n is tree formed by n and its descendants
 * <li>A binary tree is where no node have more than 2 children, every child is
 * a left child or right child, even if its the only child.
 * </ul>
 * Approach 1 : Each node has 3 references, item, parent, children stored in a
 * list.<br/>
 * Approach 2 : siblings are directly linked <br/>
 * <br/>
 * <b>Tree Traversals</b> - traversal a manner of visiting each node in a tree
 * once<br/>
 * <ul>
 * <li><b>Preorder traversal</b> - visit each node before recursively visiting
 * its children, left to right. each node visited only once, so a preorder takes
 * O(n) time, where n is number of node in tree. Good for directory structures
 * 
 * <pre>
 *            1
 *          /   \
 *         2     6
 *        /|\   /|\
 *       3 4 5 7 8 9
 * </pre>
 * 
 * </li>
 * <li><b>Postorder traversal</b> - visit each node's children (left to right)
 * before the node itself.
 * 
 * <pre>
 *              9
 *            /   \
 *           4     5
 *          /|\   /|\
 *         1 2 3 6 7 8
 * </pre>
 * 
 * Good to sum of item in the tree.</li>
 * <li><b>Binary trees - Inorder traversal</b> : visit left child, then node then right
 * child.
 * <li><b>Level order traversal</b> - Visit root, then all depth 1 nodes (left to
 * right), then all depth 2 nodes, etc. this is note naturally recursive. <br/>
 * Use a queue, which initially contains only the root.
 * 
 * <pre>
 * Repeat - Dequeue a node from front 
 *        - Visit it 
 *        - Enqueue its children (left t0 right) ) until queue is empty. Note, enqueue goes at
 *          end of list
 * </pre>
 * 
 * </li>
 * </ul>
 * 
 * 
 * @author Gregroy Dennis
 * 
 */
public class Tree
{

}
