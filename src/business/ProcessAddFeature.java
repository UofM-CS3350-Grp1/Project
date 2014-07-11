package business;

import java.util.ArrayList;

import objects.Client;
import objects.TrackedFeature;
import objects.TrackedFeatureType;

/**
 * Processes the adding of new trackable features
 */
public class ProcessAddFeature extends ProcessStorable
{
	private ArrayList<TrackedFeature> features;
	private int featureIndex;
	
	/**
	 * Create the Feature adder
	 */
	public ProcessAddFeature()
	{
		super();
		
		features = null;
		featureIndex = 0;
	}
	
	/*
	 * @return The feature type by Title
	 */
	public ArrayList<TrackedFeatureType> getFeatureTypeByTitle(String title)
	{
		ArrayList<TrackedFeatureType> feature = null;
		database.connect();
		feature = database.getTrackedFeatureTypesByTitle(title);
		database.disconnect();
		return feature;
	}
		
	/*
	 * @return all available feature types
	 */
	public ArrayList<TrackedFeatureType> getFeatureTypes()
	{
		ArrayList<TrackedFeatureType> features = null;
		database.connect();
		features = database.dumpTrackedFeatureTypes();
		database.disconnect();
		return features;
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
	 * Obtain the features tracked by a client
	 * @param client	The client in question
	 * @return	The list of features tracked by said client
	 */
	public ArrayList<TrackedFeature> getFeaturesByClient(Client client)
	{
		ArrayList<TrackedFeature> featureList = new ArrayList<TrackedFeature>();
		
		assert (client != null);
		if(client != null)
		{
			database.connect();
			featureList = database.getTrackedFeaturesByClient(client);
			database.disconnect();
		}
		
		return featureList;
	}
}
