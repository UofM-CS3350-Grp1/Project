package tests.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import objects.*;
import objects.Client.ClientStatus;
import persistence.DBController;
import persistence.DBInterface;

public class TestDBInterface
{

	@Test
	public void testValidRetrieval() 
	{
		DBInterface mainFace = new DBInterface("CacheDB");
		mainFace.connect();
		
		assertNotNull("Basic ID implementation for services", mainFace.getServiceByID(1));
		assertNotNull("Basic ID implementation for clients", mainFace.getClientByID(2));
		assertNotNull("Basic ID implementation for contracts", mainFace.getContractByID(3));
		assertNotNull("Basic ID implementation for feature history", mainFace.getFeatureHistoryByID(1));
		assertNotNull("Basic ID implementation for tracked feature", mainFace.getTrackedFeatureByID(1));
		
		assertNotNull("Get Service by Contract", mainFace.getServiceByContract(mainFace.getContractByID(1)));
		assertNotNull("Get Service by Client", mainFace.getServiceByClient(mainFace.getClientByID(1)));
		assertNotNull("Get Client By Feature", mainFace.getClientByFeature(mainFace.getTrackedFeatureByID(2)));
		
		assertNotNull("Feature history retrieval by client", mainFace.getFeatureHistoryFromParent(mainFace.getClientByID(1), mainFace.getTrackedFeatureByID(1)));
		assertNotNull("Tracked features by service", mainFace.getTrackedFeaturesByClient(mainFace.getClientByID(3)));
		
		assertNotNull("Table dump, Services", mainFace.dumpServices());
		assertNotNull("Table dump, Contracts", mainFace.dumpContracts());
		assertNotNull("Table dump, Clients", mainFace.dumpClients());
		assertNotNull("Table dump, Features", mainFace.dumpTrackedFeatures());
		assertNotNull("Table dump, Histories", mainFace.dumpFeatureHistory());
		
		mainFace.disconnect();
		
	}
	
	@Test
	public void testInvalidRetreval()
	{
		DBInterface mainFace = new DBInterface("CacheDB");
		mainFace.connect();
		
		Contract badContract = new Contract("NAME", "DETAILS", 3.1, new Date(), new Date(), new Date());
		Client badClient = new Client("NAME", new PhoneNumber("2222222222"), new Email("stuff@stuff.com"), "ADDR", "BNAME", ClientStatus.Active);
		Service badService = new Service("TITLE", "DESCRIP", 4.1, mainFace.getServiceTypeByID(1));
		
		assertNull("Basic ID MAX implementation for services", mainFace.getServiceByID(Integer.MAX_VALUE));
		assertNull("Basic ID MAX implementation for clients", mainFace.getClientByID(Integer.MAX_VALUE));
		assertNull("Basic ID MAX implementation for contracts", mainFace.getContractByID(Integer.MAX_VALUE));
		assertNull("Basic ID MAX implementation for feature history", mainFace.getFeatureHistoryByID(Integer.MAX_VALUE));
		assertNull("Basic ID MAX implementation for tracked feature", mainFace.getTrackedFeatureByID(Integer.MAX_VALUE));
		
		assertNull("Basic ID -1 implementation for services", mainFace.getServiceByID(-1));
		assertNull("Basic ID -1 implementation for clients", mainFace.getClientByID(-1));
		assertNull("Basic ID -1 implementation for contracts", mainFace.getContractByID(-1));
		assertNull("Basic ID -1 implementation for feature history", mainFace.getFeatureHistoryByID(-1));
		assertNull("Basic ID -1 implementation for tracked feature", mainFace.getTrackedFeatureByID(-1));
		
		assertNull("Get Service by null Contract", mainFace.getServiceByContract(null));
		assertNull("Get Service by null Client", mainFace.getServiceByClient(null));
		
		assertNull("Get Service by uninserted Contract", mainFace.getServiceByContract(badContract));
		assertNull("Get Service by uninserted Client", mainFace.getServiceByClient(badClient));
		
		assertNull("Feature history retrieval by negative/null client", mainFace.getFeatureHistoryFromParent(mainFace.getClientByID(-11), mainFace.getTrackedFeatureByID(1)));

		
		assertNull("Feature history retrieval by uninserted client", mainFace.getFeatureHistoryFromParent(badClient, mainFace.getTrackedFeatureByID(1)));
				
		mainFace.disconnect();
	}
	
	@Test
	public void testValidInsertUpdateDelete()
	{
		DBInterface mainFace = new DBInterface("CacheDB");
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
		
		FeatureHistory newHistory = null;
		newHistory = new FeatureHistory(mainFace.getTrackedFeatureByID(1), mainFace.getClientByID(2), 2.0, new Date(), "blahblahblah");
		TrackedFeature newTracking = new TrackedFeature("BobLoblawsLawBlog", "Lobslawbombs", 2, 1, mainFace.getTrackedFeatureTypeByID(1), null);
		assertTrue("FeatreHistory Insert", mainFace.insert(newHistory));
		assertTrue("TrackedFeature Insert", mainFace.insert(newTracking));
		
		newHistory = mainFace.getFeatureHistoryByID(3);
		newTracking = mainFace.getTrackedFeatureByID(3);
		
		newHistory.setNotes("NOT blahblahblah");
		newTracking.setNotes("Notlobbinglawbombs");
		
		assertTrue("FeatureHistory update", mainFace.update(newHistory));
		assertTrue("TrackedFeature update", mainFace.update(newTracking));
		
		assertTrue("FeatureHistory drop", mainFace.drop(newHistory));
		assertTrue("Trackedfeature drop", mainFace.drop(newTracking));

		mainFace.disconnect();
	}
	
	@Test
	public void testInvalidInsertUpdateDelete()
	{
		DBInterface mainFace = new DBInterface("CacheDB");
		mainFace.connect();
	
		TrackedFeature newTracking = new TrackedFeature("BobLoblawsLawBlog", "Lobslawbombs", mainFace.getTrackedFeatureTypeByID(2));
		Service newService = new Service(1, "TITLE", "DESCRIP", 5.7, -1, 1, mainFace.getServiceTypeByID(1),"ContractBody");
		
		assertFalse("FeatreHistory null Insert", mainFace.insert(null));
		assertFalse("TrackedFeature null Insert", mainFace.insert(null));
		
		FeatureHistory newHistory = new FeatureHistory(mainFace.getTrackedFeatureByID(1), mainFace.getClientByID(2), 2.0, new Date(), "blahblahblah");
		newTracking = new TrackedFeature("BobLoblawsLawBlog", "Lobslawbombs", Integer.MAX_VALUE, 3, mainFace.getTrackedFeatureTypeByID(1), null);
		
		assertFalse("FeatureHistory update on non-existant index", mainFace.update(newHistory));
		assertFalse("TrackedFeature update on non-existant index", mainFace.update(newTracking));
		
		assertFalse("FeatureHistory drop on non-existant index", mainFace.drop(newHistory));
		assertFalse("Trackedfeature drop on non-existant index", mainFace.drop(newTracking));

		mainFace.disconnect();
	}
	
	@Test
	public void testValidBulkInsert()
	{
		DBInterface mainFace = new DBInterface("CacheDB");
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
			FeatureHistory feaFea = mainFace.getFeatureHistoryByID(1);
			feaFea.setNotes("UPDATENOTES");
			feaList.add(feaFea);
			Client cliCli = mainFace.getClientByID(2);
			cliCli.setName("UPDATENAME");
			cliList.add(cliCli);
		}
		
		//insertList
		for(int i = 0; i < 5; i++)
		{
			conList.add(new Contract(500+i, "TESTBUSINESS", "NODETAILS", 4.1, new Date(),"Head","Foot", new Date(), new Date()));
			serList.add(new Service(500+i,"shhad", "dasdsh", 4.33, 4, 2, mainFace.getServiceTypeByID(1),"ContractBody"));
			traList.add(new TrackedFeature("NAME", "NOTES", 500 + i, 3, mainFace.getTrackedFeatureTypeByID(1), null));
			feaList.add(new FeatureHistory(mainFace.getTrackedFeatureByID(1), mainFace.getClientByID(1), 22.1, new Date(), "NKJDS", 500 +i));
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
