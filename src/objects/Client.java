package objects;
/*
 * based on "I want to be able to add, delete, edit, view *active or potential clients*
 */

public class Client implements Storable {
	
	/**
	 * Keeps track of the client's status allowing us to track
	 * one value rather and risk an invalid state and unnecessary
	 * state validation
	 */
	public enum ClientStatus
	{
		Active,
		Potential
	}
	
	public static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";	//Email verification regular expression
	public static final int PHONE_NUMBER_LENGTH = 10;		//Number of digits in a phone number
	
	private int clientID;
	private String name;
	private String phoneNumber;
	private String email;
	private String address;
	private String businessName;  // ???
	private ClientStatus status;

	/*
	 * constructs the client; Name probably shouldn't be null;
	 * throws exception since we shouldn't let broken objects
	 * live to cause mayhem at some other point in time;
	 */
	public Client( 
			String name, 
			String phoneNumber,
			String email,
			String address,
			String businessName,
			ClientStatus status
			) throws IllegalArgumentException {
		// nameless clients are likely useless for the user, thus, exception
		if ( name == null )	throw new IllegalArgumentException();
		this.name = name;
		
		// check for null?
		this.clientID = 0; //Could be -1 as well if anyone has a preference
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.businessName = businessName;
		
		this.status = status;
	}
	
	/*
	 * Database Constructor:
	 * Exact copy of previous constructor 
	 * 
	 * <Q>:	Right now I'm looking at storing ClientStatus as a 0/1 variable on the DBMS
	 * 		and doing the type check in the constructor.Do you have an alternate 
	 * 		implementation you'd prefer?
	 * 
	 * <A>:	?
	 */
	
	public Client(
			int id,
			String name, 
			String phoneNumber,
			String email,
			String address,
			String businessName,
			int status
			) throws IllegalArgumentException {
		// nameless clients are likely useless for the user, thus, exception
		if ( name == null )	throw new IllegalArgumentException();
		this.name = name;
		
		// check for null?
		this.clientID = id;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.businessName = businessName;

		
		if(status == 1)
			this.status = ClientStatus.Active;
		else
			this.status = ClientStatus.Potential;
	}
		
	/*********************************************************
	 * 				Accessors and Mutators
	 ********************************************************/

	/**
	 * @return The name of the client
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name Name of the client
	 */
	public void setName(String name)
	{
		assert (name != null && name != "");
		if(name != null && name != "");
			this.name = name;
	}

	/**
	 * 
	 * @return The client's unique ID.
	 */
	
	public int getID()
	{
		return this.clientID;
	}
	
	/**
	 * @return The client's phone number
	 */
	public String getPhoneNumber() 
	{
		return phoneNumber;
	}

	/**
	 * @param phoneNumber The client's phone number
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		assert (phoneNumber != null && phoneNumber.length() == PHONE_NUMBER_LENGTH);
		if(phoneNumber != null && phoneNumber.length() == PHONE_NUMBER_LENGTH)
			this.phoneNumber = phoneNumber;
	}

	/**
	 * @return The client's email
	 */
	public String getEmail() 
	{
		return email;
	}

	/**
	 * @param email The client's email
	 */
	public void setEmail(String email)
	{
		assert (email != null);
		if(email != null && email.matches(EMAIL_REGEX))
			this.email = email;
	}

	/**
	 * @return The address of the client
	 */
	public String getAddress() 
	{
		return address;
	}

	/**
	 * @param address The address of the client
	 */
	public void setAddress(String address)
	{
		assert (address != null && address != "");
		if(address != null && address != "")
			this.address = address;
	}

	/**
	 * @return The business name
	 */
	public String getBusinessName() 
	{
		return businessName;
	}

	/**
	 * @param businessName The name of the business
	 */
	public void setBusinessName(String businessName)
	{
		assert (businessName != null && businessName != "");
		if(businessName != null && businessName != "")
			this.businessName = businessName;
	}
	
	/**
	 * @return The status of the client
	 */
	public ClientStatus getStatus()
	{
		return status;
	}

	/**
	 * @param status The client's status
	 */
	public void setStatus(ClientStatus status)
	{
		this.status = status;
	}
	
	public String toString()
	{
		return "(Name: " + this.name +
				", ID: " + this.clientID +
				", Phone No.: " + this.phoneNumber +
				", E-Mail: " + this.email +
				", Address: " + this.address +
				", Business Name: " + this.businessName +
				", Client Status: " + (this.status == ClientStatus.Active ? "Active" : "Potential")+  ")";
	}
}