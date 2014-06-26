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
	public static final String DATABASE_NAME = "CacheDB";
	
	private DBController mainDB;
	private String dbName;
	private DBParser parser;
	private final int ERROR_LOGGING = 1; //1 to enable 0 to disable.
	
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
		
		if(id >= 0)
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
		
		
		if(id >= 0)
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
		
		if(id >= 0)
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
		
		if(id >= 0)
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
		
		if(id >= 0)
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
		
		
		if(business != null && !business.isEmpty())
		{
			
			conditions.add("BUSINESS_NAME");
			conditions.add("= ");
			conditions.add("'"+business+"'");
			
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
		else
		{
			if(business == null && ERROR_LOGGING == 1)
				errorMessage("CONTRACT", "A BUSINESS NAME STRING THAT IS NULL\n", "PASS A VALID STRING");
			
			if(business != null && business.isEmpty() && ERROR_LOGGING == 1)
				errorMessage("CONTRACT", "A BUSINESS NAME STRING THAT IS EMPTY\n", "POPULATE STRING");
			
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
	
	public ArrayList<TrackedFeature> getTrackedFeaturesByService(Service input)
	{
		if(input != null && input.getID() >= 0)
		{
			ArrayList<TrackedFeature> storage = new ArrayList<TrackedFeature>();
			ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
			ArrayList<String> conditions = new ArrayList<String>();
			ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
			
			conditions.add("SERVICE_KEY");
			conditions.add("= ");
			conditions.add("'"+input.getID()+"'");
			
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
		else
		{	
			if(input != null && input.getID() < 0 && ERROR_LOGGING == 1)
				errorMessage("SERVICE", "A SERVICE OBJECT THAT HAS NOT BEEN INSERTED INTO DMBS\n", "INSERT THE SERVICE OBJECT");
			
			if(input == null && ERROR_LOGGING == 1)
				errorMessage("SERVICE", "A NULL SERVICE OBJECT\n", "INSTANTIATE A SERVICE OBJECT");

			return null;
		}
	}
	
	/**
	 * 	GETSERVICEBYFEATURE()
	 * 
	 *  @param	Feature 	- Feature to use to retrieve Service
	 *  
	 *  @return	Service	-	Service referenced by feature, null if no ref
	 */	
	
	public Service getServiceByFeature(TrackedFeature input)
	{
		if(input != null && input.getID() >= 0)
		{
			ArrayList<Service> storage = new ArrayList<Service>();
			ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
			ArrayList<String> conditions = new ArrayList<String>();
			ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
			
			conditions.add("ROW_ID");
			conditions.add("= ");
			conditions.add("'"+input.getServiceKey()+"'");
			
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
			if(input != null && input.getID() < 0 && ERROR_LOGGING == 1)
				errorMessage("SERVICE", "A TRACKED_FEATURE OBJECT THAT HAS NOT BEEN INSERTED INTO DMBS\n", "INSERT THE TRACKED_FEATURE OBJECT");
			
			if(input == null && ERROR_LOGGING == 1)
				errorMessage("SERVICE", "A NULL TRACKED_FEATURE OBJECT\n", "INSTANTIATE A CONTRACT OBJECT");
			
			return null;
		}
	}
	
	/**
	 * 	GETSERVICEBYCONTRACT()
	 * 
	 *  @param	Contract 	- Feature to used to retrieve Services
	 *  
	 *  @return	ArrayList<Services>	-	All services referenced by contract, null if no ref
	 */	
	
	public ArrayList<Service> getServiceByContract(Contract input)
	{
		if(input != null && input.getID() >= 0)
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
		else
		{
			if(input != null && input.getID() < 0 && ERROR_LOGGING == 1)
				errorMessage("SERVICE", "A CONTRACT OBJECT THAT HAS NOT BEEN INSERTED INTO DMBS\n", "INSERT THE CONTRACT OBJECT");
			
			if(input == null && ERROR_LOGGING == 1)
				errorMessage("SERVICE", "A NULL CONTRACT OBJECT\n", "INSTANTIATE A CONTRACT OBJECT");
			
			return null;
		}
	}
	
	/**
	 * 	GETSERVICEBYCLIENT()
	 * 
	 *  @param	Client 	- Client to used to retrieve Services
	 *  
	 *  @return	ArrayList<Services>	-	All services referenced by client, null if no ref
	 */	
	
	public ArrayList<Service> getServiceByClient(Client input)
	{
		if(input != null && input.getID() >= 0)
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
		else
		{	
			if(input != null && input.getID() < 0 && ERROR_LOGGING == 1)
				errorMessage("SERVICE", "A CLIENT OBJECT THAT HAS NOT BEEN INSERTED INTO DMBS\n", "INSERT THE CLIENT OBJECT");
			
			if(input == null && ERROR_LOGGING == 1)
				errorMessage("SERVICE", "A NULL CLIENT OBJECT\n", "INSTANTIATE A CLIENT OBJECT");

			return null;
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
		
		if(element != null && feature != null && element.getID() >= 0 && feature.getID() >= 0)
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
			if(element == null && ERROR_LOGGING == 1)
				errorMessage("FEATURE HSTORY", "A NULL TRACKABLE OBJECT\n", "INSTANTIATE A TRACKABLE OBJECT");

			
			if(feature == null && ERROR_LOGGING == 1)
				errorMessage("FEATURE HSTORY", "A NULL TRACKED FEATURE OBJECT\n", "INSTANTIATE A TRCKED FEATURE OBJECT");

			
			if(element != null && element.getID() < 0 && ERROR_LOGGING == 1)
				errorMessage("FEATURE HISTORY", "A TRACKABLE OBJECT THAT HAS NOT BEEN INSERTED INTO DMBS\n", "INSERT THE TRACKABLE OBJECT");

			
			if(feature != null && feature.getID() < 0 && ERROR_LOGGING == 1)
				errorMessage("FEATURE HISTORY", "A TRACKED FEATURE OBJECT THAT HAS NOT BEEN INSERTED INTO DMBS\n", "INSERT THE TRACKED FEATURE OBJECT");

			
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
		
		if(element != null && element.getTableName().compareTo("") != 0 && element.getTableName() != null && element.isInsertable())
		{
			feedback = mainDB.insert(element.getTableName(), element);
		}
		else
		{
			if(ERROR_LOGGING == 1 && element != null && !element.isInsertable())
				System.out.println("THE TARGET ELEMENT IS NOT INSERTABLE\n PLEASE ENSURE MINIMUM SECONDARY KEY VARAIABLES ARE INSTANTIATED");
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
		
		if(element != null && element.getTableName().compareTo("") != 0 && element.getTableName() != null && element.isInsertable())
		{
			output = mainDB.update(element.getTableName(), element);
		}
		else
		{
			if(ERROR_LOGGING == 1 && element != null && !element.isInsertable())
				System.out.println("THE TARGET ELEMENT IS NOT UPDATABLE\n PLEASE ENSURE MINIMUM SECONDARY KEY VARAIABLES ARE INSTANTIATED");
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
			if(ERROR_LOGGING == 1)
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
	 * 	batchMerge()
	 * 
	 *  @param	ArrayList<Storable>	-	Bulk Storable to insert into DBMS
	 *  
	 *  @return	Boolean					If insert completed successfully.
	 */	
	
	public boolean batchMerge(ArrayList<Storable> batch)
	{
		boolean output = true;
		for(int i = 0; i < batch.size() && output; i++)
		{
			if(idExists(batch.get(i)))
				output = update(batch.get(i));
			else
			{
				output = insert(batch.get(i));
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
