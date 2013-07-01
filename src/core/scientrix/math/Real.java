package core.scientrix.math;

import java.math.BigDecimal;

import core.scientrix.syntax.*;

/*
 * @author Mohamed Adam Chaieb
 * 
 * This class represents the real number expression r
 * */

public class Real implements Expression, Operand, Comparable<Real>
{
	public final BigDecimal real;
	public static final Real TWO = new Real(new BigDecimal("2"));
	public static final Real ONE = new Real(BigDecimal.ONE);
	public static final Real ZERO = new Real(BigDecimal.ZERO);
	public static final Real PI = new Real(new BigDecimal("3.141592653589793238462643383279"));
	public static final Real E = new Real(new BigDecimal("2.718281828459045235360287471352"));

	public Real(String real)
	{
		this.real = new BigDecimal(real);
	}

	public Real(BigDecimal real)
	{
		this.real = real;
	}

	public Operand evaluate()
	{
		return this;
	}

	public Expression substitute(Variable x, Real r)
	{
		return this;
	}

	public String toString()
	{
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

	public Real divide(Real b, int scale)
	{
		try{BigDecimal output = this.real.divide(b.real); return new Real(output);}
		catch(Exception e)
		{
			BigDecimal output = this.real.divide(b.real, scale, BigDecimal.ROUND_HALF_UP); 
			return new Real(output);
		}
	}

	public Real mod(Real b)
	{
		return new Real(this.real.remainder(b.real));
	}

	public Real negate(Real a)
	{
		return new Real(this.real.negate());
	}

	public Real max(Real b)
	{
		return new Real(this.real.max(b.real));
	}

	public Real min(Real b)
	{
		return new Real(this.real.min(b.real));
	}
	
	//need to implement this
	public Real pow(int i)
	{
		return Real.ZERO;
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
}