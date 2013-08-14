package scientrix.core.error;

public class ArgumentError extends Exception
{
	private static final long serialVersionUID = 1L;

	public ArgumentError(String message)
	{
		super(message);
	}
}
