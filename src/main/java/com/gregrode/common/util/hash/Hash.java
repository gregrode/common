package com.gregrode.common.util.hash;

/**
 * Resizing Hash tables<br/>
 * 
 * If load factor n/N too large, we lose O(1) time<br/>
 * Enlarge hash table when factor > c typically, 0.75<br/>
 * Allocate a new array that is twice as large as the old array.<br/>
 * Walk through old array, rehashing entries into the new array.<br/>
 * option: Shrink hash tables (eg when n/N < 0.25) to free memory<br/>
 * 
 * Hash table operates usually O(1) time (on average). <br/>
 * When resizing happens, one operate can take O(n) (linear) time<br/>
 * Operate still take O(1) time on average.<br/>
 * 
 * <b>Transposition Tables : Speed Game Trees</b><br/>
 * Some grids can be reached through many sequences of moves, be evaluated many
 * times<br/>
 * Maintain hash table of previously encountered.<br/>
 * 
 * key is grid value is score for that grid.
 * 
 * @author Gregroy Dennis
 *
 */
public final class Hash {

	/**
	 * This a good hashcode code function because no one have yet to find a problem
	 * with it thus far.
	 * 
	 * @param key
	 *            the string to hash
	 * @return the hashCode
	 */
	public static int hashCode(String key) {
		int hashValue = 0;
		for (int index = 0; index < key.length(); index++) {
			// Pick prime numbers in your calculate to reduce the chance of hash
			// collisions. Note, if the number has some common factor, then the
			// final hash value form ax+b where b depends only on last
			// character.
			hashValue = ((31 * hashValue) + key.charAt(index));
		}
		return hashValue;
	}

}
