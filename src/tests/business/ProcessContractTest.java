package tests.business;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import business.ProcessContract;
import static org.junit.Assert.*;

public class ProcessContractTest 
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
	
	@Test
	public void testInsert()
	{
		ProcessContract process = new ProcessContract();
		assertFalse("Contract was inserted", process.insert(null));
	}
	
	@Test
	public void testDelete()
	{
		ProcessContract process = new ProcessContract();
		assertFalse("Contract was deleted", process.delete(null));
	}
	
	@Test
	public void testUpdate()
	{
		ProcessContract process = new ProcessContract();
		assertFalse("Contract was updated", process.update(null));
	}
	
	@Test
	public void testGetByID()
	{
		ProcessContract process = new ProcessContract();
		assertNull("ID is invalid", process.getContractByID(-1));
	}
	
	@Test
	public void testGetByClient()
	{
		ProcessContract process = new ProcessContract();
		assertNull("Contracts are invalid", process.getContractsByClient(null));
	}
	
	@Test
	public void testGetByContractClient()
	{
		ProcessContract process = new ProcessContract();
		assertNull("Contract is invalid", process.getContractClient(null));
	}
	
	@Test
	public void testGetByService()
	{
		ProcessContract process = new ProcessContract();
		assertNotNull("Service is invalid", process.getServices(null));
	}
	
	@Test
	public void testGetNumServices()
	{
		ProcessContract process = new ProcessContract();
		assertTrue("Contract is invalid", process.getNumberOfServices(null) == 0);
	}
	
	@Test
	public void testGetNumContractsInRange()
	{
		ProcessContract process = new ProcessContract();
		assertTrue("Date is invalid", process.getNumContractsBetween(null, null) == 0);
	}
	
	@Test
	public void testGetNumContractsInRange2()
	{
		ProcessContract process = new ProcessContract();
		assertTrue("Date is invalid", process.getNumContractsBetween(new Date(12345), null) == 0);
	}
	
	@Test
	public void testGetNumContractsInRange3()
	{
		ProcessContract process = new ProcessContract();
		assertTrue("Date is invalid", process.getNumContractsBetween(null, new Date(123456)) == 0);
	}
	
	@Test
	public void testGetValueContractsInRange()
	{
		ProcessContract process = new ProcessContract();
		assertTrue("Date is invalid", process.getNumContractsBetween(null, null) == 0);
	}
	
	@Test
	public void testGetValueContractsInRange2()
	{
		ProcessContract process = new ProcessContract();
		assertTrue("Date is invalid", process.getNumContractsBetween(new Date(12345), null) == 0);
	}
	
	@Test
	public void testGetValueContractsInRange3()
	{
		ProcessContract process = new ProcessContract();
		assertTrue("Date is invalid", process.getNumContractsBetween(null, new Date(123456)) == 0);
	}
	
	@Test
	public void testGetValueServices()
	{
		ProcessContract process = new ProcessContract();
		assertTrue("Contract is invalid", process.getValueOfServices(null) == 0);
	}
}
