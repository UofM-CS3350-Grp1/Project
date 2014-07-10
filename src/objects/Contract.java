package objects;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import objects.Service;

/**
 * Handles the service rendered between the company and their client
 */
public class Contract implements Storable
{
	private final String DATE_FORMAT = "yyyy-MM-dd";	//The string date representation
	private final String[] STATUS_TYPE = {"Pending", "Signed", "Cancelled", "Terminated"};
	
	private int contractNumber; 		 //Contract ID number
	private String businessName;		 //Name of the associated business
	private String details;				 //Specific details of the contract
	private double value;				 //Value of the contract
	private Date period;				 //Contract period (end date)
	private SimpleDateFormat sdf;		 //Date formatter
	private ArrayList<Service> services; //list of services in the contract
	private Date signedDate;			 //Date contract was signed
	private Date startDate;				 //Date contract starts
	private String header;				 //Header text
	private String footer;				 //FooterText
	private String tableName;			 //Name of table for insertion
	private String status;				 //status of contract;

	/**
	 * Creates a new contract
	 * @param contractNumber 	The number of the contract
	 * @param businessName 	 	The name of the business
	 * @param details			The details of the contract
	 * @param value				The amount the contract is worth
	 * @param period			The period of the contract
	 * @param signedDate		The date the contract was signed
	 * 
	 */
	public Contract(int contractNumber, String businessName, String details, double value, 
			Date period, String header, String footer, Date signedDate, Date startDate, String status) throws IllegalArgumentException
	{
		if(contractNumber >= 0 && 
				businessName != null && 
				!businessName.isEmpty() && 
				details != null  && 
				!details.isEmpty() && 
				value >= 0 && 
				period != null && (header != null && footer != null) && 
				signedDate != null &&
				startDate != null &&
				(status.compareTo(STATUS_TYPE[0]) ==0 || status.compareTo(STATUS_TYPE[1]) ==0 || 
				status.compareTo(STATUS_TYPE[2]) ==0 || status.compareTo(STATUS_TYPE[3]) ==0))
		{
			this.contractNumber = contractNumber;
			this.businessName = businessName;
			this.details = details;
			this.value = value;
			this.period = period;
			this.sdf = new SimpleDateFormat(DATE_FORMAT);
			this.signedDate = signedDate;
			this.startDate = startDate;
			this.header = header;
			this.footer = footer;
			this.tableName = "CONTRACTS";
			this.status = status;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Creates a new contract (without header and footer)
	 * @param contractNumber 	The number of the contract
	 * @param businessName 	 	The name of the business
	 * @param details			The details of the contract
	 * @param value				The amount the contract is worth
	 * @param period			The period of the contract
	 * @param signedDate		The date the contract was signed
	 * 
	 */
	public Contract(int contractNumber, String businessName, String details, double value, 
			Date period, Date signedDate, Date startDate) throws IllegalArgumentException
	{
		if(contractNumber >= 0 && 
				businessName != null && 
				!businessName.isEmpty() && 
				details != null  && 
				!details.isEmpty() && 
				value >= 0 && 
				period != null && 
				signedDate != null &&
				startDate != null)
		{
			this.contractNumber = contractNumber;
			this.businessName = businessName;
			this.details = details;
			this.value = value;
			this.period = period;
			this.sdf = new SimpleDateFormat(DATE_FORMAT);
			this.signedDate = signedDate;
			this.startDate = startDate;
			this.tableName = "CONTRACTS";
			this.status = STATUS_TYPE[0];
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
	 * @param signedDate		The date the contract was signed
	 * 
	 */
	public Contract(String businessName, String details, double value, 
			Date period, Date signedDate, Date startDate) throws IllegalArgumentException
	{
		if(contractNumber >= 0 && 
				businessName != null && 
				!businessName.isEmpty() && 
				details != null  && 
				!details.isEmpty() && 
				value >= 0 && 
				period != null&&
				signedDate != null &&
				startDate != null)
		{
			this.contractNumber = 0;
			this.businessName = businessName;
			this.details = details;
			this.value = value;
			this.period = period;
			this.signedDate = signedDate;
			this.startDate = startDate;
			this.sdf = new SimpleDateFormat(DATE_FORMAT);
			this.services = null;
			this.header ="";
			this.footer ="";
			this.tableName = "CONTRACTS";
			this.status = STATUS_TYPE[0];
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	
	/*********************************************************
	 * 				Accessors and Mutators
	 ********************************************************/
	
	/**
	 * @date date Set the date when the contract was signed
	 */
	public void setSignedDate(Date date)
	{
		assert (date != null);
		if(date != null)
			this.signedDate = date;
	}
	
	/**
	 * @date date Set the date when the contract was signed
	 */
	public void setStartDate(Date date)
	{
		assert (date != null);
		if(date != null)
			this.startDate = date;
	}
	
	/**
	 * @return the date the contract was signed
	 */
	public Date getSignedDate()
	{
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
	
	/**
	 * @return the list of services assigned to this contract
	 */
	public ArrayList<Service> getServices()
	{
		return services;
	}
	
	/**
	 * @return the status assigned of this contract
	 */
	public String getStatus()
	{
		return this.status;
	}
	
	/**
	 * Assign a list of services to this contract
	 * 
	 * @param service The list of services
	 */
	public void addServices(ArrayList<Service> services)
	{
		this.services = services;
	}
	
	
	/**
	 * Remove services from contract
	 * 
	 * @param service The service to remove
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
	
	/**
	 * @return Gets the period formatted to a string
	 */
	public String getFormattedPeriod()
	{
		return sdf.format(period);
	}
	
	/**
	 * @return Gets the signed date formatted to a string
	 */
	public String getFormattedSignedDate()
	{
		return (signedDate != null) ? sdf.format(signedDate) : "Not signed";
	}
	
	/**
	 * @return Gets the signed date formatted to a string
	 */
	public String getFormattedStartDate()
	{
		return (startDate != null) ? sdf.format(startDate) : "Not signed";
	}
	
	/**
	 * @return The contract header String
	 */
	
	public String getHeader()
	{
		return this.header;
	}
	
	/**
	 * @param input New value for header
	 */
	
	public void setHeader(String input)
	{
		if(input != null)
			this.header = input;
	}
	
	/**
	 * @return The contract footer String
	 */
	
	public String getFooter()
	{
		return this.header;
	}
	
	
	/**
	 * @param input New value for footer
	 */
	
	public void setFooter(String input)
	{
		if(input != null)
			this.footer = input;
	}
	
	public void setStatus(String status)
	{
		boolean insert = false;
		for(int i = 0; i< STATUS_TYPE.length;i++)
		{
			if(status.compareTo(STATUS_TYPE[i]) == 0)
				insert = true;
		}
		
		if(insert)
			this.status = status;
	}
	
	/**
	 * ToString method for contracts.
	 */
	
	public String toString()
	{
		return "(Contract ID: "+this.contractNumber+
				", Business Name: " +this.businessName +
				", Details: " + this.details +
				", Value: " + String.format("%.2f", this.value) +
				", End Date: " + this.period.toString() +")"+
				", Start Date: "+ this.signedDate.toString();
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
		index.add(this.header);
		index.add(this.footer);
		index.add(sdf.format(signedDate));
		index.add(sdf.format(startDate));
		index.add(this.status);
		
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
	
	public boolean isInsertable() 
	{
		boolean output = false;
		
		if(this.signedDate != null && this.period != null && this.businessName != null && !this.businessName.isEmpty())
			output = true;
		
		return output;		
	}
}
