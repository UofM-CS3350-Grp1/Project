package tests.business;

import objects.Client;
import objects.Email;
import objects.PhoneNumber;
import objects.TrackedFeatureType;
import objects.Client.ClientStatus;

import org.junit.Test;

import business.GenerateGraph;
import static org.junit.Assert.*;

public class GenerateGraphTest
{
	private Client client = new Client("Bob", new PhoneNumber("2044567890"), new Email("test@local.ca"), "Address", "Buiznezz", ClientStatus.Active);
	private TrackedFeatureType featureType = new TrackedFeatureType("Feature type");
	
	/** 	Testing feature chart. NOTE we are avoiding the database **/
	@Test
	public void testFeatureChart()
	{
		GenerateGraph graph = new GenerateGraph();	
		assertNotNull("Feature line chart is invalid", graph.generateFeatureLineChart(null, null));
	}
	
	@Test
	public void testFeatureChart2()
	{
		GenerateGraph graph = new GenerateGraph();	
		assertNotNull("Feature line chart is invalid", graph.generateFeatureLineChart(featureType, null));
	}
	
	@Test
	public void testFeatureChart3()
	{
		GenerateGraph graph = new GenerateGraph();	
		assertNotNull("Feature line chart is invalid", graph.generateFeatureLineChart(null, client));
	}
	
	/**	Testing revenue charts 	**/
	
	@Test
	public void testRevenueChart()
	{
		GenerateGraph graph = new GenerateGraph();	
		assertNotNull("Revenue line chart is invalid",  graph.generateRevenueLineChartForClient(null));
	}
	
	@Test
	public void testRevenueChart2()
	{
		GenerateGraph graph = new GenerateGraph();	
		assertNotNull("Revenue line chart is invalid",  graph.generateRevenueLineChartForService(null));
	}
	
	/**	Testing breakdown charts	**/
	
	@Test
	public void testBreakdownChart()
	{
		GenerateGraph graph = new GenerateGraph();	
		assertNotNull("Financial breakdown chart is invalid",  graph.generateFinancialBreakdownForClient(null));
	}
	
	@Test
	public void testBreakdownChart2()
	{
		GenerateGraph graph = new GenerateGraph();	
		assertNotNull("Financial breakdown chart is invalid",  graph.generateFinancialBreakdownForService(null));
	}
}
