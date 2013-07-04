package core.scientrix.parsing;

import core.scientrix.math.Real;
import core.scientrix.syntax.BinaryOperation;
import core.scientrix.syntax.BinaryOperator;
import core.scientrix.syntax.Operation;
import core.scientrix.syntax.UnaryOperation;
import core.scientrix.syntax.UnaryOperator;

public class Parser 
{
	public static Operation makeOperation(String input)
	{
		String e = preProcess(input);
		System.out.println(e);
		if(e.contains("("))
		{
			return makeOperation(e.replace(e.substring(e.lastIndexOf("("), indexOfMatch(e.lastIndexOf("("),e.toString())+1),
					makeOperation(e.substring(e.lastIndexOf("(")+1, indexOfMatch(e.lastIndexOf("("),e.toString()))).evaluate().toString()).toString());
		}
		else if(isNumber(e))
			return new Real(e);
		else if(e.equals("e"))
			return Real.E;
		else if(e.equals(/*to be changed to greek*/"pi"))
			return Real.PI;
		else if(containsBinaryOperator(e))
		{
			for(BinaryOperator b : BinaryOperator.values())
				if(e.contains(b.toString()))
					return new BinaryOperation(makeOperation(e.substring(0, e.indexOf(b.toString()))),
							b, makeOperation(e.substring(e.indexOf(b.toString())+b.toString().length(), e.length())));
		}
		else if(startsWithUnaryOperator(e))
		{
			for(UnaryOperator u : UnaryOperator.values())
				if(e.startsWith(u.toString()))
					return new UnaryOperation(makeOperation(e.substring(u.toString().length())), u);
		}
		else if(e.endsWith(UnaryOperator.FACTORIAL.toString()))
			return new UnaryOperation(makeOperation(e.substring(0,e.length()-1)), UnaryOperator.FACTORIAL);
		return null;
	}
	
	//helper functions -----------------------------------------------------------------------------------------
	private static String preProcess(String e)
	{
		StringBuilder input = new StringBuilder(e.replaceAll("\\s",""));
		//add implicit multiplication signs where needed
		for(int i = 0; i<input.length()-1;i++)
		{
			if((input.charAt(i+1) == '(' && (input.charAt(i) == ')' || isNumeric(input.charAt(i)))) 
				|| (input.charAt(i) == ')' && isNumeric(input.charAt(i+1))))
				input.insert(i, '*');
		}
		return input.toString();
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
	
	private static boolean containsBinaryOperator(String input)
	{
		for(BinaryOperator b : BinaryOperator.values())
			if(input.contains(b.toString()))
				return true;
		return false;
	}
	
	private static boolean startsWithUnaryOperator(String input)
	{
		for(UnaryOperator u : UnaryOperator.values())
			if(u != UnaryOperator.FACTORIAL && input.startsWith(u.toString()))
				return true;
		return false;
	}
	
	private static boolean isNumeric(char c)
	{
		return (Character.isDigit(c) || c == 'e' || c == 'i' /*|| c == pi in greek*/);
	}
	
	private static int indexOfMatch(int index, String e)//index has to be index of an open parenthesis '('
	{
		int checker = 0;
		for(int i = index;i<e.length();i++)
		{
			if(e.charAt(i) == '(')
				checker++;
			if(e.charAt(i) == ')')
				checker--;
			if(checker == 0)
				return i;
		}
		return index;
	}
}
