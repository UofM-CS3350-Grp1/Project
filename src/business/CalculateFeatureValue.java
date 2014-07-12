package business;

import objects.Client;
import objects.TrackedFeatureType;

/**
 * Computes the total value of features
 */
public class CalculateFeatureValue
{
	/**
	 * Iterates through all of a feature's history and sums up the total amount gained
	 * @param client	The client object
	 * @param feature	The feature
	 * @return	The total value
	 */
	public static double calculateTotalValue(Client client, TrackedFeatureType feature)
	{
		ProcessFeatureHistory processHistory = new ProcessFeatureHistory();
		double total = 0.0;
		
		assert (feature != null && client != null);
		if(feature != null && client != null)
		{
			total = processHistory.getFeatureTotal(client, feature);
		}
		
		return total;
	}
}
