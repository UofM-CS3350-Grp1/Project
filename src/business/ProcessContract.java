package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import objects.Contract;
import objects.Service;
import persistence.StubDBInterface;

/*
 * Performs the contract related processing between the GUI
 * component and the Database
 * Same format as ProcessClient.java for consistency
 */
public class ProcessContract {
	
	private StubDBInterface database;
	private ArrayList<Contract> contracts;
	private int contractIndex = 0;

	/**
	 * Creates a contract accessor used to create, edit, delete information from contracts
	 */
	public ProcessContract()
	{
		database = new StubDBInterface("dbName");
		contracts = null;
		contractIndex = 0;
	}
	
	/**
	 * Inserts a new contract to the database
	 * @param contract
	 * @return True if the contract was inserted successfully
	 */
	public boolean insertContract(Contract contract)
	{
		boolean wasCreated = false;
		
		assert (contract != null);
		if(contract != null)
		{
			database.insert(contract);
			wasCreated = true;
		}
		
		return wasCreated;
	}

	/**
	 * Updates a contract
	 * @param contract
	 * @return True if the contract was updated
	 */
	public boolean updateContract(Contract contract)
	{
		boolean wasUpdated = false;
		
		assert (contract != null);
		if(contract != null)
		{
			database.update(contract);
			wasUpdated = true;
		}
		
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
			database.drop(contract);
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
		contracts = database.dumpContracts();
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
	 * returns the dollar amount of all contracts collectively
	 */
	public double getTotalContractsValue(){
		double result = 0;
		contracts = database.dumpContracts();
		Contract temp = null;
		Iterator it = contracts.iterator();
		while(it.hasNext()){
			temp = (Contract) it.next();
			result += temp.getValue();
		}
		return result;
	}
	
	/*
	 * @param date range
	 * return the amount of contracts signed within a date range
	 */
	@SuppressWarnings("deprecation")
	public int getNumContractsBetween(Date start, Date end){
		int result = 0;
		contracts = database.dumpContracts();
		Contract temp = null;
		Iterator it = contracts.iterator();
		while(it.hasNext()){
			temp = (Contract) it.next();
			if(temp.getSignedDate().getSeconds()>=start.getSeconds() && temp.getSignedDate().getSeconds()<=end.getSeconds()){
				result++;
			}
		}
		return result;
	}
	
	/*
	 * @param date range
	 * return the double dollar value of contracts signed between 2 dates
	 */
	@SuppressWarnings("deprecation")
	public double getValueOfContractsBetween(Date start, Date end){
		double result = 0;
		contracts = database.dumpContracts();
		Contract temp = null;
		Iterator it = contracts.iterator();
		while(it.hasNext()){
			temp = (Contract) it.next();
			if(temp.getSignedDate().getSeconds()>=start.getSeconds() && temp.getSignedDate().getSeconds()<=end.getSeconds()){
				result += temp.getValue();
			}
		}
		return result;
	}
	
	/*
	 * @param contract
	 * return the dollar value of all services in a contract
	 */
	public double getValueOfServices(Contract contract){
		double result = 0;
		Service temp = null;
		ArrayList<Service> services = contract.getServices();
		Iterator it = services.iterator();
		while(it.hasNext()){
			temp = (Service) it.next();
			result += temp.getValue();
		}
		return result;
	}
	
}























