package tests.integration.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import business.ProcessClient;
import business.ProcessService;
import business.ProcessContract;
import objects.Client;
import objects.Contract;
import objects.Email;
import objects.PhoneNumber;
import objects.Service;
import objects.ServiceType;
import objects.Client.ClientStatus;

public class ProcessServiceTest {

	private ProcessService processService; 
	ProcessClient processClient;

	public ProcessServiceTest()
	{
		processService = new ProcessService();
		processClient = new ProcessClient();
	}

	@Test
	public void getNextServiceTest()
	{
		PhoneNumber phone = new PhoneNumber("2049615338");
		Email email = new Email("teeffst@test.com");
		ProcessClient processClient = new ProcessClient();
		
		Client client = new Client("Nickelback", phone, email, "1221BlirstSt", "Nickelback", ClientStatus.Active);
		assertTrue(processClient.insert(client));
		int clientID = processClient.getClientByBusinessName("Nickelback").getID();

		Contract contract = new Contract("Nickelback", "A band", 1000000.01, new Date(1402194845), new Date(1402194845),new Date(1402194845));
		ProcessContract processContract = new ProcessContract();
		assertTrue(processContract.insert(contract));

		ArrayList<Contract> contracts = processContract.getContractsByClient(client);
		assertNotNull(contracts);
		int contractID = -1;
		for (int i = 0; i < contracts.size() && contractID < 0; i++)
		{
			if (contracts.get(i).getDetails().equals("A band"))
					contractID = contracts.get(i).getID();
		}

		String title = "MyNewService";
		String description = "A test service";
		Double rate = 13.37;
		ServiceType serviceType = new ServiceType("myType", "myDescription");
		assertTrue(processService.insert(serviceType));

		ArrayList<ServiceType> serviceTypes = processService.getServiceTypes();
		assertNotNull(serviceTypes);

		ServiceType theType = null;
		for (int i = 0; i < serviceTypes.size() && theType == null; i++)
		{
			if (serviceTypes.get(i).getDescription().equals("myDescription"))
				theType = serviceTypes.get(i);
		}
		assertNotNull(theType);

		Service newService = new Service(title, description, rate, theType);

		newService.setClientID(clientID);
		newService.setContractID(contractID);

		assertTrue(processService.insert(newService));

		Service theService = processService.getNextService();
		assertNotNull(theService);

		processService.delete(serviceType);
		processClient.delete(client);
		processContract.delete(contract);
		processService.delete(theService);
	}

	@Test
	public void getServiceByIDTest()
	{
		PhoneNumber phone = new PhoneNumber("2049615338");
		Email email = new Email("teeffst@test.com");
		ProcessClient processClient = new ProcessClient();
		
		Client client = new Client("Creed", phone, email, "1221BlirstSt", "Creed", ClientStatus.Active);
		assertTrue(processClient.insert(client));
		int clientID = processClient.getClientByBusinessName("Creed").getID();

		Contract contract = new Contract("Creed", "A band", 1000000.01, new Date(1402194845), new Date(1402194845),new Date(1402194845));
		ProcessContract processContract = new ProcessContract();
		assertTrue(processContract.insert(contract));

		ArrayList<Contract> contracts = processContract.getContractsByClient(client);
		assertNotNull(contracts);
		int contractID = -1;
		for (int i = 0; i < contracts.size() && contractID < 0; i++)
		{
			if (contracts.get(i).getDetails().equals("A band"))
					contractID = contracts.get(i).getID();
		}

		String title = "MyNewService";
		String description = "A test service";
		Double rate = 13.37;
		ServiceType serviceType = new ServiceType("myType", "myDescription");
		assertTrue(processService.insert(serviceType));

		ArrayList<ServiceType> serviceTypes = processService.getServiceTypes();
		assertNotNull(serviceTypes);

		ServiceType theType = null;
		for (int i = 0; i < serviceTypes.size() && theType == null; i++)
		{
			if (serviceTypes.get(i).getDescription().equals("myDescription"))
				theType = serviceTypes.get(i);
		}
		assertNotNull(theType);

		Service theService = new Service(title, description, rate, theType);

		theService.setClientID(clientID);
		theService.setContractID(contractID);

		assertTrue(processService.insert(theService));

		Service myService1 = processService.getServiceByTitle(title);
		assertNotNull(myService1);

		Service myService2 = processService.getServiceByID(myService1.getID());
		assertNotNull(myService2);

		processService.delete(serviceType);
		processClient.delete(client);
		processContract.delete(contract);
		processService.delete(theService);
	}

	@Test
	public void getServiceByTitleTest()
	{
		PhoneNumber phone = new PhoneNumber("2049615338");
		Email email = new Email("teeffst@test.com");
		ProcessClient processClient = new ProcessClient();
		
		Client client = new Client("Rush", phone, email, "1221BlirstSt", "Rush", ClientStatus.Active);
		assertTrue(processClient.insert(client));
		int clientID = processClient.getClientByBusinessName("Rush").getID();

		Contract contract = new Contract("Rush", "A band", 1000000.01, new Date(1402194845), new Date(1402194845),new Date(1402194845));
		ProcessContract processContract = new ProcessContract();
		assertTrue(processContract.insert(contract));

		ArrayList<Contract> contracts = processContract.getContractsByClient(client);
		assertNotNull(contracts);
		int contractID = -1;
		for (int i = 0; i < contracts.size() && contractID < 0; i++)
		{
			if (contracts.get(i).getDetails().equals("A band"))
					contractID = contracts.get(i).getID();
		}

		String title = "MyNewService";
		String description = "A test service";
		Double rate = 13.37;
		ServiceType serviceType = new ServiceType("myType", "myDescription");
		assertTrue(processService.insert(serviceType));

		ArrayList<ServiceType> serviceTypes = processService.getServiceTypes();
		assertNotNull(serviceTypes);

		ServiceType theType = null;
		for (int i = 0; i < serviceTypes.size() && theType == null; i++)
		{
			if (serviceTypes.get(i).getDescription().equals("myDescription"))
				theType = serviceTypes.get(i);
		}
		assertNotNull(theType);

		Service theService = new Service(title, description, rate, theType);

		theService.setClientID(clientID);
		theService.setContractID(contractID);

		assertTrue(processService.insert(theService));

		Service myService = processService.getServiceByTitle(title);
		assertNotNull(myService);

		processService.delete(serviceType);
		processClient.delete(client);
		processContract.delete(contract);
		processService.delete(theService);
	}

	@Test
	public void getServiceTypeByIDTest()
	{
		ServiceType serviceType = new ServiceType("myType", "myDescription");
		assertTrue(processService.insert(serviceType));

		ArrayList<ServiceType> serviceTypes = processService.getServiceTypes();
		assertNotNull(serviceTypes);

		ServiceType theType = null;
		for (int i = 0; i < serviceTypes.size() && theType == null; i++)
		{
			if (serviceTypes.get(i).getDescription().equals("myDescription"))
				theType = serviceTypes.get(i);
		}
		assertNotNull(theType);

		ServiceType myType = processService.getServiceTypeByID(theType.getID());
		assertNotNull(myType);

		processService.delete(serviceType);
	}

	@Test
	public void getServiceTypesTest()
	{
		ServiceType serviceType = new ServiceType("myType", "myDescription");
		assertTrue(processService.insert(serviceType));

		ArrayList<ServiceType> serviceTypes = processService.getServiceTypes();
		assertNotNull(serviceTypes);

		processService.delete(serviceType);
	}
}

