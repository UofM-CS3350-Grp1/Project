package business;

import objects.Service;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Responsible for generating graphs based on clients and services
 */
public class GenerateLineGraph
{
	public GenerateLineGraph()
	{
		
	}
	
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
}
