package scientrix.core.parsing;

import java.util.ArrayList;

import scientrix.core.error.SyntaxError;
import scientrix.core.math.Real;
import scientrix.core.syntax.BinaryOperation;
import scientrix.core.syntax.BinaryOperator;
import scientrix.core.syntax.Operation;
import scientrix.core.syntax.UnaryOperation;
import scientrix.core.syntax.UnaryOperator;
import scientrix.core.syntax.Variable;


public class Parser 
{
	public static ArrayList<Variable> variables = new ArrayList<Variable>();

	public static Operation makeOperation(String input) throws SyntaxError
	{
		String e = preProcess(input);

		loadVariables();
		if(isNumber(e))
			return new Real(e);
		else if(isVariable(e))
			return getVariable(e);
		else if(containsInfixBinaryOperator(e)[0] == 1)
		{
			BinaryOperator b = BinaryOperator.values()[containsInfixBinaryOperator(e)[2]];
			int splitIndex = containsInfixBinaryOperator(e)[1];
			return new BinaryOperation(makeOperation(e.substring(0, splitIndex)),
							b, makeOperation(e.substring(splitIndex+b.toString().length(), e.length())));
		}
		else if(startsWithUnaryOperator(e)[0] == 1)
			return new UnaryOperation(makeOperation(e.substring(UnaryOperator.values()[startsWithUnaryOperator(e)[1]].toString().length())),
					UnaryOperator.values()[startsWithUnaryOperator(e)[1]]);
		else if(e.endsWith(UnaryOperator.FACTORIAL.toString()))
			return new UnaryOperation(makeOperation(e.substring(0,e.length()-1)), UnaryOperator.FACTORIAL);
		else if(e.startsWith("(") && e.endsWith(")"))
			return makeOperation(e.substring(1, e.length()-1));
		throw new SyntaxError("Malformed expression "+e);
	}

	//helper functions -----------------------------------------------------------------------------------------
	public static void loadVariables()
	{
		Parser.variables.add(new Variable("e", Real.E));
		Parser.variables.add(new Variable("\u03C0", Real.PI));
		Parser.variables.add(new Variable("pi", Real.PI));
	}
	
	public static Operation getVariable(String variable)
	{
		for(Variable v : Parser.variables)
			if(v.toString().equals(variable))
				return v;
		return null;
	}
	
	private static boolean isVariable(String input)
	{
		for(Variable v : Parser.variables)
			if(v.toString().equals(input))
				return true;
		return false;
	}

	private static String preProcess(String e)
	{
		StringBuilder input = new StringBuilder(e.replaceAll("\\s",""));
		String output = input.toString();
		//add implicit multiplication signs where needed
//		for(int i=0;i<output.length()-1;i++)
//		{
//			if((Character.isDigit(output.charAt(i)) || endsWithVariable(output.substring(0, i+1))) && (startsWithUnaryOperator(output.substring(i+1)) || output.charAt(i+1) == '(' || startsWithVariable(output.substring(i+1)))
//					|| ((output.charAt(i) == ')') && (startsWithUnaryOperator(output.substring(i+1)) || startsWithVariable(output.substring(i+1)))))
//				output = input.insert(i+1, '*').toString();
//		}
		return output;
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

	public static int[] containsInfixBinaryOperator(String input)
	{
		int[] output = {0, 0, 0};
		for(int i = 0; i<BinaryOperator.values().length;i++)
		{
			if(input.contains(BinaryOperator.values()[i].toString()))
			{
				for(int j : indicesOf(input, BinaryOperator.values()[i].toString()))
					if(validIndex(input, j))
					{
						output[0] = 1;
						output[1] = j;
						output[2] = i;
						return output;
					}
			}
		}
		return output;
	}

	private static int[] startsWithUnaryOperator(String input)
	{
		int[] output = {0,0};
		for(int i = 0;i<UnaryOperator.values().length;i++)
			if(UnaryOperator.values()[i] != UnaryOperator.FACTORIAL && input.startsWith(UnaryOperator.values()[i].toString()))
			{
				output[0] = 1;
				output[1] = i;
				break;
			}
		return output;
	}

//	private static boolean endsWithPrefixBinaryOperator(String input)
//	{
//		for(BinaryOperator b : BinaryOperator.prefixValues())
//			if(input.endsWith(b.toString()))
//				return true;
//		return false;
//	}

//	private static boolean endsWithUnaryOperator(String input)
//	{
//		for(UnaryOperator u : UnaryOperator.values())
//			if(input.endsWith(u.toString()))
//				return true;
//		return false;
//	}

//	private static boolean endsWithVariable(String e)
//	{
//		if(!(endsWithUnaryOperator(e)) && !(endsWithPrefixBinaryOperator(e)))
//		{
//			for(Variable v : Parser.variables)
//				if(e.endsWith(v.toString()))
//					return true;
//		}
//		return false;
//	}
	
	public static boolean validIndex(String input, int index)
	{
		if(index == -1 || index == 0)
			return false;
		int checker = 0;
		for(int i = 0;i<=index;i++)
		{
			if(input.charAt(i) == '(')
				checker++;
			if(input.charAt(i) == ')')
				checker--;
		}
		return (checker == 0);
	}
	
	public static ArrayList<Integer> indicesOf(String input, String match)
	{
		ArrayList<Integer> output = new ArrayList<Integer>();
		for(int i = 0;i<input.length();i++)
			if(input.substring(i).startsWith(match))
				output.add(i);
		return output;
	}
}
