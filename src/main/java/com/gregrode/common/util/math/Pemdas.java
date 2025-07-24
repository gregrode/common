package com.gregrode.common.util.math;

public enum Pemdas
{
	NUMBER(1), PARENTHESIS(2), EXPONTENT(3), MULTIPLICATION(4), DIVISION(4), ADDITION(5), SUBSTRACTION(5);

	int priority = 0;

	Pemdas(int priority)
	{
		this.priority = priority;
	}

	public int getPriority()
	{
		return priority;
	}

	public static Pemdas getPrecedence(String value)
	{
		if (MathUtils.isNumeric(value))
		{
			return NUMBER;
		}
		switch (value)
		{
			case MathUtils.MULTIPLE:
				return MULTIPLICATION;
			case MathUtils.DIVIDE:
				return DIVISION;
			case MathUtils.ADD:
				return ADDITION;
			case MathUtils.SUBTRACT:
				return SUBSTRACTION;
			case MathUtils.OPEN_PARENTHESIS:
			case MathUtils.CLOSE_PARENTHESIS:
				return PARENTHESIS;
			default:
				return NUMBER;
		}

	}

}
