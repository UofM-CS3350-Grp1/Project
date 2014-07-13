package persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import objects.Client;
import objects.Email;
import objects.Expense;
import objects.MonthReport;
import objects.PhoneNumber;
import objects.Service;
import objects.ServiceType;
import objects.Storable;
import objects.Contract;
import objects.TrackedFeature;
import objects.TrackedFeatureType;
import objects.Client.ClientStatus;
import objects.User;

public class StubDBInterface extends AbstractDBInterface
{
	private String dbName;
	private ArrayList<Client> clients;
	private ArrayList<Contract> contracts;
	private ArrayList<Service> services;
	private ArrayList<Expense> expenses;
	private ArrayList<ServiceType> serviceTypes;
	private ArrayList<TrackedFeature> trackedFeatures;
	private ArrayList<TrackedFeatureType> trackedFeatureTypes;
	private ArrayList<MonthReport> monthReports;
	private ArrayList<MonthReport> monthReports2;
	private ArrayList<User> users;
	
	public StubDBInterface(String dbName)
	{
		this.dbName = dbName;
		
		clients = new ArrayList<Client>();
		contracts = new ArrayList<Contract>();
		services = new ArrayList<Service>();
		expenses = new ArrayList<Expense>();
		serviceTypes = new ArrayList<ServiceType>();
		trackedFeatures = new ArrayList<TrackedFeature>();
		trackedFeatureTypes = new ArrayList<TrackedFeatureType>();
		monthReports = new ArrayList<MonthReport>();
		users = new ArrayList<User>();
		
		clients.add(new Client(1, "John Doe", new PhoneNumber("4035551212"), new Email("johndoe@gmail.com"), "123 Main St.", "John's Business", 1));
		clients.add(new Client(2, "Jane Doe", new PhoneNumber("4045553434"), new Email("janedoe@gmail.com"), "333 Park St.", "Jane's Business", 1));
		clients.add(new Client(3, "Steve Smith", new PhoneNumber("2065550021"), new Email("stevesmith@gmail.com"), "212 Broad St.", "Steve's Business", 0));
		
		contracts.add(new Contract(1, "John's Business", "Details go here...", 4000.00, new Date(), "Head", "Foot", new Date(),new Date(),"Pending"));
		contracts.add(new Contract(2, "Jane's Business", "Details go here...", 4500.00, new Date(), "Head", "Foot", new Date(),new Date(),"Pending"));
		contracts.add(new Contract(3, "Steve's Business", "Details go here...", 3500.00, new Date(), "Head", "Foot", new Date(),new Date(),"Pending"));
		
		serviceTypes.add(new ServiceType(1, "#1 Service", "The first service type"));
		serviceTypes.add(new ServiceType(2, "#2 Service", "The second service type"));
		serviceTypes.add(new ServiceType(3, "# Service", "The third service type"));
		
		//int id, String title, String description, double rate, int clientID, int contractID, ServiceType typeS, String contractDetails
		services.add(new Service(1,"A Service", "A Description", 1.0, 1, 1, serviceTypes.get(0), "Details here"));
		services.add(new Service(2,"A Service", "A Description", 2.0, 2, 2, serviceTypes.get(1), "Details here"));
		services.add(new Service(3,"A Service", "A Description", 3.0, 3, 3, serviceTypes.get(2), "Details here"));
		
		//int id, String title
		trackedFeatureTypes.add(new TrackedFeatureType(1,"Title1"));
		trackedFeatureTypes.add(new TrackedFeatureType(2,"Title2"));
		trackedFeatureTypes.add(new TrackedFeatureType(3,"Title3"));
		
		//String notes, int clientID, TrackedFeatureType trackedFT, Date recorded, double value
		trackedFeatures.add(new TrackedFeature("Notes 1",1, 1, trackedFeatureTypes.get(0), 1.0, new Date()));
		trackedFeatures.add(new TrackedFeature("Notes 2",2, 2, trackedFeatureTypes.get(1), 2.0, new Date()));
		trackedFeatures.add(new TrackedFeature("Notes 3",3, 3, trackedFeatureTypes.get(2), 3.0, new Date()));
		
		//int expenseID, int serviceID, String supplier, double value, String details, Date dateIncurred
		expenses.add(new Expense(1,1,"Supplier #1", 1.0, "Details 1", new Date()));
		expenses.add(new Expense(2,2,"Supplier #2", 2.0, "Details 2", new Date()));
		expenses.add(new Expense(3,3,"Supplier #3", 3.0, "Details 3", new Date()));
		
		//int id, String user, String pass
		users.add(new User(1, "User 1", "password"));
		users.add(new User(2, "User 2", "password"));
		users.add(new User(3, "User 3", "password"));
		
		//Date period, double value
		monthReports.add(new MonthReport(new Date(), 54.2));
		monthReports.add(new MonthReport(new Date(), 343.2));
		monthReports.add(new MonthReport(new Date(), 822.2));
		monthReports.add(new MonthReport(new Date(), 15.2));
		monthReports.add(new MonthReport(new Date(), 123.2));
		monthReports.add(new MonthReport(new Date(), 909.2));
		monthReports.add(new MonthReport(new Date(), 434.2));
		monthReports.add(new MonthReport(new Date(), 5655.2));
		monthReports.add(new MonthReport(new Date(), 879.2));
		monthReports.add(new MonthReport(new Date(), 34.2));
		monthReports.add(new MonthReport(new Date(), 86.2));
		monthReports.add(new MonthReport(new Date(), 567.2));
		
		monthReports2.add(new MonthReport(new Date(), 12.2));
		monthReports2.add(new MonthReport(new Date(), 977.2));
		monthReports2.add(new MonthReport(new Date(), 1.2));
		monthReports2.add(new MonthReport(new Date(), 335.2));
		monthReports2.add(new MonthReport(new Date(), 123.2));
		monthReports2.add(new MonthReport(new Date(), 909.2));
		monthReports2.add(new MonthReport(new Date(), 23.2));
		monthReports2.add(new MonthReport(new Date(), 445.2));
		monthReports2.add(new MonthReport(new Date(), 765.2));
		monthReports2.add(new MonthReport(new Date(), 32.2));
		monthReports2.add(new MonthReport(new Date(), 534.2));
		monthReports2.add(new MonthReport(new Date(), 344.2));
	}
	
	public void connect()
	{
		System.out.println("Stub DB is connected. (To nothing)");
	}
	
	public void disconnect()
	{
		System.out.println("Stub DB is disconnected. (From nothing)");
	}
	
	//Testing purposes only
	public DBController getController()
	{
		return null;
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
		Service output = null;
		
		for(int i = 0; i < services.size(); i++)
		{
			if (services.get(i).getID() == id);
				output = services.get(i);
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
		Client output = null;
		
		for(int i = 0; i < clients.size(); i++)
		{
			if (clients.get(i).getID() == id);
				output = clients.get(i);
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
		Contract output = null;
		
		for(int i = 0; i < contracts.size(); i++)
		{
			if (contracts.get(i).getID() == id);
				output = contracts.get(i);
		}
		
		return output;
		
	}
	
	
	/**
	 * 	GETEXPENSEBYID()
	 * 
	 *  @param	int id	-	ID to search for
	 *  
	 *  @return	Expense	-	Expense specified by ID, null if no match
	 */	
	
	public Expense getExpenseByID(int id)
	{
		Expense output = null;
		
		for(int i = 0; i < expenses.size(); i++)
		{
			if (expenses.get(i).getID() == id);
				output = expenses.get(i);
		}
		
		return output;
		
	}

	public TrackedFeature getTrackedFeatureByID(int id)
	{
		TrackedFeature output = null;
		
		for(int i = 0; i < trackedFeatures.size(); i++)
		{
			if (trackedFeatures.get(i).getID() == id);
				output = trackedFeatures.get(i);
		}
		
		return output;
	}
	
	public TrackedFeatureType getTrackedFeatureTypeByID(int id)
	{
		TrackedFeatureType output = null;
		
		for(int i = 0; i < trackedFeatureTypes.size(); i++)
		{
			if (trackedFeatureTypes.get(i).getID() == id);
				output = trackedFeatureTypes.get(i);
		}
		
		return output;
	}
	
	/**
	 * 	GETSERVICETYPEBYID()
	 * 
	 *  @param	int id	-	ID to search for
	 *  
	 *  @return	ServiceType	-	ServiceType specified by ID, null if no match
	 */	
	
	public ServiceType getServiceTypeByID(int id)
	{
		ServiceType output = null;
		
		for(int i = 0; i < serviceTypes.size(); i++)
		{
			if (serviceTypes.get(i).getID() == id);
				output = serviceTypes.get(i);
		}
		
		return output;
		
	}
	
	
	/**
	 * 	GETSERVICETYPESBYTYPE()
	 * 
	 *  @param	int id	-	String type to search for
	 *  
	 *  @return	ArrayList<ServiceType>	-	ArrayList<ServiceType> specified by name, null if no match
	 */	
	
	public ArrayList<ServiceType> getServiceTypesByType(String type)
	{
		ArrayList<ServiceType> output = new ArrayList<ServiceType>();
		
		for(int i = 0; i < serviceTypes.size(); i++)
		{
			if (serviceTypes.get(i).getType().compareTo(type) == 0);
				output.add(serviceTypes.get(i));
		}
		
		if(output.size() == 0)
			output = null;
		
		return output;
		
	}
	
	/**
	 * 	GETTRACKEDFEATURETYPESBYTITLE()
	 * 
	 *  @param	int id	-	String title to search for
	 *  
	 *  @return	TrackedFeatureType	-	TrackedFeatureType specified by name, null if no match
	 */	
	
	public ArrayList<TrackedFeatureType> getTrackedFeatureTypesByTitle(String title)
	{
		ArrayList<TrackedFeatureType> output = new ArrayList<TrackedFeatureType>();
		
		for(int i = 0; i < trackedFeatureTypes.size(); i++)
		{
			if (trackedFeatureTypes.get(i).getTitle().compareTo(title) == 0);
				output.add(trackedFeatureTypes.get(i));
		}
		
		if(output.size() == 0)
			output = null;
		
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
		
		for(int i = 0; i < contracts.size(); i++)
		{
			if (contracts.get(i).getBusinessName().compareTo(business) == 0);
				output.add(contracts.get(i));
		}
		
		if(output.size() == 0)
			output = null;
		
		return output;
	}
	
	/**
	 * 	GETTRACKEDFEATUREBYSERVICE()
	 * 
	 *  @param	int id	-	Service to search for
	 *  
	 *  @return	TrackedFeature	-	TrackedFeature specified by Service, null if no match
	 */	
	
	public ArrayList<TrackedFeature> getTrackedFeaturesByClient(Client input)
	{
		ArrayList<TrackedFeature> output = new ArrayList<TrackedFeature>();
		
		for(int i = 0; i < trackedFeatures.size(); i++)
		{
			if (trackedFeatures.get(i).getClientKey() == input.getID());
				output.add(trackedFeatures.get(i));
		}
		
		if(output.size() == 0)
			output = null;
		
		return output;
	}
	

	
	/**
	 * 	GETCLIENTBYFEATURE()
	 * 
	 *  @param	Feature 	- Feature to use to retrieve Service
	 *  
	 *  @return	Service	-	Service referenced by feature, null if no ref
	 */	
	
	public Client getClientByFeature(TrackedFeature input)
	{
		Client output = null;
		
		for(int i = 0; i < clients.size(); i++)
		{
			if (clients.get(i).getID() == input.getClientKey());
				output = clients.get(i);
		}
		
		return output;
	}
	
	/**
	 * 	GETSERVICEBYCONTRACT()
	 * 
	 *  @param	Contract 	- Feature to used to retrieve Services
	 *  
	 *  @return	ArrayList<Services>	-	All services referenced by contract, null if no ref
	 */	
	
	public ArrayList<Service> getServiceByContract(Contract input)
	{
		ArrayList<Service> output = new ArrayList<Service>();
		
		for(int i = 0; i < services.size(); i++)
		{
			if (services.get(i).getContractID() == input.getID());
				output.add(services.get(i));
		}
		
		if(output.size() == 0)
			output = null;
		
		return output;
	}
	
	/**
	 * 	GETSERVICEBYCLIENT()
	 * 
	 *  @param	Client 	- Client to used to retrieve Services
	 *  
	 *  @return	ArrayList<Services>	-	All services referenced by client, null if no ref
	 */	
	
	public ArrayList<Service> getServiceByClient(Client input)
	{
		ArrayList<Service> output = new ArrayList<Service>();
		
		for(int i = 0; i < services.size(); i++)
		{
			if (services.get(i).getClientID() == input.getID());
				output.add(services.get(i));
		}
		
		if(output.size() == 0)
			output = null;
		
		return output;
	}
	
	/**
	 * 	GETSERVICESBYTYPE()
	 * 
	 *  @param	ServiceType 	- ServiceType to used to retrieve Services
	 *  
	 *  @return	ArrayList<Services>	-	All services referenced by ServiceType, null if no ref
	 */	
	
	public ArrayList<Service> getServicesByType(ServiceType input)
	{
		ArrayList<Service> output = new ArrayList<Service>();
		
		for(int i = 0; i < services.size(); i++)
		{
			if (services.get(i).getServiceType().getID() == input.getID());
				output.add(services.get(i));
		}
		
		if(output.size() == 0)
			output = null;
		
		return output;
	}
	
	/**
	 * GETEXPENSESBYSERVICE()
	 * 
	 * @param Service service with associated expenses.
	 * @return - Array list containing the expenses assocated with this service
	 */
	
	public ArrayList<Expense> getExpensesByService(Service service)
	{
		ArrayList<Expense> output = new ArrayList<Expense>();
		
		for(int i = 0; i < expenses.size(); i++)
		{
			if (expenses.get(i).getServiceID() == service.getID());
				output.add(expenses.get(i));
		}
		
		if(output.size() == 0)
			output = null;
		
		return output;
	}
	

	/**
	 * INSERT()
	 * 
	 * @param element Storable to insert
	 */
	
	public boolean insert(Storable element)
	{
		boolean output = false;
		
		if(element != null)
		{
			if(element instanceof Service)
			{
				services.add((Service)element);
				output = true;
			}
			else if(element instanceof Client)
			{
				clients.add((Client)element);
				output = true;
			}
			else if(element instanceof Contract)
			{
				contracts.add((Contract)element);
				output = true;
			}
			else if(element instanceof Expense)
			{
				expenses.add((Expense)element);
				output = true;
			}
			else if(element instanceof TrackedFeature)
			{
				trackedFeatures.add((TrackedFeature)element);
				output = true;
			}
			else if(element instanceof TrackedFeatureType)
			{
				trackedFeatureTypes.add((TrackedFeatureType)element);
				output = true;
			}
			else
			{
				serviceTypes.add((ServiceType)element);
				output = true;
			}
		}

		return output;
	}
	
	/**
	 * UPDATE()
	 * 
	 * @param element Storable to update
	 */
	
	public boolean update(Storable element)
	{
		boolean output = false;
		int id = 0;
		
		if(element != null)
		{
			if(element instanceof Service)
			{
				id = services.indexOf((Service)element);
				if(id >= 0)
				{
					services.add(id, (Service)element);
					services.remove(id+1);
					output = true;
				}
			}
			else if(element instanceof Client)
			{
				id = clients.indexOf((Client)element);
				if(id >= 0)
				{
					clients.add(id, (Client)element);
					clients.remove(id+1);
					output = true;
				}
			}
			else if(element instanceof Contract)
			{
				id = contracts.indexOf((Contract)element);
				if(id >= 0)
				{
					contracts.add(id, (Contract)element);
					contracts.remove(id+1);
					output = true;
				}
			}
			else if(element instanceof Expense)
			{
				id = expenses.indexOf((Expense)element);
				if(id >= 0)
				{
					expenses.add(id, (Expense)element);
					expenses.remove(id+1);
					output = true;
				}
			}
			else if(element instanceof TrackedFeature)
			{
				id = trackedFeatures.indexOf((TrackedFeature)element);
				if(id >= 0)
				{
					trackedFeatures.add(id, (TrackedFeature)element);
					trackedFeatures.remove(id+1);
					output = true;
				}
			}
			else if(element instanceof TrackedFeatureType)
			{
				id = trackedFeatureTypes.indexOf((TrackedFeatureType)element);
				if(id >= 0)
				{
					trackedFeatureTypes.add(id, (TrackedFeatureType)element);
					trackedFeatureTypes.remove(id+1);
					output = true;
				}
			}
			else
			{
				id = serviceTypes.indexOf((ServiceType)element);
				if(id >= 0)
				{
					serviceTypes.add(id, (ServiceType)element);
					serviceTypes.remove(id+1);
					output = true;
				}
			}
		}
		
		return output;
	}
	
	/**
	 * DROP()
	 * 
	 * @param element Storable to drop
	 */
	
	public boolean drop(Storable element)
	{
		boolean output = false;
		int id = 0;
		
		if(element != null)
		{
			if(element instanceof Service)
			{
				id = services.indexOf((Service)element);
				if(id >= 0)
				{
					services.remove(id);
					output = true;
				}
			}
			else if(element instanceof Client)
			{
				id = clients.indexOf((Client)element);
				if(id >= 0)
				{
					clients.remove(id);
					output = true;
				}
			}
			else if(element instanceof Contract)
			{
				id = contracts.indexOf((Contract)element);
				if(id >= 0)
				{
					contracts.remove(id);
					output = true;
				}
			}
			else if(element instanceof Expense)
			{
				id = expenses.indexOf((Expense)element);
				if(id >= 0)
				{
					expenses.remove(id);
					output = true;
				}
			}
			else if(element instanceof TrackedFeature)
			{
				id = trackedFeatures.indexOf((TrackedFeature)element);
				if(id >= 0)
				{
					trackedFeatures.remove(id);
					output = true;
				}
			}
			else if(element instanceof TrackedFeatureType)
			{
				id = trackedFeatureTypes.indexOf((TrackedFeatureType)element);
				if(id >= 0)
				{
					trackedFeatureTypes.remove(id);
					output = true;
				}
			}
			else
			{
				id = serviceTypes.indexOf((ServiceType)element);
				if(id >= 0)
				{
					serviceTypes.remove(id);
					output = true;
				}
			}
		}
		
		return output;
	}
	
	
	/** GETLASTYEARRETURNS()
	 * 
	 * Returns up to the last 12 months worth of monthly reports.
	 */
	
	public ArrayList<MonthReport> getLastYearReturns(Client element)
	{
		//custom SQL query, complex to implement in stub, returns general purpose value
		return monthReports;
	}
	
	
	
	/**
	 * @param element client to look into
	 * @return current revenue from services to client
	 */
	
	public double getClientCurrentRevenue(Client element)
	{
		//custom SQL query, complex to implement in stub, returns general purpose value
		return 11.9;
	}
	
	
	/**
	 * @param element client to look into
	 * @return current cost of expenses
	 */
	
	public double getClientCurrentExpenses(Client element)
	{
		//custom SQL query, complex to implement in stub, returns general purpose value
		return 119.5;
	}
	
	
	/** 
	 * @param element Service of Interest
	 * @return Ex3penses + revenue for services for month
	 */
	
	public ArrayList<MonthReport> getLastYearServiceExpenses(ServiceType element)
	{
		//custom SQL query, complex to implement in stub, returns general purpose value
		return monthReports;
	}
	
	/** 
	 * @param element Service of Interest
	 * @return Expenses + revenue for services for month
	 */
	
	public ArrayList<MonthReport> getLastYearServiceRevenue(ServiceType element)
	{
		//custom SQL query, complex to implement in stub, returns general purpose value
		return monthReports;
	}
	
	/** GET ALLCLIENTRETURNS()
	 * 
	 * Returns the sum of all possible returns on a client.
	 */
	
	public double getAllClientReturns(Client element)
	{
		//custom SQL query, complex to implement in stub, returns general purpose value
		return 35.5;
	}
	
	/**DUMPCLIENTS()
	 * 
	 * Returns all clients on the DBMS;
	 * 
	 */
	
	public ArrayList<Client> dumpClients()
	{
		if (clients.size() != 0)
			return clients;
		else
			return null;
	}

	/**DUMPSERVICES()
	 * 
	 * Returns all services on the DBMS;
	 * 
	 */

	public ArrayList<Service> dumpServices()
	{
		if (services.size() != 0)
			return services;
		else
			return null;
	}
	
	/**DUMPCONTRACTS()
	 * 
	 * Returns all contracts on the DBMS;
	 * 
	 */
	
	public ArrayList<Contract> dumpContracts()
	{
		if (contracts.size() != 0)
			return contracts;
		else
			return null;
	}
	
	/**DUMPTRACKEDFEATURES()
	 * 
	 * Returns all tracked features on the DBMS;
	 * 
	 */
	
	public ArrayList<TrackedFeature> dumpTrackedFeatures()
	{
		if (trackedFeatures.size() != 0)
			return trackedFeatures;
		else
			return null;
	}
	
	
	/**DUMPTRACKEDFEATURETYPES()
	 * 
	 * Returns all tracked feature types on the DBMS;
	 * 
	 */
	
	public ArrayList<TrackedFeatureType> dumpTrackedFeatureTypes()
	{
		if (trackedFeatureTypes.size() != 0)
			return trackedFeatureTypes;
		else
			return null;
	}
	
	/**DUMPSERVICETYPES()
	 * 
	 * Returns all service types on the DBMS;
	 * 
	 */
	
	public ArrayList<ServiceType> dumpServiceTypes()
	{
		if (serviceTypes.size() != 0)
			return serviceTypes;
		else
			return null;
	}

	/**DUMPEXPENSES()
	 * 
	 * Returns all service types on the DBMS;
	 * 
	 */
	
	public ArrayList<Expense> dumpExpenses()
	{
		if (expenses.size() != 0)
			return expenses;
		else
			return null;
	}
	
	/**
	 * 	IDEXISTS()
	 * 
	 *  @param	int id	-	ID to search for
	 *  
	 *  @return	Boolean		If this ID already exists on the DBMS
	 */	
	
	public boolean idExists(Storable storableTemplate)
	{
		//Difficut to test due to being SQL reliant
		return true;
	}
	
	public boolean userLogin(String name, String login)
	{
		boolean output = false;
	
		for(int i = 0; i < users.size(); i++)
		{
			if(users.get(i).getUserName().compareTo(name) ==0 && users.get(i).getPassword().compareTo(login) == 0)
				output = true;
		}
		
		return output;
	}
	
	/**
	 * 	batchMerge()
	 * 
	 *  @param	ArrayList<Storable>	-	Bulk Storable to insert into DBMS
	 *  
	 *  @return	Boolean					If insert completed successfully.
	 */	
	
	public boolean batchMerge(ArrayList<Storable> batch)
	{
		boolean output = true;
		for(int i = 0; i < batch.size() && output; i++)
		{
			if(idExists(batch.get(i)))
				output = update(batch.get(i));
			else
			{
				output = insert(batch.get(i));
			}
		}
		
		return output;
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
	
	public void errorMessage(String retrieve, String invalid, String instruction)
	{
		System.out.println("ATTEMPTING TO RETRIEVE "+retrieve+" FROM "+invalid+" PLEASE "+instruction+" AND TRY AGAIN.\n\n");
	}

	public ArrayList<TrackedFeatureType> getFeatureTypeByClient(Client client) {
		ArrayList<TrackedFeatureType> storage = new ArrayList<TrackedFeatureType>();
		ArrayList<TrackedFeature> feats = new ArrayList<TrackedFeature>();
		ArrayList<TrackedFeatureType> ids = new ArrayList<TrackedFeatureType>();
		boolean insert = true;
		
		if(client != null && client.getID() > 0)
		{
			feats = this.getTrackedFeaturesByClient(client);
			
			if(feats != null)
			{
				for(int i = 0; i < feats.size(); i++)
				{
					if(ids.size() == 0)
						ids.add(feats.get(i).getTrackedFeatureType());
					else
					{
						for(int j = 0; j < ids.size() && insert; j++)
						{
							if(feats.get(i).getTrackedFeatureType().getID() == ids.get(j).getID())
								insert  = false;
						}
						if(insert)
								ids.add(feats.get(i).getTrackedFeatureType());
						
						insert = true;
					}
				}
			}
			
			if(ids.size() < 1)
			{
				return null;
			}
			else
			{
				return ids;
			}
		}
		else
		{
			return null;
		}
	}

	public ArrayList<MonthReport> getLastYearClientFeaturesByType(Client client, TrackedFeatureType feat) 
	{
		return monthReports2;
	}

	public ArrayList<MonthReport> getSumFeatures(Client client,TrackedFeatureType feat) 
	{
		return monthReports2;
	}

	@Override
	public double getTotalAllFeatures(Client client, TrackedFeatureType type) 
	{
		return 23212.0;
	}
}
