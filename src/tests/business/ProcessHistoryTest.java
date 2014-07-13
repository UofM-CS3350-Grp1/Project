package tests.business;

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

import business.ProcessFeatureHistory;
import static org.junit.Assert.*;

public class ProcessHistoryTest 
{
	private Client client = new Client("Bob", new PhoneNumber("2044567890"), new Email("test@local.ca"), "Address", "Buiznezz", ClientStatus.Active);
	private TrackedFeatureType featureType = new TrackedFeatureType("Feature type");
	
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
	public void testInsert()
	{
		ProcessFeatureHistory process = new ProcessFeatureHistory();
		assertFalse("History was inserted", process.insert(null));
	}
	
	@Test
	public void testDelete()
	{
		ProcessFeatureHistory process = new ProcessFeatureHistory();
		assertFalse("History was deleted", process.delete(null));
	}
	
	@Test
	public void testUpdate()
	{
		ProcessFeatureHistory process = new ProcessFeatureHistory();
		assertFalse("History was updated", process.update(null));
	}
	
	@Test
	public void testGetByID()
	{
		ProcessFeatureHistory process = new ProcessFeatureHistory();
		assertNull("ID is invalid", process.getHistoryByID(-1));
	}
	
	@Test
	public void testFeatureTotal()
	{
		ProcessFeatureHistory process = new ProcessFeatureHistory();
		assertTrue("Feature/ client is invalid", process.getFeatureTotal(null, null) == 0);
	}
	
	@Test
	public void testFeatureTotal2()
	{
		ProcessFeatureHistory process = new ProcessFeatureHistory();
		assertTrue("Feature is invalid", process.getFeatureTotal(client, null) == 0);
	}
	
	@Test
	public void testFeatureTotal3()
	{
		ProcessFeatureHistory process = new ProcessFeatureHistory();
		assertTrue("Client is invalid", process.getFeatureTotal(null, featureType) == 0);
	}
	
	@Test
	public void testFeatureReport()
	{
		ProcessFeatureHistory process = new ProcessFeatureHistory();
		assertNotNull("Feature/ client is invalid", process.getYearHistoryForFeature(null, null));
	}
	
	@Test
	public void testFeatureReport2()
	{
		ProcessFeatureHistory process = new ProcessFeatureHistory();
		assertNotNull("Client is invalid", process.getYearHistoryForFeature(featureType, null));
	}
	
	@Test
	public void testFeatureReport3()
	{
		ProcessFeatureHistory process = new ProcessFeatureHistory();
		assertNotNull("Feature is invalid", process.getYearHistoryForFeature(featureType, null));
	}
}
