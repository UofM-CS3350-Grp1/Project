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
			//reports = database.getLastYearReturns(service);
			database.disconnect();
		}
		
		return reports;
	}
}
