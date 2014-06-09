package objects;

import java.util.ArrayList;

/**
 * Base class for services
 */
public class Service implements Storable
{	
	private String title;			// Name of Service
	private String description;		// Description of service.
	private String type;			// General type of service, could be used for reinstantiating a subclass.
	private double rate;			// Rate at which this service is charged.
	private int id;					// Unique Row ID of service.
		
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
	public Service(int id, String title, String description, double rate, String type) throws IllegalArgumentException
	{
		if(title != null && !title.isEmpty() && id >= 0 && description != null && rate >= 0 && type != null && !type.isEmpty()) 
		{
			this.title = title;
			this.description = description;
			this.rate = rate;
			this.id = id;
			this.type = type;
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
	 * @throws IllegalArgumentException
	 */
	public Service(String title, String description, double rate, String type) throws IllegalArgumentException
	{		
		if(title != null && !title.isEmpty() && id >= 0 && description != null && rate >= 0 && type != null && !type.isEmpty()) 
		{
			this.title = title;
			this.description = description;
			this.rate = rate;
			this.id = 0;
			this.type = type;
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
	public String getType()
	{
		return this.type;
	}
	
	/**
	 * @return Gets the service ID
	 */
	public int getID()
	{
		return this.id;
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
	public void setType(String type)
	{
		assert (type != null && !type.isEmpty());
		if(type != null && !type.isEmpty())
			this.type = type;
	}
	
	/**
	 * Currently inactive, under normal conditions will perform either
	 * an INSERT or an UPDATE call to DBMS.
	 */	
	public void commit()
	{
		System.out.println("Currently not comitting service changes to the database.");
	}
	
	/**TOINDEX()
	 * 
	 * Returns an ArrayList containing the values of this object in the order they
	 * appear on the DBMS.
	 */
	
	public ArrayList<String> toIndex()
	{
		ArrayList<String> index = new ArrayList<String>();
		
		index.add(this.id+"");
		index.add(this.title);
		index.add(this.description);
		index.add(String.format("%.2f", this.rate));
		index.add(this.type);
			
		return index;
	}
	
	/**
	 * 
	 * NEW TOINDEX()
	 * 
	 * This can be moved in once the new service model has been implmented.
	 * 
	 */
	
	/*
	public ArrayList<String> toIndex()
	{
		ArrayList<String> index = new ArrayList<String>();
		
		index.add(this.id+"");
		index.add(this.title);
		index.add(this.description);
		index.add(String.format("%.2f", this.rate));
		index.add(this.payPeriod);
		index.add(String.format("%.2f", this.secondaryRate));
		index.add(this.secondPayPeriod);
		index.add(this.serviceRenewal);
		index.add(this.type);
			
		return index;
	}
	*/
	
	/**
	 * @returns The string representation of the service
	 */
	public String toString()
	{
		return "(Title: " + this.title + 
				", Description: " + this.description + 
				", Rate: " + this.rate + 
				", Id: " + this.id +
				", Type: " + this.type +")";
	}
}
