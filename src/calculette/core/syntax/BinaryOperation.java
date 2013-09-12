package calculette.core.syntax;

import calculette.core.error.ArgumentError;
import calculette.core.error.OutOfRangeError;
import calculette.core.math.Real;

/*
 * @author Mohamed Adam Chaieb
 * 
 * This class represents binary expressions (expressions with an operator and two operands).
 * */
public class BinaryOperation implements Operation 
{
	private Operation left;
	private Operation right;
	private BinaryOperator operator;

	public BinaryOperation(Operation left, BinaryOperator operator, Operation right)
	{
		this.left = left;
		this.right = right;
		this.operator = operator;
	}

	@Override
	public Value evaluate() throws ArgumentError, OutOfRangeError
	{
		if((this.left instanceof Real) && (this.right instanceof Real))
		{
			Real real1 = (Real)this.left;
			Real real2 = (Real)this.right;
			if(this.operator.equals(BinaryOperator.PLUS))
				return real1.add(real2);
			if(this.operator.equals(BinaryOperator.MINUS))
				return real1.subtract(real2);
			if(this.operator.equals(BinaryOperator.MULTIPLY))
				return real1.multiply(real2);
			if(this.operator.equals(BinaryOperator.DIVIDE))
				return real1.divide(real2);
			if(this.operator.equals(BinaryOperator.MOD))
				return real1.modulus(real2);
			if(this.operator.equals(BinaryOperator.POW))
				return real1.pow(real2);
			if(this.operator.equals(BinaryOperator.MIN))
				return real1.min(real2);
			if(this.operator.equals(BinaryOperator.MAX))
				return real1.max(real2);
			if(this.operator.equals(BinaryOperator.GCD))
				return real1.greatestCommonDivisor(real2);
			if(this.operator.equals(BinaryOperator.LCM))
				return real1.leastCommonMultiple(real2);
			if(this.operator.equals(BinaryOperator.PERMUTATION))
				return real1.permutations(real2);
			if(this.operator.equals(BinaryOperator.COMBINATION))
				return real1.combinations(real2);
		}
		return (new BinaryOperation((Operation)this.left.evaluate(), this.operator, (Operation)this.right.evaluate() )).evaluate();
	}

	public int operationLength()
	{
		return 1+left.operationLength()+right.operationLength();
	}

	public String toString()
	{
		return "("+this.left.toString()+") "+this.operator.toString()+" ("+this.right.toString()+")";
	}
	
	public Operation getLeft() {
		return left;
	}

	public void setLeft(Operation left) {
		this.left = left;
	}

	public Operation getRight() {
		return right;
	}

	public void setRight(Operation right) {
		this.right = right;
	}

	public BinaryOperator getOperator() {
		return operator;
	}

	public void setOperator(BinaryOperator operator) {
		this.operator = operator;
	}
}
