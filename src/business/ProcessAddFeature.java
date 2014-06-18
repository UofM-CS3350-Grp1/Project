package business;

import objects.TrackedFeature;
import persistence.DBInterface;

/**
 * Processes the adding of new trackable features
 */
public class ProcessAddFeature 
{
	private DBInterface db;
	
	/**
	 * Create the Feature adder
	 */
	public ProcessAddFeature()
	{
		db = new DBInterface("DBNAME");
	}
	
	/**
	 * Adds a new feature to the database
	 * @param feature 	The new feature to add
	 * @return True if added, false if it already exists or an error occurred
	 */
	public boolean addFeature(TrackedFeature feature)
	{
		boolean wasAdded = false;
		
		assert (feature != null);
		if(feature != null)
		{
			db.connect();
			
			//TODO Check if the feature already exist
			
			//Add the new feature
			db.insert(feature);
			wasAdded = true;
			
			db.disconnect();
		}
		
		return wasAdded;
	}
	
	/**
	 * Updates an existing feature in the database
	 * @param feature 	The feature to update
	 * @return True if added, false if it does not exist or an error occurred
	 */
	public boolean updateFeature(TrackedFeature feature)
	{
		boolean wasUpdated = false;
		
		assert (feature != null);
		if(feature != null)
		{
			db.connect();
			
			//Update the feature
			db.update(feature);
			wasUpdated = true;
			
			db.disconnect();
		}
		
		return wasUpdated;
	}
	
	/**
	 * Delete a feature from the database
	 * @param feature 	The feature to delete
	 * @return True if added, false if an error occurred
	 */
	public boolean deleteFeature(TrackedFeature feature)
	{
		boolean wasDeleted = false;
		
		assert (feature != null);
		if(feature != null)
		{
			db.connect();

			//Delete the  feature
			db.drop(feature);
			wasDeleted = true;
			
			db.disconnect();
		}
		
		return wasDeleted;
	}
}
