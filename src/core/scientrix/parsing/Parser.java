package core.scientrix.parsing;

import core.scientrix.math.Real;
import core.scientrix.syntax.Operation;
import core.scientrix.syntax.UnaryOperator;

public class Parser 
{
	public static Operation makeOperation(String expression)
	{
		//check if expression is simply a number, otherwise
		try
		{
			Double.parseDouble(expression);
			return new Real(expression);
		}
		catch(Exception e)
		{
			
		}
	}
	
	private static boolean isNumber(String input)
	{
		try
		{
			Double.parseDouble(input);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
