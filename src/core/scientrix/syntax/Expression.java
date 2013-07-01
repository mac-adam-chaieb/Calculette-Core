package core.scientrix.syntax;

import core.scientrix.math.Real;

/*
 * @author Mohamed Adam Chaieb
 * 
 * This is the interface representing mathematical expressions in the Scientrix calculator.
 * */

public interface Expression 
{
	//evaluates the Expression
	public Operand evaluate();
	
	//substitutes any occurrence of x with number
	public Expression substitute(Variable x, Real number);
}
