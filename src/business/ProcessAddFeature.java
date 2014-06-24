package business;

import java.util.ArrayList;

import objects.TrackedFeature;

/**
 * Processes the adding of new trackable features
 */
public class ProcessAddFeature extends ProcessStorable
{
	private ArrayList<TrackedFeature> features;
	private int featureIndex = 0;
	
	/**
	 * Create the Feature adder
	 */
	public ProcessAddFeature()
	{
		super();
		
		features = null;
		featureIndex = 0;
	}
		
	/**
	 * Gets the next feature in the database
	 * @return The next feature or null if we have reached the end of the list
	 */
	public TrackedFeature getNextFeature()
	{
		TrackedFeature feature = null;
		
		if(features == null)
		{
			database.connect();
			features = database.dumpTrackedFeatures();
			database.disconnect();
			
			if(features.size() > 0)
			{
				feature = features.get(0);
				featureIndex = 1;
			}
		}
		else if(featureIndex < features.size())
		{
			feature = features.get(featureIndex);
			featureIndex++;
		}
		else
		{
			features = null;
		}
		
		return feature;
	}

	/**
	 * Gets a feature by the given ID
	 * @param id 	The ID of the feature
	 * @return	The feature if found, null otherwise
	 */
	public TrackedFeature getFeatureByID(int id)
	{
		TrackedFeature feature = null;
		
		assert (id >= 0);
		if(id >= 0)
		{
			database.connect();
			feature = database.getTrackedFeatureByID(id);
			database.disconnect();
		}
		
		return feature;
	}
}
