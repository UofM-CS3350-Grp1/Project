package objects;

/**
 * Manages email addresses
 */
public class Email 
{
	public static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";	//Email verification regular expression
	
	private String email;		//The email
	
	/**
	 * Creates a new email
	 * @param email 	The string email
	 * @throws IllegalArgumentException if the email given was invalid
	 */
	public Email(String email) throws IllegalArgumentException
	{
		assert (email != null && email.matches(EMAIL_REGEX));
		if(email != null && email.matches(EMAIL_REGEX))
		{
			this.email = email;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * @return The email address
	 */
	public String getEmail()
	{
		return email;
	}
	
	/**
	 * @param other The other email
	 * @return True if the two email addresses are equal
	 */
	public boolean equals(Object other)
	{
		return toString().equals(other.toString());
	}
	
	/** 
	 * @return Overrides toString to display email address
	 */
	public String toString()
	{
		return email;
	}
}
