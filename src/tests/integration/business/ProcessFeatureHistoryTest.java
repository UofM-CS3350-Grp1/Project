package tests.integration.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import business.ProcessFeatureHistory;
import business.ProcessClient;
import business.ProcessAddFeature;
import objects.Client;
import objects.Email;
import objects.MonthReport;
import objects.PhoneNumber;
import objects.TrackedFeature;
import objects.TrackedFeatureType;
import objects.Client.ClientStatus;

public class ProcessFeatureHistoryTest
{
	private ProcessFeatureHistory processFeatureHistory; 
	private ProcessClient processClient;
	private ProcessAddFeature processAddFeature;

	public ProcessFeatureHistoryTest()
	{
		processFeatureHistory = new ProcessFeatureHistory();
		processClient = new ProcessClient();
		processAddFeature = new ProcessAddFeature(); 
	}

	@Test
	public void getFeatureTotalTest()
	{
		double value = -14;
		Email email = new Email("testmail@testing.com");
		PhoneNumber phone = new PhoneNumber("2049601538");
		Client newClient = new Client("Billy", phone, email, "123 FirstLast St", "BusinessShoe1", ClientStatus.Active);
		assertTrue(processClient.insert(newClient));

		TrackedFeatureType newFeature = new TrackedFeatureType("myFeature");
		assertTrue(processAddFeature.insert(newFeature));

		Client theClient = processClient.getClientByBusinessName("BusinessShoe1");
		assertNotNull(theClient);

		ArrayList<TrackedFeatureType> featureTypes = processAddFeature.getFeatureTypes();
		assertNotNull(featureTypes);

		TrackedFeatureType theFeature = null;
		for (int i = 0; i < featureTypes.size() && theFeature == null; i++)
		{
			if (featureTypes.get(i).getTitle().equals("myFeature"))
				theFeature = featureTypes.get(i);
		}

		processFeatureHistory = new ProcessFeatureHistory();
		value = processFeatureHistory.getFeatureTotal(theClient, newFeature);

		assertNotEquals(-14,value);

		processAddFeature.delete(theFeature);
		processClient.delete(theClient);		
	}

	@Test
	public void getHistoryByIDTest()
	{
		Email email = new Email("testmail@testing.com");
		PhoneNumber phone = new PhoneNumber("2049601538");
		Client newClient = new Client("Willy", phone, email, "123 FirstLast St", "BusinessHotel", ClientStatus.Active);
		assertTrue(processClient.insert(newClient));

		TrackedFeatureType newFeatureType = new TrackedFeatureType("myFeature");
		assertTrue(processAddFeature.insert(newFeatureType));

		ArrayList<TrackedFeatureType> featureTypes = processAddFeature.getFeatureTypes();
		assertNotNull(featureTypes);

		TrackedFeatureType myFeatureType = null;
		for (int i = 0; i < featureTypes.size() && myFeatureType == null; i++)
		{
			if (featureTypes.get(i).getTitle().equals("myFeature"))
				myFeatureType = featureTypes.get(i);
		}
		
		Client theClient = processClient.getClientByBusinessName("BusinessHotel");
		assertNotNull(theClient);

		TrackedFeature theFeature = new TrackedFeature("notes", theClient.getID(), myFeatureType, new Date(1402194845), 1.5);
		assertNotNull(theFeature);

		assertTrue(processAddFeature.insert(theFeature));
		TrackedFeature aFeature = processFeatureHistory.getNextHistory();
		assertNotNull(aFeature);

		TrackedFeature feature = processFeatureHistory.getHistoryByID(aFeature.getID());
		assertNotNull(feature);

		processClient.delete(theClient);
		processAddFeature.delete(myFeatureType);
		//processAddFeature.delete(theFeature);
	}

	@Test
	public void getNextHistoryTest() 
	{
		Email email = new Email("testmail@testingg.com");
		PhoneNumber phone = new PhoneNumber("2041960158");
		Client newClient = new Client("Will", phone, email, "1243 First Last St", "Submarines", ClientStatus.Active);
		assertTrue(processClient.insert(newClient));

		TrackedFeatureType newFeatureType = new TrackedFeatureType("myFeature");
		assertTrue(processAddFeature.insert(newFeatureType));

		ArrayList<TrackedFeatureType> featureTypes = processAddFeature.getFeatureTypes();
		assertNotNull(featureTypes);

		TrackedFeatureType myFeatureType = null;
		for (int i = 0; i < featureTypes.size() && myFeatureType == null; i++)
		{
			if (featureTypes.get(i).getTitle().equals("myFeature"))
				myFeatureType = featureTypes.get(i);
		}
		
		Client theClient = processClient.getClientByBusinessName("Submarines");
		assertNotNull(theClient);

		TrackedFeature theFeature = new TrackedFeature("notes", theClient.getID(), myFeatureType, new Date(1402194845), 1.5);
		assertNotNull(theFeature);

		assertTrue(processAddFeature.insert(theFeature));
		TrackedFeature aFeature = processFeatureHistory.getNextHistory();
		assertNotNull(aFeature);

		processClient.delete(theClient);
		processAddFeature.delete(myFeatureType);
		//processAddFeature.delete(theFeature);
	}

	@Test
	public void getYearHistoryForFeatureTest()
	{
		ArrayList<MonthReport> reports;

		Email email = new Email("testhail@testing.com");
		PhoneNumber phone = new PhoneNumber("2044601538");
		Client newClient = new Client("Sammy", phone, email, "1223 FirstLast St", "BusinessMotel", ClientStatus.Active);
		assertTrue(processClient.insert(newClient));

		TrackedFeatureType newFeatureType = new TrackedFeatureType("myFeature");
		assertTrue(processAddFeature.insert(newFeatureType));

		ArrayList<TrackedFeatureType> featureTypes = processAddFeature.getFeatureTypes();
		assertNotNull(featureTypes);
		
		Client theClient = processClient.getClientByBusinessName("BusinessMotel");

		TrackedFeatureType myFeatureType = null;
		for (int i = 0; i < featureTypes.size() && myFeatureType == null; i++)
		{
			if (featureTypes.get(i).getTitle().equals("myFeature"))
				myFeatureType = featureTypes.get(i);
		}

		reports = processFeatureHistory.getYearHistoryForFeature(myFeatureType, newClient);

		processClient.delete(theClient);
		processAddFeature.delete(myFeatureType);
	}
}
