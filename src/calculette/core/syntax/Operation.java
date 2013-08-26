package calculette.core.syntax;

import calculette.core.error.ArgumentError;
import calculette.core.error.OutOfRangeError;


/*
 * @author Mohamed Adam Chaieb
 * 
 * This is the interface representing mathematical expressions in the Scientrix calculator.
 * */

public interface Operation 
{
	//evaluates the Expression
	public Value evaluate() throws ArgumentError, OutOfRangeError;
	
	//returns the number of operations performed by the operation
	public int operationLength();
}
