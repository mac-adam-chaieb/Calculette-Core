package calculette.core.math;

import java.math.BigDecimal;
import java.math.BigInteger;

import calculette.core.error.ArgumentError;
import calculette.core.syntax.Operation;
import calculette.core.syntax.Value;
import calculette.core.syntax.Variable;


/*
 * @author Mohamed Adam Chaieb
 * 
 * This class represents the real number expression r
 * */

public class Real implements Operation, Value, Comparable<Real>, Cloneable
{
	public final double real;
	public String decimalPart;
	public String integerPart;
	public static final Real TEN = new Real(10);
	public static final Real TWO = new Real(2);
	public static final Real ONE = new Real(1);
	public static final Real ZERO = new Real(0);
	public static final Real PI = new Real(Math.PI);
	public static final Real E = new Real(Math.E);

	public Real(String real)
	{
		this.real = Double.parseDouble(real);
		this.integerPart = ((int)this.real)+"";
		this.decimalPart = (this.real - Double.parseDouble(this.integerPart))+"";
	}

	public Real(BigDecimal real)
	{
		this(real.toString());
	}

	public Real(BigInteger integer)
	{
		this(integer.toString());
	}

	public Real(int integer)
	{
		this(integer+"");
	}

	public Real(double d)
	{
		this(d+"");
	}

	public Value evaluate()
	{
		return this;
	}

	public Operation substitute(Variable x, Operation operation)
	{
		return this;
	}

	public String toString()
	{
		if(this.isInteger())
			return this.integerPart;
		return this.real+"";
	}

	public boolean equals(Real b)
	{
		return (this.real == b.real);
	}

	//math functions
	public Real add(Real b)
	{
		return new Real(this.real + b.real);
	}

	public Real multiply(Real b)
	{
		return new Real(this.real * b.real);
	}

	public Real subtract(Real b)
	{
		return new Real(this.real - b.real);
	}

	public Real divide(Real b) throws ArgumentError
	{
		if(b.equals(Real.ZERO))
			throw new ArgumentError("Cannot divide by zero");
		return new Real(this.real / b.real);
	}

	public Real modulus(Real b)
	{
		return new Real(this.real % b.real);
	}

	public Real negate()
	{
		return Real.ZERO.subtract(this);
	}

	public Real identity()
	{
		return this;
	}
	
	public Real inverse() throws ArgumentError
	{
		return Real.ONE.divide(this);
	}

	public Real factorial() throws ArgumentError
	{
		if(!this.isInteger() || this.lessThan(Real.ZERO))
			throw new ArgumentError("Factorial requires a natural number");
		if(this.lessThan(Real.TWO))
			return Real.ONE;
		else
		{
			Real next = Real.ONE;
			for(Real index = Real.ONE; (index.compareTo(this) <= 0); index = index.add(Real.ONE))
				next = next.multiply(index);
			return next;
		}
	}

	public Real permutations(Real other) throws ArgumentError
	{
		return this.factorial().divide(this.subtract(other).factorial());
	}

	public Real combinations(Real other) throws ArgumentError
	{
		return this.factorial().divide(other.factorial().multiply(this.subtract(other).factorial()));
	}

	public Real max(Real b)
	{
		return new Real(Math.max(this.real, b.real));
	}

	public Real min(Real b)
	{
		return new Real(Math.min(this.real, b.real));
	}

	public Real greatestCommonDivisor(Real b) throws ArgumentError
	{
		//implement Euclid's algorithm
		if(!this.isInteger())
			throw new ArgumentError("GCD and LCM require an integer");
		return Real.ONE;	
	}

	public Real leastCommonMultiple(Real b) throws ArgumentError
	{
		return this.multiply(b).divide(this.greatestCommonDivisor(b));
	}

	public Real square()
	{
		return this.multiply(this);
	}
	
	public Real cube()
	{
		return this.multiply(this).multiply(this);
	}

	public Real pow(Real x)
	{
		return new Real(Math.pow(this.real, x.real));	
	}

	public Real squareRoot() throws ArgumentError
	{
		if(this.lessThan(Real.ZERO))
			throw new ArgumentError("Square root requires strictly positive numbers");
		return new Real(Math.sqrt(this.real));
	}

	public Real absoluteValue()
	{
		return new Real(Math.abs(this.real));
	}

	public Real sine()
	{
		return new Real(Math.sin(this.real));
	}

	public Real cosine()
	{
		return new Real(Math.cos(this.real));
	}

	public Real tangent()
	{
		return new Real(Math.tan(this.real));
	}
	
	public Real arccos()
	{
		return new Real(Math.acos(this.real));
	}
	
	public Real arcsin()
	{
		return new Real(Math.asin(this.real));
	}
	
	public Real arctan()
	{
		return new Real(Math.atan(this.real));
	}

	public Real binaryLogarithm() throws ArgumentError
	{
		if(this.lessThan(Real.ZERO))
			throw new ArgumentError("Logarithms require strictly positive numbers");
		return this.decimalLogarithm().divide(Real.TWO.decimalLogarithm());
	}

	public Real naturalLogarithm() throws ArgumentError
	{
		if(this.lessThan(Real.ZERO))
			throw new ArgumentError("Logarithms require strictly positive numbers");
		return new Real(Math.log(this.real));
	}

	public Real decimalLogarithm() throws ArgumentError
	{
		if(this.lessThan(Real.ZERO))
			throw new ArgumentError("Logarithms require strictly positive numbers");
		return new Real(Math.log10(this.real));
	}

	public boolean greaterThan(Real b)
	{
		return (this.real > b.real);
	}

	public boolean lessThan(Real b)
	{
		return (this.real < b.real);
	}

	public int compareTo(Real other)
	{
		if(this.lessThan(other))
			return -1;
		if(this.greaterThan(other))
			return 1;
		return 0;
	}

	public Real length()
	{
		if(this.compareTo(Real.ONE) < 1)
			return new Real(this.toString().length()-1);
		return new Real(this.toString().length());
	}

	public int operationLength()
	{
		return 0;
	}

	public boolean isInteger()
	{
		return (Double.parseDouble(this.integerPart) == this.real);
	}

	public BigInteger toBigInteger()
	{
		return new BigInteger(this.integerPart);
	}

	public double toDouble()
	{
		return this.real;
	}

	public BigDecimal toBigDecimal()
	{
		return new BigDecimal(this.real);
	}

	public long toLong()
	{
		return (long)this.real;
	}

	public int toInt()
	{
		return (int)this.real;
	}

	public Real clone()
	{
		return new Real(this.real);
	}

	public Real signum()
	{
		return new Real(Math.signum(this.real));
	}

	public Real decimalShiftLeft(int n)
	{
		return this.multiply(Real.TEN);
	}

	public Real decimalShiftRight(int n) throws ArgumentError
	{
		return this.divide(Real.TEN);
	}

	public Real binaryShiftLeft(int n)
	{
		//not implemented
		return this.multiply(Real.TWO);
	}

	public Real binaryShiftRight(int n) throws ArgumentError
	{
		//not implemented
		return this.divide(Real.TWO);
	}

	public Real ceiling()
	{
		return new Real(Math.ceil(this.real));
	}

	public Real floor()
	{
		return new Real(Math.floor(this.real));
	}
}