package tests.integration.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import objects.Client;
import objects.Client.ClientStatus;
import objects.Email;
import objects.Expense;
import objects.PhoneNumber;
import objects.Service;
import objects.ServiceType;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import business.ProcessClient;
import business.ProcessContract;
import business.ProcessExpenses;
import business.ProcessService;

public class ProcessExpensesTest 
{
	
	ProcessContract processContract;
	ProcessClient processClient;
	ProcessService processService;
	ProcessExpenses processExpense;
	Expense expense;

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
	public void testProcessExpenses() 
	{
		processExpense = new ProcessExpenses();
		assertNotNull(processExpense);
	}

	@Test
	public void testInsertExpense() 
	{
		ServiceType type = new ServiceType("type", "details");
		Service service = new Service(type.getType(), "description", 1000.0, type);
		processService = new ProcessService();
		service.setClientID(1);
		service.setContractID(1);
		service.setType(null);

		service.setTitle("title");
		
		assertTrue(processService.insert(type));
		
		ArrayList<ServiceType> list = processService.getServiceTypes();
		Iterator<ServiceType> it = list.iterator();
		ServiceType temp = null;
		ServiceType actual = null;
		while(it.hasNext())
		{
			temp = it.next();
			if(temp.getType().compareTo(type.getType()) == 0)
			{
				actual = temp;
			}
		}
		if(actual==null) System.out.println("No type found");
		service.setType(actual);
		assertTrue(processService.insert(service));
		
		Service tempS = processService.getServiceByTitle("title");
		expense = new Expense(tempS.getID(), "supplier", 500.0, "details", new Date());
		
		processExpense = new ProcessExpenses();
		assertTrue(processExpense.insertExpense(expense));
		
		processService.delete(tempS);
	}

	@Test
	public void testGetExpensesByClient()
	{
		processService = new ProcessService();
		PhoneNumber phone = new PhoneNumber("2049601538");
		Email email = new Email("test@test.com");
		Client client = new Client("new", phone, email, "123 first st", "newbiz", ClientStatus.Active);
		processClient = new ProcessClient();
		assertTrue(processClient.insert(client));
		int id = processClient.getClientByBusinessName("newbiz").getID();
		
		ServiceType type = new ServiceType("type", "details");
		
		assertTrue(processService.insert(type));
		
		ArrayList<ServiceType> list = processService.getServiceTypes();
		Iterator<ServiceType> it = list.iterator();
		ServiceType temp = null;
		ServiceType actual = null;
		while(it.hasNext())
		{
			temp = it.next();
			if(temp.getType().compareTo(type.getType()) == 0)
			{
				actual = temp;
			}
		}
		
		Service service = new Service(type.getType(), "description", 1000.0, actual);
		service.setTitle("titles");

		int cID = 1;
		service.setContractID(cID);
		service.setClientID(id);

		processService = new ProcessService();
		
		assertTrue(processService.insert(service));
		
		Service tempS = processService.getServiceByTitle("titles");
		expense = new Expense(tempS.getID(), "supplier", 500.0, "details", new Date());
		
		processExpense = new ProcessExpenses();
		assertTrue(processExpense.insertExpense(expense));
		assertTrue(processExpense.getExpensesByClient(client)>=0);

		processService.delete(actual);
		processService.delete(tempS);
	}
}
