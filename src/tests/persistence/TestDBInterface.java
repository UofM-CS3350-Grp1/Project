package tests.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

import objects.*;
import persistence.DBController;
import persistence.DBInterface;

public class TestDBInterface {

	@Test
	public void testRetrieval() 
	{
		DBInterface mainFace = new DBInterface("Test");
		mainFace.connect();
		
		assertNotNull("Basic ID implementation for services", mainFace.getServiceByID(1));
		assertNotNull("Basic ID implementation for clients", mainFace.getClientByID(2));
		assertNotNull("Basic ID implementation for contracts", mainFace.getContractByID(3));
		assertNotNull("Basic ID implementation for feature history", mainFace.getFeatureHistoryByID(1));
		assertNotNull("Basic ID implementation for tracked feature", mainFace.getTrackedFeatureByID(1));
		
		
		assertNotNull("Name query implementation for service", mainFace.getServicesByTitle("SERVICE_1"));
		
		assertNotNull("Feature history retrieval by parent", mainFace.getFeatureHistoryFromParent(mainFace.getClientByID(1), mainFace.getTrackedFeatureByID(1)));
		
		assertNotNull("Table dump, Services", mainFace.dumpServices());
		assertNotNull("Table dump, Contracts", mainFace.dumpContracts());
		assertNotNull("Table dump, Clients", mainFace.dumpClients());
		assertNotNull("Table dump, Features", mainFace.dumpTrackedFeatures());
		assertNotNull("Table dump, Histories", mainFace.dumpFeatureHistory());
		
		mainFace.disconnect();
		
	}

}
