package com.gregrode.common.util.math;

public class MathOperand {

	private String value;
	private boolean openParentheses;
	private boolean closeParentheses;

	/**
	 * Simple constructor
	 */
	public MathOperand() {

	}

	/**
	 * Partial constructor
	 * 
	 * @param value
	 */
	public MathOperand(String value) {
		this(value, false, false);
	}

	/**
	 * Full constructor
	 * 
	 * @param value
	 * @param openParentheses
	 * @param closeParentheses
	 */
	public MathOperand(String value, boolean openParentheses, boolean closeParentheses) {
		setValue(value);
		setOpenParentheses(openParentheses);
		setCloseParentheses(closeParentheses);
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the operator
	 */
	public boolean isOperator() {
		return MathUtils.isOperator(value);
	}

	/**
	 * @return a boolean that indicates if the value is numeric
	 */
	public boolean isNumeric() {
		return MathUtils.isNumeric(value);
	}

	/**
	 * @return the openParentheses
	 */
	public boolean isOpenParentheses() {
		return openParentheses;
	}

	/**
	 * @param openParentheses
	 *            the openParentheses to set
	 */
	public void setOpenParentheses(boolean openParentheses) {
		this.openParentheses = openParentheses;
	}

	/**
	 * @return the closeParentheses
	 */
	public boolean isCloseParentheses() {
		return closeParentheses;
	}

	/**
	 * @param closeParentheses
	 *            the closeParentheses to set
	 */
	public void setCloseParentheses(boolean closeParentheses) {
		this.closeParentheses = closeParentheses;
	}

	/**
	 * @return get Priority
	 */
	public Pemdas getPrecedence() {
		return Pemdas.getPrecedence(value);
	}

	public boolean hasHigherPriority(MathOperand entry) {
		return (getPrecedence().getPriority() < entry.getPrecedence().getPriority());
	}

}
