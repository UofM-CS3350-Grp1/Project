package tests.integration.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import objects.Client;
import objects.Email;
import objects.PhoneNumber;
import objects.Service;
import objects.ServiceType;
import objects.TrackedFeature;
import objects.TrackedFeatureType;
import objects.Client.ClientStatus;

import org.jfree.chart.JFreeChart;
import org.junit.Test;

import business.CalculateFeatureValue;
import business.GenerateGraph;
import business.ProcessAddFeature;
import business.ProcessClient;
import business.ProcessService;

public class GenerateGraphTest 
{
	
	GenerateGraph generate;
	JFreeChart chart;

	@Test
	public void generateGraphTest() 
	{
		generate = new GenerateGraph();
		assertNotNull(generate);
	}

	@Test
	public void generateFeatureLineChartTest() 
	{
		generate = new GenerateGraph();
		TrackedFeatureType feature = new TrackedFeatureType("Testing");
		PhoneNumber phone = new PhoneNumber("2049601538");
		Email email = new Email("testtest@test.com");
		ProcessClient processClient = new ProcessClient();
		ProcessAddFeature processFeature = new ProcessAddFeature();
		
		Client client = new Client("Name", phone, email, "123FirstSt", "NameBiz", ClientStatus.Active);
		TrackedFeature history = new TrackedFeature(feature, new Date(), 200.0);

		assertTrue(processClient.insert(client));
		
		history.setClientKey(processClient.getClientByBusinessName("NameBiz").getID());
		assertTrue(processFeature.insert(feature));
		
		history.setTrackedFeatureType(processFeature.getFeatureTypeByTitle(feature.getTitle()).get(0));
		
		assertTrue(processFeature.insert(history));
		
		assertNotNull(generate.generateFeatureLineChart(processFeature.getFeatureTypeByTitle(feature.getTitle()).get(0), client));
	}

	@Test
	public void generateRevenueLineChartForClientTest() 
	{
		generate = new GenerateGraph();
		TrackedFeatureType feature = new TrackedFeatureType("Testing");
		PhoneNumber phone = new PhoneNumber("2049601538");
		Email email = new Email("testtest@test2.com");
		ProcessClient processClient = new ProcessClient();
		ProcessAddFeature processFeature = new ProcessAddFeature();
		
		Client client = new Client("Name2", phone, email, "123FirstSt2", "NameBiz2", ClientStatus.Active);
		TrackedFeature history = new TrackedFeature(feature, new Date(), 200.0);

		assertTrue(processClient.insert(client));
		
		history.setClientKey(processClient.getClientByBusinessName("NameBiz2").getID());
		assertTrue(processFeature.insert(feature));
		
		history.setTrackedFeatureType(processFeature.getFeatureTypeByTitle(feature.getTitle()).get(0));
		
		assertTrue(processFeature.insert(history));
		
		assertNotNull(generate.generateRevenueLineChartForClient(client));
	}

	@Test
	public void generateRevenueLineChartForServiceTest() 
	{
		generate = new GenerateGraph();
		ProcessService processService = new ProcessService();
		
		ServiceType type = new ServiceType("testing", "adsfasd");
		assertTrue(processService.insert(type));
		
		ArrayList<ServiceType> list = processService.getServiceTypes();
		Iterator<ServiceType> it = list.iterator();
		ServiceType temp = null;
		ServiceType newType = null;
		while(it.hasNext())
		{
			temp = it.next();
			if(temp.getType().contains(type.getType()))
			{
				newType = temp;
			}
		}
		assertNotNull(newType);
		Service service = new Service("abc", "details", 250.0, newType);
		service.setClientID(1);
		service.setContractID(1);
		assertTrue(processService.insert(service));
		
		assertNotNull(generate.generateRevenueLineChartForService(newType));
	}

	@Test
	public void generateFinancialBreakdownForClientTest() 
	{
		generate = new GenerateGraph();
		TrackedFeatureType feature = new TrackedFeatureType("Testing");
		PhoneNumber phone = new PhoneNumber("2049601538");
		Email email = new Email("testtest@test5.com");
		ProcessClient processClient = new ProcessClient();
		ProcessAddFeature processFeature = new ProcessAddFeature();
		
		Client client = new Client("Name5", phone, email, "123FirstSt5", "NameBiz5", ClientStatus.Active);
		TrackedFeature history = new TrackedFeature(feature, new Date(), 200.0);

		assertTrue(processClient.insert(client));
		
		history.setClientKey(processClient.getClientByBusinessName("NameBiz5").getID());
		assertTrue(processFeature.insert(feature));
		
		history.setTrackedFeatureType(processFeature.getFeatureTypeByTitle(feature.getTitle()).get(0));
		
		assertTrue(processFeature.insert(history));
		
		assertNotNull(generate.generateFinancialBreakdownForClient(client));
	}

	@Test
	public void generateFinancialBreakdownForServiceTest() 
	{
		generate = new GenerateGraph();
		ProcessService processService = new ProcessService();
		
		ServiceType type = new ServiceType("testing", "adsfasd");
		assertTrue(processService.insert(type));
		
		ArrayList<ServiceType> list = processService.getServiceTypes();
		Iterator<ServiceType> it = list.iterator();
		ServiceType temp = null;
		ServiceType newType = null;
		while(it.hasNext())
		{
			temp = it.next();
			if(temp.getType().contains(type.getType()))
			{
				newType = temp;
			}
		}
		assertNotNull(newType);
		Service service = new Service("abc", "details", 250.0, newType);
		service.setClientID(1);
		service.setContractID(1);
		assertTrue(processService.insert(service));
		
		assertNotNull(generate.generateFinancialBreakdownForService(newType));
	}

}
