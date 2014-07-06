package tests.persistence;
import java.util.ArrayList;
import java.util.Date;

import objects.*;
import objects.Client.ClientStatus;
import static org.junit.Assert.*;

import org.junit.Test;

import persistence.DBInterface;

public class TestDBInterface2 {

	@Test
	public void testNewTypes()
	{
		
		DBInterface mainFace = new DBInterface("MainDB");
		mainFace.connect();
		mainFace.getLastYearReturns(mainFace.getClientByID(1));
		
		assertNotNull("Tracked Feature Type by ID",mainFace.getTrackedFeatureTypeByID(1));
		assertNotNull("Service Type by ID",mainFace.getServiceTypeByID(1));
		assertNotNull("Tracked Feature Type Dump",mainFace.dumpTrackedFeatureTypes());
		assertNotNull("Service Type Dump",mainFace.dumpServiceTypes());
		
		TrackedFeature tracky = mainFace.getTrackedFeatureByID(1);
		Service servo = mainFace.getServiceByID(1);
		Contract tracty = mainFace.getContractByID(1);
		
		tracky.setNotes("NEW NOTES");
		servo.setDescription("NEW DESCRIPTION");
		tracty.setDetails("NEW DEETS");
		
		assertTrue("Update serv", mainFace.update(servo));
		assertTrue("Update tracked", mainFace.update(tracky));
		assertTrue("Update contract", mainFace.update(tracty));
		
		assertTrue("Insert serv", mainFace.insert(servo));
		assertTrue("Insert tracked", mainFace.insert(tracky));
		assertTrue("Insert contract", mainFace.insert(tracty));
		
		ArrayList<Service> servL = new ArrayList<Service>();
		ArrayList<TrackedFeature> featL = new ArrayList<TrackedFeature>();
		
		assertNotNull("ServiceType by ID", servL = mainFace.getServicesByType(mainFace.getServiceTypeByID(1)));
		assertNotNull("FeatureType", featL = mainFace.getFeaturesByType(mainFace.getTrackedFeatureTypeByID(1)));
		
		assertNotNull("ServiceType by ID", mainFace.getServiceTypesByType("Web Design"));
		assertNotNull("FeatureType", mainFace.getTrackedFeatureTypesByTitle("Page Hits"));
		
		assertNotNull("New Tracked Feature", mainFace.getTrackedFeatureByID(1));
		assertNotNull("New Service",mainFace.getServiceByID(1));
	
		
		mainFace.disconnect();
	}
	
	@Test
	public void testNewTypesInvalid()
	{
		DBInterface mainFace = new DBInterface("MainDB");
		mainFace.connect();
		
//		mainFace.getLastYearReturns(new Client("NAME", new PhoneNumber("2222222222"), new Email("stuff@stuff.com"), "ADDR", "BNAME", ClientStatus.Active));
		
		assertNull("Tracked Feature Type not in range",mainFace.getTrackedFeatureTypeByID(Integer.MAX_VALUE));
		assertNull("Service Type not in range",mainFace.getServiceTypeByID(Integer.MAX_VALUE));
		
		assertNull("ServiceType by ID", mainFace.getServiceTypesByType("Web Desaaign"));
		assertNull("FeatureType", mainFace.getTrackedFeatureTypesByTitle("Page Hssits"));
		
		mainFace.disconnect();
	}

}
