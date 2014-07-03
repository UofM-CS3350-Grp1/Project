package objects;

import java.util.ArrayList;

public class ServiceType implements Storable
{
	private int typeID;
	private String type;
	private String description;
	
	public ServiceType(String type, String description) throws IllegalArgumentException
	{
		if((type != null && description != null) && (!type.isEmpty() && !description.isEmpty()))
		{
			this.typeID = -1;
			this.description = description;
			this.type = type;
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
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<String> toIndex() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInsertable() {
		// TODO Auto-generated method stub
		return false;
	}

}
