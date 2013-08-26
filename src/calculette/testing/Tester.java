package calculette.testing;

import java.util.HashMap;

import calculette.core.error.ArgumentError;
import calculette.core.error.OutOfRangeError;
import calculette.core.error.SyntaxError;
import calculette.core.math.Real;
import calculette.core.parsing.Parser;


public class Tester 
{
	public static void main(String[] args) throws ArgumentError, OutOfRangeError, SyntaxError
	{
		HashMap<String, Real> tests = new HashMap<String, Real>();
		tests.put("-6*9+54", new Real(0));
		tests.put("2+(4*9/9)", new Real(6));
		tests.put("abs(-2*(-7+8))", new Real(2));
		tests.put("2+(3+(8+(9)))*7", new Real(142));
		tests.put("-2+(4*9/9)", new Real(2));
		tests.put("e", Real.E);
		tests.put("pi", Real.PI);
		tests.put("(len 5647)+5!", new Real(124));
		tests.put("3!+2!*(56-abs(-9))", new Real(100));
		tests.put("90/5+1200", new Real(1218));
		tests.put("floor(9!/10!)", new Real(0));
		tests.put("sig(-9)*abs(6+9)", new Real(-15));
		tests.put("4^2+8-5+0.0", new Real(19));
		tests.put("ln(e^67.897)", new Real(67.897));
		tests.put("(abs(-10)/10)*pi*cos(pi)", Real.PI.negate());
		tests.put("789.000000", new Real(789));
		for(String s : tests.keySet())
		{
			Real result = (Real)Parser.makeOperation(s).evaluate();
			System.out.print("Expected result: "+tests.get(s)+" Actual result: "+result);
			if(tests.get(s).equals(result))
				System.out.println(" => TEST SUCCESSFUL");
			else 
			{
				System.out.println(" => TEST FAILED");
				break;
			}
		}
		System.out.println("TEST DONE");
		System.out.println("NUMBER OF TESTS: "+tests.size());
//		String e = "";
//		long t = System.currentTimeMillis();
//		Operation o = Parser.makeOperation(e);
//		System.out.println("Operation formed: "+o.toString());
//		System.out.println("Time to construct expression in milliseconds: "+(System.currentTimeMillis()-t));
//		t = System.currentTimeMillis();
//		System.out.println("Result of operation: "+o.evaluate());
//		//time taken to construct expression tree and evaluate it
//		System.out.println("Time to evaluate expression in milliseconds: "+(System.currentTimeMillis()-t));
//		System.out.println("Number of operations: "+o.operationLength());
	}
}
