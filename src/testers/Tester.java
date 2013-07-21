package testers;

import scientrix.core.parsing.Parser;
import scientrix.core.syntax.Operation;

public class Tester 
{
	public static void main(String[] args)
	{
		String e = "tan(pi/3)";
		long t = System.currentTimeMillis();
		Operation o = Parser.makeOperation(e);
		System.out.println("Result of operation: "+o.evaluate());
		//time taken to construct expression tree and evaluate it
		System.out.println("Time elapsed in milliseconds: "+(System.currentTimeMillis()-t));
		System.out.println("Operation formed: "+o.toString());
		System.out.println("Number of operations: "+o.length());
	}
}
