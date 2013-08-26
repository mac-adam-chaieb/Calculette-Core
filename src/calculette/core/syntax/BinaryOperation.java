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
	public Operation o1;
	public Operation o2;
	public BinaryOperator operator;

	public BinaryOperation(Operation o1, BinaryOperator operator, Operation o2)
	{
		this.o1 = o1;
		this.o2 = o2;
		this.operator = operator;
	}

	@Override
	public Value evaluate() throws ArgumentError, OutOfRangeError
	{
		if((this.o1 instanceof Real) && (this.o2 instanceof Real))
		{
			Real real1 = (Real)this.o1;
			Real real2 = (Real)this.o2;
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
		return (new BinaryOperation((Operation)this.o1.evaluate(), this.operator, (Operation)this.o2.evaluate() )).evaluate();
	}

	public int operationLength()
	{
		return 1+o1.operationLength()+o2.operationLength();
	}

	public String toString()
	{
		return "("+this.o1.toString()+") "+this.operator.toString()+" ("+this.o2.toString()+")";
	}
}
