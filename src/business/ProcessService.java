package business;

import java.util.ArrayList;

import objects.Service;
import persistence.StubDBInterface;

/**
 * Performs the service related processing between the GUI
 * component and the Database
 */
public class ProcessService
{
	private StubDBInterface database;
	private ArrayList<Service> services;
	private int serviceIndex = 0;
		
	/**
	 * Creates a client accessor used to create, edit and delete 
	 */
	public ProcessService()
	{
		database = new StubDBInterface("dbName");
		services = null;
		serviceIndex = 0;
	}
	
	/**
	 * Creates a new service
	 * @param service The service
	 * @return True if the service was added
	 */
	public boolean insertService(Service service)
	{
		boolean wasCreated = false;
		
		assert (service != null);
		if(service != null)
		{
			database.insert(service);
			wasCreated = true;
		}
		
		return wasCreated;
	}
	
	/**
	 * Updates a service
	 * @param service The service
	 * @return True if the service was updated
	 */
	public boolean updateService(Service service)
	{
		boolean wasUpdated = false;
		
		assert (service != null);
		if(service != null)
		{
			database.update(service);
			wasUpdated = true;
		}
		
		return wasUpdated;
	}
	
	/**
	 * Deletes a service
	 * @param service The service
	 * @return True if the service was deleted
	 */
	public boolean deleteService(Service service)
	{
		boolean wasDeleted = false;
		
		assert (service != null);
		if(service != null)
		{
			database.drop(service);
			wasDeleted = true;
		}
		
		return wasDeleted;
	}
	
	/**
	 * Gets the next service in the database
	 * @return The next service or null if we have reached the end of the list
	 */
	public Service getNextService()
	{
		Service service = null;
		
		if(services == null)
		{
			services = database.dumpServices();
			
			if(services.size() > 0)
			{
				service = services.get(0);
				serviceIndex = 1;
			}
		}
		else if(serviceIndex < services.size())
		{
			service = services.get(serviceIndex);
			serviceIndex++;
		}
		else
		{
			services = null;
		}
		
		return service;
	}
}
