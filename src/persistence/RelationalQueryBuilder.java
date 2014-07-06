package persistence;

import java.util.ArrayList;

import objects.Client;
import objects.Contract;
import objects.Expense;
import objects.FeatureHistory;
import objects.Service;
import objects.ServiceType;
import objects.TrackedFeature;
import objects.TrackedFeatureType;

public class RelationalQueryBuilder extends DBInterface
{
	private final int ERROR_LOGGING = 0;
	
	public RelationalQueryBuilder()
	{
		super(DBInterface.DATABASE_NAME);
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
}
