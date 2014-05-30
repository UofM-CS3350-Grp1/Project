package persistence;

import java.util.ArrayList;

import objects.Client;
import objects.Service;
import objects.Storable;
import objects.Contract;
import objects.Client.ClientStatus;

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
	
	/**
	 * 	GETSERVICEBYID()
	 * 
	 *  @param	int id	-	ID to search for
	 *  
	 *  @return	Service	-	Service specified by ID, null if no match
	 */
	
	public Service getServiceByID(int id)
	{
		ArrayList<Service> storage = new ArrayList<Service>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		
		conditions.add("SERVICE_ID");
		conditions.add("= ");
		conditions.add(""+id+"");
		
		clauses.add(conditions);
		
		storage = this.mainDB.queryServices(clauses);
		
		if(storage.size() != 1)
		{
			return null;
		}
		else
		{
			return storage.get(0);
		}
	}
	
	/**
	 * 	GETSERVICEBYID()
	 * 
	 *  @param	int id	-	ID to search for
	 *  
	 *  @return	Service	-	Service specified by ID, null if no match
	 */
	
	public ArrayList<Service> getServicesByTitle(String name)
	{
		ArrayList<Service> storage = new ArrayList<Service>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		
		conditions.add("TITLE");
		conditions.add("= ");
		conditions.add("'"+name+"'");
		
		clauses.add(conditions);
		
		storage = this.mainDB.queryServices(clauses);
		
		if(storage.size() == 0)
		{
			return null;
		}
		else
		{
			return storage;
		}
	}
	
	/**
	 * 	GETCLIENTBYID()
	 * 
	 *  @param	int id	-	ID to search for
	 *  
	 *  @return	Service	-	Client specified by ID, null if no match
	 */
	
	public Client getClientByID(int id)
	{
		ArrayList<Client> storage = new ArrayList<Client>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		
		conditions.add("CLIENT_ID");
		conditions.add("= ");
		conditions.add(""+id+"");
		
		clauses.add(conditions);
		
		storage = this.mainDB.queryClients(clauses);
		
		if(storage.size() != 1)
		{
			return null;
		}
		else
		{
			return storage.get(0);
		}
	}
	
	/**
	 * 	GETCLIENTSBYSTATUS()
	 * 
	 *  @param	ClientStatus status	-	Status to search for
	 *  
	 *  @return	Clients	-	ArrayList of Clients matching Status, null if no match
	 */
	
	public ArrayList<Client> getClientsByStatus(ClientStatus status)
	{
		ArrayList<Client> storage = new ArrayList<Client>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		int val = 0;
		
		if(status == ClientStatus.Active)
			val = 1;
		
		conditions.add("IS_ACTIVE");
		conditions.add("= ");
		conditions.add("'"+val+"'");
		
		clauses.add(conditions);
		
		storage = this.mainDB.queryClients(clauses);
		
		if(storage.size() == 0)
		{
			return null;
		}
		else
		{
			return storage;
		}
	}
	
	/**
	 * 	GETSERVICEBYID()
	 * 
	 *  @param	int id	-	ID to search for
	 *  
	 *  @return	Contract	-	Contract specified by ID, null if no match
	 */	
	
	public Contract getContractByID(int id)
	{
		ArrayList<Contract> storage = new ArrayList<Contract>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		
		conditions.add("CONTRACT_ID");
		conditions.add("= ");
		conditions.add(""+id+"");
		
		clauses.add(conditions);
		
		storage = this.mainDB.queryContracts(clauses);
		
		if(storage.size() != 1)
		{
			return null;
		}
		else
		{
			return storage.get(0);
		}
	}
	
	/**
	 * 	GETCONTRACTSBYBUSINESS()
	 * 
	 *  @param	String business	-	Business name to search for
	 *  
	 *  @return	Contracts	-	ArrayList of Contacts matching Business, null if no match
	 */
	
	public ArrayList<Contract> getContractsByBusiness(String business)
	{
		ArrayList<Contract> storage = new ArrayList<Contract>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		
		conditions.add("BUSINESS_NAME");
		conditions.add("= ");
		conditions.add("'"+business+"'");
		
		clauses.add(conditions);
		
		storage = this.mainDB.queryContracts(clauses);
		
		if(storage.size() == 0)
		{
			return null;
		}
		else
		{
			return storage;
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
