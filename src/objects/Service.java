package objects;

import java.util.ArrayList;

import business.ProcessClient;

/**
 * Base class for services
 */
public class Service implements Storable, Trackable
{	
	private String title;			// Name of Service
	private String description;		// Description of service.
	private double rate;			// Rate at which this service is charged.
	private int id;					// Unique Row ID of service.
	private int clientID;			// Client owning this service.
	private int contractID;			// COntract this service is attached to.
	private String contractDetails;
	private String tableName;
	private ServiceType typeS;
		
	/**
	 * Creates a new service
	 *  
	 * @param id The ID of the service
	 * @param title The service title
	 * @param description The description of the service
	 * @param rate The rate of the service
	 * @param type The type of service
	 * @throws IllegalArgumentException
	 */
	public Service(int id, String title, String description, double rate, ServiceType typeS) throws IllegalArgumentException
	{
		if(title != null && 
			!title.isEmpty() && 
			id >= 0 && 
			description != null && 
			rate >= 0 && 
				typeS != null) 
		{
			this.title = title;
			this.description = description;
			this.rate = rate;
			this.id = id;
			this.clientID = -1;
			this.contractID = -1;
			this.tableName = "SERVICES";
			this.typeS = typeS;
			this.contractDetails = "";
		}
		else
		{
			// Reject objects with undefined names or invalid ID's
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Creates a new service
	 * Business Logic constructor, used if a new service is created within the program
	 *  
	 * @param id The ID of the service
	 * @param title The service title
	 * @param description The description of the service
	 * @param rate The rate of the service
	 * @param typeS the type of the service
	 * @throws IllegalArgumentException
	 */
	public Service(String title, String description, double rate, ServiceType typeS) throws IllegalArgumentException
	{		
		if(title != null && 
			!title.isEmpty() && 
			id >= 0 && 
			description != null && 
			rate >= 0  && 
			typeS != null) 
		{
			this.title = title;
			this.description = description;
			this.rate = rate;
			this.id = 0;;
			this.tableName = "SERVICES";
			this.clientID = -1;
			this.contractID = -1;
			this.typeS = typeS;
		}
		else
		{
			// Reject objects with undefined names or invalid ID's
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Creates a new service
	 * Business Logic constructor, used if a new service is created within the program
	 *  
	 * @param id The ID of the service
	 * @param title The service title
	 * @param description The description of the service
	 * @param rate The rate of the service
	 * @param type The type of service
	 * @param ClientID associated with service.
	 * @param ContractID associated with service.
	 * @throws IllegalArgumentException
	 */
	public Service(int id, String title, String description, double rate, int clientID, 
			int contractID, ServiceType typeS, String contractDetails) throws IllegalArgumentException
	{		
		if(title != null && 
			!title.isEmpty() && 
			id >= 0 && 
			(clientID >= 0 || contractID >= 0) && 
			description != null && 
			rate >= 0 && 
			typeS != null && 
			contractDetails != null) 
		{
			this.title = title;
			this.description = description;
			this.rate = rate;
			this.id = id;
			this.tableName = "SERVICES";
			this.clientID = clientID;
			this.contractID = contractID;
			this.typeS = typeS;
			this.contractDetails = contractDetails;
		}
		else
		{
			// Reject objects with undefined names or invalid ID's
			throw new IllegalArgumentException();
		}
	}
	
	//-------------
	//	GETTERS
	//-------------

	/**
	 * @return Gets the service title
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * @return Gets the description of the service
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * @return Gets the service rate
	 */
	public double getRate()
	{
		return rate;
	}
	
	/**
	 * @return Gets the type of the service
	 */
	public ServiceType getServiceType()
	{
		return this.typeS;
	}
	
	/**
	 * @return Gets the service ID
	 */
	public int getID()
	{
		return this.id;
	}
	
	/**
	 * returns the contract ID
	 */
	public int getContractID(){
		return contractID;
	}
	
	/**
	 * return the client id
	 */
	public int getClientID()
	{
		return clientID;
	}
	
	/**
	 * @return Gets the client associated with this service, null if no client
	 */
	public Client getAssociatedClient()
	{
		return (new ProcessClient()).getClientByID(clientID);
	}
	
	/**
	 * @return returns a string containing the contract details for this service
	 */
	
	public String getContractDetails()
	{
		return this.contractDetails;
	}
	
	
	//-------------
	//	SETTERS
	//-------------
	
	/**
	 * Sets the service title
	 * @param title The service title
	 */
	public void setTitle(String title)
	{
		assert (title != null && !title.isEmpty());
		if(title != null && !title.isEmpty())
			this.title = title;
	}
	
	/**
	 * Sets the service description 
	 * @param description The service description
	 */
	public void setDescription(String description)
	{
		assert (description != null);
		if(description != null)
			this.description = description;
	}
	
	/**
	 * Sets the service rate
	 * @param rate The service rate
	 */
	public void setRate(double rate)
	{
		assert (rate >= 0);
		if(rate >= 0)
			this.rate = rate;
	}
	
	/**
	 * Sets the service type
	 * @param type The service type
	 */
	public void setType(ServiceType type)
	{
		assert (type != null);
		if(type != null)
			this.typeS = type;
	}
	
	/**
	 * Sets the service id
	 * @param type The service type
	 */
	public void setClientID(int id)
	{
		assert (id >= 0);
		if(id >= 0)
			this.clientID = id;
	}
	

	/**
	 * Sets the service id
	 * @param type The service type
	 */
	public void setContractID(int id)
	{
		assert (id >= 0);
		if(id >= 0)
			this.contractID = id;
	}
	
	/**
	 * Sets the contract details 
	 * @param description The contract description
	 */
	public void setContractDetails(String details)
	{
		assert (details != null);
		if(details != null)
			this.contractDetails = details;
	}
	
	//-------
	// OTHER
	//-------
	
	
	/**TOINDEX()
	 * 
	 * Returns an ArrayList containing the values of this object in the order they
	 * appear on the DBMS.
	 */
	
	public ArrayList<String> toIndex()
	{
		ArrayList<String> index = new ArrayList<String>();
		
		index.add(this.id+"");
		index.add(this.getServiceType().getID()+"");
		index.add(this.title);
		index.add(this.description);
		index.add(String.format("%.2f", this.rate));
		index.add(""+this.clientID);
		index.add(""+this.contractID);
		index.add(this.contractDetails);
		
		return index;
	}
	
	/**GETTABLENAME()
	 * 
	 * Returns the table name of this object.
	 */
	
	public String getTableName()
	{
		return this.tableName;
	}
	
	/**
	 * @returns The string representation of the service
	 */
	public String toString()
	{
		return "(Title: " + this.title + 
				", Description: " + this.description + 
				", Rate: " + this.rate + 
				", Id: " + this.id +
				", Type: " + this.typeS.getType() +")";
	}
	
	
	/**
	 * Returns true if the object is in a valid state to be inserted into a table
	 */
	
	public boolean isInsertable() 
	{
		boolean output = true;
		
		if(this.contractID < 0)
		{
			output = false;
		}
		
		if(this.typeS == null)
		{
			output = false;
		}
		
		return output;		
	}
}
