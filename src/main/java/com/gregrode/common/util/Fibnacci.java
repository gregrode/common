package com.gregrode.common.util;

import java.util.HashMap;
import java.util.Map;

public class Fibnacci {
	private int fibCount = 0;

	public static void main(String... args) {
		Fibnacci fb = new Fibnacci();
		int ans = fb.fib(10);
		System.out.println("Answer : " + ans + " Count : " + fb.fibCount);
		fb.fibCount = 0;
		ans = fb.fib1(10);
		System.out.println("Fast Fib Answer : " + ans + " Count : " + fb.fibCount);
	}

	public int fib(int n) {
		// System.out.println("Fib of " + n);
		fibCount += 1;
		if (n <= 1) {
			return 1;
		}
		return fib(n - 1) + fib(n - 2);
	}

	public int fib1(int n) {
		Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
		memo.put(0, 1);
		memo.put(1, 1);
		return fastFib(n, memo);
	}

	private int fastFib(int n, Map<Integer, Integer> memo) {
		fibCount += 1;
		// System.out.println("finding Fib of " + n);
		if (memo.get(n) != null) {
			return memo.get(n).intValue();
		}
		int fib = fastFib(n - 1, memo) + fastFib(n - 2, memo);
		memo.put(n, fib);
		return fib;
	}
}
