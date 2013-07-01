package core.scientrix.syntax;
/*
 * @author Mohamed Adam Chaieb
 * 
 * This enumerable class represents the valid binary operators in mathematical expressions supported by scientrix.
 * */
public enum UnaryOperator 
{
	//ordered by increasing priority
	LOG("log"), LN("ln"), COSINE("cos"), SINE("sin"), TANGENT("tan"), FACTORIAL("!"), SQROOT1("sqrt"), SQROOT2("\u221A"), NEGATE("-"), ARCCOS("arccos"), ARCSIN("arcsin"), ARCTAN("arctan"), ABSOLUTE("abs");
	public String operator;
	
	private UnaryOperator(String operator)
	{
		this.operator = operator;
	}
	
	public boolean equals(UnaryOperator other)
	{
		return this.operator.equals(other.operator);
	}
}
