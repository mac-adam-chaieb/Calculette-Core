package calculette.core.syntax;

import calculette.core.error.ArgumentError;
import calculette.core.error.OutOfRangeError;

/*
 * @author Mohamed Adam Chaieb
 * 
 * This class represents variables in scientrix expressions.*/
public class Variable implements Operation, Value
{
	private String name;
	private Operation expression;

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
	
	public Value evaluate() throws ArgumentError, OutOfRangeError
	{
		return this.expression.evaluate();
	}
	
	public int operationLength()
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
	
	public Operation getExpression() {
		return expression;
	}

	public void setExpression(Operation expression) {
		this.expression = expression;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
