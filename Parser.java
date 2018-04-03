
public class Parser {
	// A method that transfers a string to an integer
	 
	public static double ParseStringToDouble(String str)
	throws NumberFormatException
	{
		double n=0;
		try
		{
		 n = Double.parseDouble(str);
		}
		catch (NumberFormatException e)
		{
			throw new NumberFormatException(
					"Error: You should enter an Double/Integer");
			
		}
		
		return n;
	}
	
	 
	 
	public static int ParseStringToDoubleMinMax(String str, double minVal, double maxVal)
			throws NumberFormatException, OutOfBoundException
			
			// A method that transfers a string to an integer and checks that 
			// the integer is within defined limits
			
			{
				int n=0;
				try
				{
					 n=Integer.parseInt(str);
				}
				catch (NumberFormatException e)
				{
					throw new NumberFormatException(
							"Error: You should enter an Integer");
					
				}
				if (n<minVal || n>maxVal)
					throw new OutOfBoundException("Your number "
							+"should be between "+minVal+", "+maxVal + " for moisture and humidity initials and ranges");
				
				return n;
			}

}
