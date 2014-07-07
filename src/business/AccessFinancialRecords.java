package business;

import java.util.ArrayList;

import objects.Client;
import objects.MonthReport;
import objects.Service;
import persistence.DBInterface;

/**
 * Accesses the database to obtain financial records
 */
public class AccessFinancialRecords 
{
	private DBInterface database;
	
	/**
	 * Creates a new record gatherer
	 */
	public AccessFinancialRecords()
	{
		database = new DBInterface(DBInterface.DATABASE_NAME);
	}
	
	/**
	 * Retrieve the revenue for the client's last 12 months
	 * @param client	The client to retrieve data for
	 * @return	The list of reports for the last 12 months
	 */
	public ArrayList<MonthReport> getYearRevenueForClient(Client client)
	{
		ArrayList<MonthReport> reports = null;
		
		assert (client != null);
		if(client != null)
		{
			database.connect();
			reports = database.getLastYearReturns(client);
			database.disconnect();
		}
		
		return reports;
	}
	
	/**
	 * Retrieve the revenue for the service's last 12 months
	 * @param service	The service to retrieve data for
	 * @return	The list of reports for the last 12 months
	 */
	public ArrayList<MonthReport> getYearRevenueForService(Service service)
	{
		ArrayList<MonthReport> reports = null;
		
		assert (service != null);
		if(service != null)
		{
			database.connect();
			//TODO Get revenue for services on 12 month cycle
			//reports = database.getLastYearReturns(service);
			database.disconnect();
		}
		
		return reports;
	}
	
	/**
	 * Retrieve the expense for the service's last 12 months
	 * @param service	The service to retrieve data for
	 * @return	The list of reports for the last 12 months
	 */
	public ArrayList<MonthReport> getYearExpenseForService(Service service)
	{
		ArrayList<MonthReport> reports = null;
		
		assert (service != null);
		if(service != null)
		{
			database.connect();
			//TODO Get revenue for services on 12 month cycle
			//reports = database.getLastYearExpense(service);
			database.disconnect();
		}
		
		return reports;
	}
	
	/**
	 * Calculates the revenue for a client the current date/ period
	 * @param client	The client to generate revenue data for
	 * @return	The revenue to date
	 */
	public double calcClientRevenueToDate(Client client)
	{
		double revenue = 0;
		
		assert (client != null);
		if(client != null)
		{
			database.connect();
			revenue = database.getClientCurrentRevenue(client);
			database.disconnect();
		}
		
		return revenue;
	}
	
	/**
	 * Calculates the expense for a client the current date/ period
	 * @param client	The client to generate expense data for
	 * @return	The expense to date
	 */
	public double calcClientExpensesToDate(Client client)
	{
		double expense = 0;
		
		assert (client != null);
		if(client != null)
		{
			database.connect();
			expense = database.getClientCurrentExpenses(client);
			database.disconnect();
		}
		
		return expense;
	}
	
	/**
	 * Calculates the revenue for a service the current date/ period
	 * @param service	The service to generate revenue data for
	 * @return	The revenue to date
	 */
	public double calcServiceRevenueToDate(Service service)
	{
		double revenue = 0;
		
		assert (service != null);
		if(service != null)
		{
			database.connect();
			//revenue = database.getServiceCurrentRevenue(service);
			database.disconnect();
		}
		
		return revenue;
	}
	
	/**
	 * Calculates the expense for a service the current date/ period
	 * @param service	The service to generate expense data for
	 * @return	The expense to date
	 */
	public double calcServiceExpensesToDate(Service service)
	{
		double expense = 0;
		
		assert (service != null);
		if(service != null)
		{
			database.connect();
			//expense = database.getServiceCurrentExpenses(service);
			database.disconnect();
		}
		
		return expense;
	}
}
