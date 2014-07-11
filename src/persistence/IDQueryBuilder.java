package persistence;

import java.util.ArrayList;

import objects.Client;
import objects.Contract;
import objects.Expense;
import objects.Service;
import objects.ServiceType;
import objects.TrackedFeature;
import objects.TrackedFeatureType;

public class IDQueryBuilder extends NewDBInterface 
{
	protected NewDBParser parser;
	public IDQueryBuilder()
	{
		super(NewDBInterface.DATABASE_NAME);
		this.parser = new NewDBParser(this);
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
		this.connect();
		
		System.out.println("In IDQUERYBUILDER");
		
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
			
			this.disconnect();
			
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
			this.disconnect();
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
}
