package persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import objects.Storable;

public class NewDBInterface
{
	public static final String DATABASE_NAME = "CacheDB";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	protected DBController mainDB;
	private String dbName;
	private final int ERROR_LOGGING = 0; //1 to enable 0 to disable.
	protected SimpleDateFormat sdf;
	
	public NewDBInterface(String dbName)
	{
		this.sdf = new SimpleDateFormat(DATE_FORMAT);
		if(dbName != null)
		{
			this.dbName = dbName;
			this.mainDB = new DBController(dbName);
		}
	}
	
	public void connect()
	{
		this.mainDB.connect();
	}
	
	public void disconnect()
	{
		this.mainDB.disconnect();
	}
	
	//Testing purposes only
	public DBController getController()
	{
		return this.mainDB;
	}
	
	/**
	 * 	IDEXISTS()
	 * 
	 *  @param	int id	-	ID to search for
	 *  
	 *  @return	Boolean		If this ID already exists on the DBMS
	 */	
	
	public boolean idExists(Storable storableTemplate)
	{
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		boolean output = false;
		
		if(storableTemplate != null && storableTemplate.getID() >= 0)
		{
			conditions.add("ROW_ID");
			conditions.add("= ");
			conditions.add(""+storableTemplate.getID()+"");
			
			clauses.add(conditions);
			
			returnValue  = this.mainDB.query(storableTemplate.getTableName(), clauses);
			
			if(returnValue.size() >= 1)
			{
				output = true;
			}

		}
		
		return output;
	}
	
	/**
	 * TOSTRING()
	 * 
	 * @return String output indicating which database is in use.
	 */
	
	public String toString()
	{
		String output;
		
		if(dbName != null)
		{
			output = "Using database: " + dbName;
		}
		else
		{
			output = "No DB loaded.";
		}
		
		return output;
	}
	
	public void errorMessage(String retrieve, String invalid, String instruction)
	{
		System.out.println("ATTEMPTING TO RETRIEVE "+retrieve+" FROM "+invalid+" PLEASE "+instruction+" AND TRY AGAIN.\n\n");
	}

}
