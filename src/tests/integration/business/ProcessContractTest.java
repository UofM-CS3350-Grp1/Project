package tests.integration.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
		int test = -1;
		test = processContract.getUnusedContractID();
		assertTrue(test>0);
	}

	@Test
	public void testGetContractByID() 
	{
		processContract = new ProcessContract();
		int test = -1;
		test = processContract.getUnusedContractID();
		Contract contract = new Contract(test, "Testing", "details", 1000.0, new Date(), new Date(), new Date());
		processContract.insert(contract);
		assertNotNull(processContract.getContractByID(test));
		processContract.delete(contract);
	}

	@Test
	public void testGetContractsByClient() 
	{
		processContract = new ProcessContract();
		int test = -1;
		test = processContract.getUnusedContractID();
		Contract contract = new Contract(test, "New biz name", "details", 1000.0, new Date(), new Date(), new Date());
		processContract.insert(contract);
		assertNotNull(processContract.getContractByID(test));

		processClient = new ProcessClient();
		PhoneNumber phone = new PhoneNumber("2049601538");
		Email email = new Email("testin@test.com");
		Client client = new Client("New", phone, email, "1234 First st", "New biz name", ClientStatus.Active);
		assertTrue(processClient.insert(client));

		Client temp = processClient.getClientByBusinessName("New biz name");
		ArrayList<Contract> result = null;
		result = processContract.getContractsByClient(temp);
		assertNotNull(result);

		processClient.delete(temp);
		processContract.delete(result.get(0));
	}

	@Test
	public void testGetContractClient() 
	{
		processContract = new ProcessContract();
		int test = -1;
		test = processContract.getUnusedContractID();
		Contract contract = new Contract(test, "New biz name", "details", 1000.0, new Date(), new Date(), new Date());
		assertTrue(processContract.insert(contract));
		contract = processContract.getContractByID(test);

		processClient = new ProcessClient();
		PhoneNumber phone = new PhoneNumber("2049601538");
		Email email = new Email("testin@test.com");
		Client client = new Client("New", phone, email, "1234 First st", "New biz name", ClientStatus.Active);
		assertTrue(processClient.insert(client));
		
		Client result = processContract.getContractClient(contract);
		assertNotNull(result);
		
		processContract.delete(contract);
		processClient.delete(result);
	}

	@Test
	public void testGetContracts() 
	{
		processContract = new ProcessContract();
		int test = -1;
		test = processContract.getUnusedContractID();
		Contract contract = new Contract(test, "New biz name", "details", 1000.0, new Date(), new Date(), new Date());
		assertTrue(processContract.insert(contract));
		contract = processContract.getContractByID(test);
		
		processContract = new ProcessContract();
		ArrayList<Contract> list = processContract.getContracts();
		assertNotNull(list);
		
		processContract.delete(contract);
	}
	
	@Test
	public void testGetServices()
	{
		processContract = new ProcessContract();
		int test = -1;
		test = processContract.getUnusedContractID();
		Contract contract = new Contract(test, "New biz name", "details", 1000.0, new Date(), new Date(), new Date());
		assertTrue(processContract.insert(contract));
		contract = processContract.getContractByID(test);
		
		processService = new ProcessService();
		ServiceType type = new ServiceType("testing", "details");
		assertTrue(processService.insert(type));
		ArrayList<ServiceType> list = processService.getServiceTypes();
		assertNotNull(list);
		Iterator<ServiceType> it = list.iterator();
		ServiceType temp = null;
		int id = -1;
		ServiceType actualType = null;
		while(it.hasNext())
		{
			temp = it.next();
			if(temp.getType().contains("testing"))
			{
				id = temp.getID();
				actualType = temp;
			}
		}
		assertTrue(id>-1);
		Service service = new Service("title", "description", 100.0, actualType);
		assertNotNull(processService.insert(service));
		ArrayList<Service> serviceList = null;
		service.setContractID(contract.getID());
		assertNotNull(processService.update(service));
		
		serviceList = processContract.getServices(contract);
		assertNotNull(serviceList);
		service = processService.getServiceByTitle("title");
		
		processContract.delete(contract);
		processService.delete(service);
		processService.delete(actualType);
	}
	 
	@Test
	public void testGetNumberOfServices()
	{
		processContract = new ProcessContract();
		int test = -1;
		test = processContract.getUnusedContractID();
		Contract contract = new Contract(test, "New biz name", "details", 1000.0, new Date(), new Date(), new Date());
		assertTrue(processContract.insert(contract));
		contract = processContract.getContractByID(test);
		
		processService = new ProcessService();
		ServiceType type = new ServiceType("testing", "details");
		assertTrue(processService.insert(type));
		ArrayList<ServiceType> list = processService.getServiceTypes();
		assertNotNull(list);
		Iterator<ServiceType> it = list.iterator();
		ServiceType temp = null;
		int id = -1;
		ServiceType actualType = null;
		while(it.hasNext())
		{
			temp = it.next();
			if(temp.getType().contains("testing"))
			{
				id = temp.getID();
				actualType = temp;
			}
		}
		assertTrue(id>-1);
		Service service = new Service("title", "description", 100.0, actualType);
		assertNotNull(processService.insert(service));
		ArrayList<Service> serviceList = null;
		service.setContractID(contract.getID());
		assertNotNull(processService.update(service));
		
		assertTrue(processContract.getNumberOfServices(contract)==1);
		
		serviceList = processContract.getServices(contract);
		assertNotNull(serviceList);
		service = processService.getServiceByTitle("title");
	}
	
	@Test
	public void testGetTotalContractsValue()
	{
		processContract = new ProcessContract();
		int test = -1;
		test = processContract.getUnusedContractID();
		Contract contract = new Contract(test, "New biz name", "details", 1000.0, new Date(), new Date(), new Date());
		assertTrue(processContract.insert(contract));
		contract = processContract.getContractByID(test);
		
		assertTrue(processContract.getTotalContractsValue()==1000.0);
		
		processContract.delete(contract);
	}
}
