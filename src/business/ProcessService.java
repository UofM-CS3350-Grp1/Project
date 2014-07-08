package business;

import java.util.ArrayList;
import java.util.Iterator;

import objects.Client;
import objects.Service;
import objects.ServiceType;

/**
 * Performs the service related processing between the GUI
 * component and the Database
 */
public class ProcessService extends ProcessStorable
{
	private ArrayList<Service> services;
	private int serviceIndex;
		
	/**
	 * Creates a client accessor used to create, edit and delete 
	 */
	public ProcessService()
	{
		super();
		
		services = null;
		serviceIndex = 0;
	}
	
	public ServiceType getServiceTypeByID(int id)
	{
		ServiceType result = null;
		database.connect();
		result = database.getServiceTypeByID(id);
		database.disconnect();
		return result;
	}
	
	public ArrayList<ServiceType> getServiceTypes()
	{
		ArrayList<ServiceType> result = null;
		database.connect();
		result = database.dumpServiceTypes();
		database.disconnect();
		return result;
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
			database.connect();
			services = database.dumpServices();
			database.disconnect();
			
			if(services != null && services.size() > 0)
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

	/**
	 * Gets a service by the given ID
	 * @param id 	The ID of the service
	 * @return	The service if found, null otherwise
	 */
	public Service getServiceByID(int id)
	{
		Service service = null;
		
		assert (id >= 0);
		if(id >= 0)
		{
			database.connect();
			service = database.getServiceByID(id);
			database.disconnect();
		}
		
		return service;
	}

	/**
	 * Gets a service by the given title
	 * @param title 	The title of the service
	 * @return	The service if found, null otherwise
	 */
	public Service getServiceByTitle(String title)
	{
		ArrayList<Service> services = null;
		Service service = null;
		
		assert (title!=null);
		
		database.connect();
		services = database.dumpServices();
		database.disconnect();
		
		Iterator<Service> it = services.iterator();
		Service temp = null;
		
		while(it.hasNext())
		{
			temp = it.next();
			if(temp.getTitle()==title)
			{
				service = temp;
				break;
			}
		}
		
		return service;
	}
	
	/**
	 * Gets the service by the provided identifiers
	 * @param serviceID	The ID of the service
	 * @param client	The client that uses the service
	 * @return	The service if found, null otherwise
	 */
	public Service getServiceByClient(int serviceID, Client client)
	{
		Service service = null;
		ArrayList<Service> clientServices;
		int size;
		
		assert (serviceID >= 0 && client != null);
		if(serviceID >= 0 && client != null)
		{
			database.connect();
			clientServices = database.getServiceByClient(client);
			database.disconnect();
			
			if(clientServices != null)
			{
				size = clientServices.size();
				for(int i = 0; i < size && service == null; i++)
				{
					if(clientServices.get(i).getID() == serviceID)
						service = clientServices.get(i);
				}
			}
		}
				
		return service;
	}
}
