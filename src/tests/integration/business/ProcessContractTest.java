package tests.integration.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import objects.Client;
import objects.Contract;
import objects.Email;
import objects.PhoneNumber;
import objects.Client.ClientStatus;
import objects.Service;
import objects.ServiceType;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import business.ProcessClient;
import business.ProcessContract;
import business.ProcessService;

public class ProcessContractTest 
{
	ProcessContract processContract;
	ProcessClient processClient;
	ProcessService processService;
	Date dateTimeStart;
	Date dateTimeEnd;
	
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
	public void testProcessContract() 
	{
		processContract = new ProcessContract();
		assertNotNull(processContract);
	}

	@Test
	public void testGetUnusedContractID() 
	{
		processContract = new ProcessContract();
		int test = processContract.getUnusedContractID();
		
		assertTrue(test>0);
	}

	@Test
	public void testGetContractByID() 
	{
		processClient = new ProcessClient();
		Email email = new Email("testasd@test.com");
		PhoneNumber phone = new PhoneNumber("2049601538");
		Client client = new Client("Jasonfsd", phone, email, "123 First st", "BusinessNew12", ClientStatus.Active);
		
		processClient.insert(client);
		client = processClient.getClientByBusinessName("BusinessNew12");
		
		Calendar newCal = Calendar.getInstance();
		newCal.set(2013, 07, 27, 10, 30, 30);
		Calendar newCal2 = Calendar.getInstance();
		newCal2.set(2014, 07, 27, 10, 30, 30);
		Date start = newCal.getTime();
		Date end = newCal2.getTime();
		
		processContract = new ProcessContract();
		int test = processContract.getUnusedContractID();
		
		Contract contract = new Contract(test, "BusinessNew12", "details", 1000.0, end, start, start);
		contract.setStatus("Signed");
		assertTrue(processContract.insert(contract));
		assertNotNull(processContract.getContractByID(test));
		
		processContract.delete(contract);
		processClient.delete(client);
	}

	@Test
	public void testGetContractsByClient() 
	{
		processClient = new ProcessClient();
		Email email = new Email("testasd@test.com");
		PhoneNumber phone = new PhoneNumber("2049601538");
		Client client = new Client("Jasonfesd", phone, email, "123 First st", "BusinessNew126", ClientStatus.Active);
		
		processClient.insert(client);		
		client = processClient.getClientByBusinessName("BusinessNew126");
		
		processContract = new ProcessContract();
		int test = processContract.getUnusedContractID();
		Contract contract = new Contract(test, "BusinessNew126", "details", 1000.0, new Date(), new Date(), new Date());
		processContract.insert(contract);
		assertNotNull(processContract.getContractByID(test));

		ArrayList<Contract> result = processContract.getContractsByClient(client);
		assertNotNull(result);

		processContract.delete(result.get(0));
		processClient.delete(client);
	}

	@Test
	public void testGetContractClient() 
	{
		processClient = new ProcessClient();
		Email email = new Email("testasd@test.com");
		PhoneNumber phone = new PhoneNumber("2049601538");
		Client client = new Client("Jasonfesd", phone, email, "123 First st", "BusinessNew120", ClientStatus.Active);
		
		processClient.insert(client);
		client = processClient.getClientByBusinessName("BusinessNew120");
		
		processContract = new ProcessContract();
		int test = processContract.getUnusedContractID();
		Contract contract = new Contract(test, "BusinessNew120", "details", 1000.0, new Date(), new Date(), new Date());
		assertTrue(processContract.insert(contract));
		contract = processContract.getContractByID(test);
		
		Client result = processContract.getContractClient(contract);
		assertNotNull(result);
		
		processContract.delete(contract);
		processClient.delete(result);
	}

	@Test
	public void testGetContracts() 
	{
		processClient = new ProcessClient();
		Email email = new Email("testasds@test.com");
		PhoneNumber phone = new PhoneNumber("2049601538");
		Client client = new Client("Jasonfwssd", phone, email, "123 First st", "BusinessNew145", ClientStatus.Active);
		
		processClient.insert(client);
		client = processClient.getClientByBusinessName("BusinessNew145");

		processContract = new ProcessContract();
		int test = processContract.getUnusedContractID();
		Contract contract = new Contract(test, "BusinessNew145", "details", 1000.0, new Date(), new Date(), new Date());
		assertTrue(processContract.insert(contract));
		contract = processContract.getContractByID(test);
		
		processContract = new ProcessContract();
		ArrayList<Contract> list = processContract.getContracts();
		assertNotNull(list);
		
		processContract.delete(contract);
		processClient.delete(client);
	}
	
	@Test
	public void testGetServices()
	{
		processClient = new ProcessClient();
		Email email = new Email("testasds@test.com");
		PhoneNumber phone = new PhoneNumber("2049601538");
		Client client = new Client("Jasonfwssd", phone, email, "123 First st", "BusinessNew14", ClientStatus.Active);
		
		processClient.insert(client);
		client = processClient.getClientByBusinessName("BusinessNew14");
		
		processContract = new ProcessContract();
		int test = -1;
		test = processContract.getUnusedContractID();
		Contract contract = new Contract(test, "BusinessNew14", "details", 1000.0, new Date(), new Date(), new Date());
		assertTrue(processContract.insert(contract));
		contract = processContract.getContractByID(test);
		
		processService = new ProcessService();
		ServiceType type = new ServiceType("testing", "details");
		assertTrue(processService.insert(type));
		
		ArrayList<ServiceType> list = processService.getServiceTypes();
		assertNotNull(list);
		
		Iterator<ServiceType> it = list.iterator();
		ServiceType temp2 = null;
		int id2 = -1;
		ServiceType actualType = null;
		
		while(it.hasNext() && actualType == null)
		{
			temp2 = it.next();
			if(temp2.getType().equals("testing"))
			{
				id2 = temp2.getID();
				actualType = temp2;
			}
		}
		
		assertTrue(id2>-1);
		assertNotNull(actualType);
		
		Service service = new Service("title", "description", 100.0, actualType);
		service.setContractID(contract.getID());
		service.setClientID(client.getID());
		assertTrue(processService.insert(service));
		service = processService.getServiceByTitle("title");
		
		ArrayList<Service> serviceList = null;		
		serviceList = processContract.getServices(contract);
		assertNotNull(serviceList);
		
		processClient.delete(client);
		processService.delete(actualType);
	}

	@Test
	public void testGetTotalContractsValue()
	{
		processClient = new ProcessClient();
		Email email = new Email("testasds@test.com");
		PhoneNumber phone = new PhoneNumber("2049601538");
		Client client = new Client("Jasonfwssd", phone, email, "123 First st", "BusinessNew149", ClientStatus.Active);
		
		processClient.insert(client);
		client = processClient.getClientByBusinessName("BusinessNew149");
		
		processContract = new ProcessContract();
		int test = processContract.getUnusedContractID();
		Contract contract = new Contract(test, "BusinessNew149", "details", 1000.0, new Date(), new Date(), new Date());
		assertTrue(processContract.insert(contract));
		contract = processContract.getContractByID(test);
		assertNotNull("Contract is null", contract);
		
		assertTrue(processContract.getTotalContractsValue()>0);

		processContract.delete(contract);
		processClient.delete(client);
	}
}
