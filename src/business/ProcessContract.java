package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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
		contracts = null;
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
	
	/**
	 * @return the dollar amount of all contracts collectively
	 */
	public double getTotalContractsValue()
	{
		double result = 0;
		contracts = getContracts();
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
		contracts = database.dumpContracts();
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
		contracts = database.dumpContracts();
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