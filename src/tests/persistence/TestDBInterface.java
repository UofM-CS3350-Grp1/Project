package tests.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import objects.*;
import objects.Client.ClientStatus;
import persistence.DBController;
import persistence.DBInterface;

public class TestDBInterface {

	@Test
	public void testValidRetrieval() 
	{
		DBInterface mainFace = new DBInterface("Test");
		mainFace.connect();
		
		assertNotNull("Basic ID implementation for services", mainFace.getServiceByID(1));
		assertNotNull("Basic ID implementation for clients", mainFace.getClientByID(2));
		assertNotNull("Basic ID implementation for contracts", mainFace.getContractByID(3));
		assertNotNull("Basic ID implementation for feature history", mainFace.getFeatureHistoryByID(1));
		assertNotNull("Basic ID implementation for tracked feature", mainFace.getTrackedFeatureByID(1));
		
		assertNotNull("Name query implementation for service", mainFace.getServicesByTitle("SERVICE_1"));
		assertNotNull("Contracts by business test", mainFace.getContractsByBusiness("Business 2"));
		assertNotNull("Clients by Ststus test", mainFace.getClientsByStatus(ClientStatus.Active));
		
		assertNotNull("Feature history retrieval by client", mainFace.getFeatureHistoryFromParent(mainFace.getClientByID(1), mainFace.getTrackedFeatureByID(1)));
		assertNotNull("Feature history retrieval by service", mainFace.getFeatureHistoryFromParent(mainFace.getServiceByID(2), mainFace.getTrackedFeatureByID(2)));
		
		assertNotNull("Feature by type", mainFace.getTrackedFeatureByTitle("Page Hits"));
		
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
		DBInterface mainFace = new DBInterface("Test");
		mainFace.connect();
		
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
		
		assertNull("Name gibbersih for service", mainFace.getServicesByTitle("asfdsfs"));
		assertNull("Contracts by gibberish", mainFace.getContractsByBusiness("jbiw29"));
		assertNull("Clients by null status", mainFace.getClientsByStatus(null));
		
		assertNull("Feature history retrieval by negative client", mainFace.getFeatureHistoryFromParent(mainFace.getClientByID(-11), mainFace.getTrackedFeatureByID(1)));
		assertNull("Feature history retrieval by negative service", mainFace.getFeatureHistoryFromParent(mainFace.getServiceByID(-22), mainFace.getTrackedFeatureByID(2)));
			
		assertNull("Feature by gibberish", mainFace.getTrackedFeatureByTitle("FHQWAGADS"));
		
		mainFace.disconnect();
	}
	
	@Test
	public void testValidInsertUpdateDelete()
	{
		DBInterface mainFace = new DBInterface("Test");
		mainFace.connect();
		
		FeatureHistory newHistory = new FeatureHistory(mainFace.getTrackedFeatureByID(1), mainFace.getClientByID(2), 2.0, new Date(), "blahblahblah");
		TrackedFeature newTracking = new TrackedFeature("BobLoblawsLawBlog", "Lobslawbombs");
		assertTrue("FeatreHistory Insert", mainFace.insert(newHistory));
		assertTrue("TrackedFeature Insert", mainFace.insert(newTracking));
		
		newHistory = mainFace.getFeatureHistoryByID(4);
		newTracking = mainFace.getTrackedFeatureByID(4);
		
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
		DBInterface mainFace = new DBInterface("Test");
		mainFace.connect();
		
		FeatureHistory newHistory = new FeatureHistory(mainFace.getTrackedFeatureByID(1), mainFace.getClientByID(2), 2.0, new Date(), "blahblahblah");
		TrackedFeature newTracking = new TrackedFeature("BobLoblawsLawBlog", "Lobslawbombs");
		assertTrue("FeatreHistory Insert", mainFace.insert(newHistory));
		assertTrue("TrackedFeature Insert", mainFace.insert(newTracking));
		
		newHistory = mainFace.getFeatureHistoryByID(4);
		newTracking = mainFace.getTrackedFeatureByID(4);
		
		newHistory.setNotes("NOT blahblahblah");
		newTracking.setNotes("Notlobbinglawbombs");
		
		assertTrue("FeatureHistory update", mainFace.update(newHistory));
		assertTrue("TrackedFeature update", mainFace.update(newTracking));
		
		assertTrue("FeatureHistory drop", mainFace.drop(newHistory));
		assertTrue("Trackedfeature drop", mainFace.drop(newTracking));

		mainFace.disconnect();
	}

}
