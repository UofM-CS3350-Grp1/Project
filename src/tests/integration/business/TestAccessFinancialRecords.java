package tests.integration.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import objects.Client;
import objects.Client.ClientStatus;
import objects.Contract;
import objects.Email;
import objects.Expense;
import objects.MonthReport;
import objects.PhoneNumber;
import objects.Service;
import objects.ServiceType;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import business.AccessFinancialRecords;
import business.ProcessClient;
import business.ProcessContract;
import business.ProcessExpenses;
import business.ProcessService;

public class TestAccessFinancialRecords
{
	private AccessFinancialRecords financialRecords;
	private ProcessClient processClient;
	private ProcessService processService;
	private ProcessContract processContract;
	private ServiceType serviceType;
	private Contract contract;
	private Client client;
	private Service service;
	private Email email;
	private PhoneNumber phone;
	
	@Rule
	public TestName testName = new TestName();
		
	@Before
	public void before()
	{
		boolean found = false;
		
		System.out.println("Running test: " + this.getClass().toString() + "::" + testName.getMethodName());
		
		financialRecords = new AccessFinancialRecords();
		processClient = new ProcessClient();
		processService = new ProcessService();
		processContract = new ProcessContract();
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -2);
		serviceType = new ServiceType("Type", "Description");
		contract = new Contract(processContract.getUnusedContractID(), "Biz", "details", 50.0, new Date(), cal.getTime(), cal.getTime());
		phone = new PhoneNumber("2049601538");
		email = new Email("test@hotmail.com");
		client = new Client("Client", phone, email, "123 First st", "Biz", ClientStatus.Active);
		contract.setStatus("Signed");
		
		assertTrue("Service type not inserted", processService.insert(serviceType));
		assertTrue("Client not inserted", processClient.insert(client));
		assertTrue("Contract not inserted", processContract.insert(contract));
		
		ArrayList<ServiceType> types = processService.getServiceTypes();
		for(int i = 0; i < types.size() && !found; i++)
		{
			if(types.get(i).getType().equals(serviceType.getType()))
			{
				serviceType = types.get(i);
				found = true;
			}
		}
		
		client = processClient.getClientByBusinessName("Biz");
		assertNotNull("Client is null", client);
		
		service = new Service("Title", "Description", 50.0, serviceType);		
		service.setClientID(client.getID());
		service.setContractID(contract.getID());		
		assertTrue("Service not inserted", processService.insert(service));
		service = processService.getServiceByTitle("Title");
		assertNotNull("Service is null", service);
	}
	
	@After
	public void after()
	{
		//Delete the client and service type and everything associated with them
		assertTrue("Client not deleted", processClient.delete(client));
		assertTrue("Service type not deleted", processService.delete(serviceType));
		
		System.out.println("Finished test.\n");
	}
	
	@Test
	public void testAccessFinancialRecords() 
	{
		assertNotNull(financialRecords);
	}

	@Test
	public void testGetYearRevenueForClient() 
	{
		MonthReport actual = financialRecords.getYearRevenueForClient(client).get(0);
		assertTrue("Client revenue does not match expected", 50.0 == actual.getValue());
	}

	@Test
	public void testGetYearRevenueForService()
	{
		MonthReport actual = financialRecords.getYearRevenueForService(serviceType).get(0);		
		assertTrue("Service revenue does not match expected", 50 == actual.getValue());
	}

	@Test
	public void testGetYearExpenseForService() 
	{
		ProcessExpenses processExpense = new ProcessExpenses(); 
		
		assertTrue("Expense not inserted", processExpense.insertExpense(new Expense(service.getID(), "Sup", 100.0, "Some expense", new Date())));		
		
		MonthReport report = new MonthReport(new Date(), 100.0);
		MonthReport actual = financialRecords.getYearExpenseForService(serviceType).get(0);

		assertTrue("Service revenue does not match expected", report.getValue() == actual.getValue());
	}

	@Test
	public void testCalcClientRevenueToDate() 
	{
		double revenue = financialRecords.calcClientRevenueToDate(client);
		assertTrue("Client revenue does not match", 50.0 == revenue);
	}

	@Test
	public void testCalcClientExpensesToDate()
	{
		ProcessExpenses processExpense = new ProcessExpenses();
		double expense;
		
		assertTrue("Expense not inserted", processExpense.insertExpense(new Expense(service.getID(), "Sup", 100.0, "Some expense", new Date())));		
		
		expense = financialRecords.calcClientExpensesToDate(client);
		assertTrue("Client expenses does not match", 100.0 == expense);
	}

	@Test
	public void testCalcServiceRevenueToDate() 
	{
		double revenue = financialRecords.calcServiceRevenueToDate(serviceType);
		assertTrue("Service revenue does not match", 50.0 == revenue);
	}

	@Test
	public void testCalcServiceExpensesToDate() 
	{
		ProcessExpenses processExpense = new ProcessExpenses();
		double expense;
		
		assertTrue("Expense not inserted", processExpense.insertExpense(new Expense(service.getID(), "Sup", 100.0, "Some expense", new Date())));		
		
		expense = financialRecords.calcServiceExpensesToDate(serviceType);
		assertTrue("Service expenses does not match", 100.0 == expense);
	}

}
