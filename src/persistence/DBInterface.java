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
		if(dbName != null)
		{
			this.dbName = dbName;
			this.mainDB = new DBController(dbName);
			this.mainDB.connect();
		}
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
	 * 	GETSERVICEBYTITLE()
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
	 * 	GETCONTRACTBYID()
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
	
	/**
	 * INSERT()
	 * 
	 * @param element Storable to insert
	 */
	
	public void insert(Storable element)
	{
		if(element instanceof Client)
		{
			this.mainDB.insertClient((Client)element);
		}
		else if(element instanceof Service)
		{
			this.mainDB.insertService((Service)element);
		}
		else if(element instanceof Contract)
		{
			this.mainDB.insertContract((Contract)element);
		}
		else
		{
			System.out.println("Invalid input for INSERT statement.");
		}
	}
	
	/**
	 * UPDATE()
	 * 
	 * @param element Storable to update
	 */
	
	public void update(Storable element)
	{
		if(element instanceof Client)
		{
			this.mainDB.updateClient((Client)element);
		}
		else if(element instanceof Service)
		{
			this.mainDB.updateService((Service)element);
		}
		else if(element instanceof Contract)
		{
			this.mainDB.updateContract((Contract)element);
		}
		else
		{
			System.out.println("Invalid input for UPDATE statement.");
		}
	}
	
	/**
	 * DROP()
	 * 
	 * @param element Storable to drop
	 */
	
	public void drop(Storable element)
	{
		if(element instanceof Client)
		{
			Client toDrop = (Client)element;
			this.mainDB.drop("CLIENT", toDrop.getID());
		}
		else if(element instanceof Service)
		{
			Service toDrop = (Service)element;
			this.mainDB.drop("SERVICE", toDrop.getID());
		}
		else if(element instanceof Contract)
		{
			Contract toDrop = (Contract)element;
			this.mainDB.drop("CONTRACT", toDrop.getContractNumber());
		}
		else
		{
			System.out.println("Invalid input for DROP statement.");
		}
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
}
