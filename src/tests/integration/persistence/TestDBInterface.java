package tests.integration.persistence;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import objects.*;
import objects.Client.ClientStatus;
import persistence.DBController;
import persistence.DBInterface;

public class TestDBInterface
{

	@Before
	public void before()
	{
		System.out.println("Running test: " + this.getClass().toString());
	}
	
	@After
	public void after()
	{
		System.out.println("Exiting test: " + this.getClass().toString());
	}
	
	@Test
	public void testValidRetrieval() 
	{
		DBInterface mainFace = new DBInterface("CacheDB");
		mainFace.connect();
		
		assertNotNull("Basic ID implementation for services", mainFace.getServiceByID(1));
		assertNotNull("Basic ID implementation for clients", mainFace.getClientByID(2));
		assertNotNull("Basic ID implementation for contracts", mainFace.getContractByID(3));
		assertNotNull("Basic ID implementation for tracked feature", mainFace.getTrackedFeatureByID(1));
		
		assertNotNull("Get Service by Contract", mainFace.getServiceByContract(mainFace.getContractByID(1)));
		assertNotNull("Get Service by Client", mainFace.getServiceByClient(mainFace.getClientByID(1)));
		assertNotNull("Get Client By Feature", mainFace.getClientByFeature(mainFace.getTrackedFeatureByID(2)));
		
		assertNotNull("Tracked features by service", mainFace.getTrackedFeaturesByClient(mainFace.getClientByID(1)));
		
		assertNotNull("Table dump, Services", mainFace.dumpServices());
		assertNotNull("Table dump, Contracts", mainFace.dumpContracts());
		assertNotNull("Table dump, Clients", mainFace.dumpClients());
		assertNotNull("Table dump, Features", mainFace.dumpTrackedFeatures());
		
		mainFace.disconnect();
		
	}
	
	@Test
	public void testInvalidRetreval()
	{
		DBInterface mainFace = new DBInterface("MainDB");
		mainFace.connect();
		
		Contract badContract = new Contract("NAME", "DETAILS", 3.1, new Date(), new Date(), new Date());
		Client badClient = new Client("NAME", new PhoneNumber("2222222222"), new Email("stuff@stuff.com"), "ADDR", "BNAME", ClientStatus.Active);
		Service badService = new Service("TITLE", "DESCRIP", 4.1, mainFace.getServiceTypeByID(1));
		
		assertNull("Basic ID MAX implementation for services", mainFace.getServiceByID(Integer.MAX_VALUE));
		assertNull("Basic ID MAX implementation for clients", mainFace.getClientByID(Integer.MAX_VALUE));
		assertNull("Basic ID MAX implementation for contracts", mainFace.getContractByID(Integer.MAX_VALUE));
		assertNull("Basic ID MAX implementation for tracked feature", mainFace.getTrackedFeatureByID(Integer.MAX_VALUE));
		
		assertNull("Basic ID -1 implementation for services", mainFace.getServiceByID(-1));
		assertNull("Basic ID -1 implementation for clients", mainFace.getClientByID(-1));
		assertNull("Basic ID -1 implementation for contracts", mainFace.getContractByID(-1));
		assertNull("Basic ID -1 implementation for tracked feature", mainFace.getTrackedFeatureByID(-1));
		
		assertNull("Get Service by null Contract", mainFace.getServiceByContract(null));
		assertNull("Get Service by null Client", mainFace.getServiceByClient(null));
		
		assertNull("Get Service by uninserted Contract", mainFace.getServiceByContract(badContract));
		assertNull("Get Service by uninserted Client", mainFace.getServiceByClient(badClient));
					
		mainFace.disconnect();
	}
	
	@Test
	public void testValidInsertUpdateDelete()
	{
		DBInterface mainFace = new DBInterface("MainDB");
		mainFace.connect();
		
		Service newService = new Service(80, "TITLE", "DESCRIP", 5.7, -1, 1, mainFace.getServiceTypeByID(1),"ContractBody");
		
		assertTrue("Valid service insert with -1 CLIENT, +1 CONTRACT", mainFace.insert(newService));
		mainFace.drop(mainFace.getServiceByID(14));
		
		newService = new Service(80, "TITLE", "DESCRIP", 5.7, 1, -1, mainFace.getServiceTypeByID(1),"ContractBody");
		
		assertFalse("Valid service insert with +1 CLIENT, -1 CONTRACT", mainFace.insert(newService));
		mainFace.drop(mainFace.getServiceByID(2));
		
		newService = new Service(80, "TITLE", "DESCRIP", 5.7, 1, 1, mainFace.getServiceTypeByID(1),"ContractBody");
		
		assertTrue("Valid service insert with +1 CLIENT, +1 CONTRACT", mainFace.insert(newService));
		mainFace.drop(mainFace.getServiceByID(14));
		
		Contract contract = new Contract(500, "Business Med", "NODETAILS", 4.1, new Date(),"Head","Foot", new Date(), new Date(),"Pending");
    	contract = new Contract(500, "Business Med", "NODETAILS", 4.1, new Date(),"Head","Foot", new Date(), new Date(),"Signed");
    	contract = new Contract(500, "Business Med", "NODETAILS", 4.1, new Date(),"Head","Foot", new Date(), new Date(),"Cancelled");
    	contract = new Contract(500, "Business Med", "NODETAILS", 4.1, new Date(),"Head","Foot", new Date(), new Date(),"Terminated");
    	
    	assertTrue(mainFace.insert(contract));
    	
    	contract = mainFace.getContractByID(9);
    	contract.setStatus("Pending");
    	
    	assertTrue(mainFace.update(contract));

    	//String notes, int id, int clientID, TrackedFeatureType trackedFT, double value,Date recorded
		TrackedFeature newTracking = new TrackedFeature("Lobslawbombs", 2, mainFace.getTrackedFeatureTypeByID(2), new Date(), 4.0);
	
		assertTrue("TrackedFeature Insert", mainFace.insert(newTracking));
		

		newTracking = mainFace.getTrackedFeatureByID(3);
		
		newTracking.setNotes("Notlobbinglawbombs");
		
		assertTrue("TrackedFeature update", mainFace.update(newTracking));
		
		assertTrue("Trackedfeature drop", mainFace.drop(newTracking));

		mainFace.disconnect();
	}
	
	@Test
	public void testInvalidInsertUpdateDelete()
	{
		DBInterface mainFace = new DBInterface("MainDB");
		mainFace.connect();
	
		TrackedFeature newTracking = new TrackedFeature("Lobslawbombs",2, mainFace.getTrackedFeatureTypeByID(2), new Date(), 4.0);
		Service newService = new Service(1, "TITLE", "DESCRIP", 5.7, -1, 1, mainFace.getServiceTypeByID(1),"ContractBody");
		
		assertFalse("TrackedFeature null Insert", mainFace.insert(null));
		

		assertFalse("TrackedFeature update on non-existant index", mainFace.update(newTracking));
		

		assertFalse("Trackedfeature drop on non-existant index", mainFace.drop(newTracking));

		mainFace.disconnect();
	}
	
	@Test
	public void testValidBulkInsert()
	{
		DBInterface mainFace = new DBInterface("MainDB");
		mainFace.connect();
		
		ArrayList<Storable> conList = new ArrayList<Storable>();
		ArrayList<Storable> serList = new ArrayList<Storable>();
		ArrayList<Storable> traList = new ArrayList<Storable>();
		ArrayList<Storable> feaList = new ArrayList<Storable>();
		ArrayList<Storable> cliList = new ArrayList<Storable>();
		
		//updateList
		for(int i = 0; i < 5; i++)
		{
			Contract conCon = mainFace.getContractByID(3);
			conCon.setBusinessName("UPDATEDBUSINESS");
			conList.add(conCon);
			Service serSer = mainFace.getServiceByID(3);
			serSer.setDescription("UPDATEDESRIPTION");
			serList.add(serSer);
			TrackedFeature traTra = mainFace.getTrackedFeatureByID(1);
			traTra.setNotes("UPDATENOTTES");
			traList.add(traTra);
			Client cliCli = mainFace.getClientByID(2);
			cliCli.setName("UPDATENAME");
			cliList.add(cliCli);
		}
		
		//insertList
		for(int i = 0; i < 5; i++)
		{
			conList.add(new Contract(500+i, "TESTBUSINESS", "NODETAILS", 4.1, new Date(),"Head","Foot", new Date(), new Date(),"Pending"));
			serList.add(new Service(500+i,"shhad", "dasdsh", 4.33, 4, 2, mainFace.getServiceTypeByID(1),"ContractBody"));
			traList.add(new TrackedFeature("Lobslawbombs",2 ,mainFace.getTrackedFeatureTypeByID(2), new Date(), 4.0));
			cliList.add(new Client(500+i, "NAME", new PhoneNumber("2222222222"), new Email("cat@catcat.com"), "ADDR", "NAME", 1));
		}
		
		assertNotNull(mainFace.batchMerge(conList));
		assertNotNull(mainFace.batchMerge(serList));
		assertNotNull(mainFace.batchMerge(traList));
		assertNotNull(mainFace.batchMerge(feaList));
		assertNotNull(mainFace.batchMerge(cliList));
		
		mainFace.disconnect();
	}

}
