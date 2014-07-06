package objects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Expense implements Storable
{
	private final String DATE_FORMAT = "yyyy-MM-dd";	//The string date representation
	private int expenseID;
	private int serviceID;
	private String supplier;
	private SimpleDateFormat sdf;
	private double value;
	private String details;
	private Date dateIncurred;
	
	public Expense(int serviceID, String supplier, double value, String details, Date dateIncurred) throws IllegalArgumentException
	{
		if(serviceID > 0 && 
				!supplier.isEmpty() && 
				supplier!= null &&
				dateIncurred != null&&
				details!= null)
			{
				this.expenseID = -1;
				this.serviceID = serviceID;
				this.supplier = supplier;
				this.value = value;
				this.details = details;
				this.dateIncurred = dateIncurred;
				this.sdf = new SimpleDateFormat(DATE_FORMAT);
			}
			else
			{
				throw new IllegalArgumentException();
			}
	}
	
	public Expense(int expenseID, int serviceID, String supplier, double value, String details, Date dateIncurred) throws IllegalArgumentException
	{
		if(expenseID >0 && 
			serviceID > 0 && 
			!supplier.isEmpty() && 
			supplier!= null &&
			dateIncurred != null&&
			details!= null)
		{
			this.expenseID = expenseID;
			this.serviceID = serviceID;
			this.supplier = supplier;
			this.value = value;
			this.details = details;
			this.dateIncurred = dateIncurred;
			this.sdf = new SimpleDateFormat(DATE_FORMAT);
		}
		else
		{
			throw new IllegalArgumentException();
		}
		
	}
	
	//---------
	// GETTERS
	//---------
	
	int getServiceID()
	{
		return this.serviceID;
	}
	
	String getSupplier()
	{
		return this.supplier;
	}
	
	String getDetails()
	{
		return this.details;
	}
	
	double getValue()
	{
		return this.value;
	}
	
	Date getDateIncurred()
	{
		return this.dateIncurred;
	}
	
	//---------
	// SETTERS
	//---------

	void setServiceID(int id)
	{
		if(id > 0)
			this.serviceID = id;
	}
	
	void setSupplier(String insert)
	{
		if(insert != null && !insert.isEmpty())
			this.supplier = insert;
	}
	
	void setDetails(String insert)
	{
		if(insert != null && !insert.isEmpty())
			this.details = insert;
	}
	
	void setValue(float insert)
	{
		this.value = insert;
	}
	
	void setDate(Date insert)
	{
		if(insert != null)
			this.dateIncurred = insert;
	}
	
	
	public int getID()
	{
		return this.serviceID;
	}


	public ArrayList<String> toIndex()
	{
		ArrayList<String> index = new ArrayList<String>();
		
		index.add(this.expenseID+"");
		index.add(this.serviceID+"");
		index.add(this.supplier);
		index.add(this.value+"");
		index.add(this.details);
		index.add(this.sdf.format(this.dateIncurred));
		
		return index;
	}

	public String getTableName() 
	{
		return "EXPENSE";
	}

	public boolean isInsertable() 
	{
		boolean output = false;
		
		if(this.serviceID > 0 && !this.supplier.isEmpty() && this.supplier!= null && this.dateIncurred != null)
			output = true;
		
		return output;
	}

}
