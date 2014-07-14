package tests.integration.business;

import static org.junit.Assert.*;

import java.util.Date;

import objects.Client;
import objects.Email;
import objects.PhoneNumber;
import objects.TrackedFeature;
import objects.TrackedFeatureType;
import objects.Client.ClientStatus;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import business.ProcessAddFeature;
import business.ProcessClient;
import persistence.DBInterface;

public class TestProcessAddFeature
{
	DBInterface database = new DBInterface("MainDB");
	TrackedFeatureType featureTypeValid = new TrackedFeatureType("Test");
	TrackedFeatureType featureTypeValid2 = new TrackedFeatureType("Test2");

	@Rule
	public TestName testName = new TestName();
	
	@Before
	public void before()
	{
		System.out.println("Running test: " + testName.getMethodName());
	}
	
	@After
	public void after()
	{
		System.out.println("Finished test.\n");
	}
	
	@Test
	public void testProcessAddFeature() 
	{
		ProcessAddFeature processAddFeature = new ProcessAddFeature();		
		assertNotNull(processAddFeature);
	}

	@Test
	public void testGetFeatureTypeByTitle() 
	{
		ProcessAddFeature processAddFeature = new ProcessAddFeature();		
		processAddFeature.insert(featureTypeValid);

		assertEquals(featureTypeValid.getTitle(), processAddFeature.getFeatureTypeByTitle("Test").get(0).getTitle());
		assertNotNull(processAddFeature.getFeatureTypeByTitle("Test").get(0).getTitle());
		assertNull(processAddFeature.getFeatureTypeByTitle("Invalid title"));
		
		TrackedFeatureType output = processAddFeature.getFeatureTypeByTitle("Test").get(0);
		
		processAddFeature.delete(output);
	}

	@Test
	public void testGetFeatureTypes() 
	{
		ProcessAddFeature processAddFeature = new ProcessAddFeature();
		
		processAddFeature.insert(featureTypeValid);
		
		assertNotNull(processAddFeature.getFeatureTypes());
		
		TrackedFeatureType output = processAddFeature.getFeatureTypeByTitle("Test").get(0);

		database.connect();
		database.drop(output);
		database.disconnect();
	}

	@Test
	public void testGetNextFeature()
	{
		ProcessAddFeature processAddFeature = new ProcessAddFeature();
		
		processAddFeature.insert(featureTypeValid);
		processAddFeature.insert(featureTypeValid2);

		assertNotNull(processAddFeature.getNextFeature());
		assertNotNull(processAddFeature.getNextFeature());
		
		TrackedFeatureType output = processAddFeature.getFeatureTypeByTitle("Test").get(0);
		TrackedFeatureType output2 = processAddFeature.getFeatureTypeByTitle("Test2").get(0);
		
		database.connect();
		database.drop(output);
		database.drop(output2);
		database.disconnect();
		
	}

	@Test
	public void testGetFeatureByID() 
	{
		ProcessAddFeature processAddFeature = new ProcessAddFeature();		
		processAddFeature.insert(featureTypeValid);

		assertNotNull(processAddFeature.getFeatureByID(1));
		assertNull(processAddFeature.getFeatureByID(-1));
		
		TrackedFeatureType output = processAddFeature.getFeatureTypeByTitle("Test").get(0);
		
		database.connect();
		database.drop(output);
		database.disconnect();
	}

	@Test
	public void testGetFeaturesByClient() 
	{
		Client client = new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);

		ProcessAddFeature processAddFeature = new ProcessAddFeature();
		ProcessClient processClient = new ProcessClient();

		processClient.insert(client);
		processAddFeature.insert(featureTypeValid);
		
		client = processClient.getClientByBusinessName(client.getBusinessName());
		featureTypeValid = processAddFeature.getFeatureTypeByTitle(featureTypeValid.getTitle()).get(0);
		
		TrackedFeature featureValid = new TrackedFeature("notes", client.getID(), featureTypeValid, new Date(), 100.0);

		processAddFeature.insert(featureValid);
		
		assertEquals(featureValid.getNotes(), processAddFeature.getFeaturesByClient(client).get(0).getNotes());
		
		database.connect();
		database.drop(featureValid);
		database.drop(featureTypeValid);
		processClient.delete(client);
		database.disconnect();
	}
}
