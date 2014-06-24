package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import objects.Client;
import objects.Contract;
import objects.Service;

/**
 * Performs the contract related processing between the GUI
 * component and the Database
 * Same format as ProcessClient.java for consistency
 */
public class ProcessContract extends ProcessStorable
{	
	private ArrayList<Contract> contracts;
	
	/**
	 * Creates a contract accessor used to create, edit, delete information from contracts
	 */
	public ProcessContract()
	{
		super();
		//database = new DBInterface("dbName");
		contracts = null;
	}
	
	/**
	 * Inserts a new contract to the database
	 * @param contract
	 * @return True if the contract was inserted successfully
	 */
	public boolean insertContract(Contract contract)
	{
		boolean wasCreated = insert(contract);
		/*
		assert (contract != null);
		if(contract != null)
		{
			database.insert(contract);
			wasCreated = true;
		}*/
		
		return wasCreated;
	}
	
	/*
	 * Returns the contract id
	 */
	public Contract getContractByID(int id)
	{
		database.connect();
		return database.getContractByID(id);
	}
	
	/*
	 * returns the client that signed this contract
	 */
	public Client getContractClient(Contract contract)
	{
		database.connect();
		ArrayList<Client> list = database.dumpClients();
		database.disconnect();
		Iterator it = list.iterator();
		Client result = null;
		Client temp = null;
		while(it.hasNext())
		{
			temp = (Client) it.next();
			if(temp.getID()==contract.getID())
			{
				result = temp;
			}
		}
		return result;
	}

	/**
	 * Updates a contract
	 * @param contract
	 * @return True if the contract was updated
	 */
	public boolean updateContract(Contract contract)
	{
		boolean wasUpdated = update(contract);
		/*
		assert (contract != null);
		if(contract != null)
		{
			database.update(contract);
			wasUpdated = true;
		}*/
		
		return wasUpdated;
	}

	/**
	 * Deletes a contract
	 * @param contract
	 * @return True if the contract was deleted
	 */
	public boolean deleteContract(Contract contract)
	{
		boolean wasDeleted = false;
		
		assert (contract != null);
		if(contract != null)
		{
			database.connect();
			database.drop(contract);
			database.disconnect();
			wasDeleted = true;
		}
		
		return wasDeleted;
	}

	/**
	 * Gets the next contract in the database
	 * @return The next contract or null if we have reached the end of the list
	 */
	public ArrayList<Contract> getContracts()
	{
		database.connect();
		contracts = database.dumpContracts();
		database.disconnect();
		return contracts;
	}

	/**
	 * Gets the list of services associated with this contract
	 * @return The list of services in the contract
	 */
	public ArrayList<Service> getServices(Contract contract)
	{
		ArrayList<Service> result = null;
		result = contract.getServices();
		return result;
	}
	
	/*
	 * Add services to this contract
	 */
	public void setServices(Contract contract, ArrayList<Service> services)
	{
		contract.addServices(services);
		database.connect();
		database.update(contract);
		database.disconnect();
	}
	
	/*
	 * returns the number of services in this contract
	 */
	public int getNumberOfServices(Contract contract)
	{
		int result = 0;
		ArrayList<Service> services = null;
		database.connect();
		database.disconnect();
		services = contract.getServices();
		
		result = services.size();
		
		/*
		ArrayList<Service> services = null;
		database.connect();
		services = database.dumpServices();
		database.disconnect();
		
		Iterator it = services.iterator();
		Service temp = null;
		
		while(it.hasNext())
		{
			temp = (Service) it.next();
			if(contract.getServices().)
			{
				result++;
			}
		}*/
		
		return result;
	}
	
	/**
	 * @return the dollar amount of all contracts collectively
	 */
	public double getTotalContractsValue()
	{
		double result = 0;
		database.connect();
		contracts = database.dumpContracts();
		database.disconnect();
		Contract temp = null;
		Iterator<Contract> it = contracts.iterator();
		
		while(it.hasNext())
		{
			temp = (Contract) it.next();
			result += temp.getValue();
		}
		return result;
	}
	
	/**
	 * @param date range
	 * @return the amount of contracts signed within a date range
	 */
	@SuppressWarnings("deprecation")
	public int getNumContractsBetween(Date start, Date end)
	{
		int result = 0;
		database.connect();
		contracts = database.dumpContracts();
		database.disconnect();
		Contract temp = null;
		Iterator<Contract> it = contracts.iterator();
		
		while(it.hasNext())
		{
			temp = (Contract) it.next();
			if(temp.getSignedDate().getSeconds()>=start.getSeconds() && temp.getSignedDate().getSeconds()<=end.getSeconds())
			{
				result++;
			}
		}
		return result;
	}
	
	/**
	 * @param date range
	 * @return the double dollar value of contracts signed between 2 dates
	 */
	@SuppressWarnings("deprecation")
	public double getValueOfContractsBetween(Date start, Date end)
	{
		double result = 0;
		database.connect();
		contracts = database.dumpContracts();
		database.disconnect();
		Contract temp = null;
		Iterator<Contract> it = contracts.iterator();
		
		while(it.hasNext())
		{
			temp = (Contract) it.next();
			if(temp.getSignedDate().getSeconds()>=start.getSeconds() && temp.getSignedDate().getSeconds()<=end.getSeconds())
			{
				result += temp.getValue();
			}
		}
		return result;
	}
	
	/**
	 * @param contract
	 * @return the dollar value of all services in a contract
	 */
	public double getValueOfServices(Contract contract)
	{
		double result = 0;
		Service temp = null;
		ArrayList<Service> services = contract.getServices();
		Iterator<Service> it = services.iterator();
		
		while(it.hasNext())
		{
			temp = (Service) it.next();
			result += temp.getRate();
		}
		
		return result;
	}
	
}