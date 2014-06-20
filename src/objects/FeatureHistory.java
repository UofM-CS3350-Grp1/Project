package objects;

import java.util.ArrayList;
import java.util.Date;

/**
 * Stores the specific performance metric of a tracked feature.
 * This is done for a service/ client over a specified time period.
 */
public class FeatureHistory implements Storable
{
	private TrackedFeature feature;		//The tracked feature this history object is for
	private Trackable trackedService;	//The service/ client that the history is for
	private double value;				//Value/ performance metric of feature
	private Date date;					//The date of the information
	private String notes;				//Additional information for this history
	private int parentID;				//ID of the featue being tracked
	
	/**
	 * Creates a new feature history
	 * @param feature 		 The feature we storing history for
	 * @param trackedService The client/service we are storing history for
	 * @param value 		 The value/ performance metric for this period
	 * @param date			 The period
	 * @throws IllegalArgumentException
	 */
	public FeatureHistory(TrackedFeature feature, Trackable trackedService, double value, Date date, int paretID) throws IllegalArgumentException
	{
		if(feature != null && trackedService != null && date != null)
		{
			this.feature = feature;
			this.trackedService = trackedService;
			this.value = value;
			this.date = date;
			this.notes = "";
			this.parentID = parentID;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Creates a new feature history
	 * @param feature 		 The feature we storing history for
	 * @param trackedService The client/service we are storing history for
	 * @param value 		 The value/ performance metric for this period
	 * @param date			 The period
	 * @param notes 		 Additional documentation for the record
	 * @throws IllegalArgumentException
	 */
	public FeatureHistory(TrackedFeature feature, Trackable trackedService, double value, Date date, String notes) throws IllegalArgumentException
	{
		if(feature != null && trackedService != null && date != null)
		{
			this.feature = feature;
			this.trackedService = trackedService;
			this.value = value;
			this.date = date;
			this.notes = notes;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public int getID() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<String> toIndex() 
	{
		// TODO Auto-generated method stub
		return null;
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

}
