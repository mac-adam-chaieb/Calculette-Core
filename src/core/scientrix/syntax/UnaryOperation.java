package core.scientrix.syntax;

import core.scientrix.math.Real;

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
		return null;
	}

	@Override
	public Operation substitute(Variable x, Real number) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String toString()
	{
		if(this.operator.equals(UnaryOperator.FACTORIAL))
			return "("+this.o1.toString()+")!";
		else return this.operator.toString()+"("+this.o1.toString()+")";
	}
}
