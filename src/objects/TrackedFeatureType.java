package objects;

import java.util.ArrayList;

public class TrackedFeatureType implements Storable 
{
	private int typeID;
	private String type;
	private String geneticTitle;
	private String tableName;
	
	public TrackedFeatureType(String type, String title) throws IllegalArgumentException
	{
		if((type != null && title != null) && (!type.isEmpty() && !title.isEmpty()))
		{
			this.typeID = -1;
			this.geneticTitle = title;
			this.type = type;
			this.tableName = "FEATURE_TYPES";
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	public TrackedFeatureType(int id, String title, String type) throws IllegalArgumentException
	{
		if(id >= 0 && (type != null && title != null) && (!type.isEmpty() && !title.isEmpty()))
		{
			this.typeID = id;
			this.geneticTitle = title;
			this.type = type;
			this.tableName = "FEATURE_TYPES";
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


	public ArrayList<String> toIndex() 
	{ 
		ArrayList<String> index = new ArrayList<String>();
		
		if(this.typeID >= 0)
		{
			index.add(this.typeID+"");
			index.add(this.geneticTitle);
			index.add(this.type);
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
