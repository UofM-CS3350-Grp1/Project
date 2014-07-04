package tests.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import persistence.DBInterface;

public class TestDBInterface2 {

	@Test
	public void testNewTypes()
	{
		DBInterface mainFace = new DBInterface("MainDB");
		mainFace.connect();
		
		assertNotNull("Tracked Feature Type by ID",mainFace.getTrackedFeatureTypeByID(1));
		assertNotNull("Service Type by ID",mainFace.getServiceTypeByID(1));
		assertNotNull("Tracked Feature Type Dump",mainFace.dumpTrackedFeatureTypes());
		assertNotNull("Service Type Dump",mainFace.dumpServiceTypes());
		
		assertNotNull("New Tracked Feature", mainFace.getTrackedFeatureByID(1));
		
		mainFace.disconnect();
	}

}
