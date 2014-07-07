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
	
	public ProcessExpenses()
	{
		database = new DBInterface(DBInterface.DATABASE_NAME);
	}

	public void insertExpense(Expense expense)
	{
		database.connect();
		database.insert(expense);
		database.disconnect();
	}
	
	public double getExpensesByClient(Client client)
	{
		double result = 0;
		ArrayList<Expense> expenseList;
		ArrayList<Service> serviceList;
		
		database.connect();
		serviceList = database.getServiceByClient(client);
		Iterator<Service> itSvc = serviceList.iterator();
		Service service = null;
		while(itSvc.hasNext())
		{
			service = itSvc.next();
			expenseList = database.getExpensesByService(service);
			if(expenseList!=null)
			{
				Iterator<Expense> itExp = expenseList.iterator();
				Expense expense = null;
				
				while(itExp.hasNext())
				{
					expense = itExp.next();
					result += expense.getValue();
				}
			}
		}
		database.disconnect();
		
		
		return result;
	}
	
	public double getExpensesByService(Service service)
	{
		double result = 0;
		ArrayList<Expense> expenseList;
		
		database.connect();
		expenseList = database.getExpensesByService(service);
		database.disconnect();
		
		if(expenseList!=null)
		{
			Iterator<Expense> it = expenseList.iterator();
			Expense expense = null;
			
			while(it.hasNext())
			{
				expense = it.next();
				result += expense.getValue();
			}
		}
		return result;
	}

	public double getExpensesByServiceType(ServiceType serviceType) 
	{
		double result = 0;
		ArrayList<Expense> expenseList;
		ArrayList<Service> serviceList;
		Service service;
		Expense expense;
		
		database.connect();
		serviceList = database.getServicesByType(serviceType);
		Iterator<Service> it = serviceList.iterator();
		while(it.hasNext())
		{
			service = it.next();
			expenseList = database.getExpensesByService(service);
			Iterator<Expense> itExp = expenseList.iterator();
			while(itExp.hasNext())
			{
				expense = itExp.next();
				result += expense.getValue();
			}
		}
		database.disconnect();
		
		return result;
	}
	
	public double getExpensesByContract(Contract contract)
	{
		double result = 0;
		ArrayList<Expense> expenseList;
		ArrayList<Expense> expenses;
		ArrayList<Service> serviceList;
		
		database.connect();
		serviceList = database.getServiceByContract(contract);
		database.disconnect();

		Iterator<Service> it2 = serviceList.iterator();
		Service service = null;

		database.connect();

		while(it2.hasNext())
		{
			service = it2.next();
			expenseList = database.getExpensesByService(service);
			if(expenseList!=null)
			{
				Iterator<Expense> it = expenseList.iterator();
				Expense expense = null;
				
				while(it.hasNext())
				{
					expense = it.next();
					if(expense.getServiceID()==service.getID())
					{
						result += expense.getValue();
					}
				}
			}
		}
		
		database.disconnect();
		
		return result;
	}
}
