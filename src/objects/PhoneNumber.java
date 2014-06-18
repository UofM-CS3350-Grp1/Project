package objects;

/**
 * Manages phone numbers
 */
public class PhoneNumber 
{
	public static final int AREA_CODE_LENGTH = 3;		//Area code in the phone number
	public static final int PREFIX_CODE_LENGTH = 3;		//Prefix code in the phone number
	public static final int LINE_NUMBER_CODE = 4;		//Line number code in the phone number
	public static final int PHONE_NUMBER_LENGTH = 10;	//Length of a phone number
	
	private String areaCode;
	private String prefix;
	private String lineNumber;
	
	/**
	 * Creates a new phone number object
	 * @param phoneNumber 	10-digit phone number
	 * @throws IllegalArgumentException
	 */
	public PhoneNumber(String phoneNumber) throws IllegalArgumentException
	{
		boolean isValid = true;
		
		assert (phoneNumber != null && phoneNumber.length() == PHONE_NUMBER_LENGTH);
		if(phoneNumber != null && phoneNumber.length() == PHONE_NUMBER_LENGTH)
		{
			//We need to ensure that the phone number is numeric only
			for(int i = 0; i < PHONE_NUMBER_LENGTH && isValid; i++)
			{
				if(phoneNumber.charAt(i) < '0' || phoneNumber.charAt(i) > '9')
					isValid = false;
			}
			
			if(isValid)
			{
				areaCode 	= phoneNumber.substring(0, 3);
				prefix   	= phoneNumber.substring(3, 6);
				lineNumber	= phoneNumber.substring(6);				
			}
			else
				throw new IllegalArgumentException();
		}
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * @return Area code
	 */
	public String getAreaCode()
	{
		return areaCode;
	}
	
	
	/**
	 * @return The prefix portion
	 */
	public String getPrefix()
	{
		return prefix;
	}
		
	/**
	 * @return The line number portion
	 */
	public String getLineNumber()
	{
		return lineNumber;
	}
	
	/**
	 * @return The phone number in the form (012) 345-6789
	 */
	public String formattedPhoneNumber()
	{
		return "(" + areaCode + ") " + prefix + "-" + lineNumber;
	}
	
	/**
	 * @param other The other number
	 * @return True if the two phone numbers are equal
	 */
	public boolean equals(Object other)
	{
		return toString().equals(other.toString());
	}
	
	/**
	 * @return String representation of a phone number
	 */
	public String toString()
	{
		return areaCode + prefix + lineNumber;
	}
}
