package objects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Stores the specific performance metric of a tracked feature.
 * This is done for a service/ client over a specified time period.
 */
public class FeatureHistory implements Storable
{
	private final String DATE_FORMAT = "yyyy-MM-dd";	//The string date representation
	
	private TrackedFeature feature;		//The tracked feature this history object is for
	private Trackable trackedService;	//The service that the history is for, null if none
	private double value;				//Value/ performance metric of feature
	private SimpleDateFormat sdf;
	private Date date;					//The date of the information
	private String notes;				//Additional information for this history
	private String tableName;
	private int id;
	
	/**
	 * Creates a new feature history
	 * @param feature 		 The feature we storing history for
	 * @param trackedClient	The client we are storing history for
	 * @param trackedService The service we are storing history for
	 * @param value 		 The value/ performance metric for this period
	 * @param date			 The period
	 * @throws IllegalArgumentException
	 */
	public FeatureHistory(TrackedFeature feature, Trackable trackedService, double value, Date date) throws IllegalArgumentException
	{
		if(feature != null && trackedService != null && date != null)
		{
			this.feature = feature;

			this.trackedService = trackedService;
			this.value = value;
			this.date = date;
			this.notes = "";
			this.sdf = new SimpleDateFormat(DATE_FORMAT);
			this.tableName = "FEATURE_HISTORY";
			this.id = -1;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Creates a new feature history
	 * @param feature 		 The feature we storing history for
	 * @param trackedCLient  The client we are storing history for.
	 * @param trackedService The service we are storing history for
	 * @param value 		 The value/ performance metric for this period
	 * @param date			 The period
	 * @param notes 		 Additional documentation for the record
	 * @throws IllegalArgumentException
	 */
	public FeatureHistory(TrackedFeature feature, Trackable trackedService, double value, Date date, String notes) throws IllegalArgumentException
	{
		if(feature != null && trackedService != null && date != null && notes != null)
		{
			this.feature = feature;
			this.trackedService = trackedService;
			this.value = value;
			this.date = date;
			this.notes = notes;
			this.sdf = new SimpleDateFormat(DATE_FORMAT);
			this.tableName = "FEATURE_HISTORY";
			this.id = -1;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Creates a new feature history
	 * @param feature 		 The feature we storing history for
	 * @param trackedService The service we are storing history for
	 * @param trackedCleint	 The client we are storing history for, null if empty
	 * @param value 		 The value/ performance metric for this period
	 * @param date			 The period
	 * @param notes 		 Additional documentation for the record
	 * @param id			Unique id of this object
	 * @throws IllegalArgumentException
	 */
	
	public FeatureHistory(TrackedFeature feature, Trackable trackedService, double value, Date date, String notes, int id) throws IllegalArgumentException
	{
		if(feature != null && trackedService != null && date != null && notes != null && id >= 0)
		{
			this.feature = feature;
			this.trackedService = trackedService;
			this.value = value;
			this.date = date;
			this.notes = notes;
			this.sdf = new SimpleDateFormat(DATE_FORMAT);
			this.tableName = "FEATURE_HISTORY";
			this.id = id;
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
	 * @return The feature this history is for
	 */
	public TrackedFeature getFeature() 
	{
		return feature;
	}

	/**
	 * Sets the owning feature of this history
	 * @param feature The feature
	 */
	public void setFeature(TrackedFeature feature) 
	{
		assert (feature != null);
		if(feature != null)
			this.feature = feature;
	}

	/**
	 * @return Gets the value of the history
	 */
	public double getValue()
	{
		return value;
	}

	/**
	 * Sets the value of the history
	 * @param value The historical value
	 */
	public void setValue(double value)
	{
		this.value = value;
	}

	/**
	 * @return The date of the history object
	 */
	public Date getDate()
	{
		return date;
	}
	
	/**
	 * @return The formatted date of the form yyyy-mm-dd
	 */
	public String getShortDate()
	{
		return sdf.format(date);
	}

	/**
	 * Sets the date of the history object
	 * @param date The date of the history object
	 */
	public void setDate(Date date) 
	{
		assert (date != null);
		if(date != null)
			this.date = date;
	}

	/**
	 * @return Additional information on the record
	 */
	public String getNotes() 
	{
		return notes;
	}

	/**
	 * Sets the additional information on the record
	 * @param notes The additional information
	 */
	public void setNotes(String notes) 
	{
		assert (notes != null);
		if(notes != null)
			this.notes = notes;
	}

	/**
	 * @return The trackedService
	 */
	public Trackable getTrackedService() 
	{
		return trackedService;
	}
	
	/**
	 * @param trackedService The trackedService to set
	 */
	public void setTrackedService(Trackable trackedService)
	{
		assert (trackedService != null);
		if(trackedService != null)
			this.trackedService = trackedService;
	}
	
	public ArrayList<String> toIndex() 
	{
		ArrayList<String> index = new ArrayList<String>();
		index.add(""+this.id);
		index.add(""+this.getFeature().getID());
		
		if(this.trackedService instanceof Client)
		{
			index.add(""+trackedService.getID());
			index.add(""+(-1)+"");
		}
		else
		{
			index.add(""+(-1)+"");
			index.add(""+trackedService.getID());
		}
		index.add(this.sdf.format(date));
		index.add(this.notes);
		index.add(""+this.value);
		
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
	
	public boolean isInsertable() 
	{
		return true;		
	}

}
