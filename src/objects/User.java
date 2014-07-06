package objects;

import java.util.ArrayList;

public class User implements Storable
{
	private String userName;
	private String password;
	private String tableName;
	private int id;

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

	public int getID()
	{
		return id;
	}

	public boolean isInsertable() 
	{
		return true;		
	}

	public String getTableName()
	{
		return tableName;
	}

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
	
	public void updateUsername(String user)
	{
		userName = user;
	}

	public void updatePassword(String pass)
	{
		password = pass;
	}

	public String getUserName()
	{
		return userName;
	}

	public String getPassword()
	{
		return password;
	}
}
