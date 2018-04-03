
public class OutOfBoundException extends RuntimeException{
	
	// raise exception if a certain value is outside the bounds
	
	public OutOfBoundException()
	{
		super();
	}
	public OutOfBoundException(String message)
	{
		super(message);
	}
	@Override
	public String toString()
	{
		return getMessage();
	}

}
