package objects;

import java.util.ArrayList;

/**
 * Manages the different types of features that can be tracked
 * for different types of services
 */
public class TrackedFeature implements Storable
{
	private String featureName;				//Name of the feature to track
	private String notes;					//Notes related to the feature
	private int id;							//Id of object
	private int clientID;					//Key of associated client
	private String tableName;				//Name of table object belongs on
	private String supplier;				//Supplier paid for expenses
	private TrackedFeatureType trackedFT;	//Type of feature

	/**
	 * Creates a new feature tracker
	 * @param featureName 	Name of the feature to track
	 * @param trackedFT		TrackedFeature Type of object
	 * @throws IllegalArgumentException
	 */
	public TrackedFeature(String featureName, TrackedFeatureType trackedFT) throws IllegalArgumentException
	{
		if(featureName != null && !featureName.isEmpty() && trackedFT != null)
		{
			this.id = -1;
			this.clientID = -1;
			this.featureName = featureName;
			this.notes = "";
			this.tableName = "FEATURE";
			this.trackedFT = trackedFT;
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
	 * @param trackedFT		TrackedFeature Type of object
	 * @throws IllegalArgumentException
	 */
	public TrackedFeature(String featureName, String notes, TrackedFeatureType trackedFT) throws IllegalArgumentException
	{
		if(featureName != null && !featureName.isEmpty() && notes != null && trackedFT != null)
		{
			this.clientID = -1;
			this.id = -1;
			this.featureName = featureName;
			this.notes = notes;
			this.tableName = "FEATURE";
			this.trackedFT = trackedFT;
			this.supplier = "";
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
	 * @param serviceKey	Key to corresponding Service object
	 * @param trackedFT		TrackedFeature Type of object
	 * @throws IllegalArgumentException
	 */
	public TrackedFeature(String featureName, String notes, int id, int clientID, TrackedFeatureType trackedFT, String supplier) throws IllegalArgumentException
	{
		if(featureName != null && !featureName.isEmpty() && notes != null && id >= 0 && clientID >= 0 && trackedFT != null)
		{
			this.clientID = clientID;
			this.id = id;
			this.featureName = featureName;
			this.notes = notes;
			this.tableName = "FEATURE";
			this.trackedFT = trackedFT;
			this.supplier = supplier;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	//----------
	// GETTERS
	//----------

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
	 * @return Additional information on the tracked feature
	 */
	public String getNotes() 
	{
		return notes;
	}

	/**
	 * @return Additional information on the supplier
	 */
	public String getSupplier() 
	{
		return supplier;
	}

	/**
	 * @return Primary key of parent service associated with object
	 */
	public int getClientKey()
	{
		return this.clientID;
	}

	/**
	 * @return Gets the FeatureType object that this belongs to
	 */
	public TrackedFeatureType getTrackedFeatureType()
	{
		return this.trackedFT;
	}

	//----------
	// SETTERS
	//----------

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
	 * Sets the additional feature information
	 * @param notes
	 */
	public void setNotes(String notes) 
	{
		assert (notes != null);
		if(notes != null)
			this.notes = notes;
	}

	/**
	 * Sets the supplier information
	 * @param notes
	 */
	public void setSupplier(String supplier) 
	{
		assert (supplier != null);
		if(supplier != null)
			this.supplier = supplier;
	}

	/**
	 * @param key TrackedFeatureType to change this object to
	 */
	public void setTrackedFeatureType( TrackedFeatureType key)
	{
		if(key != null)
			this.trackedFT = key;
	}

	/**
	 * @param key ID of new parent object to be returned to
	 */

	public void setClientKey(int key)
	{
		if(key >= 0)
			this.clientID = key;
	}

	//----------
	// OTHER
	//----------
	public ArrayList<String> toIndex() 
	{
		ArrayList<String> index = new ArrayList<String>();

		index.add(""+this.id);
		index.add(""+this.clientID);
		index.add(""+this.trackedFT.getID());
		index.add(this.featureName);
		index.add(this.notes);
		index.add(this.supplier);

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

	/**
	 * Checks the object see to see if it can be inserted into a table
	 */
	public boolean isInsertable() 
	{
		boolean output = true;
		if((this.clientID < 0) || (this.trackedFT == null))
		{
			output = false;
		}
		return output;		
	}

	public String toString()
	{
		String returnVal = "";
		returnVal = "FeatureName: " + featureName + ", Client ID: " + clientID + ", TableName: " + tableName + ", Supplier: " + supplier + ", TrackedFeatureType: " + trackedFT.toString() + ", Notes: " + notes;
		return returnVal;
	}
}
