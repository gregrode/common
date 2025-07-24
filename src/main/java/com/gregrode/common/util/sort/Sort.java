package com.gregrode.common.util.sort;

import java.util.Comparator;
import java.util.List;

/**
 * <b>Insertion Sort</b><br/>
 * Running Time : O(n^2) Invariant: S is sorted<br/>
 * 
 * Start with empty list S and unsorted list I of N items for (each item x in I)
 * { insert x into S, in sorted order }<br/>
 * 
 * If S is linked list, O(n) worst-case time to find right position<br/>
 * 
 * If S is array, O(n) worst-case time to shift higher items over.<br/>
 * If S is an array, insertion sort is in place.<br/>
 * 
 * If S is a balance Search Tree, then running time is greater than O(n Log
 * n)<br/>
 * <br/>
 * 
 * <b>Selection</b><br/>
 * Sort Running Time : O(n^2) <br/>
 * Invariant: S is sorted<br/>
 * 
 * Start with empty list S and unsorted list I of n items<br/>
 * 
 * <pre>
 *  for (i = 0; i < n; i++){
 *  	x - item in I with smallest key
 *  	remove x from I
 *  	Append x to end of S
 *  }
 * </pre>
 * 
 * <br/>
 * Whether S is array or linkedlist, O(n^2) time even in best case. Also
 * in-place <br/>
 * <br/>
 * <br/>
 * <b>Heapsort</b><br/>
 * Selection sort where I is a heap<br/>
 * 
 * Start with empty list S and unsorted List I of n items<br/>
 * Toss all items in I onto heap h (ignore heap-order)<br/>
 * h.bottomUpHeap(); O(n) time<br/>
 * 
 * <pre>
 *  for(i=0;i<n;i++)
 *  {
 *  	x h.removeMin(); O(log n) time
 *  	Append x to the end of S
 *  }
 * </pre>
 * 
 * Heapsort runs in O(log n) time. <br/>
 * Also, in-place by maintaining heap backward at the end of the array.<br/>
 * 
 * Excellent for arrays, but clumsy for linkedlists<br/>
 * 
 * <b>Mergesort</b><br/>
 * Merge 2 sorted lists into one sorted list in linear time.<br/>
 * 
 * Let Q1 and Q2 be 2 sorted queues Let Q by empty queue<br/>
 * 
 * <pre>
 *  While (neither Q1 nor Q2 is empty){
 *  	item1 = Q1.front();
 *  	item2 = Q2.front();
 *  	move smaller of item1, item2 from present queue to end of Q
 *  	
 *  } 
 *  concatenate remaining non-empty queue (Q1 or Q2) to end of Q<br/>
 * </pre>
 * 
 * Mergesort is a recursive divide-and-conquer algorithm
 * <ol>
 * <li>Start with unsorted list I of items
 * <li>Break into halves I1 and I2, having n/2 and n/2 items
 * <li>Sort I1 recursively, yielding S1
 * <li>Sort I2 recursively, yielding S2
 * <li>Merge S1 and S2 into one sorted list S
 * </ol>
 * Natural for linked lists. NOT in-place sort for arrays. Running Time : O(n
 * log n) time <br/>
 * 
 * <b>QuickSort<b><br/>
 * Recursive divide and conquer algorithm Fastest comparison-based sort for
 * array.<br/>
 * 
 * Running Time: O(n^2) worst-case time Virtually always O(n log n) in practice.
 * 
 * <ol>
 * <li>Start with list I of n items
 * <li>Choose pivot item v from I
 * <li>Partition I into 2 unsorted list I1 and I2 I1 - all the keys smaller than
 * v's key I2 - all the keys larger than v's key Items w/ same key as v can go
 * into either list the pivot v does into go into either list
 * <li>Sort I1 recursively, yielding sorted list S1
 * <li>Sort I2 recursively, yielding sorted list S2
 * <li>Concatenated S1 and S2 with pivot in the middle into list S.
 * </ol>
 * 
 * When input already sort, choosing first item as pivot is disastrous<br/>
 * Randomly select an item from I as pivot on average, 1/4, 3/4 split Average
 * running time O(n log n)<br/>
 * 
 * <i>"Median of three"</i> pick 3 random pivots and find median. Use if data
 * set is large<br/>
 * 
 * Quicksort on linked lists Suppose we put all item w/ same key as v into
 * I1<br>
 * 
 * Better - partition I into 3 lists, I1, I2, and Iv Iv contains pivot and all
 * items w/ same key as v Sort I1, I2, not Iv Concatenate S1, Iv and S2<br/>
 * 
 * Quicksort on arrays In-place is fast Array a Sort items a[low] ... a[high] We
 * choose pivot v, swap it with last item in a[high] pick i = low -1 pick j =
 * high
 * 
 * Invariants: All items left of index i have a key <= pivot <br/>
 * All items right of index j have a key >= pivot<br/>
 * 
 * Advance i to key >= pivot Decrement j to key <= pivot swap items at i and j
 * repeat until i >= j swap pivot back to middle, whatever is at i.
 * 
 * Items w/ same key as pivot
 * 
 * <br . <br>
 * 
 * <b>Selection<b><br/>
 * Find kth smallest key in list (item at index j if list is sorted j = k-1.)
 * 
 * eg. Median of set of n keys item whose index is j = (n-1)/ c in sorted list.
 * 
 * <b>QuickSelect</b><br/>
 * 
 * Find
 * 
 * Start w/ unsorted list I of n items Choose pivot v from I Partition I into
 * lists I1 , Iv and I2 -Items w/ same keys a v go into any list -(List-based:
 * Iv; array-based: I1 and I2)
 * 
 * <pre>
 * Note, |I| indicates the array's length
 * 
 * if (j < |I1|)
 * {
 *   recursively find item w/ index j in I1 return it
 * }
 * else if (j < |I1| + |Iv|)
 * {
 * 		return v;
 * 
 * }
 * else
 * {
 *  Recursively find item w/index j - |I1| - |Iv| in I2; return it.
 * }
 * </pre>
 * 
 * O(n) average time if select pivot randomly.<br/>
 * 
 * 
 * <b>A Lower bound on comparison-based sorting</b><br/>
 * n number - each 1 ..n occurring once each.
 * 
 * how many orders can they be in? Answer n!= 1*2*3*...*(n-2)*(n-1)*n
 * 
 * eachn order is an permutation of the number n! possible permutations
 * 
 * upper bound of n! <= n^n lower bound of n! <= (n/2)^(n/2)
 * 
 * log(n/2)^(n/2) <= O(n log n)
 * 
 * log n^n = n log n. : log(n!) <= 0(n log n)
 * 
 * <br/>
 * <br/>
 * 
 * <b>Comparison-based sort</b>: all decisions based on comparing keys (if
 * statements).
 * 
 * A correct sorting algorithm must generate a different sequence of true/false
 * answer for each permutation of 1...n<br/>
 * 
 * If algorithm asks <= d true/false questions, it generates <= 2^d different
 * sequences of true/false answer<br/>
 * n!<= 2^d log 2(n!) <=d
 * 
 * 
 * Algorithm spends 0(d) time asking d questions. Every comparison-based sorting
 * algorithm take omega(n log n) worst-case time.
 * 
 * fast algorithm make q way decision for large q.<br/>
 * 
 * <b>Linear-time sorting</b></br>
 * <b>Bucket sort</b> - Work when keys are in small rang, eg O to q-1 ie. when q
 * <= O(n) Array of q queues, numbered from 0 to q-1<br/>
 * Enqueue each item key i goes in queue i
 * 
 * 0(q+n) time 0(q) time to initialize & concatenate buckets 0(n) time to put
 * items in buckets If q<= O(n) total is 0(n) time
 * 
 * Bucket sort is stable: items with equal key come out in same order they went
 * in. Insertion, selection, mergesort are easily made stable. Linkedlist
 * quicksort can too; array version is not. Heapsort is never stable. (not true
 * is secondary key is added).
 * 
 * <br/>
 * <br/>
 * 
 * @author greg
 * 
 * 
 * @param <T>
 */
public interface Sort<T> {

	T[] sort(T[] array, Comparator<T> comparator);

	List<T> sort(List<T> list, Comparator<T> comparator);

	<S extends Comparable<S>> S[] sort(S[] array);

}
