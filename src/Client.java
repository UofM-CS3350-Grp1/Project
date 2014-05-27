/*
 * based on "I want to be able to add, delete, edit, view *active or potential clients*
 */

public class Client {
	/*
	 * add this if you want:  private int clientID;
	 */
	private String name;
	private String phoneNumber;
	private String email;
	private String address;
	private String bizName;  // ???
	private boolean active;
	private boolean potential;
	
	public Client( 
			String name, 
			String phoneNumber,
			String email,
			String address,
			String bizName,
			boolean active, 
			boolean potential
			) throws IllegalArgumentException {
		// nameless clients are likely useless for the user, thus, exception
		if ( name == null )	throw new IllegalArgumentException();
		this.name = name;
		
		// check for null?
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.bizName = bizName;
		
		// ...is it valid for both active and potential to be false?
		this.active = active;
		this.potential = potential;
	}
}