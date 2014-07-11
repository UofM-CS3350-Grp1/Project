package objects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Manages the different types of features that can be tracked
 * for different types of services
 */
public class TrackedFeature implements Storable
{
	private final String DATE_FORMAT = "yyyy-MM-dd";	//The string date representation
	
	private String notes;					//Notes related to the feature
	private int id;							//Id of object
	private int clientID;					//Key of associated client
	private String tableName;				//Name of table object belongs on
	private double value;					//value of feature
	private Date recorded;					//Date reccorded
	private TrackedFeatureType trackedFT;	//Type of feature
	private SimpleDateFormat sdf;		 //Date formatter

	/**
	 * Creates a new feature tracker
	 * @param featureName 	Name of the feature to track
	 * @param trackedFT		TrackedFeature Type of object
	 * @throws IllegalArgumentException
	 */
	public TrackedFeature(TrackedFeatureType trackedFT, Date recorded, double value) throws IllegalArgumentException
	{
		if(trackedFT != null && recorded != null)
		{
			this.id = -1;
			this.clientID = -1;
			this.notes = "";
			this.tableName = "FEATURE";
			this.sdf = new SimpleDateFormat(DATE_FORMAT);
			this.trackedFT = trackedFT;
			this.recorded = recorded;
			this.value = value;
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
	public TrackedFeature(String notes, int clientID, TrackedFeatureType trackedFT, Date recorded, double value) throws IllegalArgumentException
	{
		if(notes != null && trackedFT != null && recorded != null && clientID > 0)
		{
			this.clientID = clientID;
			this.id = -1;
			this.notes = notes;
			this.tableName = "FEATURE";
			this.trackedFT = trackedFT;
			this.recorded = recorded;
			this.value = value;
			this.recorded = new Date();
			this.sdf = new SimpleDateFormat(DATE_FORMAT);
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
	
	public TrackedFeature(String notes, int id, int clientID, TrackedFeatureType trackedFT, double value,Date recorded) throws IllegalArgumentException
	{
		if(notes != null && id >= 0 && clientID >= 0 && trackedFT != null && recorded != null)
		{
			this.clientID = clientID;
			this.id = id;
			this.notes = notes;
			this.tableName = "FEATURE";
			this.trackedFT = trackedFT;
			this.value = value;
			this.recorded = recorded;
			this.sdf = new SimpleDateFormat(DATE_FORMAT);
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
	 * @return Additional information on the tracked feature
	 */
	public String getNotes() 
	{
		return notes;
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
	
	public Date getDate()
	{
		return this.recorded;
	}
	
	public double getValue()
	{
		return this.value;
	}

	//----------
	// SETTERS
	//----------


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
	
	public void setDate(Date date)
	{
		if(date != null)
			this.recorded = date;
	}
	
	public void setValue(double value)
	{
		this.value = value;
	}

	//----------
	// OTHER
	//----------
	
	//ROW_ID INTEGER NOT NULL PRIMARY KEY,CLIENT_ID INTEGER,FEATURE_TYPE_ID INTEGER,NOTES VARCHAR(500),VALUE FLOAT, DATE_RECCORDED DATE
	public ArrayList<String> toIndex() 
	{
		ArrayList<String> index = new ArrayList<String>();

		index.add(""+this.id);
		index.add(""+this.clientID);
		index.add(""+this.trackedFT.getID());
		index.add(this.notes);
		index.add(this.value+"");
		index.add(this.sdf.format(recorded));

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
		returnVal = "Client ID: " + clientID + ", TableName: " + tableName +" TrackedFeatureType: " + trackedFT.toString() + ", Notes: " + notes;
		return returnVal;
	}
}
