package business;

import objects.FeatureHistory;
import persistence.DBInterface;

/**
 * Processes the adding, editing and deletion of new feature history
 */
public class ProcessFeatureHistory
{
	private DBInterface db;
	
	/**
	 * Create the Feature adder
	 */
	public ProcessFeatureHistory()
	{
		db = new DBInterface("DBNAME");
	}
	
	/**
	 * Adds a new history object to the database
	 * @param history 	The new history object to add
	 * @return True if added, false if it already exists or an error occurred
	 */
	public boolean addFeatureHistory(FeatureHistory history)
	{
		boolean wasAdded = false;
		
		assert (history != null);
		if(history != null)
		{
			db.connect();
			
			//TODO Check if the feature already exist
			
			//Add the new feature
			db.insert(history);
			wasAdded = true;
			
			db.disconnect();
		}
		
		return wasAdded;
	}
	
	/**
	 * Updates an existing history object in the database
	 * @param history 	The history object to update
	 * @return True if added, false if it does not exist or an error occurred
	 */
	public boolean updateFeatureHistory(FeatureHistory history)
	{
		boolean wasUpdated = false;
		
		assert (history != null);
		if(history != null)
		{
			db.connect();
			
			//Update the history
			db.update(history);
			wasUpdated = true;
			
			db.disconnect();
		}
		
		return wasUpdated;
	}
	
	/**
	 * Delete a history object from the database
	 * @param history 	The history object to delete
	 * @return True if added, false if an error occurred
	 */
	public boolean deleteFeatureHistory(FeatureHistory history)
	{
		boolean wasDeleted = false;
		
		assert (history != null);
		if(history != null)
		{
			db.connect();

			//Delete the history
			db.drop(history);
			wasDeleted = true;
			
			db.disconnect();
		}
		
		return wasDeleted;
	}
}
