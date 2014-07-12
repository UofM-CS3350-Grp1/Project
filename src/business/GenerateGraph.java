package business;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import objects.Client;
import objects.MonthReport;
import objects.ServiceType;
import objects.TrackedFeatureType;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
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
	 * @param client 	The client to generate data for
	 * @return	A line chart of the data
	 */
	public JFreeChart generateFeatureLineChart(TrackedFeatureType feature, Client client)
	{
		JFreeChart chart = null;
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Plot plot;
		ArrayList<MonthReport> histories;
		int size;
		SimpleDateFormat sdf;
		MonthReport history;
		
		assert (feature != null);
		if(feature != null)
		{
			histories = processHistory.getYearHistoryForFeature(feature, client);
			
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
						data.addValue(history.getValue(), feature.getTitle(), sdf.format(history.getPeriod()));
					}
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		}	
		
		//Setup the chart names and axes
		chart = ChartFactory.createLineChart(feature.getTitle(), "Period (Months)", feature.getTitle(), data);
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
		ArrayList<MonthReport> reports;		
		JFreeChart chart = null;
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Plot plot;
		
		assert (client != null);
		if(client != null)
		{
			reports = financialRecords.getYearRevenueForClient(client);
			addDataToChart(data, reports, "Revenue");
		}
			
		//Setup the chart
		chart = ChartFactory.createLineChart("Revenue", "Period (Months)", "Dollars", data);
		chart.removeLegend();
		
		plot = chart.getPlot();
		plot.setNoDataMessage("No data available");
		
		return chart;
	}
	
	/**
	 * Creates a line graph of the revenue for the past 12 months
	 * @param serviceType	The service to produce the data for
	 * @return A chart containing the past 12 months of revenue
	 */
	public JFreeChart generateRevenueLineChartForService(ServiceType serviceType)
	{
		JFreeChart chart = null;
		ArrayList<MonthReport> reports = null;
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Plot plot;
		
		assert (serviceType != null);
		if(serviceType != null)
		{
			reports = financialRecords.getYearRevenueForService(serviceType);
			addDataToChart(data, reports, "Revenue");
			
			reports = financialRecords.getYearExpenseForService(serviceType);
			addDataToChart(data, reports, "Expenses");
		}
			
		//Setup the chart
		chart = ChartFactory.createLineChart("Revenue/ Expense", "Period (Months)", "Dollars", data);
		
		plot = chart.getPlot();
		plot.setNoDataMessage("No data available");
		
		return chart;
	}
	
	/**
	 * Adds the report data to a graph
	 * @param data			The chart's data container
	 * @param reports		List of reports
	 * @param xaxisName		Name of the X axis value
	 */
	private void addDataToChart(DefaultCategoryDataset data, ArrayList<MonthReport> reports, String xaxisName)
	{
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
					data.addValue(report.getValue(), xaxisName, sdf.format(report.getPeriod()));
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
	
	/**
	 * Creates a chart to illustrate the breakdown of revenue to expenses
	 * @param client	The client to generate breakdown for
	 * @return	The chart of the financial break down
	 */
	public JFreeChart generateFinancialBreakdownForClient(Client client)
	{
		JFreeChart chart = null;
		double revenue, expenses;

		assert (client != null);
		if(client != null)
		{
			revenue  = financialRecords.calcClientRevenueToDate(client);
			expenses = financialRecords.calcClientExpensesToDate(client);
			
			chart = generateFinancialBreakdown(revenue, expenses);
		}	
		
		return chart;
	}
	
	/**
	 * Creates a chart to illustrate the breakdown of revenue to expenses
	 * @param serviceType	The service to generate breakdown for
	 * @return	The chart of the financial break down
	 */
	public JFreeChart generateFinancialBreakdownForService(ServiceType serviceType)
	{
		JFreeChart chart = null;
		double revenue, expenses;

		assert (serviceType != null);
		if(serviceType != null)
		{
			revenue  = financialRecords.calcServiceRevenueToDate(serviceType);
			expenses = financialRecords.calcServiceExpensesToDate(serviceType);

			chart = generateFinancialBreakdown(revenue, expenses);
		}

		return chart;
	}
	
	/**
	 * Creates a chart to illustrate the breakdown of revenue and expenses
	 * @param revenue 	The total revenue
	 * @param expenses 	The total expenses
	 * @return	The chart of the financial break down
	 */
	private JFreeChart generateFinancialBreakdown(double revenue, double expenses)
	{
		JFreeChart chart = null;
		DefaultPieDataset data = new DefaultPieDataset();;
		PiePlot plot;

		data.setValue("Profit", revenue - expenses);
		data.setValue("Expenses", expenses);
		
		//Setup the chart names and axes
		chart = ChartFactory.createPieChart("Financial Breakdown", data);
		
		plot = (PiePlot) chart.getPlot();
		plot.setSectionPaint("Profit", Color.GREEN);
		plot.setSectionPaint("Expenses", Color.RED);
		plot.setNoDataMessage("No data available");

		return chart;
	}
}
