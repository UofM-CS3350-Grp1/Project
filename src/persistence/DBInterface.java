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
	public static final String DATABASE_NAME = "MainDB";
	
	private DBController mainDB;
	private String dbName;
	private DBParser parser;
	
	public DBInterface(String dbName)
	{
		if(dbName != null)
		{
			this.parser = null;
			this.dbName = dbName;
			this.mainDB = new DBController(dbName);
		}
	}
	
	public void connect()
	{
		this.parser = new DBParser(this);
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
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		if(id > 0)
		{
			conditions.add("ROW_ID");
			conditions.add("= ");
			conditions.add(""+id+"");
			
			clauses.add(conditions);
			
			returnValue  = this.mainDB.query("SERVICES", clauses);
			
			storage = parser.parseServices(returnValue);
			
			if(storage.size() != 1)
			{
				return null;
			}
			else
			{
				return storage.get(0);
			}
		}
		else
		{
			return null;
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
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		
		if(id > 0)
		{
			conditions.add("ROW_ID");
			conditions.add("= ");
			conditions.add(""+id+"");
			
			clauses.add(conditions);
			
			returnValue  = this.mainDB.query("CLIENTS", clauses);
			
			storage = parser.parseClients(returnValue);
			
			if(storage.size() != 1)
			{
				return null;
			}
			else
			{
				return storage.get(0);
			}
		}
		else
		{
			return null;
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
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		if(id > 0)
		{
			conditions.add("ROW_ID");
			conditions.add("= ");
			conditions.add(""+id+"");
			
			clauses.add(conditions);
			
			returnValue  = this.mainDB.query("CONTRACTS", clauses);
			
			storage = parser.parseContracts(returnValue);
			
			if(storage.size() != 1)
			{
				return null;
			}
			else
			{
				return storage.get(0);
			}
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * 	GETFEATUREHISTORYBYID()
	 * 
	 *  @param	int id	-	ID to search for
	 *  
	 *  @return	FeatureHistory	-	FeatureHistory specified by ID, null if no match
	 */	
	
	public FeatureHistory getFeatureHistoryByID(int id)
	{
		ArrayList<FeatureHistory> storage = new ArrayList<FeatureHistory>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		if(id > 0)
		{
			conditions.add("ROW_ID");
			conditions.add("= ");
			conditions.add(""+id+"");
			
			clauses.add(conditions);
			
			returnValue  = this.mainDB.query("FEATURE_HISTORY", clauses);
			
			storage = parser.parseFeatureHistories(returnValue);
			
			if(storage.size() != 1)
			{
				return null;
			}
			else
			{
				return storage.get(0);
			}
		}
		else
		{
			return null;
		}
		
	}
	
	/**
	 * 	GETTRACKEDFEATUREBYID()
	 * 
	 *  @param	int id	-	ID to search for
	 *  
	 *  @return	TrackedFeature	-	TrackedFeature specified by ID, null if no match
	 */	
	
	public TrackedFeature getTrackedFeatureByID(int id)
	{
		ArrayList<TrackedFeature> storage = new ArrayList<TrackedFeature>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		if(id > 0)
		{
			conditions.add("ROW_ID");
			conditions.add("= ");
			conditions.add(""+id+"");
			
			clauses.add(conditions);
			
			returnValue  = this.mainDB.query("FEATURE", clauses);
			
			storage = parser.parseFeatures(returnValue);
			
			if(storage.size() != 1)
			{
				return null;
			}
			else
			{
				return storage.get(0);
			}
		}
		else
		{
			return null;
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
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		conditions.add("TITLE");
		conditions.add("= ");
		conditions.add("'"+name+"'");
		
		clauses.add(conditions);
		
		returnValue  = this.mainDB.query("SERVICES", clauses);
		
		storage = parser.parseServices(returnValue);
		
		if(storage.size() == 0)
		{
			return null;
		}
		else
		{
			return storage;
		}
	}
	
	public ArrayList<Service> getServiceByContract(Contract input)
	{
		ArrayList<Service> storage = new ArrayList<Service>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		conditions.add("CONTRACT_ID");
		conditions.add("= ");
		conditions.add("'"+input.getID()+"'");
		
		clauses.add(conditions);
		
		returnValue  = this.mainDB.query("SERVICES", clauses);
		
		storage = parser.parseServices(returnValue);
		
		if(storage.size() == 0)
		{
			return null;
		}
		else
		{
			return storage;
		}
	}
	
	public ArrayList<Service> getServiceByClient(Client input)
	{
		ArrayList<Service> storage = new ArrayList<Service>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		conditions.add("CLIENT_ID");
		conditions.add("= ");
		conditions.add("'"+input.getID()+"'");
		
		clauses.add(conditions);
		
		returnValue  = this.mainDB.query("SERVICES", clauses);
		
		storage = parser.parseServices(returnValue);
		
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
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		if(status != null)
		{
			int val = 0;
			
			if(status == ClientStatus.Active)
				val = 1;
			
			conditions.add("IS_ACTIVE");
			conditions.add("= ");
			conditions.add("'"+val+"'");
			
			clauses.add(conditions);
			
			returnValue = this.mainDB.query("CLIENTS",clauses);
			
			storage = this.parser.parseClients(returnValue);
			
			if(storage.size() == 0)
			{
				return null;
			}
			else
			{
				return storage;
			}
		}
		else
		{
			return null;
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
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		conditions.add("BUSINESS_NAME");
		conditions.add("= ");
		conditions.add("'"+business+"'");
		
		clauses.add(conditions);
		
		returnValue = this.mainDB.query("CONTRACTS", clauses);
		
		storage = this.parser.parseContracts(returnValue);
		
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
	 * @param element Object with that can handle trackable features.
	 * @return - Array list containing the tracked features history items associated with this object otherise null
	 */
	
	public ArrayList<FeatureHistory> getFeatureHistoryFromParent(Trackable element, TrackedFeature feature)
	{
		ArrayList<FeatureHistory> storage = new ArrayList<FeatureHistory>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions1 = new ArrayList<String>();
		ArrayList<String> conditions2 = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		if(element != null && feature != null)
		{
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
			conditions2.add(" FEATURE_ID");
			conditions2.add("= ");
			conditions2.add("'"+feature.getID()+"'");
			clauses.add(conditions2);
			returnValue = this.mainDB.query("FEATURE_HISTORY",clauses);
			
			storage = parser.parseFeatureHistories(returnValue);
			
			if(storage.size() == 0)
			{
				return null;
			}
			else
			{
				return storage;
			}
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * 	GETTRACKEDFEATUREBYTITLE()
	 * 
	 *  @param	String type	-	Title to search for
	 *  
	 *  @return	TrackedFeature	-	Tracked Feature specified by ID, null if no match
	 */	
	
	public TrackedFeature getTrackedFeatureByTitle(String type)
	{
		ArrayList<TrackedFeature> storage = new ArrayList<TrackedFeature>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		if(type != null && !type.isEmpty() && type.compareTo("") != 0)
		{
			conditions.add("TITLE");
			conditions.add("= ");
			conditions.add("'"+type+"'");
			
			clauses.add(conditions);
			
			returnValue  = this.mainDB.query("FEATURE", clauses);
			
			storage = parser.parseFeatures(returnValue);
			
			if(storage.size() != 1)
			{
				return null;
			}
			else
			{
				return storage.get(0);
			}
		}
		else
		{
			return null;
		}
		
	}

	/**
	 * INSERT()
	 * 
	 * @param element Storable to insert
	 */
	
	public boolean insert(Storable element)
	{
		boolean output = false;
		int feedback = -1;
		
		if(element != null && element.getTableName().compareTo("") != 0 && element.getTableName() != null)
		{
			feedback = mainDB.insert(element.getTableName(), element);
		}
		else
		{
			System.out.println("Invalid input for INSERT statement.");
		}
		
		if(feedback != -1)
			output = true;

		return output;
	}
	
	/**
	 * UPDATE()
	 * 
	 * @param element Storable to update
	 */
	
	public boolean update(Storable element)
	{
		boolean output = false;
		
		if(element != null && element.getTableName().compareTo("") != 0 && element.getTableName() != null)
		{
			output = mainDB.update(element.getTableName(), element);
		}
		else
		{
			System.out.println("Invalid input for UPDATE statement.");
		}
		
		return output;
	}
	
	/**
	 * DROP()
	 * 
	 * @param element Storable to drop
	 */
	
	public boolean drop(Storable element)
	{
		boolean output = false;
		
		if(element != null && element.getTableName().compareTo("") != 0 && element.getTableName() != null)
		{
			output = mainDB.drop(element.getTableName(), element.getID());
		}
		else
		{
			System.out.println("Invalid input for DROP statement.");
		}
		
		return output;
	}
	
	
	/**DUMPCLIENTS()
	 * 
	 * Returns all clients on the DBMS;
	 * 
	 */
	
	public ArrayList<Client> dumpClients()
	{
		ArrayList<Client> storage = new ArrayList<Client>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		conditions.add("ALL");
		
		clauses.add(conditions);
		
		returnValue  = this.mainDB.query("CLIENTS", clauses);
		
		storage = parser.parseClients(returnValue);
		
		if(storage.size() == 0)
		{
			return null;
		}
		else
		{
			return storage;
		}
	}

	/**DUMPSERVICES()
	 * 
	 * Returns all services on the DBMS;
	 * 
	 */

	public ArrayList<Service> dumpServices()
	{
		ArrayList<Service> storage = new ArrayList<Service>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		conditions.add("ALL");
		
		clauses.add(conditions);
		
		returnValue  = this.mainDB.query("SERVICES", clauses);
		
		storage = parser.parseServices(returnValue);
		
		if(storage.size() == 0)
		{
			return null;
		}
		else
		{
			return storage;
		}
	}
	
	/**DUMPCONTRACTS()
	 * 
	 * Returns all contracts on the DBMS;
	 * 
	 */
	
	public ArrayList<Contract> dumpContracts()
	{
		ArrayList<Contract> storage = new ArrayList<Contract>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		conditions.add("ALL");
		
		clauses.add(conditions);
		
		returnValue  = this.mainDB.query("CONTRACTS", clauses);
		
		storage = parser.parseContracts(returnValue);
		
		if(storage.size() == 0)
		{
			return null;
		}
		else
		{
			return storage;
		}
	}
	
	/**DUMPTRACKEDFEATURES()
	 * 
	 * Returns all tracked features on the DBMS;
	 * 
	 */
	
	public ArrayList<TrackedFeature> dumpTrackedFeatures()
	{
		ArrayList<TrackedFeature> storage = new ArrayList<TrackedFeature>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		conditions.add("ALL");
		
		clauses.add(conditions);
		
		returnValue  = this.mainDB.query("FEATURE", clauses);
		
		storage = parser.parseFeatures(returnValue);
		
		if(storage.size() == 0)
		{
			return null;
		}
		else
		{
			return storage;
		}
	}
	
	/**DUMPFEATUREHISTORY()
	 * 
	 * Returns all tracked features on the DBMS;
	 * 
	 */
	
	public ArrayList<FeatureHistory> dumpFeatureHistory()
	{
		ArrayList<FeatureHistory> storage = new ArrayList<FeatureHistory>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		conditions.add("ALL");
		
		clauses.add(conditions);
		
		returnValue  = this.mainDB.query("FEATURE_HISTORY", clauses);
		
		storage = parser.parseFeatureHistories(returnValue);
		
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
