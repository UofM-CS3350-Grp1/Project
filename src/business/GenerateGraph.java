package business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import objects.Client;
import objects.FeatureHistory;
import objects.MonthReport;
import objects.Service;
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
	private AccessFinancialRecords financialRecords;
	
	/**
	 * Creates a new line graph generator
	 */
	public GenerateGraph()
	{	
		processHistory = new ProcessFeatureHistory();
		financialRecords = new AccessFinancialRecords();
	}
	
	/**
	 * Generates a line chart given a feature
	 * @param feature	The feature to use
	 * @return	A line chart of the data
	 */
	public JFreeChart generateFeatureLineChart(TrackedFeature feature)
	{
		JFreeChart chart = null;
		DefaultCategoryDataset data = new DefaultCategoryDataset();;
		Plot plot;
		ArrayList<FeatureHistory> histories;
		int size;
		SimpleDateFormat sdf;
		FeatureHistory history;
		
		assert (feature != null);
		if(feature != null)
		{
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
		}	
		
		//Setup the chart names and axes
		chart = ChartFactory.createLineChart(feature.getFeatureName(), "Period (Months)", feature.getFeatureName(), data);
		chart.removeLegend();
		
		plot = chart.getPlot();
		plot.setNoDataMessage("No data available");
		
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
		ArrayList<MonthReport> reports;
		
		assert (client != null);
		if(client != null)
		{
			reports = financialRecords.getYearRevenueForClient(client);
			chart = generateRevenueLineChart(reports);
		}
		
		return chart;
	}
	
	/**
	 * Creates a line graph of the revenue for the past 12 months
	 * @param service	The service to produce the data for
	 * @return A chart containing the past 12 months of revenue
	 */
	public JFreeChart generateRevenueLineChartForService(Service service)
	{
		JFreeChart chart = null;
		ArrayList<MonthReport> reports;
		
		assert (service != null);
		if(service != null)
		{
			reports = financialRecords.getYearRevenueForService(service);
			chart = generateRevenueLineChart(reports);
		}
		
		return chart;
	}
	
	/**
	 * Creates a line graph of the revenue for the past 12 months
	 * @param reports	The last year's monthly values
	 * @return A chart containing the past 12 months of revenue
	 */
	private JFreeChart generateRevenueLineChart(ArrayList<MonthReport> reports)
	{
		JFreeChart chart = null;
		DefaultCategoryDataset data = new DefaultCategoryDataset();;
		Plot plot;
		MonthReport report;
		int size;
		SimpleDateFormat sdf;
		
		assert (reports != null);
		if(reports != null)
		{
			//Plot the last 12 months worth of data. Note that we don't 
			//care if the months are within the same year. 
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
		
		return chart;
	}
	
	/**
	 * Creates a chart to illustrate the breakdown of revenue to expenses
	 * @param client	The client to generate breakdown for
	 * @return	The chart of the financial break down
	 */
	public JFreeChart generateFinancialBreakdownForClient(Client client)
	{
		JFreeChart chart = null;
		
		DefaultPieDataset data = new DefaultPieDataset();;
		Plot plot;
		double revenue, expenses;

		assert (client != null);
		if(client != null)
		{
			revenue  = financialRecords.calcClientRevenueToDate(client);
			expenses = financialRecords.calcClientExpensesToDate(client);
	
			data.setValue("Revenue", revenue);
			data.setValue("Expenses", expenses);
		}	
		
		//Setup the chart names and axes
		chart = ChartFactory.createPieChart("Financial Breakdown", data);
		plot = chart.getPlot();
		plot.setNoDataMessage("No data available");

		return chart;
	}
}
