package com.gregrode.common.util.graph;

/**
 * A graph G is a set of V of vertices and a set of W edges that connect
 * vertices. G = (V, E)
 * 
 * 2 types: Directed and Undirected Digraph : every edge is directed from some
 * vertex v to some other vertes e = (v, w) v -> w<br/>
 * v is the origin and w is the destination<br/>
 * 
 * <pre>
 *      City Streets
 *      1 <-- 2 <-- 3
 *      |          ^| 
 *      v          |v
 *      4 --> 5 --> 6
 * 
 * </pre>
 * 
 * <b>Undirected graph</b> : e is an unordered pair v - w = w - v (no arrow)
 * 
 * <pre>
 *       State borders:
 *           Albany -- Kensington
 *                 \   |
 *    EmeryVille--Berkeley
 *           |   /  
 *         Oakland -- Predmont
 * </pre>
 * 
 * Multiple copies of an edge are forbidden.<br>
 * Digraphs can have both (v,w) and (w,v)
 * 
 * Self-edge: (v,v)
 * 
 * <pre>
 * 		 o--
 * 		 ^--|
 * 
 * </pre>
 * 
 * 
 * Path: sequence of vertices with each adjacent pair connected by an edge. If
 * graph is directed, edges must be aligned with direction of path. Length of
 * path number of edges in path. <4,5,6,3> path of length 3 <2> length 0 <br/>
 * <br/>
 * <b>Strongly connected</b> - if there is a path from any vertex to any other
 * vertex (Called connected in undirected graph)<br/>
 * <b>Degree of an vertex</b> - number of edges incident on vertex. (self-edges
 * count as one).<br/>
 * 
 * digraph Degrees: indegree - number of edges directed toward vertex outdegree
 * - number of directed away
 * 
 * 
 * Graph Representations
 * <ul>
 * <li>Adjacency matrix : |v| - by |v| array of booleans t = connected
 * 
 * <pre>
 *   1 2 3 4 5 6 
 * 1 t f ... 
 * 2 f t
 * 3 t
 * 4
 * 5
 * 6
 * </pre>
 * 
 * Maximum possible edges is |v|^2 (digraph)<br/>
 * <b>Planar graphs</b> have O(|v|) linear<br/>
 * <b>Sparse graph</b> has far fewer edges than maximum possible.
 * <li>Adjacency list - each vertex v has a linked list of edges out <br/>
 * Memory used O(|v| + |e|)<br/>
 * 
 * If vertices have names ("Albany") use hash table to map strings (or any
 * object) to lists<br/>
 * key vertex name<br/>
 * value: list<br/>
 * </ul>
 * <br/>
 * 
 * Adjacency list is more spare and time-efficient for a sparse graph, but less
 * efficient for a complete graph.<br/>
 * 
 * Graph Traversal: visits each vertex once. Depth-First Search( DFS) preorder
 * traversal
 * 
 * Each vertex has boolean visited field that tells us if we've visited it
 * before.<br/>
 * 
 * Run time: O (|v| + |e|) w/ Adjacency List O(|v|^2) w/ Adjacency matrix
 * 
 * <br/>
 * <br/>
 * Breadth-First Search(BFS) level order - traversal.<br/>
 * We use a queue, so the vertices are visited by distance from starting vertex.<br.>
 * 
 * When edge (v, w) is traversed to visit w, depth of w + depth of v+1, and v
 * becomes the "parent" of w <br/>
 * 
 * Find shortest path form any vertex to start vertex by following parent
 * pointers <br/>
 * 
 * BFS runs in O(|v| +|e|) time w/ adjacency list O(|v|^2) time w/ adjacency
 * matrix.
 * 
 * <br/ <b>Weighted Graphs</b><br/>
 * Each edge labeled with a numerical weight.<br/>
 * Adjacency matrix array of ints/doubles/ whatever. <br/>
 * Adjacency list: Each listnode includes a weight. <br/>
 * 
 * Problems
 * <ul>
 * <li>Shortest path problem.
 * <li>Minumum spanning tree Each node is an outlent, or source of electricity
 * Edges labeled with length of wire. Connect all nodes with shortest length of
 * wire.
 * </ul>
 * 
 * <br>
 * <b>Krushal's Algorithm</b><br/>
 * 
 * G = (V,E) undirected graph
 * 
 * "Spanning tree" T = (V,F) of G is a graph /w same vertices as G and |V| -1
 * edges of that form a tree
 * 
 * If G is not connected, T is a forest, a collection of trees. If G is
 * weighted, a "minimum spanning tree" T of G is a spanning tree of G whose
 * total weight (summed overall edges of T is minimal.)
 * 
 * <ol>
 * <li>Create new graph T with same vertices as G, but no edges(yet)/
 * <li>Make list of all edges in G
 * <li>Sort edges by weight, lowest to highest
 * <li>Iterate through edges in sorted order for edge(u, w)
 * <ol>
 * <li>If u and w are not connected by path in T, add(u, w) to T.
 * </ol>
 * Never adds (u,w) if some pah connects u and w, T is guaranteed to be a
 * tree(if G is connected) or a forest.<br/>
 * </ol>
 * 
 * Running time:
 * Step 3 |E| edges takes O(|E| log |E|)
 * Step 4a < O(|E| log |E|) (far now)
 * Kruskals algorithm in O(|V| + |E| log |E|) time
 *                     =O(|V| + |E| log |V|) time
 * 
 * @author greg
 * 
 */
public class GraphInterface
{

}
