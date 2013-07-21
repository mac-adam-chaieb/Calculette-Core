package scientrix.core.math;

import java.math.BigDecimal;
import java.math.BigInteger;

import scientrix.core.syntax.Operation;
import scientrix.core.syntax.Value;
import scientrix.core.syntax.Variable;

/*
 * @author Mohamed Adam Chaieb
 * 
 * This class represents the real number expression r
 * */

public class Real implements Operation, Value, Comparable<Real>
{
	public final BigDecimal real;
	public String decimalPart;
	public String integerPart;
	private int scale;
	public static final Real TWO = new Real(new BigDecimal("2"));
	public static final Real ONE = new Real(BigDecimal.ONE);
	public static final Real ZERO = new Real(BigDecimal.ZERO);
	public static final Real PI = new Real(new BigDecimal("3.141592653589793238462643383279"));
	public static final Real E = new Real(new BigDecimal("2.718281828459045235360287471352"));

	public Real(String real)
	{
		this.real = new BigDecimal(real);
		this.integerPart = this.real.toBigInteger().toString();
		this.decimalPart = this.real.subtract(new BigDecimal(this.integerPart)).toString();
		this.scale = 20;
	}

	public Real(BigDecimal real)
	{
		this(real.toString());
	}
	
	public Real(BigInteger integer)
	{
		this(integer.toString());
	}
	
	public Real(int real)
	{
		this(real+"");
	}

	public Value evaluate()
	{
		return this;
	}

	public Operation substitute(Variable x, Real r)
	{
		return this;
	}

	public String toString()
	{
		if(this.subtract(new Real(this.real.toBigInteger())).equals(Real.ZERO))
			return this.real.toBigInteger().toString();
		return this.real.toString();
	}

	public boolean equals(Real b)
	{
		return (this.real.compareTo(b.real) == 0);
	}

	//math functions
	public Real add(Real b)
	{
		return new Real(this.real.add(b.real));
	}

	public Real multiply(Real b)
	{
		return new Real(this.real.multiply(b.real));
	}

	public Real subtract(Real b)
	{
		return new Real(this.real.subtract(b.real));
	}

	public Real divide(Real b)
	{
		try
		{
			BigDecimal output = this.real.divide(b.real); 
			return new Real(output);
		}
		catch(Exception e)
		{
			BigDecimal output = this.real.divide(b.real, this.scale, BigDecimal.ROUND_HALF_UP); 
			return new Real(output);
		}
	}

	public Real mod(Real b)
	{
		return new Real(this.real.remainder(b.real));
	}

	public Real negate()
	{
		return new Real(this.real.negate());
	}
	
	public Real factorial()
	{
		if(this.equals(Real.ZERO) || this.equals(Real.ONE))
			return Real.ONE;
		else
		{
			Real next = Real.ONE;
			for(Real index = Real.ONE; (index.compareTo(this) <= 0); index = index.add(Real.ONE))
				next = next.multiply(index);
			return next;
		}
	}
	
	public Real max(Real b)
	{
		return new Real(this.real.max(b.real));
	}

	public Real min(Real b)
	{
		return new Real(this.real.min(b.real));
	}
	
	public Real gcd(Real b)
	{
		return new Real(this.real.toBigInteger().gcd(b.real.toBigInteger()));
	}
	
	public Real lcm(Real b)
	{
		return this.multiply(b).divide(this.gcd(b));
	}
	
	public Real pow(Real x)
	{
		return intPow(x.real.toBigInteger()).multiply(decPow(x.real.subtract(new BigDecimal(x.real.toBigInteger()))));		
	}
	
	//helper methods for pow function
	private Real intPow(BigInteger intPart)
	{
		if(intPart.equals(BigInteger.ZERO))
			return Real.ONE;
		else if(intPart.mod(new BigInteger("2")).equals(BigInteger.ZERO))
			return intPow(intPart.divide(new BigInteger("2"))).multiply(intPow(intPart.divide(new BigInteger("2"))));
		else return intPow(intPart.divide(new BigInteger("2"))).multiply(intPow(intPart.divide(new BigInteger("2")))).multiply(this);
	}
	
	//need to implement this
	private Real decPow(BigDecimal decPart)
	{
		return Real.ONE;
	}
	
	public Real sine()
	{
		Real output = Real.ZERO;
		for(Real r = Real.ZERO; (r.compareTo(new Real(50)) <= 0);r = r.add(Real.ONE))
			output = output.add(((Real.ONE.negate()).pow(r)).multiply(this.pow((r.multiply(Real.TWO)).add(Real.ONE)))
					.divide(((r.multiply(Real.TWO)).add(Real.ONE)).factorial()));
		return output;
	}
	
	public Real cosine()
	{
		Real output = Real.ZERO;
		for(Real r = Real.ZERO; (r.compareTo(new Real(50)) <= 0);r = r.add(Real.ONE))
			output = output.add(((Real.ONE.negate()).pow(r)).multiply(this.pow(r.multiply(Real.TWO)))
					.divide((r.multiply(Real.TWO)).factorial()));
		return output;
	}
	
	public Real tangent()
	{
		return this.sine().divide(this.cosine());
	}
	
	public static boolean isReal(String input)
	{
		try
		{
			Double.parseDouble(input);
			return true;
		}
		catch(Exception e){return false;}
	}

	public boolean greaterThan(Real b)
	{
		return (this.real.compareTo(b.real) == 1);
	}

	public boolean lessThan(Real b)
	{
		return (this.real.compareTo(b.real) == -1);
	}
	
	public int compareTo(Real other)
	{
		return this.real.compareTo(other.real);
	}
	
	public Real len()
	{
		return new Real(this.toString().length());
	}
	
	public int length()
	{
		return 0;
	}
	
	public boolean isInteger()
	{
		return (this.real.toBigInteger().equals(new BigInteger(this.integerPart)));
	}
	
	public Real clone()
	{
		return new Real(this.real);
	}
	
	public void setScale(int scale)
	{
		this.scale = scale;
	}
	
	public int getScale()
	{
		return this.scale;
	}
}