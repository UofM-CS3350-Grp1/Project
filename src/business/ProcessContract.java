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

		contracts = null;
	}
	
	/**
	 * @return The next unused contract number
	 */
	public int getUnusedContractID()
	{
		int result = 0;
		ArrayList<Contract> contractList = new ArrayList<Contract>();
		
		database.connect();
		contractList = database.dumpContracts();
		database.disconnect();
		
		Contract temp = null;
		
		boolean found = false;
		boolean used = false;
		while(!found)
		{
			result++;
			used=false;
			Iterator<Contract> it = contractList.iterator();
			while(it.hasNext() && !used)
			{
				temp = it.next();
				if(temp.getID()==result)
				{
					used=true;
				}
			}
			if(!used)
			{
				found = true;
			}
			
		}
		
		return result;
	}
	
	/**
	 * @param id The contract id
	 * @return Gets the contract of the given ID or null
	 */
	public Contract getContractByID(int id)
	{
		database.connect();
		Contract theContract = database.getContractByID(id);
		database.disconnect();
		
		return theContract;
	}
	
	/**
	 * @param client The client whos contracts we want
	 * @return an array List of contracts for the given clients
	 */
	public ArrayList<Contract> getContractsByClient(Client client)
	{
		database.connect();
		ArrayList<Contract> theContract = database.getContractsByBusiness(client.getBusinessName());
		database.disconnect();
		
		return theContract;
	}
	
	/**
	 * @return The client that signed this contract
	 */
	public Client getContractClient(Contract contract)
	{
		database.connect();
		ArrayList<Client> list = database.dumpClients();
		database.disconnect();
		
		Iterator<Client> it = list.iterator();
		Client result = null;
		Client temp = null;
		
		while(it.hasNext())
		{
			temp = it.next();
			if(contract.getBusinessName()==temp.getBusinessName())
			{
				result = temp;
			}
		}
		return result;
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
		ArrayList<Service> result = new ArrayList<Service>();
		ArrayList<Service> temp = null;
		
		database.connect();
		temp = database.dumpServices();
		database.disconnect();
		
		System.out.println("Searching for services");
		Iterator<Service> it = temp.iterator();
		Service service = null;
		
		while(it.hasNext())
		{
			service = it.next();
			if(service.getContractID() == contract.getID())
			{
				System.out.println("Service found: "+service.getTitle());
				result.add(service);
			}
		}
		return result;
	}
	
	/**
	 * Add services to this contract
	 * @param contract The contract to add services to
	 * @param services The services to add to the contract
	 */
	public void setServices(Contract contract, ArrayList<Service> services)
	{
		contract.addServices(services);
		database.connect();
		database.update(contract);
		database.disconnect();
	}
	
	/**
	 * @return contract The number of services in this contract
	 */
	public int getNumberOfServices(Contract contract)
	{
		int result = 0;
		ArrayList<Service> services = null;
		database.connect();
		database.disconnect();
		services = contract.getServices();
		
		result = services.size();
		
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
