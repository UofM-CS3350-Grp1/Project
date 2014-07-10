package objects;

import java.util.ArrayList;

public class User implements Storable
{
	private static final String VALIDATION_REGEX = "^([a-zA-Z0-9@*#]){1,255}$";
	private static String loggedInAs;
	private String userName;
	private String password;
	private String tableName;
	private int id;

	/**
	 * Creates a new User object
	 * @param id	ID of the user
	 * @param user	User name
	 * @param pass	Password
	 * @throws IllegalArgumentException
	 */
	public User(int id, String user, String pass) throws IllegalArgumentException
	{
		if(id >= 0 && user != null && !user.isEmpty() && pass != null && !pass.isEmpty())
		{
			this.id = id;
			userName = user;
			password = pass;
			tableName = "USERS";
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @return Get the ID
	 */
	public int getID()
	{
		return id;
	}

	/**
	 * @return True if the user is valid
	 */
	public boolean isInsertable() 
	{
		return true;		
	}

	/**
	 * @return Name of the database table
	 */
	public String getTableName()
	{
		return tableName;
	}

	/**
	 * @return Database indexing
	 */
	public ArrayList<String> toIndex()
	{
		ArrayList<String> index = new ArrayList<String>();

		index.add(""+id);
		index.add(userName);
		index.add(password);
		
		return index;
	}

	/*********************************************************
	 * 				Accessors and Mutators
	 ********************************************************/

	/**
	 * Sets the user name
	 * @param user	The new user name
	 */
	public void updateUsername(String user)
	{
		assert (user != null && !user.isEmpty());
		if(user != null && !user.isEmpty() && user.matches(VALIDATION_REGEX))
			userName = user;
	}

	/**
	 * Sets the password 
	 * @param pass	The new password
	 */
	public void updatePassword(String pass)
	{
		assert (pass != null && !pass.isEmpty());
		if(pass != null && !pass.isEmpty() && pass.matches(VALIDATION_REGEX))
			password = pass;
	}

	/**
	 * @return The user name
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * @return The password
	 */
	public String getPassword()
	{
		return password;
	}

	public static boolean loggedIn()
	{
		return loggedInAs != null;
	}

	public static String getCurrentUser()
	{
		return loggedInAs;
	}

	public static void setCurrentUser(String newUser)
	{
		loggedInAs = newUser;
	}

	public static void logout()
	{
		loggedInAs = null;
	}
}
