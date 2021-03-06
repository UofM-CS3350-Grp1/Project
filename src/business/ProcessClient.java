package business;

import java.util.ArrayList;
import java.util.Iterator;

import objects.Client;
import objects.Service;

/**
 * Performs the client related processing between the GUI
 * component and the Database
 */
public class ProcessClient extends ProcessStorable
{
	private ArrayList<Client> clients;
	private ArrayList<Service> clientServices;
	private int clientIndex;
	private int clientServiceIndex;
		
	/**
	 * Creates a client accessor used to create, edit and delete 
	 */
	public ProcessClient()
	{
		super();
		
		clients = null;
		clientServices = null;
		clientIndex = 0;
		clientServiceIndex = 0;
	}
	
	/**
	 * Gets a client by the given ID
	 * @param id 	The ID of the client
	 * @return	The client if found, null otherwise
	 */
	public Client getClientByID(int id)
	{
		Client client = null;
		
		assert (id >= 0);
		if(id >= 0)
		{
			database.connect();
			client = database.getClientByID(id);
			database.disconnect();
		}
		
		return client;
	}
	
	/**
	 * Gets the client by business name
	 * @return The the client
	 */
	public Client getClientByBusinessName(String businessName)
	{
		ArrayList<Client> clientList = null;
		Client client = null;
		Client temp = null;
		Iterator<Client> it;
		boolean found = false;
		
		assert (businessName != null && !businessName.isEmpty());
		if(businessName != null && !businessName.isEmpty())
		{
			database.connect();
			clientList = database.dumpClients();
			database.disconnect();
			
			if(clientList != null)
			{
				it = clientList.iterator();					
				while(it.hasNext() && !found)
				{
					temp = it.next();
					if(temp.getBusinessName().startsWith(businessName))
					{
						client = temp;
						found = true;
					}
				}
			}
		}
		
		return client;
	}
	
	/**
	 * Gets the next client in the database
	 * @return The next client or null if we have reached the end of the list
	 */
	public Client getNextClient()
	{
		Client client = null;
		
		if(clients == null)
		{
			database.connect();
			clients = database.dumpClients();
			database.disconnect();
			
			if(clients != null && clients.size() > 0)
			{
				client = clients.get(0);
				clientIndex = 1;
			}
		}
		else if(clientIndex < clients.size())
		{
			client = clients.get(clientIndex);
			clientIndex++;
		}
		else
		{
			clients = null;
		}
		
		return client;
	}
	
	/**
	 * @return the number of clients
	 */
	public int getNumberOfClients()
	{
		ArrayList<Client> clientList = null;
		
		database.connect();
		clientList = database.dumpClients();
		database.disconnect();
		
		return clientList.size();
	}
	
	/**
	 * Adds a service to a given client
	 * @param client 	The client to add to
	 * @param service	The service to add
	 * @return	True if added successfully
	 */
	public boolean addServiceToClient(Client client, Service service)
	{
		boolean added = false;
		
		assert (client != null && service != null);
		if(client != null && service != null)
		{
			database.connect();
			
			service.setClientID(client.getID());
			added = database.update(service);
			
			database.disconnect();
		}
		
		return added;
	}
	
	/**
	 * Gets the next client service in the database
	 * @return The next service or null if we have reached the end of the list
	 */
	public Service getNextClientService(Client client)
	{		
		Service service = null;
		
		assert (client != null);
		if(client != null)
		{
			if(clientServices == null)
			{
				database.connect();
				clientServices = database.getServiceByClient(client);
				database.disconnect();
				
				if(clientServices != null && clientServices.size() > 0)
				{
					service = clientServices.get(0);
					clientServiceIndex = 1;
				}
			}
			else if(clientServiceIndex < clientServices.size())
			{
				service = clientServices.get(clientServiceIndex);
				clientServiceIndex++;
			}
			else
			{
				clientServices = null;
			}
		}
		
		return service;
	}
}
