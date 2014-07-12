package tests.business;

import objects.Client;
import objects.Email;
import objects.PhoneNumber;
import objects.Client.ClientStatus;

import org.junit.Test;

import business.ProcessService;
import static org.junit.Assert.*;

public class ProcessServiceTest 
{
	private Client client = new Client("Bob", new PhoneNumber("2044567890"), new Email("test@local.ca"), "Address", "Buiznezz", ClientStatus.Active);
	
	@Test
	public void testInsert()
	{
		ProcessService process = new ProcessService();
		assertFalse("Service was inserted", process.insert(null));
	}
	
	@Test
	public void testDelete()
	{
		ProcessService process = new ProcessService();
		assertFalse("Service was deleted", process.delete(null));
	}
	
	@Test
	public void testUpdate()
	{
		ProcessService process = new ProcessService();
		assertFalse("Service was updated", process.update(null));
	}
	
	@Test
	public void testGetByID()
	{
		ProcessService process = new ProcessService();
		assertNull("ID is invalid", process.getServiceTypeByID(-1));
	}
	
	@Test
	public void testGetServiceByID()
	{
		ProcessService process = new ProcessService();
		assertNull("ID is invalid", process.getServiceByID(-1));
	}
	
	@Test
	public void testGetServiceByTitle()
	{
		ProcessService process = new ProcessService();
		assertNull("Title is invalid", process.getServiceByTitle(null));
	}
	
	@Test
	public void testGetServiceByTitle2()
	{
		ProcessService process = new ProcessService();
		assertNull("Title is invalid", process.getServiceByTitle(""));
	}
	
	@Test
	public void testGetServiceByClient()
	{
		ProcessService process = new ProcessService();
		assertNull("ID is invalid", process.getServiceByClient(-1, client));
	}
	
	@Test
	public void testGetServiceByClient2()
	{
		ProcessService process = new ProcessService();
		assertNull("Client is invalid", process.getServiceByClient(0, null));
	}
}
