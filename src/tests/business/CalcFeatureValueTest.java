package tests.business;

import static org.junit.Assert.*;
import objects.Client;
import objects.Email;
import objects.PhoneNumber;
import objects.TrackedFeatureType;
import objects.Client.ClientStatus;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import business.CalculateFeatureValue;

public class CalcFeatureValueTest
{
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
	
	/**		Testing Basic Logic 	**/
	
	@Test
	public void testCalcFeature1()
	{
		double result = CalculateFeatureValue.calculateTotalValue(null, null);
		
		assertTrue("Result is invalid", result == 0);
	}
	
	@Test
	public void testCalcFeature2()
	{
		Client client = new Client("Bob", new PhoneNumber("2044567890"), new Email("test@local.ca"), "Address", "Buiznezz", ClientStatus.Active);
		double result = CalculateFeatureValue.calculateTotalValue(client, null);
		
		assertTrue("Result is invalid", result == 0);
	}
	
	@Test
	public void testCalcFeature3()
	{
		TrackedFeatureType featureType = new TrackedFeatureType("Feature type");
		double result = CalculateFeatureValue.calculateTotalValue(null, featureType);
		
		assertTrue("Result is invalid", result == 0);
	}
}
