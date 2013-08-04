package scientrix.core.syntax;

import scientrix.core.math.Real;

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
	public Value evaluate() 
	{
		if((this.o1 instanceof Real))
		{
			Real real = (Real)this.o1;
			if(this.operator.equals(UnaryOperator.LEN))
				return real.len();
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
				return real;
		}
		return new UnaryOperation((Operation)o1.evaluate(), this.operator).evaluate();
	}

	@Override
	public Operation substitute(Variable x, Operation operation)
	{
		return new UnaryOperation(this.o1.substitute(x, operation), this.operator);
	}

	public int length()
	{
		return 1+o1.length();
	}

	public String toString()
	{
		if(this.operator.equals(UnaryOperator.FACTORIAL))
			return "("+this.o1.toString()+")!";
		else return this.operator.toString()+"("+this.o1.toString()+")";
	}
}
