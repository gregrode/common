package com.gregrode.common.util.math;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Stream;

import com.gregrode.common.ErrorStatus;
import com.gregrode.common.ErrorStatusException;
import com.gregrode.common.util.Util;
import com.gregrode.common.util.StringUtil;

/**
 * @author gdennis
 *
 */
public class MathUtils {
	public static final double ZERO = 0;
	public static final String MULTIPLE = "*";
	public static final String DIVIDE = "/";
	public static final String ADD = "+";
	public static final String SUBTRACT = "-";
	public static final String OPEN_PARENTHESIS = "(";
	public static final String CLOSE_PARENTHESIS = ")";
	public static final String SQUAREROOT = "sqrt";

	/**
	 * Perform the given operation using the first and second operand
	 * 
	 * @param operator
	 *            the operator
	 * @param firstOperand
	 *            the first operand
	 * @param secondOperand
	 *            the second operand
	 * @return the answer as a double
	 */
	public static double calc(final String operator, final double firstOperand, final double secondOperand) {
		switch (operator) {
			case MULTIPLE:
				return firstOperand * secondOperand;
			case DIVIDE:
				if (secondOperand == ZERO) {
					throw new ErrorStatusException(ErrorStatus.MATH_ERROR_CANNOT_DIVIDE_BY_ZERO);
				}
				return firstOperand / secondOperand;
			case ADD:
				return firstOperand + secondOperand;
			case SUBTRACT:
				return firstOperand - secondOperand;
			case SQUAREROOT:
				return Math.sqrt(firstOperand);
			default: {
				return ZERO;
			}
		}
	}

	/**
	 * Perform the given operation using the first and second operand
	 * 
	 * @param operator
	 *            the operator
	 * @param operand1
	 *            the first operand
	 * @param operand2
	 *            the second operand
	 * @return the answer as a String
	 */
	public static String calc(final String operator, final String operand1, final String operand2) {
		if (StringUtil.isEmpty(operator, operand1, operand2)) {
			return String.valueOf(ZERO);
		}
		return String.valueOf(calc(operator, Double.parseDouble(operand1), Double.parseDouble(operand2)));
	}

	/**
	 * Determines if the first operator (first argument) has a higher precedent than
	 * the second.
	 * 
	 * @param operator
	 *            the first operator
	 * @param operator2
	 *            the second operator
	 * @return boolean
	 */
	public static boolean isPrecedenceHigher(final String operator, final String operator2) {
		final int precedence = Pemdas.getPrecedence(operator).getPriority();
		final Integer precedence2 = Pemdas.getPrecedence(operator2).getPriority();
		return precedence > precedence2;
	}

	/**
	 * Determines if the given string is an open parenthesis.
	 * 
	 * @param operator
	 *            the string to check
	 * @return boolean
	 */
	public static boolean isOpenParenthesis(final String operator) {
		return StringUtil.equals(operator, OPEN_PARENTHESIS);
	}

	/**
	 * Determines if the given string is an close parenthesis.
	 * 
	 * @param operator
	 *            the string to check
	 * @return boolean
	 */
	public static boolean isCloseParenthesis(final String operator) {
		return StringUtil.equals(operator, CLOSE_PARENTHESIS);
	}

	/**
	 * Return a boolean that indicates if the given string is a operator.
	 * 
	 * @param operator
	 *            the operator
	 * @return boolean
	 */
	public static boolean isOperator(final String operator) {
		switch (operator) {
			case MULTIPLE:
			case DIVIDE:
			case ADD:
			case SUBTRACT:
				return true;
			default:{
				return false;
			}
		}
	}

	/**
	 * Determine if the given string is numeric or not
	 * 
	 * @param str
	 *            the string to check
	 * @return boolean
	 */
	public static boolean isNumeric(final String str) {
		if (StringUtil.isEmpty(str)) {
			return false;
		}

		final NumberFormat formatter = NumberFormat.getInstance();
		final ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		final String first = str.substring(0);
		final boolean isNumeric = str.length() == pos.getIndex();
		if (!isNumeric && (str.matches("-?\\d+(\\.\\d+)?")) && StringUtil.isAny(first, ADD, MathUtils.SUBTRACT)) {
			return true;
		}
		return isNumeric;
	}

	@SafeVarargs
	public static <T> Set<T[]> permutation(T... array) {
		Set<T[]> set = new HashSet<>();
		permutation(set, array, array.length - 1);
		return set;
	}

	/**
	 * Find the permutation
	 * 
	 * @param set
	 *            a set of the all the permutations
	 * @param array
	 *            the array
	 * @param idx
	 *            the starting index of the array.
	 */
	private static <T> void permutation(Set<T[]> set, T[] array, int currentIndex) {
		set.add(array);
		if (currentIndex == -1) {
			return;
		}
		for (int index = 0; index < currentIndex; index++) {
			set.add(Util.swap(array, index, currentIndex));
			permutation(set, array, currentIndex - 1);
		}
		permutation(set, array, currentIndex - 1);
	}

	/**
	 * Check if the value at the given index is the beginning of a negative number
	 * (true) or an operator (false)
	 * 
	 * @param expr
	 *            the expression
	 * @param index
	 *            the index to check
	 * @return boolean
	 */
	public static boolean isIndexNegativeNumber(final String expr, final int index) {
		final String str = String.valueOf(expr.charAt(index));
		if (!StringUtil.equals(str, "-")) {
			return false;
		}

		if (index == 0) {
			return true;
		}
		final int previousIndex = index - 1;
		final String prev = String.valueOf(expr.charAt(previousIndex));
		if (MathUtils.isOpenParenthesis(prev) || MathUtils.isOperator(prev)) {
			return true;
		}
		return false;
	}

	public static Double parseExpression(String expression) {
		return null;
	}

	public static List<Stack<String>> getExpressionAsStacks(final String expression) {
		if (isMathExpressionValid(expression)) {
			return null;
		}
		final List<Stack<String>> stacks = new ArrayList<>(2);
		final StringBuilder value = new StringBuilder();
		final Stack<String> operators = new Stack<>();
		final Stack<String> numbers = new Stack<>();

		for (int index = 0; index < expression.length(); index++) {
			value.append(String.valueOf(expression.charAt(index)).trim());
			if (MathUtils.isIndexNegativeNumber(expression, index)) {
				value.append(expression.charAt(index++));
			}

			switch (value.toString()) {
				case MathUtils.OPEN_PARENTHESIS:
				case MathUtils.ADD:
				case MathUtils.SUBTRACT:
				case MathUtils.MULTIPLE:
				case MathUtils.DIVIDE:
				case MathUtils.CLOSE_PARENTHESIS:
					operators.push(value.toString());
					value.setLength(0);
					break;
				default:
					numbers.push(value.toString());
					value.setLength(0);
					break;
			}
		}

		stacks.add(numbers);
		stacks.add(simplifyOperators(operators, null));
		return stacks;
	}

	private static boolean isMathExpressionValid(String expression) {
		if (StringUtil.isEmpty(expression)) {
			return false;
		}
		final Stack<String> expressionStack = toStack(expression);
		int parathesisCount = 0;
		while (!expressionStack.isEmpty()) {
			final String top = expressionStack.pop();
			switch (top) {
				case MathUtils.OPEN_PARENTHESIS:
					parathesisCount++;
					break;
				case MathUtils.CLOSE_PARENTHESIS:
					parathesisCount--;
					break;
			}
		}
		return parathesisCount == 0;
	}

	private static Stack<String> toStack(String expr) {
		final Stack<String> sk = new Stack<>();
		Stream.of(expr.toCharArray()).forEach(val -> sk.add(String.valueOf(val)));
		return sk;
	}

	private static Stack<String> simplifyOperators(final Stack<String> operators, StringBuilder combinedOperator) {
		final Stack<String> op = new Stack<>();
		if (operators.isEmpty()) {
			return op;
		}
		final String top = operators.pop();

		switch (top) {
			case MathUtils.OPEN_PARENTHESIS:
				op.addAll(simplifyOperators(operators, new StringBuilder(top)));
				break;
			case MathUtils.CLOSE_PARENTHESIS:
				if (!StringUtil.isEmpty(combinedOperator)) {
					combinedOperator.append(top);
					op.push(combinedOperator.toString());
					combinedOperator = null;
				}
				break;
			default:
				if (!StringUtil.isEmpty(combinedOperator)) {
					combinedOperator.append(top);
				} else {
					op.push(top);
				}
				break;
		}
		op.addAll(simplifyOperators(operators, combinedOperator));
		return op;
	}

}
