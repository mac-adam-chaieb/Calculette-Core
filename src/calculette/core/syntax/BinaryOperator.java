package calculette.core.syntax;
/*
 * @author Mohamed Adam Chaieb
 * 
 * This enumerable class represents the valid binary operators in mathematical expressions supported by scientrix.
 * */

public enum BinaryOperator 
{
	//ordered by increasing priority
	PLUS("+"), MINUS("-"), MOD("mod"), DIVIDE("/"), MULTIPLY("*"), PERMUTATION("P"), COMBINATION("C"), POW("^"), MIN("min"), MAX("max"), GCD("gcd"), LCM("lcm");
	public String operator;

	private BinaryOperator(String operator)
	{
		this.operator = operator;
	}

	public boolean equals(BinaryOperator other)
	{
		return this.operator.equals(other.operator);
	}

	public String toString()
	{
		return this.operator;
	}
}