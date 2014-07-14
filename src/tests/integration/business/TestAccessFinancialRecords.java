package tests.integration.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import objects.Client;
import objects.Client.ClientStatus;
import objects.Contract;
import objects.Email;
import objects.MonthReport;
import objects.PhoneNumber;
import objects.Service;
import objects.ServiceType;

import org.junit.Test;

import persistence.DBInterface;
import business.AccessFinancialRecords;
import business.ProcessClient;
import business.ProcessContract;
import business.ProcessService;

public class TestAccessFinancialRecords {

	DBInterface database = new DBInterface("db");
	AccessFinancialRecords financialRecords;
	
	@Test
	public void testAccessFinancialRecords() {
		financialRecords = new AccessFinancialRecords();
		assertNotNull(financialRecords);
	}

	@Test
	public void testGetYearRevenueForClient() {
		
		financialRecords = new AccessFinancialRecords();
		ServiceType serviceType = new ServiceType("Type", "Description");
		Service service = new Service("Title", "Description", 50.0, serviceType);
		Contract contract = new Contract("Biz", "details", 50.0, new Date(), new Date(), new Date());
		PhoneNumber phone = new PhoneNumber("2049601538");
		Email email = new Email("test@hotmail.com");
		Client client = new Client("Client", phone, email, "123 First st", "Biz", ClientStatus.Active);
		ProcessClient processClient = new ProcessClient();
		ProcessService processService = new ProcessService();
		ProcessContract processContract = new ProcessContract();

		database.connect();
		processContract.insert(contract);
		processService.insert(serviceType);
		processClient.insert(client);
		database.disconnect();
		
		client = processClient.getClientByBusinessName("Biz");
		ArrayList<Contract> list = processContract.getContractsByClient(client);

		if(client==null) System.out.println("Client is null");
		if(list==null) System.out.println("List is null");
		if(service==null) System.out.println("Service is null");
		if(client!=null) System.out.println("Client biz name is "+client.getBusinessName());
		if(contract!=null) System.out.println("Contract biz name is "+contract.getBusinessName());
		
		service.setClientID(client.getID());
		service.setContractID(contract.getID());
		
		database.connect();
		database.insert(service);
		database.disconnect();
		
		if(service!=null) System.out.println("service biz name is "+service.getClientID()+" for client "+client.getID());
		
		MonthReport report = new MonthReport(new Date(), 100.0);
		MonthReport actual = financialRecords.getYearRevenueForClient(client).get(0);

		System.out.println(actual.getValue());
		System.out.println(report.getValue());
		
		assertEquals(report, actual);
		
		database.connect();
		database.drop(contract);
		database.drop(serviceType);
		database.drop(client);
		database.drop(service);
		database.disconnect();
		
		
		
	}

	@Test
	public void testGetYearRevenueForService() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetYearExpenseForService() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalcClientRevenueToDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalcClientExpensesToDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalcServiceRevenueToDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalcServiceExpensesToDate() {
		fail("Not yet implemented");
	}

}
