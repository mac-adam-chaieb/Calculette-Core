package scientrix.core.syntax;


/*
 * @author Mohamed Adam Chaieb
 * 
 * This is the interface representing mathematical expressions in the Scientrix calculator.
 * */

public interface Operation 
{
	//evaluates the Expression
	public Value evaluate();
	
	//substitutes any occurrence of x with number
	public Operation substitute(Variable x, Operation operation);
	
	//returns the number of operations performed by the operation
	public int length();
}