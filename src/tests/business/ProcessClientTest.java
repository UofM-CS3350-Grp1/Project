package tests.business;

import objects.Client;
import objects.Email;
import objects.PhoneNumber;
import objects.Service;
import objects.Client.ClientStatus;
import objects.ServiceType;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import business.ProcessClient;
import static org.junit.Assert.*;

public class ProcessClientTest
{
	private Client client = new Client("Bob", new PhoneNumber("2044567890"), new Email("test@local.ca"), "Address", "Buiznezz", ClientStatus.Active);
	private Service service = new Service("Title", "Description", 1.0, new ServiceType("Type", "Desc"));
	
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
		ProcessClient process = new ProcessClient();
		assertFalse("Client was inserted", process.insert(null));
	}
	
	@Test
	public void testDelete()
	{
		ProcessClient process = new ProcessClient();
		assertFalse("Client was deleted", process.delete(null));
	}
	
	@Test
	public void testUpdate()
	{
		ProcessClient process = new ProcessClient();
		assertFalse("Client was updated", process.update(null));
	}
	
	@Test
	public void testGetByBusiness()
	{
		ProcessClient process = new ProcessClient();
		assertNull("Business name is invalid", process.getClientByBusinessName(null));
	}
	
	@Test
	public void testGetByID()
	{
		ProcessClient process = new ProcessClient();
		assertNull("ID is invalid", process.getClientByID(-1));
	}
	
	@Test
	public void testAddService()
	{
		ProcessClient process = new ProcessClient();
		assertFalse("New client service is invalid", process.addServiceToClient(null, null));
	}
	
	@Test
	public void testAddService2()
	{
		ProcessClient process = new ProcessClient();
		assertFalse("New client service is invalid", process.addServiceToClient(client, null));
	}
	
	@Test
	public void testAddService3()
	{
		ProcessClient process = new ProcessClient();
		assertFalse("New client service is invalid", process.addServiceToClient(null, service));
	}
	
	@Test
	public void testGetNextService()
	{
		ProcessClient process = new ProcessClient();
		assertNull("Service is invalid", process.getNextClientService(null));
	}
}
