package calculette.core.error;

public class SyntaxError extends Exception 
{
	private static final long serialVersionUID = 1L;
	
	public SyntaxError(String message)
	{
		super(message);
	}
}
