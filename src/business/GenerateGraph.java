package business;

import objects.Client;
import objects.FeatureHistory;
import objects.Service;
import objects.Trackable;
import objects.TrackedFeature;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * Responsible for generating graphs based on clients and services
 */
public class GenerateGraph
{
	private ProcessFeatureHistory processHistory;
	private ProcessAddFeature processFeature;
	private ProcessClient processClient;
	
	/**
	 * Creates a new line graph generator
	 */
	public GenerateGraph()
	{	
		processHistory = new ProcessFeatureHistory();
		processFeature = new ProcessAddFeature();
		processClient = new ProcessClient();
	}
	
	/**
	 * Generates a chart given the client which will pull all service data
	 * @param client	The client to use
	 * @return	A chart of the data
	 */
	public JFreeChart GenerateChartForClient(Client client)
	{
		JFreeChart chart = null;
		DefaultPieDataset data;
		Service service = null;
		TrackedFeature feature = null;
		Plot plot;
		
		assert (client != null);
		if(client != null)
		{
			data = new DefaultPieDataset();
			
			while((service = processClient.getNextClientService(client)) != null)
			{
				while((feature = processFeature.getNextFeatureForService(service)) != null)
				{							
					//Populate the data in the chart
					data.setValue(service.getTitle(), CalculateFeatureValue.calculateTotalValue(service, feature));
				}
			}
				
			//Setup the chart names and axes
			chart = ChartFactory.createPieChart("Client Services", data);
			plot = chart.getPlot();
			plot.setNoDataMessage("No data available");
		}		
		
		return chart;
	}
	
	/**
	 * Generates a chart given the service's history data
	 * @param service	The service to use
	 * @return	A chart of the data
	 */
	public JFreeChart GenerateChartForService(Service service)
	{
		JFreeChart chart = null;
		DefaultPieDataset data;
		TrackedFeature feature = null;
		Plot plot;
		
		assert (service != null);
		if(service != null)
		{
			data = new DefaultPieDataset();
			
			while((feature = processFeature.getNextFeatureForService(service)) != null)
			{							
				//Populate the data in the chart
				data.setValue(service.getTitle(), CalculateFeatureValue.calculateTotalValue(service, feature));
			}
				
			//Setup the chart names and axes
			chart = ChartFactory.createPieChart("Features", data);
			plot = chart.getPlot();
			plot.setNoDataMessage("No data available");
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
		Plot plot;
		
		assert (service != null && feature != null);
		if(service != null && feature != null)
		{
			data = new DefaultCategoryDataset();
			
			//Populate the data with all of the the history for a service's 
			//given feature to plot it on a nice graph
			while((history = processHistory.getNextHistoryForFeature(service, feature)) != null)
			{
				data.addValue(history.getValue(), feature.getFeatureName(), history.getShortDate());
			}
			
			//Finally set up the chart with the axis and formatting
			chart = ChartFactory.createLineChart(feature.getFeatureName(), "Period", feature.getFeatureName(), data);
			plot = chart.getPlot();
			plot.setNoDataMessage("No data available");
		}
		
		return chart;
	}
}
