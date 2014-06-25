package business;

import java.util.ArrayList;

import objects.FeatureHistory;

/**
 * Processes the adding, editing and deletion of new feature history
 */
public class ProcessFeatureHistory extends ProcessStorable
{
	private ArrayList<FeatureHistory> histories;
	private int histIndex = 0;
	
	/**
	 * Create the Feature adder
	 */
	public ProcessFeatureHistory()
	{
		super();
		
		histories = null;
		histIndex = 0;
	}
		
	/**
	 * Gets the next history in the database
	 * @return The next history or null if we have reached the end of the list
	 */
	public FeatureHistory getNextHistory()
	{
		FeatureHistory history = null;
		
		if(histories == null)
		{
			database.connect();
			histories = database.dumpFeatureHistory();
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

	/**
	 * Gets a history by the given ID
	 * @param id 	The ID of the history
	 * @return	The history if found, null otherwise
	 */
	public FeatureHistory getHistoryByID(int id)
	{
		FeatureHistory history = null;
		
		assert (id >= 0);
		if(id >= 0)
		{
			database.connect();
			history = database.getFeatureHistoryByID(id);
			database.disconnect();
		}
		
		return history;
	}
}
