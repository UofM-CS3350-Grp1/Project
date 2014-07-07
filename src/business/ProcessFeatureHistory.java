package business;

import java.util.ArrayList;

import objects.FeatureHistory;
import objects.Trackable;
import objects.TrackedFeature;

/**
 * Processes the adding, editing and deletion of new feature history
 */
public class ProcessFeatureHistory extends ProcessStorable
{
	private ArrayList<FeatureHistory> histories;
	private ArrayList<FeatureHistory> featureHistories;
	private int histIndex;
	private int featureHistIndex;

	/**
	 * Create the Feature adder
	 */
	public ProcessFeatureHistory()
	{
		super();
		
		histories = null;
		featureHistories = null;
		histIndex = 0;
		featureHistIndex = 0;
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

	/**
	 * Retrieves the history objects for a given feature
	 * @param service The service to find the history for
	 * @param feature The feature to get history for
	 * @return The next history object or null if we have reached the end
	 */
	public FeatureHistory getNextHistoryForFeature(Trackable service, TrackedFeature feature)
	{
		FeatureHistory history = null;

		if(featureHistories == null)
		{
			database.connect();
			//TODO FIX THIS
			//featureHistories = database.getFeatureHistoryFromParent(service, feature);
			database.disconnect();
			
			if(featureHistories != null && featureHistories.size() > 0)
			{
				history = featureHistories.get(0);
				featureHistIndex = 1;
			}
		}
		else if(featureHistIndex < featureHistories.size())
		{
			history = featureHistories.get(featureHistIndex);
			featureHistIndex++;
		}
		else
		{
			featureHistories = null;
		}

		return history;
	}

	/**
	 * Retrieves the history of a feature
	 * @param feature 	The feature to get history for
	 * @return	The list of history
	 */
	public ArrayList<FeatureHistory> getHistoryListForFeature(TrackedFeature feature)
	{
		ArrayList<FeatureHistory> list = new ArrayList<FeatureHistory>();
		
		assert (feature != null);
		if(feature != null)
		{
			database.connect();
			list = database.getFeatureHistoryByFeature(feature);
			database.disconnect();
		}
		
		return list;
	}

	public void insertFeature(FeatureHistory feature)
	{
		database.connect();
		database.insert(feature);
		database.disconnect();
	}
}
