package persistence;

import java.util.ArrayList;

import objects.Client;
import objects.Service;
import objects.Storable;
import objects.Contract;
import objects.TrackedFeature;
import objects.FeatureHistory;
import objects.Trackable;
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
		
		conditions.add("ROW_ID");
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
		
		conditions.add("ROW_ID");
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
		
		conditions.add("ROW_ID");
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
	 * GETTRACKEDFEATUREFROMPARENT()
	 * 
	 * @param element Object with that can handle tackable features.
	 * @return - Array list containing the tracked features associated with this object otherise null
	 */
	
	public ArrayList<TrackedFeature> getTrackedFeaturesFromParent(Trackable element)
	{
		ArrayList<TrackedFeature> storage = new ArrayList<TrackedFeature>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		
		if(element instanceof Service)
		{
			conditions.add("SERVICE_ID");
			conditions.add("= ");
			conditions.add("'"+element.getID()+"'");
		}
		else
		{
			conditions.add("CLIENT_ID");
			conditions.add("= ");
			conditions.add("'"+element.getID()+"'");
		}
		clauses.add(conditions);
		storage = this.mainDB.queryTrackedFeatures(clauses);
		
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
	 * GETFEATUREHISTORYFROMPARENT()
	 * 
	 * @param element Object with that can handle tackable features.
	 * @return - Array list containing the tracked features history items associated with this object otherise null
	 */
	
	public ArrayList<FeatureHistory> getFeatureHistoryFromParent(Trackable element, TrackedFeature feature)
	{
		ArrayList<FeatureHistory> storage = new ArrayList<FeatureHistory>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions1 = new ArrayList<String>();
		ArrayList<String> conditions2 = new ArrayList<String>();
		
		if(element instanceof Service)
		{
			conditions1.add("SERVICE_ID");
			conditions1.add("= ");
			conditions1.add("'"+element.getID()+"'");
			
			clauses.add(conditions1);
		}
		else
		{
			conditions1.add("CLIENT_ID");
			conditions1.add("= ");
			conditions1.add("'"+element.getID()+"'");
			clauses.add(conditions1);
		}
		conditions2.add("FEATURE_ID");
		conditions2.add("= ");
		conditions2.add("feature.getID()");
		clauses.add(conditions2);
		//storage = this.mainDB.queryFeatureHistory(clauses);
		
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
			this.mainDB.insert("CLIENTS", element);
		}
		else if(element instanceof Service)
		{
			this.mainDB.insert("SERVICES", element);
		}
		else if(element instanceof Contract)
		{
			this.mainDB.insert("CONTRACTS", element);
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
			this.mainDB.update("CLIENTS", element);
		}
		else if(element instanceof Service)
		{
			this.mainDB.update("SERVICES", element);
		}
		else if(element instanceof Contract)
		{
			this.mainDB.update("CONTRACTS", element);
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
			this.mainDB.drop("CLIENTS", toDrop.getID());
		}
		else if(element instanceof Service)
		{
			Service toDrop = (Service)element;
			this.mainDB.drop("SERVICES", toDrop.getID());
		}
		else if(element instanceof Contract)
		{
			Contract toDrop = (Contract)element;
			this.mainDB.drop("CONTRACTS", toDrop.getID());
		}
		else
		{
			System.out.println("Invalid input for DROP statement.");
		}
	}
	
	
	/**DUMPCLIENTS()
	 * 
	 * Returns all clients on the DBMS;
	 * 
	 */
	
	public ArrayList<Client> dumpClients()
	{
		return null;
	}

	/**DUMPSERVICES()
	 * 
	 * Returns all services on the DBMS;
	 * 
	 */

	ArrayList<Service> dumpServices()
	{
		return null;
	}
	
	/**DUMPCONTRACTS()
	 * 
	 * Returns all contracts on the DBMS;
	 * 
	 */
	
	public ArrayList<Contract> dumpContracts()
	{
		return null;
	}
	
	/**
	 * BLINDSQLQUERY();
	 *
	 * Runs a query against the DBMS and returns an arrayList of strings
	 *
	 * @param sql
	 * @return
	 */
	
	public ArrayList<String> blindSQLQuery(String sql)
	{
		ArrayList<String> output = this.mainDB.blindQuery(sql);
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
}
