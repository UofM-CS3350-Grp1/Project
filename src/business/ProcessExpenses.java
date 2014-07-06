package business;

import java.util.ArrayList;
import java.util.Iterator;

import objects.Contract;
import objects.Expense;
import objects.Service;
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
		
		database.disconnect();
		
		return result;
	}
}
