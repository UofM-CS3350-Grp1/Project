package objects;

import java.util.ArrayList;

public class TrackedFeatureType implements Storable 
{
	private int typeID;				//Unique id of this feature type
	private String type;			//The type of this feature (Feature/Expense)
	private String genericTitle;	//General title of the feature ie. "Facebook Likes", etc.
	private String tableName;		//The table this feature belongs to
	
	public TrackedFeatureType(String type, String title) throws IllegalArgumentException
	{
		if((type != null && title != null) && (!type.isEmpty() && !title.isEmpty()))
		{
			this.typeID = -1;
			this.genericTitle = title;
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
			this.genericTitle = title;
			this.type = type;
			this.tableName = "FEATURE_TYPES";
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	//---------
	// GETTERS
	//---------
	
	/**
	 * @return The type of this FeatureType
	 */
	public String getType()
	{
		return this.type;
	}
	
	/**
	 * @return The generic title of this FeatureType
	 */
	public String getTitle()
	{
		return this.genericTitle;
	}

	/**
	 * @return the unique id of this feature
	 */
	
	public int getID() 
	{
		return this.typeID;
	}
	
	/**
	 * @return The table name for this feature
	 */
	public String getTableName() 
	{
		return this.tableName;
	}

	//---------
	// SETTERS
	//---------	
	
	/**
	 * @param String to change this object's title to
	 */
	public void setTitle(String title)
	{
		if(title != null && !title.isEmpty())
			this.genericTitle = title;
	}
	
	/**
	 * Switches the object type between feature and expense
	 */
	
	public void switchType()
	{
		if(this.tableName.compareTo("Feature") == 0)
			this.type = "Expense";
		else
			this.type = "Feature";
	}
	

	//---------
	// OTHERS
	//---------
	
	/**
	 * Returns the table friendly value of this object in an array list
	 */
	
	public ArrayList<String> toIndex() 
	{ 
		ArrayList<String> index = new ArrayList<String>();
		
		if(this.typeID >= 0)
		{
			index.add(this.typeID+"");
			index.add(this.genericTitle);
			index.add(this.type);
		}
		else
			index = null;
		
		return index;
	}

	/**
	 * Checks to validate if this object is in a valid state to be inserted into a table
	 */
	
	public boolean isInsertable()
	{
		boolean output = false;
		
		if(this.type != null && (this.type.compareTo("Feature") == 0 || this.type.compareTo("Expense") == 0) && 
				this.genericTitle != null && !this.genericTitle.isEmpty())
			output = true;
		
		return output;
	}
	
	/**
	 * Returns an output string for thi object
	 */
	public String toString()
	{
		return "id: "+typeID+" Type: "+type+" Title: "+genericTitle+".";
	}

}
