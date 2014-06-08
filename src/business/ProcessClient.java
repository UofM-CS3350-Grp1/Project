package business;

import java.util.ArrayList;

import objects.Client;
import persistence.StubDBInterface;

/**
 * Performs the client related processing between the GUI
 * component and the Database
 */
public class ProcessClient
{
	private StubDBInterface database;
	private ArrayList<Client> clients;
	private int clientIndex = 0;
		
	/**
	 * Creates a client accessor used to create, edit and delete 
	 */
	public ProcessClient()
	{
		database = new StubDBInterface("dbName");
		clients = null;
		clientIndex = 0;
	}
	
	/**
	 * Creates a new client
	 * @param client The client
	 * @return True if the client was added
	 */
	public boolean insertClient(Client client)
	{
		boolean wasCreated = false;
		
		assert (client != null);
		if(client != null)
		{
			database.insert(client);
			wasCreated = true;
		}
		
		return wasCreated;
	}
	
	/**
	 * Updates a client
	 * @param client The client
	 * @return True if the client was updated
	 */
	public boolean updateClient(Client client)
	{
		boolean wasUpdated = false;
		
		assert (client != null);
		if(client != null)
		{
			database.update(client);
			wasUpdated = true;
		}
		
		return wasUpdated;
	}
	
	/**
	 * Deletes a client
	 * @param client The client
	 * @return True if the client was deleted
	 */
	public boolean deleteClient(Client client)
	{
		boolean wasDeleted = false;
		
		assert (client != null);
		if(client != null)
		{
			database.drop(client);
			wasDeleted = true;
		}
		
		return wasDeleted;
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
			clients = database.dumpClients();
			
			if(clients.size() > 0)
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
}
