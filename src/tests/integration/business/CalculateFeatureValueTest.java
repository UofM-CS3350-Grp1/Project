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

import business.CalculateFeatureValue;
import business.ProcessAddFeature;
import business.ProcessClient;

public class CalculateFeatureValueTest 
{
	CalculateFeatureValue calc;
	
	@Rule
	public TestName testName = new TestName();
	
	@Before
	public void before()
	{
		System.out.println("Running test: " + this.getClass().toString() + "::" + testName.getMethodName());
	}
	
	@After
	public void after()
	{
		System.out.println("Finished test.\n");
	}

	@Test
	public void calculateTotalValueTest() 
	{
		calc = null;
		calc = new CalculateFeatureValue();
		assertNotNull(calc);
	}
	
	@Test
	public void calculateTotalValueTest2() 
	{
		calc = null;
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
		
		assertNotNull(CalculateFeatureValue.calculateTotalValue(client, feature));
		
		Client deleteClient = processClient.getClientByBusinessName("NameBiz");
		
		processClient.delete(deleteClient);
	}

}
