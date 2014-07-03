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


	public int getID() 
	{
		return this.typeID;
	}

	@Override
	public ArrayList<String> toIndex() 
	{
		ArrayList<String> index = new ArrayList<String>();
		
		if(this.typeID >= 0)
		{
			index.add(this.typeID+"");
			index.add(this.type);
			index.add(this.description);
		}
		else
			index = null;
		
		return index;
	}

	public String getTableName()
	{
		return this.tableName;
	}

	public boolean isInsertable() 
	{
		return true;
	}

}
