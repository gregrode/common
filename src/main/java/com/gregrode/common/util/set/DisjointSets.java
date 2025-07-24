package com.gregrode.common.util.set;

/**
 * 
 * <b>Disjoint Set</b><br/>
 * No item is in more than one set.<br/>
 * Collection of disjoint sets in called a partition.<br/>
 * 
 * Universe of items - all items that can be a member of a set. Each items is in
 * exactly one set. <br/>
 * 
 * 2 operations:
 * <ol>
 * <li>Union : merges 2 sets int one
 * <li>Find : Takes an item and tell us what set its in.
 * <ol>
 * 
 * Kruskal's algorithm is an example of Disjoint set.
 * 
 * <pre>
 *       Predmont Air    Empire Air    US Air   Pacific Southwest   Web TV  Microsoft
 *				\        /                \     /                      \      / 
 *              Predmont Air              US Air                       Microsoft 
 *                          \            /
 *                              US Air
 * 
 * Find (Empire Air) returns "Empire Air" if first Set.
 * Find (Empire Air) returns "Predmont Air" after 3 unions
 * Find (Empire Air) returns "US Air" after final union.
 * 
 * </pre>
 * 
 * <b>List-Based Disjoint Sets</b><br>
 * Each set references lisr of items in the set.<br/>
 * Each item references set that contains it.<br/>
 * Find: O(1) Quick find Algorithms Union : slow
 * 
 * <b>Tree-based Disjoint Sets and the Quick-Union Algorithm<b/><br/>
 * Union: O(1) time<br/>
 * find Slower<br/>
 * 
 * Quick-union faster overall than quick-find<br/>
 * 
 * Each set maintained as a tree. Data structure is a forest (collection of
 * tree)<br/>
 * Each item is initially root of its own tree<br/>
 * No child or sibling references; only parent.<br/>
 * True identity of each set recorded at root<br/>
 * Union make the root of one set be child of root of the other set<br/>
 * 
 * Find : follow parent references from item to root of tree
 * 
 * Cost proportional to items depth.<br/>
 * 
 * Keep items from getting too deep: At each root, we record size of tree. Union
 * make smaller one a subtree of larger one (Union-by-size Algorithm)<br/>
 * 
 * Implementation Quick-Union with Array Items numbered from zero Array records
 * parent of each item. If item has no parent, record size of its tree Record
 * size s as negative number -s <br/>
 * 
 * <pre>
 *     
 *    -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
 *     0  1  2  3  4  5  6  7  8  9 
 *     
 *     8            1     2   
 *    / \          /|\
 *   5   3        9 0 6 
 *   |   |
 *   4   7
 *   
 *    
 *     1 -4 -1  8  5  8  1  3 -5  1
 *     0  1  2  3  4  5  6  7  8  9
 * 
 * </pre>
 * 
 * Union by Size <br/>
 * 
 * <pre>
 * 	public void union(int root1, int root2)
 *  {
 *  	if (array[root2] < array[root1]
 *      {
 *      	array[root2] += array[root1];
 *      	array[root1] = root2;
 *      }
 *      else
 *      {
 *         array[root1] += array[root2];
 *         array[root2] = root1;
 *      }
 *  }
 * </pre>
 * 
 * Path Compression<br/>
 * 
 * <pre>
 *                0
 *              / | \
 *             1  2  3 
 *            /|\                
 *           4 5 6
 *          /|\ 
 *         7 8 9  
 *         
 *         find(7)
 *         
 *                   0
 *            / /    |  \ \
 *           7 4     1   2 3
 *            / \   / \
 *           8   9 5   6 
 *           
 *           
 *           
 *    Public int find(int x){
 *    	if (array[x] < 0) {
 *    		return x;
 *    	} else {
 *    	 	array[x] = find(array[x]);
 *    		return array[x];
 *    	}
 *    }
 * </pre>
 * 
 * Union O(1) time find : O(log u) worst-case time, where u is number of unions
 * prior to find<br/>
 * average: running time close to constant<br/>
 * A sequence of f find and union operations takes O(u+f alpha(f+u, u)) time
 * worst case.<br/>
 * alpha - Extremely slow growing "" inverse Ackermann<br/>
 * 
 * Function > 4 for any values of f & u you will ever see.<br/>
 * 
 * 
 *
 * <br/>
 * <br/>
 * 
 * @author greg
 *
 */
public interface DisjointSets {
	int find(int x);

	void union(int root1, int root2);

}
