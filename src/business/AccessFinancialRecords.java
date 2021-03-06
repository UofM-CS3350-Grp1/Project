package business;

import java.util.ArrayList;

import objects.Client;
import objects.MonthReport;
import objects.Service;
import objects.ServiceType;
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
	 * @param serviceType	The service to retrieve data for
	 * @return	The list of reports for the last 12 months
	 */
	public ArrayList<MonthReport> getYearRevenueForService(ServiceType serviceType)
	{
		ArrayList<MonthReport> reports = null;
		
		assert (serviceType != null);
		if(serviceType != null)
		{
			database.connect();
			reports = database.getLastYearServiceRevenue(serviceType);
			database.disconnect();
		}
		
		return reports;
	}
	
	/**
	 * Retrieve the expense for the service's last 12 months
	 * @param serviceType	The service to retrieve data for
	 * @return	The list of reports for the last 12 months
	 */
	public ArrayList<MonthReport> getYearExpenseForService(ServiceType serviceType)
	{
		ArrayList<MonthReport> reports = null;
		
		assert (serviceType != null);
		if(serviceType != null)
		{
			database.connect();
			reports = database.getLastYearServiceExpenses(serviceType);
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
		ProcessExpenses processExpenses;
		
		assert (client != null);
		if(client != null)
		{
			processExpenses = new ProcessExpenses();
			expense = processExpenses.getExpensesByClient(client);
		}
		
		return expense;
	}
	
	/**
	 * Calculates the revenue for a service the current date/ period
	 * @param serviceType	The service to generate revenue data for
	 * @return	The revenue to date
	 */
	public double calcServiceRevenueToDate(ServiceType serviceType)
	{
		ProcessService processService = new ProcessService();
		Service service = null;
		double revenue = 0;
		
		assert (serviceType != null);
		if(serviceType != null)
		{
			while((service = processService.getNextService()) != null)
			{
				if(service.getServiceType().getType().equals(serviceType.getType()))
					revenue += service.getRate();
			}
		}
		
		return revenue;
	}
	
	/**
	 * Calculates the expense for a service the current date/ period
	 * @param serviceType	The service to generate expense data for
	 * @return	The expense to date
	 */
	public double calcServiceExpensesToDate(ServiceType serviceType)
	{
		ProcessExpenses processExpenses = new ProcessExpenses();
		double expense = 0;
		
		assert (serviceType != null);
		if(serviceType != null)
		{
			expense = processExpenses.getExpensesByServiceType(serviceType);
		}
		
		return expense;
	}
}
