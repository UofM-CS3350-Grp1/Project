package business;

import java.util.ArrayList;

import objects.Client;
import objects.MonthReport;
import objects.TrackedFeature;
import objects.TrackedFeatureType;

/**
 * Processes the adding, editing and deletion of new feature history
 */
public class ProcessFeatureHistory extends ProcessStorable
{
	private ArrayList<TrackedFeature> histories;
	private int histIndex;

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
	public TrackedFeature getNextHistory()
	{
		TrackedFeature history = null;

		if(histories == null)
		{
			database.connect();
			histories = database.dumpTrackedFeatures();
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
	public TrackedFeature getHistoryByID(int id)
	{
		TrackedFeature history = null;

		assert (id >= 0);
		if(id >= 0)
		{
			database.connect();
			history = database.getTrackedFeatureByID(id);
			database.disconnect();
		}
		
		return history;
	}

	/**
	 * Retrieves the history objects for a given feature
	 * @param client The client to find the history for
	 * @param feature The feature to get history for
	 * @return The next history object or null if we have reached the end
	 */
	public double getFeatureTotal(Client client, TrackedFeatureType feature)
	{
		double sum = 0;

		assert (client != null && feature != null);
		if(client != null && feature != null)
		{
			database.connect();
			sum = database.getTotalAllFeatures(client, feature);
			database.disconnect();
		}

		return sum;
	}

	/**
	 * Retrieves the history of a feature
	 * @param feature 	The feature to get history for
	 * @param client 	The client to find the history for
	 * @return	The list of history
	 */
	public ArrayList<MonthReport> getYearHistoryForFeature(TrackedFeatureType feature, Client client)
	{
		ArrayList<MonthReport> list = new ArrayList<MonthReport>();
		
		assert (feature != null && client != null);
		if(feature != null && client != null)
		{
			database.connect();
			list = database.getLastYearClientFeaturesByType(client, feature);
			database.disconnect();
		}
		
		return list;
	}
}
