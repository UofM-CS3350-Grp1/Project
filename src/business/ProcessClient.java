package business;

import java.util.ArrayList;

import objects.Client;

/**
 * Performs the client related processing between the GUI
 * component and the Database
 */
public class ProcessClient extends ProcessStorable
{
	private ArrayList<Client> clients;
	private int clientIndex = 0;
		
	/**
	 * Creates a client accessor used to create, edit and delete 
	 */
	public ProcessClient()
	{
		super();
		
		clients = null;
		clientIndex = 0;
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
	
	/*
	 * Returns the number of clients
	 */
	public int getNumberOfClients()
	{
		ArrayList<Client> clientList = null;
		
		database.connect();
		clientList = database.dumpClients();
		database.disconnect();
		return clientList.size();
	}
}
