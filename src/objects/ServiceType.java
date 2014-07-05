package objects;

import java.util.ArrayList;

public class ServiceType implements Storable
{
	private int typeID;
	private String type;
	private String description;
	private String tableName;
	
	public ServiceType(String type, String description) throws IllegalArgumentException
	{
		if((type != null && description != null) && (!type.isEmpty() && !description.isEmpty()))
		{
			this.typeID = -1;
			this.description = description;
			this.type = type;
			this.tableName = "SERVICES_TYPES";
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	public ServiceType(int id, String type, String description) throws IllegalArgumentException
	{
		if(id >= 0 && (type != null && description != null) && (!type.isEmpty() && !description.isEmpty()))
		{
			this.typeID = id;
			this.description = description;
			this.type = type;
			this.tableName = "SERVICES_TYPES";
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	//---------
	//GETTERS
	//---------
	
	/**
	 * @return Returns a string containing the service type
	 */
	
	public String getType()
	{
		return this.type;
	}

	/**
	 * @return returns a string containing the qunique service ID
	 */
	
	public int getID() 
	{
		return this.typeID;
	}
	
	/**
	 * @return returns a string containing the appropriate table for this object
	 */
	
	public String getTableName()
	{
		return this.tableName;
	}
	
	/**
	 * @return returns a string containing this item's description
	 */
	
	public String getDescription()
	{
		return this.description;
	}
	
	//---------
	//SETTERS
	//---------

	/**
	 * @param type String describing object's new type
	 */
	
	public void setType(String type)
	{
		if(type != null && !type.isEmpty())
			this.type = type;
	}
	
	/**
	 * @param type String describing object's new description
	 */
	
	public void setDescription(String description)
	{
		if(description != null && !description.isEmpty())
			this.description = description;
	}

	/**
	 * Returns the table friendly value of this object in an array list
	 */
	
	public ArrayList<String> toIndex() 
	{
		ArrayList<String> index = new ArrayList<String>();
		

		index.add(this.typeID+"");
		index.add(this.type);
		index.add(this.description);

		return index;
	}

	/**
	 * Checks to validate if this object is in a valid state to be inserted into a table
	 */
	
	public boolean isInsertable() 
	{
		boolean output = false;
		if(type != null && !type.isEmpty())
		{
			output = true;
		}
		return output;
	}

}
