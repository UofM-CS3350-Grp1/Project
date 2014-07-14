package tests.integration.business;

import static org.junit.Assert.*;

import java.util.Date;

import objects.Client;
import objects.Email;
import objects.PhoneNumber;
import objects.TrackedFeature;
import objects.TrackedFeatureType;
import objects.Client.ClientStatus;

import org.junit.Test;

import business.CalculateFeatureValue;
import business.ProcessAddFeature;
import business.ProcessClient;

public class CalculateFeatureValueTest 
{
	
	CalculateFeatureValue calc;

	@Test
	public void calculateTotalValueTest() 
	{
		calc = null;
		calc = new CalculateFeatureValue();
		assertNotNull(calc);
	}
	
	@Test
	public void calculateTotalValueTest2() 
	{
		calc = null;
		TrackedFeatureType feature = new TrackedFeatureType("Testing");
		PhoneNumber phone = new PhoneNumber("2049601538");
		Email email = new Email("testtest@test.com");
		ProcessClient processClient = new ProcessClient();
		ProcessAddFeature processFeature = new ProcessAddFeature();
		
		Client client = new Client("Name", phone, email, "123FirstSt", "NameBiz", ClientStatus.Active);
		TrackedFeature history = new TrackedFeature(feature, new Date(), 200.0);

		assertTrue(processClient.insert(client));
		
		history.setClientKey(processClient.getClientByBusinessName("NameBiz").getID());
		assertTrue(processFeature.insert(feature));
		
		history.setTrackedFeatureType(processFeature.getFeatureTypeByTitle(feature.getTitle()).get(0));
		

		System.out.println("Feature id "+feature.getID());
		System.out.println(history.getClientKey());
		System.out.println(history.getID());
		System.out.println(history.getNotes());
		System.out.println(history.getTableName());
		System.out.println(history.getValue());
		System.out.println(history.getDate());
		System.out.println(history.getTrackedFeatureType().getTitle());
		assertTrue(processFeature.insert(history));
		
		assertNotNull(CalculateFeatureValue.calculateTotalValue(client, feature));
		
		Client deleteClient = processClient.getClientByBusinessName("NameBiz");
		
		processClient.delete(deleteClient);
	}

}
