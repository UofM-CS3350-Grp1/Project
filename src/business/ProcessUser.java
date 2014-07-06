package business;

import objects.User;

/**
 * Performs the user related processing between the GUI
 * component and the Database
 */

public class ProcessUser extends ProcessStorable
{

	public ProcessUser()
	{
		super();
	}

	/*
	 * Stub method - No method in db interface for retrieving a user
	 */
	public User getUser()
	{
		User theUser = new User(0,"username","password");
		return theUser;
	}

	public boolean validateUser(String userName, String password)
	{
		boolean isValid = false;
		database.connect();
		isValid = database.userLogin(userName, password);
		database.disconnect();
		return isValid;
	}
}
