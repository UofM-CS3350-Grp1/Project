package business;

import objects.Storable;
import persistence.DBInterface;

public class ProcessStorable
{
	protected DBInterface database;
	
	/**
	 * Create a new storable object processor
	 */
	public ProcessStorable()
	{
		database = new DBInterface(DBInterface.DATABASE_NAME);
	}
	
	/**
	 * Insert a storable item into the database
	 * @param item The item to insert
	 * @return True if the item was inserted
	 */
	public boolean insert(Storable item)
	{
		boolean wasInserted = false;
		
		assert (item != null);
		if(item != null)
		{
			database.connect();
			wasInserted = database.insert(item);
			database.disconnect();
		}
		
		return wasInserted;
	}
	
	/**
	 * Updates a storable item in the database
	 * @param item The item to update
	 * @return True if the item was updated
	 */
	public boolean update(Storable item)
	{
		boolean wasUpdated = false;
		
		assert (item != null);
		if(item != null)
		{
			database.connect();
			wasUpdated = database.update(item);
			database.disconnect();
		}
		
		return wasUpdated;
	}
	
	/**
	 * Deletes an item from the database
	 * @param item The item to delete
	 * @return True if the item was deleted
	 */
	public boolean delete(Storable item)
	{
		boolean wasDeleted = false;
		
		assert (item != null);
		if(item != null)
		{
			database.connect();
			wasDeleted = database.drop(item);
			database.disconnect();
		}
		
		return wasDeleted;
	}
}
