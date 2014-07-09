package business;

import java.util.ArrayList;
import java.util.Iterator;

import objects.Client;
import objects.Contract;
import objects.Expense;
import objects.Service;
import objects.ServiceType;
import persistence.DBInterface;

public class ProcessExpenses 
{	
	private DBInterface database;
	
	/**
	 * Creates a new expense processor
	 */
	public ProcessExpenses()
	{
		database = new DBInterface(DBInterface.DATABASE_NAME);
	}

	/**
	 * Inserts the expense into the database
	 * @param expense The expense to insert
	 */
	public void insertExpense(Expense expense)
	{
		database.connect();
		database.insert(expense);
		database.disconnect();
	}
	
	/**
	 * Calculates the total expenses for a client
	 * @param client	The client
	 * @return	The total expenses
	 */
	public double getExpensesByClient(Client client)
	{
		double result = 0;
		ArrayList<Expense> expenseList;
		ArrayList<Service> serviceList;
		Iterator<Service> itSvc;
		Iterator<Expense> itExp;
		Service service = null;
		Expense expense = null;
		
		database.connect();
		serviceList = database.getServiceByClient(client);
		
		if(serviceList != null)
		{
			itSvc = serviceList.iterator();
			while(itSvc.hasNext())
			{
				service = itSvc.next();
				expenseList = database.getExpensesByService(service);
				
				if(expenseList!=null)
				{
					itExp = expenseList.iterator();
					
					while(itExp.hasNext())
					{
						expense = itExp.next();
						result += expense.getValue();
					}
				}
			}
		}
		
		database.disconnect();
				
		return result;
	}
	
	/**
	 * Calculates the total expense per service
	 * @param service	The service
	 * @return	The total expense
	 */
	public double getExpensesByService(Service service)
	{
		double result = 0;
		ArrayList<Expense> expenseList;
		Iterator<Expense> it;
		Expense expense = null;
		
		database.connect();
		expenseList = database.getExpensesByService(service);
		database.disconnect();
		
		if(expenseList != null)
		{
			it = expenseList.iterator();
			
			while(it.hasNext())
			{
				expense = it.next();
				result += expense.getValue();
			}
		}
		
		return result;
	}

	/**
	 * Calculates the total expense for a given service type 
	 * @param serviceType	The service type
	 * @return	The total expense
	 */
	public double getExpensesByServiceType(ServiceType serviceType) 
	{
		double result = 0;
		ArrayList<Expense> expenseList;
		ArrayList<Service> serviceList;
		Service service;
		Expense expense;
		Iterator<Service> it;
		Iterator<Expense> itExp;
		
		database.connect();
		serviceList = database.getServicesByType(serviceType);
		if(serviceList != null)
		{
			it = serviceList.iterator();
			while(it.hasNext())
			{
				service = it.next();
				expenseList = database.getExpensesByService(service);
				
				if(expenseList != null)
				{
					itExp = expenseList.iterator();
					while(itExp.hasNext())
					{
						expense = itExp.next();
						result += expense.getValue();
					}
				}
			}
		}
		
		database.disconnect();
		
		return result;
	}
	
	/**
	 * Calculates the total expense for a contract
	 * @param contract	The contract
	 * @return	The total expense
	 */
	public double getExpensesByContract(Contract contract)
	{
		double result = 0;
		ArrayList<Expense> expenseList;
		ArrayList<Service> serviceList;
		Iterator<Service> it2;
		Iterator<Expense> it;
		Expense expense = null;
		
		database.connect();
		serviceList = database.getServiceByContract(contract);

		if(serviceList!=null)
		{
			it2 = serviceList.iterator();
			Service service = null;
	
			while(it2.hasNext())
			{
				service = it2.next();
				expenseList = database.getExpensesByService(service);
				
				if(expenseList!=null)
				{
					it = expenseList.iterator();
										
					while(it.hasNext())
					{
						expense = it.next();
						if(expense.getServiceID() == service.getID())
						{
							result += expense.getValue();
						}
					}
				}
			}
		}
		
		database.disconnect();
		
		return result;
	}
}
