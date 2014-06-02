package persistence;

import java.util.ArrayList;
import java.util.Date;

import objects.Client;
import objects.Service;
import objects.Storable;
import objects.Contract;
import objects.Client.ClientStatus;

public class StubDBInterface 
{
	private String dbName;
	private ArrayList<Client> clients;
	private ArrayList<Contract> contracts;
	private ArrayList<Service> services;
	
	public StubDBInterface(String dbName)
	{
		this.dbName = dbName;
		
		clients = new ArrayList<Client>();
		contracts = new ArrayList<Contract>();
		services = new ArrayList<Service>();
		
		clients.add(new Client(1, "John Doe", "5551212", "johndoe@gmail.com", "123 Main St.", "John's Business", 1));
		clients.add(new Client(2, "Jane Doe", "5553434", "janedoe@gmail.com", "333 Park St.", "Jane's Business", 1));
		clients.add(new Client(3, "Steve Smith", "5550021", "stevesmith@gmail.com", "212 Broad St.", "Steve's Business", 0));
		
		contracts.add(new Contract(1, "John's Business", "Details go here...", 4000.00, new Date()));
		contracts.add(new Contract(2, "Jane's Business", "Details go here...", 4500.00, new Date()));
		contracts.add(new Contract(3, "Steve's Business", "Details go here...", 3500.00, new Date()));
		
		services.add(new Service(1, "Service 1", "Service description...", 4.0, "Web Design"));
		services.add(new Service(2, "Service 2", "Service description...", 3.0, "Social Media"));
		services.add(new Service(3, "Service 3", "Service description...", 5.5, "Marketing"));

	}
	
	/**
	 * 	GETSERVICEBYID()
	 * 
	 *  @param	int id	-	ID to search for
	 *  
	 *  @return	Service	-	Service specified by ID, null if no match
	 */
	
	public Service getServiceByID(int id)
	{
		boolean breakloop = false;
		Service output = null;
		for(int i = 0; i < services.size() && !breakloop ;i ++)
		{
			if(services.get(i).getID() == id)
			{
				output = services.get(i);
				breakloop = true;
			}
		}
		
		return output;
	}
	
	/**
	 * 	GETSERVICEBYTITLE()
	 * 
	 *  @param	int id	-	ID to search for
	 *  
	 *  @return	Service	-	Service specified by ID, null if no match
	 */
	
	public ArrayList<Service> getServicesByTitle(String name)
	{
		ArrayList<Service> output = new ArrayList<Service>();
		for(int i = 0; i < services.size();i ++)
		{
			if(services.get(i).getTitle().compareTo(name) == 0)
			{
				output.add(services.get(i));
			}
		}
		
		return output;
	}
	
	/**
	 * 	GETCLIENTBYID()
	 * 
	 *  @param	int id	-	ID to search for
	 *  
	 *  @return	Service	-	Client specified by ID, null if no match
	 */
	
	public Client getClientByID(int id)
	{
		boolean breakloop = false;
		Client output = null;
		for(int i = 0; i < clients.size() && !breakloop ;i ++)
		{
			if(clients.get(i).getID() == id)
			{
				output = clients.get(i);
				breakloop = true;
			}
		}
		
		return output;
	}
	
	/**
	 * 	GETCLIENTSBYSTATUS()
	 * 
	 *  @param	ClientStatus status	-	Status to search for
	 *  
	 *  @return	Clients	-	ArrayList of Clients matching Status, null if no match
	 */
	
	public ArrayList<Client> getClientsByStatus(ClientStatus status)
	{
		ArrayList<Client> output = new ArrayList<Client>();
		for(int i = 0; i < clients.size();i ++)
		{
			if(clients.get(i).getStatus() == status)
			{
				output.add(clients.get(i));
			}
		}
		
		return output;
	}
	
	/**
	 * 	GETCONTRACTBYID()
	 * 
	 *  @param	int id	-	ID to search for
	 *  
	 *  @return	Contract	-	Contract specified by ID, null if no match
	 */	
	
	public Contract getContractByID(int id)
	{
		boolean breakloop = false;
		Contract output = null;
		for(int i = 0; i < services.size() && !breakloop ;i ++)
		{
			if(contracts.get(i).getContractNumber() == id)
			{
				output = contracts.get(i);
				breakloop = true;
			}
		}
		
		return output;
	}
	
	/**
	 * 	GETCONTRACTSBYBUSINESS()
	 * 
	 *  @param	String business	-	Business name to search for
	 *  
	 *  @return	Contracts	-	ArrayList of Contacts matching Business, null if no match
	 */
	
	public ArrayList<Contract> getContractsByBusiness(String business)
	{
		ArrayList<Contract> output = new ArrayList<Contract>();
		for(int i = 0; i < contracts.size();i ++)
		{
			if(contracts.get(i).getBusinessName().compareTo(business) == 0)
			{
				output.add(contracts.get(i));
			}
		}
		
		return output;
	}
	
	/**
	 * INSERT()
	 * 
	 * @param element
	 */
	
	public void insert(Storable element)
	{
		if(element instanceof Client)
		{
			clients.add((Client)element);
		}
		else if(element instanceof Service)
		{
			services.add((Service)element);
		}
		else if(element instanceof Contract)
		{
			contracts.add((Contract)element);
		}
		else
		{
			System.out.println("Invalid input for INSERT statement.");
		}
	}
	
	/**
	 * UPDATE()
	 * 
	 * 		In the stub DBMS this function makes no difference whatsoever.
	 * 		Changing params in the object itself achieves the same effect.
	 * 		However for implementation purposes, this would actually call an UPDATE on the DBMS.
	 * 
	 * @param element
	 */
	
	public void update(Storable element)
	{
		//Does Nothing.
	}
	
	/**
	 * DROP()
	 * 
	 * @param element
	 */
	
	public void drop(Storable element)
	{
		boolean breakloop = false;
		if(element instanceof Client)
		{
			Client update = (Client)element;
			
			for(int i = 0; i< clients.size() && !breakloop; i++)
			{
				if(clients.get(i).getID() == update.getID())
				{
					clients.remove(i);
					breakloop = true;
				}
			}
		}
		else if(element instanceof Service)
		{
			Service update = (Service)element;
			
			for(int i = 0; i< services.size() && !breakloop; i++)
			{
				if(services.get(i).getID() == update.getID())
				{
					services.remove(i);
					breakloop = true;
				}
			}
		}
		else if(element instanceof Contract)
		{
			Contract update = (Contract)element;
			
			for(int i = 0; i< contracts.size() && !breakloop; i++)
			{
				if(contracts.get(i).getContractNumber() == update.getContractNumber())
				{
					contracts.remove(i);
					breakloop = true;
				}
			}
		}
		else
		{
			System.out.println("Invalid input for DROP statement.");
		}
	}
	
	/**
	 * TOSTRING()
	 * 
	 * @return String output indicating which database is in use.
	 */
	
	public String toString()
	{
		return "Using database: " + dbName;
	}
}
