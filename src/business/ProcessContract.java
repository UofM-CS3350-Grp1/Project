package business;

import java.util.ArrayList;

import objects.Client;
import objects.Contract;
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
	 * Creates a contract accessor used to create, edit and delete contracts
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
	public Contract getNextContract()
	{
		Contract contract = null;
		
		if(contracts == null)
		{
			contracts = database.dumpContracts();
			
			if(contracts.size() > 0)
			{
				contract = contracts.get(0);
				contractIndex = 1;
			}
		}
		else if(contractIndex < contracts.size())
		{
			contract = contracts.get(contractIndex);
			contractIndex++;
		}
		else
		{
			contracts = null;
		}
		
		return contract;
	}
	
}
