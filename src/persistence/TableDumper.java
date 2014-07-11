package persistence;

import java.util.ArrayList;

import objects.Client;
import objects.Contract;
import objects.Expense;
import objects.Service;
import objects.ServiceType;
import objects.Storable;
import objects.TrackedFeature;
import objects.TrackedFeatureType;

public class TableDumper extends IDQueryBuilder
{
	public TableDumper()
	{
		super();
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
}
