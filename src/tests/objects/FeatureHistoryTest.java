package tests.objects;

import java.util.Date;

import objects.FeatureHistory;
import objects.Service;
import objects.ServiceType;
import objects.TrackedFeature;
import objects.TrackedFeatureType;

import org.junit.Test;

import static org.junit.Assert.*;

public class FeatureHistoryTest 
{
	private static TrackedFeatureType featureType = new TrackedFeatureType("Feature type", "Feature title");
	private static TrackedFeature validFeature = new TrackedFeature("Feature", featureType);
	private static ServiceType serviceType = new ServiceType("Service type", "Description");
	private static Service validService = new Service("Service X", "X != Y", 16.25, serviceType);
	private static Date validDate = new Date(1402194845);
	
	/** 	Testing Creation	**/
	
	@Test(expected=IllegalArgumentException.class)
	public void testHistory1()
	{
		new FeatureHistory(null, null, 0, null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testHistory2()
	{
		new FeatureHistory(validFeature, null, 0, null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testHistory3()
	{
		new FeatureHistory(validFeature, validService, 0, null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testHistory4()
	{
		new FeatureHistory(validFeature, null, 0, validDate, "SDA");
	}
	
	public void testHistory5()
	{
		FeatureHistory history = new FeatureHistory(validFeature, validService, 0, validDate, "");
			
		assertNotNull("History is null", history);
	}
	
	/**		Testing Mutators	**/
	
	public void testHistory6()
	{
		FeatureHistory history = new FeatureHistory(validFeature, validService, 0, validDate, "Note");
		history.setDate(new Date(4455512));
		assertTrue("Date is invalid", history.getDate().getTime() == 4455512);
	}
	
	public void testHistory7()
	{
		FeatureHistory history = new FeatureHistory(validFeature, validService, 0, validDate, "Note");
		history.setDate(null);
		assertTrue("Date is invalid", history.getDate().getTime() == 1402194845);
	}
	
	public void testHistory8()
	{
		FeatureHistory history = new FeatureHistory(validFeature, validService, 0, validDate, "Note");
		history.setValue(21312231.32);
		assertTrue("Value is invalid", history.getValue() == 21312231.32);
	}
	
	public void testHistory9()
	{
		FeatureHistory history = new FeatureHistory(validFeature, validService, 0, validDate, "Note");
		history.setValue(-2.32);
		assertTrue("Value is invalid", history.getValue() == -2.32);
	}
	
	public void testHistory10()
	{
		FeatureHistory history = new FeatureHistory(validFeature, validService, 0, validDate, "Note");
		history.setNotes(null);
		assertTrue("Notes are invalid", history.getNotes().equals("Note"));
	}
	
	public void testHistory11()
	{
		FeatureHistory history = new FeatureHistory(validFeature, validService, 0, validDate, "Note");
		history.setNotes("");
		assertTrue("Notes are invalid", history.getNotes().equals(""));
	}
	
	public void testHistory12()
	{
		FeatureHistory history = new FeatureHistory(validFeature, validService, 0, validDate, "Note");
		history.setNotes("Hello");
		assertTrue("Notes are invalid", history.getNotes().equals("Hello"));
	}
	
	public void testHistory13()
	{
		FeatureHistory history = new FeatureHistory(validFeature, validService, 0, validDate, "Note");
		history.setFeature(null);
		assertNotNull("Feature is null", history.getFeature());
	}
	
	public void testHistory14()
	{
		FeatureHistory history = new FeatureHistory(validFeature, validService, 0, validDate, "Note");
		history.setTrackedService(null);
		assertNotNull("Service is null", history.getTrackedService());
	}
}
