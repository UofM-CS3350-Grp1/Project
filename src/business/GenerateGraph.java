package business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import objects.Client;
import objects.FeatureHistory;
import objects.MonthReport;
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
	 * Generates a line chart given a feature
	 * @param feature	The feature to use
	 * @return	A line chart of the data
	 */
	public JFreeChart generateFeatureLineChart(TrackedFeature feature)
	{
		JFreeChart chart = null;
		DefaultCategoryDataset data;
		Plot plot;
		ArrayList<FeatureHistory> histories;
		int size;
		SimpleDateFormat sdf;
		FeatureHistory history;
		
		assert (feature != null);
		if(feature != null)
		{
			data = new DefaultCategoryDataset();
			histories = processHistory.getHistoryListForFeature(feature);
			
			if(histories != null)
			{
				try
				{
					sdf = new SimpleDateFormat("MMM, yyyy");
					size = histories.size();
					for(int i = 0; i < size; i++)
					{
						//NOTE These will need to be properly summed by month and 
						//probably impose the same 12 month cycle property
						history = histories.get(i);
						data.addValue(history.getValue(), sdf.format(history.getDate()), feature.getFeatureName());
					}
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
				
			//Setup the chart names and axes
			chart = ChartFactory.createLineChart(feature.getFeatureName(), "Period (Months)", feature.getFeatureName(), data);
			chart.removeLegend();
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
		//TrackedFeature feature = null;
		Plot plot;
		
		assert (service != null);
		if(service != null)
		{
			data = new DefaultPieDataset();
			
			/*while((feature = processFeature.getNextFeatureForService(service)) != null)
			{							
				//Populate the data in the chart
				data.setValue(service.getTitle(), CalculateFeatureValue.calculateTotalValue(service, feature));
			}*/
				
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
	
	/**
	 * Creates a line graph of the revenue for the past 12 months
	 * @param client	The client to produce the data for
	 * @return A chart containing the past 12 months of revenue
	 */
	public JFreeChart generateRevenueLineChartForClient(Client client)
	{
		JFreeChart chart = null;
		DefaultCategoryDataset data;
		Plot plot;
		ArrayList<MonthReport> reports;
		MonthReport report;
		int size;
		SimpleDateFormat sdf;
		
		assert (client != null);
		if(client != null)
		{
			data = new DefaultCategoryDataset();
			reports = (new AccessFinancialRecords()).getYearRevenueForClient(client);
			
			//Plot the last 12 months worth of data. Note that we don't 
			//care if the months are within the same year. 
			if(reports != null)
			{
				try
				{
					sdf  = new SimpleDateFormat("MMM, yyyy");
					size = reports.size();
					for(int i = 0; i < size; i++)
					{
						report = reports.get(i);
						data.addValue(report.getValue(), "Revenue", sdf.format(report.getPeriod()));
					}
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			
			//Setup the chart
			chart = ChartFactory.createLineChart("Revenue", "Period (Months)", "Dollars", data);
			chart.removeLegend();
			plot = chart.getPlot();
			plot.setNoDataMessage("No data available");
		}
		
		return chart;
	}
}
