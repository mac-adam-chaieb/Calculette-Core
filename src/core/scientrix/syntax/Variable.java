package core.scientrix.syntax;
/*
 * @author Mohamed Adam Chaieb
 * 
 * This class represents variables in scientrix expressions.*/
public class Variable implements Operand
{
	public String name;
	
	public Variable(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
}
