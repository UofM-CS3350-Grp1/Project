package persistence;

import java.util.ArrayList;

import objects.Storable;

public class TableUpdater extends NewDBInterface
{
	private final int ERROR_LOGGING = 0;
	
	public TableUpdater()
	{
		super(NewDBInterface.DATABASE_NAME);
	}
	
	/**
	 * INSERT()
	 * 
	 * @param element Storable to insert
	 */
	
	public boolean insert(Storable element)
	{
		boolean output = false;
		int feedback = -1;
		
		if(element != null && element.getTableName().compareTo("") != 0 && element.getTableName() != null && element.isInsertable())
		{
			feedback = mainDB.insert(element.getTableName(), element);
		}
		else
		{
			if(ERROR_LOGGING == 1 && element != null && !element.isInsertable())
				System.out.println("THE TARGET ELEMENT IS NOT INSERTABLE\n PLEASE ENSURE MINIMUM SECONDARY KEY VARAIABLES ARE INSTANTIATED");
		}
		
		if(feedback != -1)
			output = true;

		return output;
	}
	
	/**
	 * UPDATE()
	 * 
	 * @param element Storable to update
	 */
	
	public boolean update(Storable element)
	{
		boolean output = false;
		
		if(element != null && element.getTableName().compareTo("") != 0 && element.getTableName() != null && element.isInsertable())
		{
			output = mainDB.update(element.getTableName(), element);
		}
		else
		{
			if(ERROR_LOGGING == 1 && element != null && !element.isInsertable())
				System.out.println("THE TARGET ELEMENT IS NOT UPDATABLE\n PLEASE ENSURE MINIMUM SECONDARY KEY VARAIABLES ARE INSTANTIATED");
		}
		
		return output;
	}
	
	/**
	 * DROP()
	 * 
	 * @param element Storable to drop
	 */
	
	public boolean drop(Storable element)
	{
		boolean output = false;
		
		if(element != null && element.getTableName().compareTo("") != 0 && element.getTableName() != null)
		{
			output = mainDB.drop(element.getTableName(), element.getID());
		}
		else
		{
			if(ERROR_LOGGING == 1)
				System.out.println("Invalid input for DROP statement.");
		}
		
		return output;
	}
	
	/**
	 * 	batchMerge()
	 * 
	 *  @param	ArrayList<Storable>	-	Bulk Storable to insert into DBMS
	 *  
	 *  @return	Boolean					If insert completed successfully.
	 */	
	
	public boolean batchMerge(ArrayList<Storable> batch)
	{
		boolean output = true;
		for(int i = 0; i < batch.size() && output; i++)
		{
			if(idExists(batch.get(i)))
				output = update(batch.get(i));
			else
			{
				output = insert(batch.get(i));
			}
		}
		
		return output;
	}
}
