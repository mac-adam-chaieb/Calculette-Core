package core.scientrix.parsing;

import core.scientrix.syntax.Operation;

public class Tester 
{
	public static void main(String[] args)
	{
		Operation o = Parser.makeOperation("4+2*3!");
		System.out.println(o);
		System.out.println(o.evaluate());
	}
}
