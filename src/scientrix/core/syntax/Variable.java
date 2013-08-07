package scientrix.core.syntax;
/*
 * @author Mohamed Adam Chaieb
 * 
 * This class represents variables in scientrix expressions.*/
public class Variable implements Operation
{
	public String name;
	public Operation expression;
	
	public Variable(String name)
	{
		this.name = name;
	}
	
	public Variable(String name, Operation expression)
	{
		this.name = name;
		this.expression = expression;
	}
	
	public String toString()
	{
		return this.name;
	}
	
	public Value evaluate()
	{
		return this.expression.evaluate();
	}
	
	public int length()
	{
		return 0;
	}
	
	public Operation substitute(Variable variable, Operation operation)
	{
		if(this.equals(variable))
			return operation;
		return this;
	}
	
	public boolean equals(Variable other)
	{
		return this.name.equals(other.name);
	}
}
