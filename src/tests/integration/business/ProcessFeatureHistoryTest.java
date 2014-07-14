package tests.integration.business;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import business.ProcessFeatureHistory;
import objects.Client;
import objects.Email;
import objects.MonthReport;
import objects.PhoneNumber;
import objects.TrackedFeature;
import objects.TrackedFeatureType;
import objects.Client.ClientStatus;
import persistence.DBInterface;

public class ProcessFeatureHistoryTest
{
	private ProcessFeatureHistory processFeatureHistory; 

	public ProcessFeatureHistoryTest()
	{
		processFeatureHistory = new ProcessFeatureHistory();
	}

	@Test
	public void getFeatureTotalTest()
	{
		Email email = new Email("test@test.com");
		PhoneNumber phone = new PhoneNumber("2049601538");
		Client newClient = new Client("Jason", phone, email, "123 First st", "BusinessNew1", ClientStatus.Active);

		TrackedFeatureType newFeature = new TrackedFeatureType("Features");

		processFeatureHistory = new ProcessFeatureHistory();
		double value = processFeatureHistory.getFeatureTotal(newClient, newFeature);

		assertNotNull(value);
	}

	@Test
	public void getHistoryByIDTest()
	{
		TrackedFeature feature;
		feature = processFeatureHistory.getHistoryByID(-1);
		assertEquals(null,feature);
	}

	@Test
	public void getNextHistoryTest() 
	{
		TrackedFeature feature;
		feature = processFeatureHistory.getNextHistory();
		assertNotNull(feature);
	}

	@Test
	public void getYearHistoryForFeatureTest1()
	{
		ArrayList<MonthReport> reports;
		reports = processFeatureHistory.getYearHistoryForFeature(null, null);
		assertNotNull(reports);
	}

	public void getYearHistoryForFeatureTest2()
	{
		ArrayList<MonthReport> reports;

		Email email = new Email("test@test.com");
		PhoneNumber phone = new PhoneNumber("2049601538");
		Client newClient = new Client("Jason", phone, email, "123 First st", "BusinessNew1", ClientStatus.Active);

		reports = processFeatureHistory.getYearHistoryForFeature(null, newClient);
		assertNotNull(reports);
	}

	public void getYearHistoryForFeatureTest3()
	{
		ArrayList<MonthReport> reports;
		TrackedFeatureType featureType = new TrackedFeatureType("Features");
		reports = processFeatureHistory.getYearHistoryForFeature(featureType, null);
		assertNotNull(reports);
	}
}
