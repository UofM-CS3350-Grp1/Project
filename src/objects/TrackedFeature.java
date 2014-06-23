package objects;

import java.util.ArrayList;

/**
 * Manages the different types of features that can be tracked
 * for different types of services
 */
public class TrackedFeature implements Storable
{
	private String featureName;			//Name of the feature to track
	private String notes;				//Notes related to the feature
	private int id;
	private String tableName;
	
	/**
	 * Creates a new feature tracker
	 * @param featureName 	Name of the feature to track
	 * @throws IllegalArgumentException
	 */
	public TrackedFeature(String featureName) throws IllegalArgumentException
	{
		if(featureName != null && !featureName.isEmpty())
		{
			this.id = -1;
			this.featureName = featureName;
			this.notes = "";
			this.tableName = "FEATURE";
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Creates a new feature tracker
	 * @param featureName 	Name of the feature to track
	 * @param notes 		Some additional notes/ documentation on the feature
	 * @throws IllegalArgumentException
	 */
	public TrackedFeature(String featureName, String notes) throws IllegalArgumentException
	{
		if(featureName != null && !featureName.isEmpty() && notes != null)
		{
			this.id = -1;
			this.featureName = featureName;
			this.notes = notes;
			this.tableName = "FEATURE";
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Creates a new feature tracker
	 * @param featureName 	Name of the feature to track
	 * @param notes 		Some additional notes/ documentation on the feature
	 * @param id			The unique id of this feature
	 * @throws IllegalArgumentException
	 */
	public TrackedFeature(String featureName, String notes, int id) throws IllegalArgumentException
	{
		if(featureName != null && !featureName.isEmpty() && notes != null && id >= 0)
		{
			this.id = id;
			this.featureName = featureName;
			this.notes = notes;
			this.tableName = "FEATURE";
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	public int getID()
	{
		return this.id;
	}


	
	/**
	 * @return The name of the tracked feature
	 */
	public String getFeatureName() 
	{
		return featureName;
	}

	/**
	 * Sets the name of the tracked feature
	 * @param featureName The name of the tracked feature
	 */
	public void setFeatureName(String featureName) 
	{
		assert (featureName != null && !featureName.isEmpty());
		if(featureName != null && !featureName.isEmpty())
			this.featureName = featureName;
	}

	/**
	 * @return Additional information on the tracked feature
	 */
	public String getNotes() 
	{
		return notes;
	}

	/**
	 * Sets the additional feature information
	 * @param notes
	 */
	public void setNotes(String notes) 
	{
		assert (notes != null);
		if(notes != null)
			this.notes = notes;
	}
	
	public ArrayList<String> toIndex() 
	{
		ArrayList<String> index = new ArrayList<String>();
		
		index.add(""+this.id);
		index.add(this.featureName);
		index.add(this.notes);
		
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
