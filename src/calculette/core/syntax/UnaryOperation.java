package calculette.core.syntax;

import calculette.core.error.ArgumentError;
import calculette.core.error.OutOfRangeError;
import calculette.core.math.Matrix;
import calculette.core.math.Real;

public class UnaryOperation implements Operation 
{
	public Operation o1;
	public UnaryOperator operator;

	public UnaryOperation(Operation o1, UnaryOperator operator)
	{
		this.o1 = o1;
		this.operator = operator;
	}

	@Override
	public Value evaluate() throws ArgumentError, OutOfRangeError
	{
		if((this.o1 instanceof Real))
		{
			Real real = (Real)this.o1;
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
		else if(this.o1 instanceof Matrix)
		{
			Matrix matrix = (Matrix)this.o1;
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
		return new UnaryOperation((Operation)o1.evaluate(), this.operator).evaluate();
	}

	public int operationLength()
	{
		return 1+o1.operationLength();
	}

	public String toString()
	{
		if(this.operator.equals(UnaryOperator.FACTORIAL))
			return "("+this.o1.toString()+")!";
		else return this.operator.toString()+"("+this.o1.toString()+")";
	}
}
