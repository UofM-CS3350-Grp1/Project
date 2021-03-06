package persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;

import objects.Client;
import objects.Expense;
import objects.MonthReport;
import objects.Service;
import objects.Storable;
import objects.Contract;
import objects.TrackedFeature;
import objects.Trackable;
import objects.TrackedFeatureType;
import objects.ServiceType;
import objects.Client.ClientStatus;

public class DBInterface extends AbstractDBInterface
{
	public static final String DATABASE_NAME = "MainDB";
	public static final String CACHE_DATABASE_NAME = "CacheDB";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	protected DBController mainDB;
	private String dbName;
	protected DBParser parser;
	private final int ERROR_LOGGING = 0; //1 to enable 0 to disable.
	protected SimpleDateFormat sdf;
	
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
	 * GETEXPENSESBYSERVICE()
	 * 
	 * @param Service service with associated expenses.
	 * @return - Array list containing the expenses assocated with this service
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
			
			if(service!= null && service.getID() < 0 && ERROR_LOGGING == 1)
				errorMessage("EXPENSE", "AN UNINSTANTIATED (-1) SERVICE ID\n", "INSERT THE SERVICE OBJECT");
			
			return null;
		}
	}
	
	public ArrayList<TrackedFeatureType> getFeatureTypeByClient(Client client)
	{
		ArrayList<TrackedFeatureType> storage = new ArrayList<TrackedFeatureType>();
		ArrayList<TrackedFeature> feats = new ArrayList<TrackedFeature>();
		ArrayList<TrackedFeatureType> ids = new ArrayList<TrackedFeatureType>();
		boolean insert = true;
		
		if(client != null && client.getID() > 0)
		{
			feats = this.getTrackedFeaturesByClient(client);
			
			if(feats != null)
			{
				for(int i = 0; i < feats.size(); i++)
				{
					if(ids.size() == 0)
						ids.add(feats.get(i).getTrackedFeatureType());
					else
					{
						for(int j = 0; j < ids.size() && insert; j++)
						{
							if(feats.get(i).getTrackedFeatureType().getID() == ids.get(j).getID())
								insert  = false;
						}
						if(insert)
								ids.add(feats.get(i).getTrackedFeatureType());
						
						insert = true;
					}
				}
			}
			
			if(ids.size() < 1)
			{
				return null;
			}
			else
			{
				return ids;
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
		
		if(element != null && element.getTableName().compareTo("") != 0 && element.getTableName() != null && element.isInsertable())
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
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		Calendar contractDate = Calendar.getInstance();
		ArrayList<Contract> clientContracts = new ArrayList<Contract>();
		toDate.setTime(startDate);
		fromDate.setTime(startDate);
		toDate.add(Calendar.MONTH, -1);
		
		if(element!= null)
			clientContracts = this.getContractsByBusiness(element.getBusinessName());
		
		if(element != null && element.getID() > -1 && clientContracts != null)
		{
			
			tally = new ArrayList<MonthReport>();
			
			//Find the oldest active contract to use for contract calendar
			contractDate.setTime(clientContracts.get(0).getSignedDate());
			for(int i = 1; i <clientContracts.size(); i++)
			{
				if(contractDate.getTime().before(clientContracts.get(i).getSignedDate()))
					contractDate.setTime(clientContracts.get(i).getSignedDate());
			}
			
			
			for(int i = 0; i < 12 && toDate.getTime().after(contractDate.getTime()); i++)
			{
				sql = "SELECT SUM (RATE)" +
					"FROM " +
					"(SELECT DISTINCT SV.RATE "+
					"FROM "+
					"CLIENTS CL "+
					"INNER JOIN CONTRACTS CON ON(CON.BUSINESS_NAME = CL.BUSINESS_NAME AND CON.END_DATE > '"+sdf.format(toDate.getTime())+"' AND CON.START_DATE <= '"+sdf.format(toDate.getTime())+"')"+
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
					"INNER JOIN CONTRACTS CON ON(CON.BUSINESS_NAME = CL.BUSINESS_NAME AND CON.END_DATE > '"+sdf.format(toDate.getTime())+"' AND CON.START_DATE <= '"+sdf.format(toDate.getTime())+"') "+
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
			sql = "SELECT SUM (VALUE) "+
			"FROM" + 
			"(SELECT DISTINCT CON.VALUE "+
			"FROM "+
			"CLIENTS CL "+ 
			"INNER JOIN CONTRACTS CON ON(CON.BUSINESS_NAME = CL.BUSINESS_NAME) "+
			"WHERE "+
			"CL.ROW_ID = "+element.getID()+" AND CON.STATUS != 'Pending') ";
			
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
					"INNER JOIN CONTRACTS CON ON(CON.BUSINESS_NAME = CL.BUSINESS_NAME AND CON.END_DATE > '"+sdf.format(toDate.getTime())+"' AND CON.START_DATE <= '"+sdf.format(toDate.getTime())+"') "+
					"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID) "+
					"INNER JOIN EXPENSE EX ON(EX.SERVICE_ID = SV.ROW_ID AND INCURRED_DATE > '"+sdf.format(toDate.getTime())+"' AND INCURRED_DATE <= '"+sdf.format(new Date())+"') "+
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
	
	
	/** 
	 * @param element Service of Interest
	 * @return Ex3penses + revenue for services for month
	 */
	
	public ArrayList<MonthReport> getLastYearServiceExpenses(ServiceType element)
	{
		ArrayList<MonthReport> tally = null;
		double expenseValue = 0;
		String sql = "";
		ArrayList<String> returnVal = new ArrayList<String>();
		Date startDate = new Date();
		Date endDate = new Date();
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		Calendar contractDate = Calendar.getInstance();
		Contract clientContract = null;
		toDate.setTime(startDate);
		fromDate.setTime(startDate);
		toDate.add(Calendar.MONTH, -1);
		
		if(element != null && element.getID() > -1)
		{
			
			tally = new ArrayList<MonthReport>();
			
			//Find the oldest active contract to use for contract calendar
			sql = "SELECT MIN(CON.SIGNED_DATE) "+
			"FROM "+
			"CONTRACTS CON "+
			"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID) "+
			"INNER JOIN SERVICES_TYPES ST ON (ST.ROW_ID = SV.SERVICE_TYPE_ID) "+
			"WHERE "+
			"ST.ROW_ID ="+ element.getID();
			
			returnVal = this.mainDB.blindQuery(sql);
			
			if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
			{
				try
				{
					endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(returnVal.get(0));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else
				endDate  = null;
						
			if(endDate != null)
			{
				contractDate.setTime(endDate);
				
				for(int i = 0; i < 12 && toDate.getTime().after(contractDate.getTime()); i++)
				{
					sql = "SELECT SUM(EX.VALUE) "+
					"FROM "+
					"CONTRACTS CON "+  
					"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID AND CON.END_DATE > '"+sdf.format(toDate.getTime())+"' AND CON.START_DATE <= '"+sdf.format(toDate.getTime())+"' ) "+
					"INNER JOIN SERVICES_TYPES ST ON (ST.ROW_ID = SV.SERVICE_TYPE_ID) "+
					"INNER JOIN EXPENSE EX ON(EX.SERVICE_ID = SV.ROW_ID AND INCURRED_DATE > '"+sdf.format(toDate.getTime())+"' AND INCURRED_DATE <= '"+sdf.format(fromDate.getTime())+"') "+
					"WHERE "+
					"ST.ROW_ID = "+element.getID();
				
					
					returnVal = this.mainDB.blindQuery(sql);
					
					if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
						expenseValue = Double.parseDouble(returnVal.get(0));
					else
						expenseValue = 0;	
				
					toDate.add(Calendar.MONTH, -1);
					fromDate.add(Calendar.MONTH, -1);
					
					tally.add(new MonthReport(fromDate.getTime(), (expenseValue)));
					expenseValue = 0;
				}
			}
		}
		
		return tally;
	}
	
	/** 
	 * @param element Service of Interest
	 * @return Expenses + revenue for services for month
	 */
	
	public ArrayList<MonthReport> getLastYearServiceRevenue(ServiceType element)
	{
		ArrayList<MonthReport> tally = null;
		double expenseValue = 0;
		String sql = "";
		double divisor = 1;
		ArrayList<String> returnVal = new ArrayList<String>();
		Date startDate = new Date();
		Date endDate = new Date();
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		Calendar contractDate = Calendar.getInstance();
		Contract clientContract = null;
		toDate.setTime(startDate);
		fromDate.setTime(startDate);
		toDate.add(Calendar.MONTH, -1);
		
		if(element != null && element.getID() > -1)
		{
			
			tally = new ArrayList<MonthReport>();
			
			sql = "SELECT MIN(CON.SIGNED_DATE) "+
				"FROM "+
				"CONTRACTS CON "+
				"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID) "+
				"INNER JOIN SERVICES_TYPES ST ON (ST.ROW_ID = SV.SERVICE_TYPE_ID) "+
				"WHERE "+
				"ST.ROW_ID ="+ element.getID();
				
			returnVal = this.mainDB.blindQuery(sql);
					
			if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
			{
				try
				{
					endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(returnVal.get(0));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else
				endDate  = null;
				
			if(element.getID() == 2)
				divisor = 12;
					
			if(endDate != null)
			{
				contractDate.setTime(endDate);
				
				for(int i = 0; i < 12 && toDate.getTime().after(contractDate.getTime()); i++)
				{
					sql = "SELECT SUM (RATE) "+
					"FROM" + 
					"(SELECT SV.RATE "+
					"FROM "+
					"CONTRACTS CON "+ 
					"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID AND CON.END_DATE > '"+sdf.format(toDate.getTime())+"' AND CON.START_DATE <= '"+sdf.format(toDate.getTime())+"' ) "+
					"INNER JOIN SERVICES_TYPES ST ON (ST.ROW_ID = SV.SERVICE_TYPE_ID) "+
					"WHERE "+
					"ST.ROW_ID = "+element.getID()+")";
					
					returnVal = this.mainDB.blindQuery(sql);
					
					if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
						expenseValue = (Double.parseDouble(returnVal.get(0)) / divisor);
					else
						expenseValue = 0;	
				
					toDate.add(Calendar.MONTH, -1);
					fromDate.add(Calendar.MONTH, -1);
					
					tally.add(new MonthReport(fromDate.getTime(), (expenseValue)));
					expenseValue = 0;
				}
			}
		}
		
		return tally;
	}
	
	/** 
	 * @param element Service of Interest
	 * @return Expenses + revenue for services for month
	 */
	
	public ArrayList<MonthReport> getLastYearClientFeaturesByType(Client client, TrackedFeatureType feat)
	{
		ArrayList<MonthReport> tally = null;
		double expenseValue = 0;
		String sql = "";
		double divisor = 1;
		ArrayList<String> returnVal = new ArrayList<String>();
		Date startDate = new Date();
		Date endDate = new Date();
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		Calendar contractDate = Calendar.getInstance();
		Contract clientContract = null;
		toDate.setTime(startDate);
		fromDate.setTime(startDate);
		toDate.add(Calendar.MONTH, -1);
		
		if(client != null && client.getID() > -1 && feat != null && feat.getID() > -1)
		{
			
			tally = new ArrayList<MonthReport>();
			
			sql = "SELECT MIN(FE.DATE_RECCORDED) "+
				"FROM "+
				"CLIENTS CL "+
				"INNER JOIN FEATURE FE ON (FE.CLIENT_ID = CL.ROW_ID) "+
				"INNER JOIN FEATURE_TYPES FT ON (FT.ROW_ID = FE.FEATURE_TYPE_ID) "+
				"WHERE "+
				"CL.ROW_ID = "+client.getID()+" AND FT.ROW_ID = "+feat.getID();
				
			returnVal = this.mainDB.blindQuery(sql);
					
			if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
			{
				try
				{
					endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(returnVal.get(0));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else
				endDate = null;
				
			contractDate.setTime(endDate);
			contractDate.add(Calendar.MONTH, -1);
			contractDate.setTime(contractDate.getTime());
			Date testTime = contractDate.getTime();
			
			if(endDate!= null)
			{
				for(int i = 0; i <12 &&toDate.getTime().after(contractDate.getTime()); i++)
				{
					sql = "SELECT SUM(FE.VALUE) "+
					"FROM "+
					"CLIENTS CL "+ 
					"INNER JOIN FEATURE FE ON (FE.CLIENT_ID = CL.ROW_ID AND FE.DATE_RECCORDED > '"+sdf.format(toDate.getTime())+"' AND FE.DATE_RECCORDED <= '"+sdf.format(fromDate.getTime())+"' ) "+
					"INNER JOIN FEATURE_TYPES FT ON (FE.FEATURE_TYPE_ID = FT.ROW_ID) "+
					"WHERE "+
					"CL.ROW_ID = "+client.getID()+" AND FT.ROW_ID = "+feat.getID();
					
					returnVal = this.mainDB.blindQuery(sql);
					
					if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
						expenseValue = (Double.parseDouble(returnVal.get(0)));
					else
						expenseValue = 0;	
				
					toDate.add(Calendar.MONTH, -1);
					fromDate.add(Calendar.MONTH, -1);
					
					tally.add(new MonthReport(fromDate.getTime(), (expenseValue)));
					expenseValue = 0;
				}
			}
		}
		return tally;
	}
	
	/** 
	 * @param element Service of Interest
	 * @return Expenses + revenue for services for month
	 */
	
	public ArrayList<MonthReport> getSumFeatures(Client client, TrackedFeatureType feat)
	{
		ArrayList<MonthReport> tally = null;
		double expenseValue = 0;
		String sql = "";
		double divisor = 1;
		ArrayList<String> returnVal = new ArrayList<String>();
		Date startDate = new Date();
		Date endDate = new Date();
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		Calendar contractDate = Calendar.getInstance();
		Contract clientContract = null;
		toDate.setTime(startDate);
		fromDate.setTime(startDate);
		toDate.add(Calendar.MONTH, -1);
		
		if(client != null && client.getID() > -1 && feat != null && feat.getID() > -1)
		{
			
			tally = new ArrayList<MonthReport>();
			
			sql = "SELECT MIN(FE.DATE_RECCORDED) "+
				"FROM "+
				"CLIENTS CL "+
				"INNER JOIN FEATURE FE ON (FE.CLIENT_ID = CL.ROW_ID) "+
				"INNER JOIN FEATURE_TYPES FT ON (FT.ROW_ID = FE.FEATURE_TYPE_ID) "+
				"WHERE "+
				"CL.ROW_ID = "+client.getID()+" AND FT.ROW_ID = "+feat.getID();
				
			returnVal = this.mainDB.blindQuery(sql);
					
			if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
			{
				try
				{
					endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(returnVal.get(0));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else
				endDate = null;
				
			contractDate.setTime(endDate);
			contractDate.add(Calendar.MONTH, -1);
			contractDate.setTime(contractDate.getTime());
			if(endDate != null)
			{
				while(toDate.getTime().after(contractDate.getTime()))
				{
					sql = "SELECT SUM(FE.VALUE) "+
					"FROM "+
					"CLIENTS CL "+ 
					"INNER JOIN FEATURE FE ON (FE.CLIENT_ID = CL.ROW_ID AND FE.DATE_RECCORDED > '"+sdf.format(toDate.getTime())+"' AND FE.DATE_RECCORDED <= '"+sdf.format(fromDate.getTime())+"' ) "+
					"INNER JOIN FEATURE_TYPES FT ON (FE.FEATURE_TYPE_ID = FT.ROW_ID) "+
					"WHERE "+
					"CL.ROW_ID = "+client.getID()+" AND FT.ROW_ID = "+feat.getID();
					
					returnVal = this.mainDB.blindQuery(sql);
					
					if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
						expenseValue = (Double.parseDouble(returnVal.get(0)));
					else
						expenseValue = 0;	
				
					toDate.add(Calendar.MONTH, -1);
					fromDate.add(Calendar.MONTH, -1);
					
					tally.add(new MonthReport(fromDate.getTime(), (expenseValue)));
					expenseValue = 0;
				}
			}
		}
		return tally;
	}
	
	public double getTotalAllFeatures(Client client, TrackedFeatureType type)
	{
		double output = 0;
		ArrayList<MonthReport> summer = new ArrayList<MonthReport>();
		summer = getSumFeatures(client, type);
		
		if(summer != null)
		{
			for(int i = 0 ; i < summer.size(); i++)
			{
				output += summer.get(i).getValue();
			}
		}
		return output;
	}
	
	/** GET ALLCLIENTRETURNS()
	 * 
	 * Returns the sum of all possible returns on a client.
	 */
	
	public double getAllClientReturns(Client element)
	{
		double output = 0;
		double serviceValue = 0;
		double expenseValue = 0;
		String sql = "";
		ArrayList<String> returnVal = new ArrayList<String>();
		Date startDate = new Date();
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		Calendar contractDate = Calendar.getInstance();
		ArrayList<Contract> clientContracts = new ArrayList<Contract>();
		toDate.setTime(startDate);
		fromDate.setTime(startDate);
		toDate.add(Calendar.MONTH, -1);
		
		if(element!= null)
			clientContracts = this.getContractsByBusiness(element.getBusinessName());
		
		if(element != null && element.getID() > -1 && clientContracts != null)
		{
			//Find the oldest active contract to use for contract calendar
			
			contractDate.setTime(clientContracts.get(0).getSignedDate());
			
			for(int i = 1; i <clientContracts.size(); i++)
			{
				if(contractDate.getTime().before(clientContracts.get(i).getSignedDate()))
					contractDate.setTime(clientContracts.get(i).getSignedDate());
			}
					
			while(toDate.getTime().after(contractDate.getTime()))
			{
				sql = "SELECT SUM (RATE)" +
					"FROM " +
					"(SELECT DISTINCT SV.RATE "+
					"FROM "+
					"CLIENTS CL "+
					"INNER JOIN CONTRACTS CON ON(CON.BUSINESS_NAME = CL.BUSINESS_NAME AND CON.END_DATE > '"+sdf.format(toDate.getTime())+"' AND CON.START_DATE <= '"+sdf.format(toDate.getTime())+"')"+
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
					"INNER JOIN CONTRACTS CON ON(CON.BUSINESS_NAME = CL.BUSINESS_NAME AND CON.END_DATE > '"+sdf.format(toDate.getTime())+"' AND CON.START_DATE <= '"+sdf.format(toDate.getTime())+"') "+
					"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID) "+
					"INNER JOIN EXPENSE EX ON(EX.SERVICE_ID = SV.ROW_ID AND INCURRED_DATE > '"+sdf.format(toDate.getTime())+"' AND INCURRED_DATE <= '"+sdf.format(fromDate.getTime())+"') "+
					"WHERE "+
					"CL.ROW_ID = " +element.getID();
				
				returnVal = this.mainDB.blindQuery(sql);
				
				if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
					expenseValue = Double.parseDouble(returnVal.get(0));
				else
					expenseValue = 0;
				
				toDate.add(Calendar.MONTH, -1);
				fromDate.add(Calendar.MONTH, -1);
				
				output = output+(serviceValue-expenseValue);
				expenseValue = 0;
				serviceValue = 0;
			}
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
		
		String protectedName = name;
		String protectedPass = login;
		if(name != null && login != null && !name.isEmpty() && !login.isEmpty())
		{
			protectedName = protectedName.replaceAll( "'", "''" );
			protectedPass = protectedPass.replaceAll( "'", "''" );
			sql = "SELECT COUNT(*) FROM USERS WHERE NAME = '"+protectedName+"' AND PASSWORD = '"+protectedPass+"'";
			
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
