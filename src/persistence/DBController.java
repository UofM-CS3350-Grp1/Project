package persistence;
import objects.*;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;

import java.util.Map;
import java.util.ArrayList;

//----------------------------------------------------------------------------
//Class: DBController
//
//Def: Provides interface to DBMS.
//----------------------------------------------------------------------------

public class DBController 
{
	
	private String dbName;
	private String dbType;
	
	private Statement statem1;
	private Connection conn;
	
	private String cmdString;

	//----------------------------------------------------------------------------
	//	Constructor, is passed specified database name.
	//----------------------------------------------------------------------------
	
	public DBController(String dbName)
	{
		this.dbName = dbName;
	}
	
	//----------------------------------------------------------------------------
	//	CONNECT()
	//
	//	PARAMS: NONE
	//
	//	NOTES: 	Starts new session with specified DB.
	//----------------------------------------------------------------------------
	
	public void connect()
	{
		String addr = "jdbc:hsqldb:database/" + dbName;
		try
		{
			dbType = "HSQL";
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			conn = DriverManager.getConnection(addr, "SA", "");
			statem1 = conn.createStatement();
		}
		catch(Exception e)
		{
			errorOutput(e);
		}
		
		System.out.println("Opened " +dbType +" database " +dbName);
		
	}
	
	//----------------------------------------------------------------------------
	//	DISCONNECT()
	//
	//	PARAMS: NONE
	//
	//	NOTES: 	Ends current session.
	//----------------------------------------------------------------------------
	
	public void disconnect()
	{
		try
		{	// commit all changes to the database
			cmdString = "shutdown compact";
			statem1.executeQuery(cmdString);
			conn.close();
		}
		catch (Exception e)
		{
			errorOutput(e);
		}
		
		System.out.println("Closed " +dbType +" database " +dbName);
		
	}
	
	//----------------------------------------------------------------------------
	//	INSERT()
	//
	//	PARAMS: String table	- 	Table to drop from
	//			Object element	-	Storable element
	//
	//	NOTES: 	####VERY EARLY#### Will be re-implemented just throwing out ideas. 
	//			Will drop insert element into table.
	//----------------------------------------------------------------------------
	
	public void insert(String Table, Storable element)
	{
		if(element instanceof Service)
		{
		
		}
		else if(element instanceof Client)
		{
			
		}
	/*	else if(element instanceof Contract)
		{
			
		} */
		else
		{
			System.out.println("Invalid Object: " + element.getClass().getName());
		}
		
	}
	
	//----------------------------------------------------------------------------
	//	UPDATE()
	//
	//	PARAMS: String table	- 	Table to drop from
	//			Object element	-	Storable element
	//
	//	NOTES: 	####VERY EARLY#### Will be re-implemented just throwing out ideas. 
	//			Will update specified element on table.
	//----------------------------------------------------------------------------
	
	public void update(String tiable, Storable element)
	{
		if(element instanceof Service)
		{
		
		}
		else if(element instanceof Client)
		{
			
		}
	/*	else if(element instanceof Contract)
		{
			
		} */
		else
		{
			System.out.println("Invalid Object: " + element.getClass().getName());
		}
		
	}
	
	//----------------------------------------------------------------------------
	//	DROP()
	//
	//	PARAMS: String table	- 	Table to drop from
	//			Object element	-	Storable element
	//
	//	NOTES: 	####VERY EARLY#### Will be re-implemented just throwing out ideas. 
	//			Will drop specified element from table.
	//----------------------------------------------------------------------------
	
	public void drop(String table, Storable element)
	{
		if(element instanceof Service)
		{
		
		}
		else if(element instanceof Client)
		{
			
		}
	/*	else if(element instanceof Contract)
		{
			
		} */
		else
		{
			System.out.println("Invalid Object: " + element.getClass().getName());
		}
		
	}
	
	//----------------------------------------------------------------------------
	//	QUERYSERVICES()
	//
	//	PARAMS: ArrayList<String> selects	-   SELECT selects.get(1)... etc.
	//			ArrayList<String> clauses	-   WHERE clauses.get(1)... etc.
	//
	//	NOTES: 	####VERY EARLY#### Will be re-implemented just throwing out ideas.
	//			Selects services with conditionals. 
	//----------------------------------------------------------------------------
	
	public Service queryServices(boolean unqique, ArrayList<String> selects, ArrayList<String> clauses)
	{
		//Not making assumptions about
		return null;
	}
	
	//----------------------------------------------------------------------------
	//	QUERYCLIENT()
	//
	//	PARAMS: ArrayList<String> selects	-   SELECT selects.get(1)... etc.
	//			ArrayList<String> clauses	-   WHERE clauses.get(1)... etc.
	//
	//	NOTES: 	####VERY EARLY#### Will be re-implemented just throwing out ideas. 
	//			Selects clients with conditionals. 
	//----------------------------------------------------------------------------
	
	public Client queryClient(boolean unqique, ArrayList<String> selects, ArrayList<String> clauses)
	{
		return null;
	}
	
	//----------------------------------------------------------------------------
	//	QUERYCONTRACT()
	//
	//	PARAMS: ArrayList<String> selects	-   SELECT selects.get(1)... etc.
	//			ArrayList<String> clauses	-   WHERE clauses.get(1)... etc.
	//
	//	NOTES: 	####VERY EARLY#### Will be re-implemented just throwing out ideas.
	//			Selects contracts with conditionals. 
	//----------------------------------------------------------------------------
	
	/*public Contract queryContract(ArrayList<String> selects, ArrayList<String> clauses)
	{
		return null;
	}*/
	
	//----------------------------------------------------------------------------
	//	COMPLEXQUERY()
	//
	//	PARAMS: boolean distinct 			-	Flag for distinct rows
	//			ArrayList<String> selects	-   SELECT selects.get(1)... etc.
	//			ArrayList<String> joins		-   joins.get(1).get(1) JOIN ON ... etc.
	//			ArrayList<String> clauses	-   WHERE clauses.get(1)... etc.
	//
	//	NOTES: 	####VERY EARLY#### This will likely change several times as
	//			this is far from an ideal interface.
	//----------------------------------------------------------------------------
	
	public ArrayList<Object> complexQuery(
								boolean distinct,
								ArrayList<String> selects,
								ArrayList<ArrayList<String>> joins,  
								ArrayList<String> clauses)
	{
		return null;
	}
	
	//----------------------------------------------------------------------------
	//	ERROROUTPUT()
	//
	//	PARAMS: NONE
	//
	//	NOTES: 	Returns error message & stack trace.
	//----------------------------------------------------------------------------
	
	public void errorOutput(Exception e)
	{
		
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	
	//----------------------------------------------------------------------------
	//	TOSTRING()
	//
	//	PARAMS: NONE
	//
	//	NOTES: 	Standard toString() method.
	//----------------------------------------------------------------------------
	
	public String toString()
	{
		return "Currently connected to: " + dbName;
	}
	
}
