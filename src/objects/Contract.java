package objects;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import objects.Service;

/**
 * Handles the service rendered between the company and their client
 */
//public class Contract implements Storable, Financials
public class Contract implements Storable
{
	private final String DATE_FORMAT = "yyyy-MM-dd";	//The string date representation
	
	private int contractNumber; 	//Contract ID number
	private String businessName;	//Name of the associated business
	private String details;			//Specific details of the contract
	private double value;			//Value of the contract
	private Date period;			//Contract period (end date)
	private SimpleDateFormat sdf;
	private ArrayList<Service> services; //list of services in the contract
	private Date signedDate;
	private String tableName;
	
	/**
	 * Creates a new contract
	 * @param contractNumber 	The number of the contract
	 * @param businessName 	 	The name of the business
	 * @param details			The details of the contract
	 * @param value				The amount the contract is worth
	 * @param period			The period of the contract
	 * 
	 */
	public Contract(int contractNumber, String businessName, String details, double value, Date period) throws IllegalArgumentException
	{
		if(contractNumber >= 0 && businessName != null && !businessName.isEmpty() && details != null && !details.isEmpty() && value >= 0 && period != null)
		{
			this.contractNumber = contractNumber;
			this.businessName = businessName;
			this.details = details;
			this.value = value;
			this.period = period;
			this.sdf = new SimpleDateFormat(DATE_FORMAT);
			this.signedDate = null;
			this.tableName = "CONTRACTS";
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Creates a new contract without a preset number. Used for adding new contracts to DBMS.
	 * @param contractNumber 	The number of the contract
	 * @param businessName 	 	The name of the business
	 * @param details			The details of the contract
	 * @param value				The amount the contract is worth
	 * @param period			The period of the contract
	 * 
	 */
	public Contract(String businessName, String details, double value, Date period) throws IllegalArgumentException
	{
		if(contractNumber >= 0 && businessName != null && !businessName.isEmpty() && details != null && !details.isEmpty() && value >= 0 && period != null)
		{
			this.contractNumber = 0;
			this.businessName = businessName;
			this.details = details;
			this.value = value;
			this.period = period;
			this.sdf = new SimpleDateFormat(DATE_FORMAT);
			this.services = null;
			this.tableName = "CONTRACTS";
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	
	/*********************************************************
	 * 				Accessors and Mutators
	 ********************************************************/
	
	/*
	 * set the date when the contract was signed
	 */
	public void setSignedDate(Date date){
		this.signedDate = date;
	}
	
	/*
	 * returns the date the contract was signed
	 */
	public Date getSignedDate(){
		return signedDate;
	}
	
	/**
	 * Get the number of the contract
	 * @return The contract number
	 */
	public int getID() 
	{
		return contractNumber;
	}

	/**
	 * Gets the business name
	 * @return The business' name
	 */
	public String getBusinessName()
	{
		return businessName;
	}
	
	/*
	 * returns the list of services assigned to this contract
	 */
	public ArrayList<Service> getServices()
	{
		return services;
	}
	
	/*
	 * Assign a list of services to this contract
	 */
	public void addServices(ArrayList<Service> services)
	{
		this.services = services;
	}
	
	/*
	 * Remove services from contract
	 */
	public void removeService(Service service)
	{
		this.services.remove(service);
	}

	/**
	 * Sets the name of the business
	 * @param businessName The business name
	 */
	public void setBusinessName(String businessName) 
	{
		assert (businessName != null && !businessName.isEmpty());
		if(businessName != null && !businessName.isEmpty())
			this.businessName = businessName;
	}

	/**
	 * Gets the contract details
	 * @return The contract details
	 */
	public String getDetails() 
	{
		return details;
	}

	/**
	 * Sets the additional data of a contract
	 * @param details The contract details
	 */
	public void setDetails(String details) 
	{
		assert (details != null && !details.isEmpty());
		if(details != null && !details.isEmpty())
			this.details = details;
	}

	/**
	 * Gets the value of the contract
	 * @return The value of the contract
	 */
	public double getValue() 
	{
		return value;
	}

	/**
	 * Sets the amount of the contract
	 * @param value A non-negative value
	 */
	public void setValue(double value) 
	{
		assert (value >= 0);
		if(value >= 0)
			this.value = value;
	}

	/**
	 * Gets the period of the contract
	 * @return The contract period
	 */
	public Date getPeriod() 
	{
		return period;
	}

	/**
	 * Sets the contract period
	 * @param period	Period of the contract
	 */
	public void setPeriod(Date period) 
	{
		assert (period != null);
		if(period != null)
			this.period = period;
	}
	

	
	public String toString()
	{
		return "(Contract ID: "+this.contractNumber+
				", Business Name: " +this.businessName +
				", Details: " + this.details +
				", Value: " + String.format("%.2f", this.value) +
				", End Date: " + this.period.toString() +")";
	}
	
	/**TOINDEX()
	 * 
	 * Returns an ArrayList containing the values of this object in the order they
	 * appear on the DBMS.
	 */
	
	public ArrayList<String> toIndex()
	{
		ArrayList<String> index = new ArrayList<String>();
		
		index.add(""+this.contractNumber+"");
		index.add(this.businessName);
		index.add(this.details);
		index.add(String.format("%.2f", this.value));
		index.add(this.sdf.format(period));
		
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
}
