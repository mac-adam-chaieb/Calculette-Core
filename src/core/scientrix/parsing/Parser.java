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
			return makeOperation(e.replace(e.substring(e.lastIndexOf("("), matchIndex(e.lastIndexOf("("),e.toString())+1),
					makeOperation(e.substring(e.lastIndexOf("(")+1, matchIndex(e.lastIndexOf("("),e.toString()))).evaluate().toString()).toString());
		else if(isNumber(e))
			return new Real(e);
		else if(e.equals("e"))
			return Real.E;
		else if(e.equals("pi") || e.equals("\u03C0"))
			return Real.PI;
		else if(containsInfixBinaryOperator(e))
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
		StringBuilder input = new StringBuilder(e.replaceAll("\\s","").replaceAll("pi", "\u03C0"));
		//add implicit multiplication signs where needed
		for(int i = 1; i<input.length();i++)
		{
			//to be fixed
			if((Character.isLetter(input.charAt(i)) && (Character.isDigit(input.charAt(i-1)) || input.charAt(i-1) == 'e' || input.charAt(i-1) == '\u03C0' || input.charAt(i-1) == ')')) 
					|| (Character.isDigit(input.charAt(i)) && input.charAt(i-1) == ')') 
					|| (input.charAt(i) == '(' && input.charAt(i-1) == ')')
					|| ((input.charAt(i-1) == 'e' || input.charAt(i-1) == '\u03C0') && input.charAt(i) == '('))
		        input.insert(i,"*");
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
	
	private static boolean containsInfixBinaryOperator(String input)
	{
		for(BinaryOperator b : BinaryOperator.values())
		{
			if(b.isPrefix())//ignore prefix operator in BinaryOperator.values()
				break;
			if(input.contains(b.toString()))
				return true;
		}
		return false;
	}
	
	private static boolean startsWithPrefixBinaryOperator(String input)
	{
		for(BinaryOperator b : BinaryOperator.prefixValues())
			if(input.startsWith(b.toString()))
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
	
	private static int matchIndex(int index, String input)//index has to be index of an open parenthesis '('
	{
		int checker = 0;
		for(int i = index;i<input.length();i++)
		{
			if(input.charAt(i) == '(')
				checker++;
			if(input.charAt(i) == ')')
				checker--;
			if(checker == 0)
				return i;
		}
		return index;
	}
	
	private static int commaIndex(String input)
	{
		int occurrences = 0;
		int counter = 0;
		for(int i=0;i<input.length();i++)
			if(input.charAt(i) == ',')
				occurrences++;
		for(int i=0;i<input.length();i++)
			if(input.charAt(i) == ',')
			{
				counter++;
				if(counter == (int)Math.ceil(occurrences/2.0))
					return i;
			}
		return -1;
	}
}
