package tests.integration.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import objects.Client;
import objects.Client.ClientStatus;
import objects.Contract;
import objects.Email;
import objects.PhoneNumber;
import objects.Service;
import objects.ServiceType;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import persistence.DBInterface;
import business.ProcessClient;
import business.ProcessContract;
import business.ProcessService;

public class TestProcessClient 
{
	ProcessClient processClient;
	ProcessService processService;
	ProcessContract processContract;
	DBInterface database = new DBInterface("mainDB");

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
	public void testProcessClient()
	{
		processClient = new ProcessClient();
		assertNotNull(processClient);
	}

	@Test
	public void testGetClientByID()
	{
		processClient = new ProcessClient();
		Email email = new Email("test@test.com");
		PhoneNumber phone = new PhoneNumber("2049601538");
		Client client = new Client("Jason", phone, email, "123 First st", "BusinessNew1", ClientStatus.Active);
		
		processClient.insert(client);
		
		Client temp = processClient.getClientByBusinessName("BusinessNew1");
		int id = temp.getID();
		Client result = processClient.getClientByID(id);
		Client resultInvalid = processClient.getClientByID(-5);

		assertNotNull(result);
		assertNull(resultInvalid);
		
		processClient.delete(result);
	}

	@Test
	public void testGetClientByBusinessName()
	{
		processClient = new ProcessClient();
		Email email = new Email("test@test.com");
		PhoneNumber phone = new PhoneNumber("2049601538");
		Client client = new Client("Jason", phone, email, "123 First st", "BusinessNew2", ClientStatus.Active);
		
		processClient.insert(client);
		
		Client result = processClient.getClientByBusinessName("BusinessNew");
		Client resultInvalid = processClient.getClientByBusinessName("Not available");

		assertNotNull(result);
		assertNull(resultInvalid);
		
		processClient.delete(result);
	}

	@Test
	public void testGetNextClient() 
	{
		processClient = new ProcessClient();
		Email email = new Email("test@test2.com");
		PhoneNumber phone = new PhoneNumber("2049601539");
		Client client = new Client("Jason", phone, email, "123 First st", "BusinessNew3", ClientStatus.Active);
		Client client2 = new Client("Jason2", phone, email, "125 First st", "BusinessNew4", ClientStatus.Active);

		processClient.insert(client);
		processClient.insert(client2);

		Client result = processClient.getNextClient();
		assertNotNull(result);

		Client result2 = processClient.getNextClient();
		assertNotNull(result2);

		Client delete1 = processClient.getClientByBusinessName("BusinessNew3");
		Client delete2 = processClient.getClientByBusinessName("BusinessNew4");

		processClient.delete(delete1);
		processClient.delete(delete2);
	}

	@Test
	public void testGetNumberOfClients() 
	{
		processClient = new ProcessClient();
		Email email = new Email("test@test2.com");
		PhoneNumber phone = new PhoneNumber("2049601539");
		Client client = new Client("Jason", phone, email, "123 First st", "BusinessNew5", ClientStatus.Active);
		Client client2 = new Client("Jason2", phone, email, "125 First st", "BusinessNew6", ClientStatus.Active);

		processClient.insert(client);
		processClient.insert(client2);

		int num = processClient.getNumberOfClients();
		
		assertTrue(num>=2);

		Client delete1 = processClient.getClientByBusinessName("BusinessNew5");
		Client delete2 = processClient.getClientByBusinessName("BusinessNew6");

		processClient.delete(delete1);
		processClient.delete(delete2);
	}

	@Test
	public void testAddServiceToClient()
	{
		processClient = new ProcessClient();
		processService = new ProcessService();
		processContract = new ProcessContract();
		Email email = new Email("test@test3.com");
		PhoneNumber phone = new PhoneNumber("2049601539");
		Client client = new Client("Jason", phone, email, "123 First st", "BusinessNew7", ClientStatus.Active);
		ServiceType serviceType = new ServiceType("Type2", "Description");
		Contract newContract = new Contract("BusinessNew7", "Details", 1111.0, new Date(), new Date(), new Date());
		
		processClient.insert(client);
		processService.insert(serviceType);
		processContract.insert(newContract);

		ServiceType typeNew = null;
		ArrayList<ServiceType> list = processService.getServiceTypes();
		Iterator<ServiceType> it = list.iterator();
		ServiceType temp = null;
		
		while(it.hasNext())
		{
			temp = it.next();
			if(temp.getType().compareTo(serviceType.getType()) == 0)
			{
				typeNew = temp;
			}
		}
		
		Client clientNew = processClient.getClientByBusinessName("BusinessNew7");
		ArrayList<Contract> clientContracts = processContract.getContractsByClient(clientNew);
		
		Service service = new Service("Title", "Description", 150.0, typeNew);
		
		if(clientContracts != null && clientContracts.size() >= 1)
		{
			service.setContractID(clientContracts.get(0).getID());
		}
		processService.insert(service);
		
		Service serviceNew = null;
		Service temp2 = null;
		processService = new ProcessService();
		while((temp2 = processService.getNextService())!=null)
		{
			if(temp2.getServiceType().getType().compareTo(serviceType.getType()) == 0)
			{
				serviceNew = temp2;
			}
		}
		
		assertTrue(processClient.addServiceToClient(clientNew, serviceNew));
		
		Client delete1 = null;
		delete1 = processClient.getClientByBusinessName("BusinessNew7");

		processClient.delete(delete1);
		processService.delete(typeNew);
	}
}
