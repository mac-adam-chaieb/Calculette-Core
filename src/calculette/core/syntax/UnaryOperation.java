package calculette.core.syntax;

import calculette.core.error.ArgumentError;
import calculette.core.error.OutOfRangeError;
import calculette.core.math.Matrix;
import calculette.core.math.Real;

public class UnaryOperation implements Operation 
{
	private Operation operand;
	private UnaryOperator operator;

	public UnaryOperation(Operation operand, UnaryOperator operator)
	{
		this.operand = operand;
		this.operator = operator;
	}

	@Override
	public Value evaluate() throws ArgumentError, OutOfRangeError
	{
		if((this.operand instanceof Real))
		{
			Real real = (Real)this.operand;
			if(this.operator.equals(UnaryOperator.LENGTH))
				return real.length();
			if(this.operator.equals(UnaryOperator.FACTORIAL))
				return real.factorial();
			if(this.operator.equals(UnaryOperator.SINE))
				return real.sine();
			if(this.operator.equals(UnaryOperator.COSINE))
				return real.cosine();
			if(this.operator.equals(UnaryOperator.TANGENT))
				return real.tangent();
			if(this.operator.equals(UnaryOperator.NEGATE))
				return real.negate();
			if(this.operator.equals(UnaryOperator.IDENTITY))
				return real.identity();
			if(this.operator.equals(UnaryOperator.LN))
				return real.naturalLogarithm();
			if(this.operator.equals(UnaryOperator.LOG))
				return real.decimalLogarithm();
			if(this.operator.equals(UnaryOperator.ABSOLUTE))
				return real.absoluteValue();
			if(this.operator.equals(UnaryOperator.FLOOR))
				return real.floor();
			if(this.operator.equals(UnaryOperator.CEILING))
				return real.ceiling();
			if(this.operator.equals(UnaryOperator.SIGNUM))
				return real.signum();
			if(this.operator.equals(UnaryOperator.SQROOT1) || this.operator.equals(UnaryOperator.SQROOT2))
				return real.squareRoot();
			if(this.operator.equals(UnaryOperator.BINARYLOG))
				return real.binaryLogarithm();
			if(this.operator.equals(UnaryOperator.ARCCOS))
				return real.arccos();
			if(this.operator.equals(UnaryOperator.ARCSIN))
				return real.arcsin();
			if(this.operator.equals(UnaryOperator.ARCTAN))
				return real.arctan();
			if(this.operator.equals(UnaryOperator.INVERSE))
				return real.inverse();
		}
		else if(this.operand instanceof Matrix)
		{
			Matrix matrix = (Matrix)this.operand;
			if(this.operator.equals(UnaryOperator.DETERMINANT))
				return matrix.determinant();
			if(this.operator.equals(UnaryOperator.TRANSPOSE))
				return matrix.transpose();
			if(this.operator.equals(UnaryOperator.ADJOINT))
				return matrix.adjoint();
			if(this.operator.equals(UnaryOperator.COFACTORS))
				return matrix.cofactorsMatrix();
			if(this.operator.equals(UnaryOperator.INVERSE))
				return matrix.inverse();
		}
		return new UnaryOperation((Operation)operand.evaluate(), this.operator).evaluate();
	}

	public int operationLength()
	{
		return 1+operand.operationLength();
	}

	public String toString()
	{
		if(this.operator.equals(UnaryOperator.FACTORIAL))
			return "("+this.operand.toString()+")!";
		else return this.operator.toString()+"("+this.operand.toString()+")";
	}
	
	public Operation getOperand() {
		return operand;
	}

	public void setOperand(Operation operand) {
		this.operand = operand;
	}

	public UnaryOperator getOperator() {
		return operator;
	}

	public void setOperator(UnaryOperator operator) {
		this.operator = operator;
	}
}
