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

	public boolean validateUser(String userName, String password)
	{
		boolean isValid = false;

		if(userName != null && !userName.isEmpty() && password != null && !password.isEmpty())
		{
			database.connect();
			isValid = database.userLogin(userName, password);
			database.disconnect();
		}
		return isValid;
	}
}
