package business;

import java.util.ArrayList;

import objects.FeatureHistory;
import objects.Trackable;
import objects.TrackedFeature;

/**
 * Processes the adding of new trackable features
 */
public class ProcessAddFeature extends ProcessStorable
{
	private ArrayList<TrackedFeature> features;
	private ArrayList<FeatureHistory> histories;
	private int histIndex;
	private int featureIndex;
	
	/**
	 * Create the Feature adder
	 */
	public ProcessAddFeature()
	{
		super();
		
		features = null;
		histories = null;
		featureIndex = 0;
		histIndex = 0;
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
			
			if(features != null && features.size() > 0)
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
	
	/**
	 * Retrieves the history objects for a given feature
	 * @param service The service to find the history for
	 * @param feature The feature to get history for
	 * @return The next history object or null if we have reached the end
	 */
	public FeatureHistory getNextHistoryForFeature(Trackable service, TrackedFeature feature)
	{
		FeatureHistory history = null;
		
		if(histories == null)
		{
			database.connect();
			histories = database.getFeatureHistoryFromParent(service, feature);
			database.disconnect();
			
			if(histories != null && histories.size() > 0)
			{
				history = histories.get(0);
				histIndex = 1;
			}
		}
		else if(histIndex < histories.size())
		{
			history = histories.get(histIndex);
			histIndex++;
		}
		else
		{
			histories = null;
		}
		
		return history;
	}
}
