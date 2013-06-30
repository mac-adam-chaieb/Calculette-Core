package core.syntax;
/*
 * @author Mohamed Adam Chaieb
 * 
 * This is the interface representing mathematical expressions in the Scientrix calculator.
 * */
public interface Expression 
{
	//evaluates the Expression
	public Number evaluate();
	
	//substitutes any occurrence of x with number
	public Expression substitute(Variable x, Number number);
}
