package persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class LoginValidator extends DeadInterface
{
	public LoginValidator()
	{
		super(DBInterface.DATABASE_NAME);
	}
	
	/**USERLOGIN()
	 * 
	 * Checks logins against table. 
	 */
	
	public boolean userLogin(String name, String login)
	{
		boolean output = false;
		String sql = "";
		ArrayList<String> returnVal = new ArrayList<String>();
		
		if(name != null && login != null && !name.isEmpty() && !login.isEmpty())
		{
			sql = "SELECT COUNT(*) FROM USERS WHERE NAME = '"+name+"' AND PASSWORD = '"+login+"'";
			
			returnVal = this.mainDB.blindQuery(sql);
			
			if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0 && Integer.parseInt(returnVal.get(0)) == 1)
				output = true;
		}
		
		return output;
	}
}
