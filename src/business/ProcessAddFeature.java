package business;

import java.util.ArrayList;

import objects.Service;
import objects.TrackedFeature;

/**
 * Processes the adding of new trackable features
 */
public class ProcessAddFeature extends ProcessStorable
{
	private ArrayList<TrackedFeature> features;
	private ArrayList<TrackedFeature> serviceFeatures;
	private int serviceFeatureIndex;
	private int featureIndex;
	
	/**
	 * Create the Feature adder
	 */
	public ProcessAddFeature()
	{
		super();
		
		features = null;
		serviceFeatures = null;
		serviceFeatureIndex = 0;
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
	 * Iterate through the list of features that a service tracks
	 * @param service The service to look up features for
	 * @return	The feature if found, null otherwise
	 */
	public TrackedFeature getNextFeatureForService(Service service)
	{
		TrackedFeature feature = null;
		
		assert (service != null);
		if(service != null)
		{
			if(serviceFeatures == null)
			{
				database.connect();
				//NO Database support yet
				//features = database.getFeaturesFromService(service);
				database.disconnect();
				
				if(features != null && features.size() > 0)
				{
					feature = serviceFeatures.get(0);
					serviceFeatureIndex = 1;
				}
			}
			else if(serviceFeatureIndex < serviceFeatures.size())
			{
				feature = serviceFeatures.get(serviceFeatureIndex);
				serviceFeatureIndex++;
			}
			else
			{
				serviceFeatures = null;
			}
		}
		
		return feature;
	}
}
