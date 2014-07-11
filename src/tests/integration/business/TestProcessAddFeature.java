package tests.integration.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import objects.Client;
import objects.Email;
import objects.PhoneNumber;
import objects.TrackedFeature;
import objects.TrackedFeatureType;
import objects.Client.ClientStatus;

import org.junit.Test;

import business.ProcessAddFeature;
import business.ProcessClient;
import persistence.DBInterface;

public class TestProcessAddFeature {
	
	DBInterface database = new DBInterface("db");
	TrackedFeatureType featureTypeValid = new TrackedFeatureType("Test");
	TrackedFeatureType featureTypeValid2 = new TrackedFeatureType("Test2");
	
	@Test
	public void testProcessAddFeature() {
		ProcessAddFeature processAddFeature = new ProcessAddFeature();
		
		assertNotNull(processAddFeature);
	}

	@Test
	public void testGetFeatureTypeByTitle() {

		ProcessAddFeature processAddFeature = new ProcessAddFeature();
		
		database.connect();
		processAddFeature.insert(featureTypeValid);
		database.disconnect();

		assertEquals(featureTypeValid.getTitle(), processAddFeature.getFeatureTypeByTitle("Test").get(0).getTitle());
		assertNotNull(processAddFeature.getFeatureTypeByTitle("Test").get(0).getTitle());
		assertNull(processAddFeature.getFeatureTypeByTitle("Invalid title"));
		
		database.connect();
		database.drop(featureTypeValid);
		database.disconnect();
	}

	@Test
	public void testGetFeatureTypes() {

		ProcessAddFeature processAddFeature = new ProcessAddFeature();
		
		database.connect();
		processAddFeature.insert(featureTypeValid);
		database.disconnect();
		
		assertNotNull(processAddFeature.getFeatureTypes());
		
		database.connect();
		database.drop(featureTypeValid);
		database.disconnect();
	}

	@Test
	public void testGetNextFeature() {
		ProcessAddFeature processAddFeature = new ProcessAddFeature();
		
		database.connect();
		processAddFeature.insert(featureTypeValid);
		processAddFeature.insert(featureTypeValid2);
		database.disconnect();

		assertNotNull(processAddFeature.getNextFeature());
		assertNotNull(processAddFeature.getNextFeature());
		
		database.connect();
		database.drop(featureTypeValid);
		database.drop(featureTypeValid2);
		database.disconnect();
		
	}

	@Test
	public void testGetFeatureByID() {

		ProcessAddFeature processAddFeature = new ProcessAddFeature();
		
		database.connect();
		processAddFeature.insert(featureTypeValid);
		database.disconnect();

		assertNotNull(processAddFeature.getFeatureByID(1));
		assertNull(processAddFeature.getFeatureByID(-1));
		
		database.connect();
		database.drop(featureTypeValid);
		database.disconnect();
	}

	@Test
	public void testGetFeaturesByClient() {
		
		Client client = new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);

		ProcessAddFeature processAddFeature = new ProcessAddFeature();
		ProcessClient processClient = new ProcessClient();

		database.connect();
		processClient.insert(client);
		processAddFeature.insert(featureTypeValid);
		database.disconnect();
		
		client = processClient.getClientByBusinessName(client.getBusinessName());
		featureTypeValid = processAddFeature.getFeatureTypeByTitle(featureTypeValid.getTitle()).get(0);
		
		TrackedFeature featureValid = new TrackedFeature("notes", client.getID(), featureTypeValid, new Date(), 100.0);

		database.connect();
		processAddFeature.insert(featureValid);
		database.disconnect();
		
		assertEquals(featureValid.getNotes(), processAddFeature.getFeaturesByClient(client).get(0).getNotes());
		
		database.connect();
		database.drop(featureValid);
		database.drop(featureTypeValid);
		database.drop(client);
		database.disconnect();
	}

}
