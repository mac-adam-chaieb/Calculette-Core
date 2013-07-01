package core.scientrix.syntax;

import core.scientrix.math.Real;

/*
 * @author Mohamed Adam Chaieb
 * 
 * This class represents binary expressions (expressions with an operator and two oeprands).
 * */
public class BinaryExpression implements Expression 
{
	public Operand o1;
	public Operand o2;
	public BinaryOperator operator;
	
	public BinaryExpression(Operand o1, BinaryOperator operator, Operand o2)
	{
		this.o1 = o1;
		this.o2 = o2;
		this.operator = operator;
	}
	
	@Override
	public Operand evaluate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression substitute(Variable x, Real number) {
		// TODO Auto-generated method stub
		return null;
	}

}
