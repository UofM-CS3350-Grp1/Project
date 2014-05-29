package persistence;

import java.util.ArrayList;

import objects.Client;
import objects.Service;
import objects.Storable;

public class DBInterface 
{
	private DBController mainDB;
	private String dbName;
	
	public DBInterface(String dbName)
	{
		this.dbName = dbName;
		this.mainDB = new DBController(dbName);
		this.mainDB.connect();
	}
	
	public Service getServiceByID(int id)
	{
		ArrayList<Service> storage = new ArrayList();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		
		conditions.add("SERVICE_ID");
		conditions.add("= ");
		conditions.add(""+id+"");
		
		clauses.add(conditions);
		
		storage = this.mainDB.queryServices(clauses);
		
		if(storage.size() >1)
		{
			return null;
		}
		else
		{
			return storage.get(0);
		}
	}
	
	public Service getServiceByTitle(String name)
	{
		ArrayList<Service> storage = new ArrayList();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		
		conditions.add("TITLE");
		conditions.add("= ");
		conditions.add("'"+name+"'");
		
		clauses.add(conditions);
		
		storage = this.mainDB.queryServices(clauses);
		
		if(storage.size() >1)
		{
			return null;
		}
		else
		{
			return storage.get(0);
		}
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
		return "Using database: " + dbName;
	}
}
