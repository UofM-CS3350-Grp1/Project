package persistence;

import java.util.ArrayList;
import java.util.Date;

import objects.Client;
import objects.Email;
import objects.PhoneNumber;
import objects.Service;
import objects.Storable;
import objects.Contract;
import objects.Client.ClientStatus;

public class StubDBInterface extends AbstractDBInterface
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
		
		clients.add(new Client(1, "John Doe", new PhoneNumber("4035551212"), new Email("johndoe@gmail.com"), "123 Main St.", "John's Business", 1));
		clients.add(new Client(2, "Jane Doe", new PhoneNumber("4045553434"), new Email("janedoe@gmail.com"), "333 Park St.", "Jane's Business", 1));
		clients.add(new Client(3, "Steve Smith", new PhoneNumber("2065550021"), new Email("stevesmith@gmail.com"), "212 Broad St.", "Steve's Business", 0));
		
		contracts.add(new Contract(1, "John's Business", "Details go here...", 4000.00, new Date(), "Head", "Foot", new Date(),new Date(),"Pending"));
		contracts.add(new Contract(2, "Jane's Business", "Details go here...", 4500.00, new Date(), "Head", "Foot", new Date(),new Date(),"Pending"));
		contracts.add(new Contract(3, "Steve's Business", "Details go here...", 3500.00, new Date(), "Head", "Foot", new Date(),new Date(),"Pending"));
		
	}
	
	public void connect()
	{
		//stub
	}
	
	public void disconnect()
	{
		//stub
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
		for(int i = 0; i < contracts.size() && !breakloop ;i ++)
		{
			if(contracts.get(i).getID() == id)
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
	
	public boolean insert(Storable element)
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
		
		return false;
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
	
	public boolean update(Storable element)
	{
		return false;
		//Does Nothing.
	}
	
	/**
	 * DROP()
	 * 
	 * @param element
	 */
	
	public boolean drop(Storable element)
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
				if(contracts.get(i).getID() == update.getID())
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
		return false;
	}
	
	/**DUMPCLIENTS()
	 * 
	 * Returns all clients on the DBMS;
	 * 
	 */
	
	public ArrayList<Client> dumpClients()
	{
		return clients;
	}
	
	/**DUMPSERVICES()
	 * 
	 * Returns all services on the DBMS;
	 * 
	 */
	
	public ArrayList<Service> dumpServices()
	{
		return services;
	}
	
	/**DUMPCONTRACTS()
	 * 
	 * Returns all contracts on the DBMS;
	 * 
	 */
	
	public ArrayList<Contract> dumpContracts()
	{
		return contracts;
	}
	
	/**
	 * BLINDSQLQUERY();
	 *
	 * Runs a query against the DBMS and returns an arrayList of strings
	 *
	 * @param sql
	 * @return
	 */
	
	public ArrayList<String> blindSQLQuery(String sql)
	{
		//The stub DB doesen't evaluate actual SQL;
		return null;
	}
	
	/**
	 * TOSTRING()
	 * 
	 * @return String output indicating which database is in use.
	 */
	
	public String toString()
	{
		String output;
		
		if(dbName != null)
		{
			output = "Using database: " + dbName;
		}
		else
		{
			output = "No DB loaded.";
		}
		
		return output;
	}

	@Override
	public boolean idExists(Storable storableTemplate) 
	{
		return true;
	}

	@Override
	public void errorMessage(String retrieve, String invalid, String instruction) 
	{
		//No returns invalid output not in stub.
	}
}
