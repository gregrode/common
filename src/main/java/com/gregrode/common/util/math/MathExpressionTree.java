package com.gregrode.common.util.math;

import com.gregrode.common.util.tree.BinaryTreeNode;

/**
 * @author gdennis
 *
 */
public class MathExpressionTree extends BinaryTreeNode<MathOperand>
{

	public MathExpressionTree()
	{
	}

	public MathExpressionTree(MathOperand entry)
	{
		super(entry);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNode#visit()
	 */
	@Override
	public void visit()
	{
		super.visit();
		MathExpressionTree left = getLeftNode();
		MathExpressionTree right = getRightNode();
		if (left != null && left.isLeaf() && right != null && right.isLeaf())
		{
			String operator = getEntry().getValue();
			String operand1 = left.getEntry().getValue();
			String operand2 = right.getEntry().getValue();
			super.setEntry(new MathOperand(MathUtils.calc(operator, operand1, operand2)));
			super.setRightNode((MathOperand) null);
			super.setLeftNode((MathOperand) null);
			System.out.println(operand1 + operator + operand2 + " = " + getEntry().getValue());

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNode#getLeftNode()
	 */
	@Override
	public MathExpressionTree getLeftNode()
	{
		return (MathExpressionTree) super.getLeftNode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNode#getRightNode()
	 */
	@Override
	public MathExpressionTree getRightNode()
	{
		return (MathExpressionTree) super.getRightNode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNode#getParent()
	 */
	@Override
	public MathExpressionTree getParent()
	{
		return (MathExpressionTree) super.getParent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNode#getRoot()
	 */
	@Override
	public MathExpressionTree getRoot()
	{
		return (MathExpressionTree) super.getRoot();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gregrode.common.util.tree.BinaryTreeNodeInterface#setEntry(java.lang
	 * .Object)
	 */
	@Override
	public void setEntry(MathOperand entry)
	{
		MathOperand existingEntry = getEntry();
		if (existingEntry == null)
		{
			super.setEntry(entry);
			return;
		}

		MathExpressionTree left = getLeftNode();
		MathExpressionTree right = getRightNode();
		MathExpressionTree temp;
		if (entry.isOperator())
		{
			if (isLeaf())
			{
				super.setEntry(entry);
				setRightNode(existingEntry);
				return;
			}

			if (entry.hasHigherPriority(existingEntry))
			{
				temp = left;
				left = new MathExpressionTree(entry);
				left.setLeftNode(temp);
				setLeftNode(left);
				return;
			}
		}
		else if (entry.isNumeric())
		{
			if (isLeaf())
			{
				getParent().setRightNode(entry);
				return;
			}

			if (left == null)
			{
				setLeftNode(entry);
				return;
			}

			if (right == null)
			{
				setRightNode(entry);
				return;
			}

			left.getLeftNode().setEntry(entry);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gregrode.common.util.tree.BinaryTreeNode#setLeftNode(java.lang.Object
	 * )
	 */
	@Override
	public void setLeftNode(MathOperand entry)
	{
		setLeftNode(new MathExpressionTree(entry));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gregrode.common.util.tree.BinaryTreeNode#setLeftNode(java.lang.Object
	 * )
	 */
	@Override
	public void setRightNode(MathOperand entry)
	{
		setRightNode(new MathExpressionTree(entry));
	}

	public boolean isLeaf()
	{
		return getEntry().isNumeric();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#isBalanced()
	 */
	@Override
	public boolean isBalanced()
	{
		if (getEntry() == null)
		{
			return false;
		}

		if (isLeaf())
		{
			return true;
		}

		final MathExpressionTree right = getRightNode();
		final MathExpressionTree left = getLeftNode();

		return ((left != null && left.isBalanced()) && (right != null && right.isBalanced()));
	}

}
