package business;

import objects.FeatureHistory;
import objects.Service;
import objects.Trackable;
import objects.TrackedFeature;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Responsible for generating graphs based on clients and services
 */
public class GenerateGraph
{
	private ProcessAddFeature processFeature;
	
	/**
	 * Creates a new line graph generator
	 */
	public GenerateGraph()
	{	
		processFeature = new ProcessAddFeature();
	}
	
	/**
	 * Generates a chart given the service's history data
	 * @param service	The service to use
	 * @return	A chart of the data
	 */
	public JFreeChart GenerateChartForService(Service service)
	{
		JFreeChart chart = null;
		DefaultCategoryDataset data;
		
		assert (service != null);
		if(service != null)
		{
			data = new DefaultCategoryDataset();
			
			//Populate the data in the chart
			data.addValue(15,  service.getTitle(), "January");
			data.addValue(30, service.getTitle(), "February");
			
			//Setup the chart names and axes
			chart = ChartFactory.createLineChart(service.getTitle(), "Period", "Revenue", data);
		}		
		
		return chart;
	}
	
	/**
	 * Creates a line graph for a given service and feature
	 * @param service	A trackable object that the feature belongs to
	 * @param feature	The feature we want to track
	 * @return	A chart containing all of the data represented via a graph
	 */
	public JFreeChart GenerateChartForFeature(Trackable service, TrackedFeature feature)
	{
		JFreeChart chart = null;
		DefaultCategoryDataset data;
		FeatureHistory history = null;
		
		assert (service != null && feature != null);
		if(service != null && feature != null)
		{
			data = new DefaultCategoryDataset();
			
			//Populate the data with all of the the history for a service's 
			//given feature to plot it on a nice graph
			while((history = processFeature.getNextHistoryForFeature(service, feature)) != null)
			{
				data.addValue(history.getValue(), feature.getFeatureName(), history.getDate().toString());
			}
			
			//Finally set up the chart with the axis and formatting
			chart = ChartFactory.createLineChart(feature.getFeatureName(), "Period", feature.getFeatureName(), data);
		}
		
		return chart;
	}
}
