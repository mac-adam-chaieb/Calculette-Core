package core.scientrix.syntax;
/*
 * @author Mohamed Adam Chaieb
 * 
 * This class represents variables in scientrix expressions.*/
public class Variable
{
	public String name;
	public Value value;
	
	public Variable(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
}
