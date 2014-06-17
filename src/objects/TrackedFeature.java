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
	
	/**
	 * Creates a new feature tracker
	 * @param featureName 	Name of the feature to track
	 * @param value			Performance metric of the feature
	 * @throws IllegalArgumentException
	 */
	public TrackedFeature(String featureName, double value) throws IllegalArgumentException
	{
		if(featureName != null && !featureName.isEmpty())
		{
			this.featureName = featureName;
			this.notes = "";
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
			this.featureName = featureName;
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
		assert (notes != null && !notes.isEmpty());
		if(notes != null && !notes.isEmpty())
			this.notes = notes;
	}
	
}
