package persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import objects.Client;
import objects.Expense;
import objects.MonthReport;
import objects.Service;
import objects.Storable;
import objects.Contract;
import objects.TrackedFeature;
import objects.FeatureHistory;
import objects.Trackable;
import objects.TrackedFeatureType;
import objects.ServiceType;
import objects.Client.ClientStatus;

public class DBInterface 
{
	public static final String DATABASE_NAME = "CacheDB";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	protected DBController mainDB;
	private String dbName;
	protected DBParser parser;
	private final int ERROR_LOGGING = 0; //1 to enable 0 to disable.
	private SimpleDateFormat sdf;
	
	public DBInterface(String dbName)
	{
		this.sdf = new SimpleDateFormat(DATE_FORMAT);
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
	 * 	GETTRACKEDFEATURETYPEBYID()
	 * 
	 *  @param	int id	-	ID to search for
	 *  
	 *  @return	TrackedFeatureType	-	TrackedFeatureType specified by ID, null if no match
	 */	
	
	public TrackedFeatureType getTrackedFeatureTypeByID(int id)
	{
		ArrayList<TrackedFeatureType> storage = new ArrayList<TrackedFeatureType>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		if(id >= 0)
		{
			conditions.add("ROW_ID");
			conditions.add("= ");
			conditions.add(""+id+"");
			
			clauses.add(conditions);
			
			returnValue  = this.mainDB.query("FEATURE_TYPES", clauses);
			
			storage = parser.parseFeatureTypes(returnValue);
			
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
	 * 	GETEXPENSEBYID()
	 * 
	 *  @param	int id	-	ID to search for
	 *  
	 *  @return	Expense	-	Expense specified by ID, null if no match
	 */	
	
	public Expense getExpenseByID(int id)
	{
		ArrayList<Expense> storage = new ArrayList<Expense>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		if(id >= 0)
		{
			conditions.add("ROW_ID");
			conditions.add("= ");
			conditions.add(""+id+"");
			
			clauses.add(conditions);
			
			returnValue  = this.mainDB.query("EXPENSE", clauses);
			
			storage = parser.parseExpenses(returnValue);
			
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
	 * 	GETSERVICETYPEBYID()
	 * 
	 *  @param	int id	-	ID to search for
	 *  
	 *  @return	ServiceType	-	ServiceType specified by ID, null if no match
	 */	
	
	public ServiceType getServiceTypeByID(int id)
	{
		ArrayList<ServiceType> storage = new ArrayList<ServiceType>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		if(id >= 0)
		{
			conditions.add("ROW_ID");
			conditions.add("= ");
			conditions.add(""+id+"");
			
			clauses.add(conditions);
			
			returnValue  = this.mainDB.query("SERVICES_TYPES", clauses);
			
			storage = parser.parseServiceTypes(returnValue);
			
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
	 * 	GETSERVICETYPESBYTYPE()
	 * 
	 *  @param	int id	-	String type to search for
	 *  
	 *  @return	ArrayList<ServiceType>	-	ArrayList<ServiceType> specified by name, null if no match
	 */	
	
	public ArrayList<ServiceType> getServiceTypesByType(String type)
	{
		ArrayList<ServiceType> storage = new ArrayList<ServiceType>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		if(type != null && !type.isEmpty())
		{
			conditions.add("SERVICE_TYPE");
			conditions.add("= ");
			conditions.add("'"+type+"'");
			
			clauses.add(conditions);
			
			returnValue  = this.mainDB.query("SERVICES_TYPES", clauses);
			
			storage = parser.parseServiceTypes(returnValue);
			
			if(storage.size() < 1)
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
	 * 	GETTRACKEDFEATURETYPESBYTITLE()
	 * 
	 *  @param	int id	-	String title to search for
	 *  
	 *  @return	TrackedFeatureType	-	TrackedFeatureType specified by name, null if no match
	 */	
	
	public ArrayList<TrackedFeatureType> getTrackedFeatureTypesByTitle(String title)
	{
		ArrayList<TrackedFeatureType> storage = new ArrayList<TrackedFeatureType>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		if(title != null && !title.isEmpty())
		{
			conditions.add("TITLE");
			conditions.add("= ");
			conditions.add("'"+title+"'");
			
			clauses.add(conditions);
			
			returnValue  = this.mainDB.query("FEATURE_TYPES", clauses);
			
			storage = parser.parseFeatureTypes(returnValue);
			
			if(storage.size() < 1)
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
	 * 	GETTRACKEDFEATUREBYSERVICE()
	 * 
	 *  @param	int id	-	Service to search for
	 *  
	 *  @return	TrackedFeature	-	TrackedFeature specified by Service, null if no match
	 */	
	
	public ArrayList<TrackedFeature> getTrackedFeaturesByClient(Client input)
	{
		if(input != null && input.getID() >= 0)
		{
			ArrayList<TrackedFeature> storage = new ArrayList<TrackedFeature>();
			ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
			ArrayList<String> conditions = new ArrayList<String>();
			ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
			
			conditions.add("CLIENT_ID");
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
				errorMessage("TRACKED FEATURE", "A SERVICE OBJECT THAT HAS NOT BEEN INSERTED INTO DMBS\n", "INSERT THE SERVICE OBJECT");
			
			if(input == null && ERROR_LOGGING == 1)
				errorMessage("TRACKED FEATURE", "A NULL SERVICE OBJECT\n", "INSTANTIATE A SERVICE OBJECT");

			return null;
		}
	}
	

	
	/**
	 * 	GETCLIENTBYFEATURE()
	 * 
	 *  @param	Feature 	- Feature to use to retrieve Service
	 *  
	 *  @return	Service	-	Service referenced by feature, null if no ref
	 */	
	
	public Client getClientByFeature(TrackedFeature input)
	{
		if(input != null && input.getID() >= 0)
		{
			ArrayList<Client> storage = new ArrayList<Client>();
			ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
			ArrayList<String> conditions = new ArrayList<String>();
			ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
			
			conditions.add("ROW_ID");
			conditions.add("= ");
			conditions.add("'"+input.getClientKey()+"'");
			
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
	 * 	GETSERVICESBYTYPE()
	 * 
	 *  @param	ServiceType 	- ServiceType to used to retrieve Services
	 *  
	 *  @return	ArrayList<Services>	-	All services referenced by ServiceType, null if no ref
	 */	
	
	public ArrayList<Service> getServicesByType(ServiceType input)
	{
		if(input != null && input.getID() >= 0)
		{
			ArrayList<Service> storage = new ArrayList<Service>();
			ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
			ArrayList<String> conditions = new ArrayList<String>();
			ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
			
			conditions.add("SERVICE_TYPE_ID");
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
				errorMessage("SERVICE", "A SERVICE TYPE OBJECT THAT HAS NOT BEEN INSERTED INTO DMBS\n", "INSERT THE SERVICE TYPE OBJECT");
			
			if(input == null && ERROR_LOGGING == 1)
				errorMessage("SERVICE", "A NULL SERVICE TYPE OBJECT\n", "INSTANTIATE A SERVICE TYPE OBJECT");

			return null;
		}
	}

	
	
	/**
	 * GETFEATUREHISTORYFROMFEATURE()
	 * 
	 * @param element Object with that can handle trackable features.
	 * @return - Array list containing the tracked features history items associated with this object otherise null
	 */
	
	public ArrayList<FeatureHistory> getFeatureHistoryByFeature(TrackedFeature feature)
	{
		ArrayList<FeatureHistory> storage = new ArrayList<FeatureHistory>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions1 = new ArrayList<String>();
		ArrayList<String> conditions2 = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		if(feature != null && feature.getID() >= 0)
		{
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
			if(feature == null && ERROR_LOGGING == 1)
				errorMessage("FEATURE HSTORY", "A NULL TRACKED FEATURE OBJECT\n", "INSTANTIATE A TRCKED FEATURE OBJECT");	
			
			if(feature != null && feature.getID() < 0 && ERROR_LOGGING == 1)
				errorMessage("FEATURE HISTORY", "A TRACKED FEATURE OBJECT THAT HAS NOT BEEN INSERTED INTO DMBS\n", "INSERT THE TRACKED FEATURE OBJECT");

			
			return null;
		}
		
	}
	
	/**
	 * GETFEATUREHISTORYFROMFEATURE()
	 * 
	 * @param element Object with that can handle trackable features.
	 * @return - Array list containing the tracked features history items associated with this object otherise null
	 */
	
	public ArrayList<Expense> getExpensesByService(Service service)
	{
		ArrayList<Expense> storage = new ArrayList<Expense>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions1 = new ArrayList<String>();
		ArrayList<String> conditions2 = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		if(service != null && service.getID() >= 0)
		{
			conditions2.add(" SERVICE_ID");
			conditions2.add("= ");
			conditions2.add("'"+service.getID()+"'");
			clauses.add(conditions2);
			returnValue = this.mainDB.query("EXPENSE",clauses);
			
			storage = parser.parseExpenses(returnValue);
			
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
			if(service == null && ERROR_LOGGING == 1)
				errorMessage("EXPENSE", "A NULL SERVICE OBJECT\n", "INSTANTIATE A SERVICE OBJECT");
			
			if(service.getID() >= 0 && ERROR_LOGGING == 1)
				errorMessage("EXPENSE", "AN UNINSTANTIATED (-1) SERVICE ID\n", "INSERT THE SERVICE OBJECT");
			
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
	
	
	/** GETLASTYEARRETURNS()
	 * 
	 * Returns up to the last 12 months worth of monthly reports.
	 */
	
	public ArrayList<MonthReport> getLastYearReturns(Client element)
	{
		ArrayList<MonthReport> tally = null;
		double serviceValue = 0;
		double expenseValue = 0;
		String sql = "";
		ArrayList<String> returnVal = new ArrayList<String>();
		Date startDate = new Date();
		Date endDate = new Date();
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		Calendar contractDate = Calendar.getInstance();
		ArrayList<Contract> clientContracts = new ArrayList<Contract>();
		toDate.setTime(startDate);
		fromDate.setTime(startDate);
		toDate.add(Calendar.MONTH, -1);
		
		if(element != null && element.getID() > -1)
		{
			
			tally = new ArrayList<MonthReport>();
			
			//Find the oldest active contract to use for contract calendar
			clientContracts = this.getContractsByBusiness(element.getBusinessName());
			contractDate.setTime(clientContracts.get(0).getPeriod());
			
			for(int i = 1; i <clientContracts.size(); i++)
			{
				if(contractDate.before(clientContracts.get(i).getPeriod()))
					contractDate.setTime(clientContracts.get(i).getPeriod());
			}
			
			
			for(int i = 0; i < 12 && !toDate.after(contractDate.getTime()); i++)
			{
				sql = "SELECT SUM (RATE)" +
					"FROM " +
					"(SELECT DISTINCT SV.RATE "+
					"FROM "+
					"CLIENTS CL "+
					"INNER JOIN CONTRACTS CON ON(CON.BUSINESS_NAME = CL.BUSINESS_NAME AND CON.END_DATE > '"+sdf.format(toDate.getTime())+"' AND CON.START_DATE < '"+sdf.format(toDate.getTime())+"')"+
					"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID)"+
					"INNER JOIN SERVICES_TYPES ST ON(SV.SERVICE_TYPE_ID = ST.ROW_ID AND ST.SERVICE_TYPE != 'Web Design') "+
					"WHERE "+
					"CL.ROW_ID ="+element.getID()+")";
					
				returnVal = this.mainDB.blindQuery(sql);
					
				if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
					serviceValue = Double.parseDouble(returnVal.get(0));
				else
					serviceValue = 0;
				
				sql ="SELECT SUM(EX.VALUE) "+
					"FROM "+
					"CLIENTS CL "+ 
					"INNER JOIN CONTRACTS CON ON(CON.BUSINESS_NAME = CL.BUSINESS_NAME AND CON.END_DATE > '"+sdf.format(toDate.getTime())+"' AND CON.START_DATE < '"+sdf.format(toDate.getTime())+"') "+
					"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID) "+
					"INNER JOIN EXPENSE EX ON(EX.SERVICE_ID = SV.ROW_ID AND INCURRED_DATE > '"+sdf.format(toDate.getTime())+"' AND INCURRED_DATE < '"+sdf.format(fromDate.getTime())+"') "+
					"WHERE "+
					"CL.ROW_ID = " +element.getID();
				
				returnVal = this.mainDB.blindQuery(sql);
				
				if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
					expenseValue = Double.parseDouble(returnVal.get(0));
				else
					expenseValue = 0;
				
				toDate.add(Calendar.MONTH, -1);
				fromDate.add(Calendar.MONTH, -1);
				
				tally.add(new MonthReport(fromDate.getTime(), (serviceValue-expenseValue)));
				expenseValue = 0;
				serviceValue = 0;
			}
		}
		
		return tally;
		
	}
	
	/**
	 * @param element client to look into
	 * @return current revenue from services to client
	 */
	
	public double getClientCurrentRevenue(Client element)
	{
		double output = -1;
		String sql = "";
		ArrayList<String> returnVal = new ArrayList<String>();
		
		if(element != null && element.getID() > -1)
		{
			sql = "SELECT SUM (RATE) "+
			"FROM" + 
			"(SELECT DISTINCT SV.RATE "+
			"FROM "+
			"CLIENTS CL "+ 
			"INNER JOIN CONTRACTS CON ON(CON.BUSINESS_NAME = CL.BUSINESS_NAME AND CON.END_DATE > '"+sdf.format(new Date())+"' AND CON.START_DATE < '"+sdf.format(new Date())+"') "+
			"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID) "+
			"INNER JOIN SERVICES_TYPES ST ON(SV.SERVICE_TYPE_ID = ST.ROW_ID AND ST.SERVICE_TYPE != 'Web Design') " +
			"WHERE "+
			"CL.ROW_ID = "+element.getID()+") ";
			
			returnVal = this.mainDB.blindQuery(sql);
			
			if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
				output = Double.parseDouble(returnVal.get(0));
			else
				output = 0;
		}
		
		return output;
	}
	
	
	/**
	 * @param element client to look into
	 * @return current cost of expenses
	 */
	
	public double getClientCurrentExpenses(Client element)
	{
		double output = -1;
		String sql = "";
		ArrayList<String> returnVal = new ArrayList<String>();
		Calendar toDate = Calendar.getInstance();
		toDate.setTime(new Date());
		toDate.add(Calendar.MONTH, -1);
		
		if(element != null && element.getID() > -1)
		{
			sql ="SELECT SUM(EX.VALUE) "+
					"FROM "+
					"CLIENTS CL "+ 
					"INNER JOIN CONTRACTS CON ON(CON.BUSINESS_NAME = CL.BUSINESS_NAME AND CON.END_DATE > '"+sdf.format(toDate.getTime())+"' AND CON.START_DATE < '"+sdf.format(toDate.getTime())+"') "+
					"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID) "+
					"INNER JOIN EXPENSE EX ON(EX.SERVICE_ID = SV.ROW_ID AND INCURRED_DATE > '"+sdf.format(toDate.getTime())+"' AND INCURRED_DATE < '"+sdf.format(new Date())+"') "+
					"WHERE "+
					"CL.ROW_ID = " +element.getID();
			
			returnVal = this.mainDB.blindQuery(sql);
			
			if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
			{
				output = Double.parseDouble(returnVal.get(0));
				output = -output;
			}
			else
				output = 0;
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
	 * Returns all tracked feature histries on the DBMS;
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
	
	/**DUMPTRACKEDFEATURETYPES()
	 * 
	 * Returns all tracked feature types on the DBMS;
	 * 
	 */
	
	public ArrayList<TrackedFeatureType> dumpTrackedFeatureTypes()
	{
		ArrayList<TrackedFeatureType> storage = new ArrayList<TrackedFeatureType>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		conditions.add("ALL");
		
		clauses.add(conditions);
		
		returnValue  = this.mainDB.query("FEATURE_TYPES", clauses);
		
		storage = parser.parseFeatureTypes(returnValue);
		
		if(storage.size() == 0)
		{
			return null;
		}
		else
		{
			return storage;
		}
	}
	
	/**DUMPSERVICETYPES()
	 * 
	 * Returns all service types on the DBMS;
	 * 
	 */
	
	public ArrayList<ServiceType> dumpServiceTypes()
	{
		ArrayList<ServiceType> storage = new ArrayList<ServiceType>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		conditions.add("ALL");
		
		clauses.add(conditions);
		
		returnValue  = this.mainDB.query("SERVICES_TYPES", clauses);
		
		storage = parser.parseServiceTypes(returnValue);
		
		if(storage.size() == 0)
		{
			return null;
		}
		else
		{
			return storage;
		}
	}
	
	/**DUMPEXPENSES()
	 * 
	 * Returns all service types on the DBMS;
	 * 
	 */
	
	public ArrayList<Expense> dumpExpenses()
	{
		ArrayList<Expense> storage = new ArrayList<Expense>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		ArrayList<String> conditions = new ArrayList<String>();
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		
		conditions.add("ALL");
		
		clauses.add(conditions);
		
		returnValue  = this.mainDB.query("EXPENSE", clauses);
		
		storage = parser.parseExpenses(returnValue);
		
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
	
	public boolean userLogin(String name, String login)
	{
		boolean output = false;
		String sql = "";
		ArrayList<String> returnVal = new ArrayList<String>();
		
		if(name != null && login != null && !name.isEmpty() && !login.isEmpty())
		{
			sql = "SELECT COUNT(*) FROM USERS WHERE NAME = '"+name+"' AND PASSWORD = '"+login+"'";
			
			returnVal = this.mainDB.blindQuery(sql);
			
			if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0  && Integer.parseInt(returnVal.get(0)) == 1)
				output = true;
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
