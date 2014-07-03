package objects;

import java.util.ArrayList;

public class TrackedFeatureType implements Storable 
{
	private int typeID;
	private String type;
	private String geneticTitle;
	
	public TrackedFeatureType(String type, String title) throws IllegalArgumentException
	{
		if((type != null && title != null) && (!type.isEmpty() && !title.isEmpty()))
		{
			this.typeID = -1;
			this.geneticTitle = title;
			this.type = type;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	public TrackedFeatureType(int id, String type, String title) throws IllegalArgumentException
	{
		if(id >= 0 && (type != null && title != null) && (!type.isEmpty() && !title.isEmpty()))
		{
			this.typeID = id;
			this.geneticTitle = title;
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
