package objects;

import java.util.ArrayList;

//----------------------------------------------------------------------------
//	Class: Service
//
//	Def: Base class for services.
//----------------------------------------------------------------------------

public class Service implements Storable
{	
	private String title;			// Name of Service
	private String description;		// Description of service.
	private String type;			// General type of service, could be used for reinstantiating a subclass.
	private double rate;				// Rate at which this service is charged.
	private int id;					// Unique Row ID of service.
	
	
	/**
	 * NEW DATA MEMBERS FOR SERVICE, can be moved in once code is adjusted.
	 */
	
	private String payPeriod;		//month/week/year/now
	private double secondaryRate;	//optional secondary rate for maintenance
	private String secondPayPeriod;	//secondary pay period
	private String serviceRenewal;	// Monthly/Yearly/Per Request

	
	
	//-------------
	//	CONSTRUCT
	//-------------
	

	/**
	 * NEW SERVICE
	 * 
	 * This the the new model for services, please make the needed changes to your code and we will move this
	 * into production.
	 * 
	 * Note that "Type" is moved to the end of the constructor, to allow for the new variables.
	 * 
	 * @param id
	 * @param title
	 * @param description
	 * @param rate
	 * @param payPeriod
	 * @param secondaryRate
	 * @param secondaryPayPeriod
	 * @param serviceRenewal
	 * @param type
	 * @throws IllegalArgumentException
	 */
	
	/*
	public Service(
					int id,
					String title,
					String description,
					double rate,
					String payPeriod,
					double sdcondaryRate,
					String secondaryPayPeriod,
					String serviceRenewal,
					String type
					) throws IllegalArgumentException
	{
		if(title == null || title.isEmpty() || id < 0 || description == null ||
				rate < 0 || type == null || type.isEmpty()) // Reject objects with undefined names or invalid ID's
		{
			throw new IllegalArgumentException();
		}
		
		this.title = title;
		this.description = description;
		this.rate = rate;
		this.id = id;
		this.type = type;
		this.payPeriod = payPeriod;
		this.secondaryRate = secondaryRate;
		this.secondPayPeriod = secondaryPayPeriod;
		this.serviceRenewal = serviceRenewal;
		
	}
	*/
		
	/*
		public Service(
					String title,
					String description,
					double rate,
					String payPeriod,
					double sdcondaryRate,
					String secondaryPayPeriod,
					String serviceRenewal,
					String type
					) throws IllegalArgumentException
	{
		if(title == null || title.isEmpty() || id < 0 || description == null ||
				rate < 0 || type == null || type.isEmpty()) // Reject objects with undefined names or invalid ID's
		{
			throw new IllegalArgumentException();
		}
	
		this.title = title;
		this.description = description;
		this.rate = rate;
		this.id = 0;
		this.type = type;
		this.payPeriod = payPeriod;
		this.secondaryRate = secondaryRate;
		this.secondPayPeriod = secondaryPayPeriod;
		this.serviceRenewal = serviceRenewal;
	}
	*/
	
	
	public Service(
				int id,
				String title,
				String description,
				double rate,
				String type
				) throws IllegalArgumentException
	{
	if(title == null || title.isEmpty() || id < 0 || description == null ||
			rate < 0 || type == null || type.isEmpty()) // Reject objects with undefined names or invalid ID's
	{
		throw new IllegalArgumentException();
	}
	
	this.title = title;
	this.description = description;
	this.rate = rate;
	this.id = id;
	this.type = type;
	}
	
	//----------------------------------------------------------------------------
	//	Business Logic constructor, used if a new service is created 
	//	within the program
	//----------------------------------------------------------------------------
	
	public Service(
					String title,
					String description,
					double rate,
					String type
					) throws IllegalArgumentException
	{
		if(title == null || title.isEmpty() || id < 0 || description == null ||
				rate < 0 || type == null || type.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		
		this.title = title;
		this.description = description;
		this.rate = rate;
		this.id = 0;
		this.type = type;
	}
	
	
	
	//----------------------------------------------------------------------------
	//	Null constructor, currently creates a test service
	//----------------------------------------------------------------------------
	
	//TODO: Remove this and replace with a proper null constructor as needed.
	
	public Service()
	{
		this.title = "A Service";
		this.description = "This is a test service";
		this.rate = 10.00f;
		this.id = 1;
		this.type = "SOCIAL_MEDIA";
	}
	
	//-------------
	//	GETTERS
	//-------------

	public String getTitle()
	{
		return title;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public double getRate()
	{
		return rate;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public int getID()
	{
		return this.id;
	}
	
	//-------------
	//	SETTERS
	//-------------
	
	public void setTitle(String title)
	{
		assert (title != null && !title.isEmpty());
		if(title != null && !title.isEmpty())
			this.title = title;
	}
	
	public void setDescription(String description)
	{
		assert (description != null);
		if(description != null)
			this.description = description;
	}
	
	public void setRate(double rate)
	{
		assert (rate >= 0);
		if(rate >= 0)
			this.rate = rate;
	}
	
	public void setType(String type)
	{
		assert (type != null && !type.isEmpty());
		if(type != null && !type.isEmpty())
			this.type = type;
	}
	
	//----------------------------------------------------------------------------
	//	COMMIT()
	//
	//	PARAMS: NONE
	//
	//	NOTES: 	Currently inactive, under normal conditions will perform either
	//			an INSERT or an UPDATE call to DBMS.
	//----------------------------------------------------------------------------
	
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
	
	//----------------------------------------------------------------------------
	//	TOSTRING()
	//
	//	PARAMS: NONE
	//
	//	NOTES: 	Standard toString() method.
	//----------------------------------------------------------------------------
	
	public String toString()
	{
		return "(Title: " + this.title + 
				", Description: " + this.description + 
				", Rate: " + this.rate + 
				", Id: " + this.id +
				", Type: " + this.type +")";
	}
}
