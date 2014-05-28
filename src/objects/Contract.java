package objects;

import java.util.Date;

/**
 * Handles the service rendered between the company and their client
 */
public class Contract implements Storable
{
	private int contractNumber; 	//Contract ID number
	private String businessName;	//Name of the associated business
	private String details;			//Specific details of the contract
	private double value;			//Value of the contract
	private Date period;			//Contract period (end date)
	
	/**
	 * Creates a new contract
	 * @param contractNumber 	The number of the contract
	 * @param businessName 	 	The name of the business
	 * @param details			The details of the contract
	 * @param value				The amount the contract is worth
	 * @param period			The period of the contract
	 */
	public Contract(int contractNumber, String businessName, String details, double value, Date period)
	{
		this.contractNumber = contractNumber;
		this.businessName = businessName;
		this.details = details;
		this.value = value;
		this.period = period;
	}
	
	/*********************************************************
	 * 				Accessors and Mutators
	 ********************************************************/
	
	/**
	 * Get the number of the contract
	 * @return The contract number
	 */
	public int getContractNumber() 
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
	 * Sets the name of the business
	 * @param businessName The business name
	 */
	public void setBusinessName(String businessName) 
	{
		assert (businessName != null && businessName != "");
		if(businessName != null && businessName != "")
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
		assert (details != null);
		if(details != null)
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
}
