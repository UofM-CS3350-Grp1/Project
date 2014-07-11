package business;

import objects.Trackable;
import objects.TrackedFeature;

/**
 * Computes the total value of features
 */
public class CalculateFeatureValue
{
	/**
	 * Iterates through all of a feature's history and sums up the total amount gained
	 * @param trackable	The trackable object
	 * @param feature	The feature
	 * @return	The total value
	 */
	public static double calculateTotalValue(Trackable trackable, TrackedFeature feature)
	{
		ProcessFeatureHistory processHistory = new ProcessFeatureHistory();
		TrackedFeature history;
		double total = 0.0;
		
		assert (feature != null && trackable != null);
		if(feature != null && trackable != null)
		{
			while((history = processHistory.getNextHistoryForFeature(trackable, feature)) != null)
			{
				total += history.getValue();
			}
		}
		
		return total;
	}
}
