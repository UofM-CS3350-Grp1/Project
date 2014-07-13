package tests.objects;

import static org.junit.Assert.*;

import java.util.Date;

import objects.TrackedFeature;
import objects.TrackedFeatureType;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class TrackedFeatureTest
{
	TrackedFeatureType featureType = new TrackedFeatureType("Feature type");
	
	@Rule
	public TestName testName = new TestName();
	
	@Before
	public void before()
	{
		System.out.println("Running test: " + this.getClass().toString() + "::" + testName.getMethodName());
	}
	
	@After
	public void after()
	{
		System.out.println("Finished test.\n");
	}
	
	/** 	Testing Creation	**/
	
	@Test(expected=IllegalArgumentException.class)
	public void testFeature1()
	{
		new TrackedFeature(null, null, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFeature2()
	{
		new TrackedFeature("", 0, null, null, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFeature3()
	{
		new TrackedFeature(null, null, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFeature4()
	{
		new TrackedFeature("Some feature", 0, null, null, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFeature5()
	{
		new TrackedFeature("Notes", -23, 2, featureType, 0, null);
	}
	
	@Test
	public void testFeature6()
	{
		TrackedFeature feature = new TrackedFeature("Notes", 1, featureType, new Date(12345), 10);		
		assertTrue("Name is invalid", feature.getNotes().equals("Notes"));
	}
	
	@Test
	public void testFeature7()
	{
		TrackedFeature feature = new TrackedFeature("Notes", 1, featureType, new Date(12345), 10);
		assertTrue("Notes are invalid", feature.getNotes().equals("Notes"));
		assertTrue("Name is invalid", feature.getTrackedFeatureType().getTitle().equals("Feature type"));
	}
	
	@Test
	public void testFeature8()
	{
		TrackedFeature feature = new TrackedFeature("", 1, featureType, new Date(12345), 10);
		assertTrue("Name is invalid", feature.getTrackedFeatureType().getTitle().equals("Feature type"));
		assertTrue("Notes are invalid", feature.getNotes().equals(""));
	}
	
	@Test
	public void testFeature9()
	{
		TrackedFeature feature = new TrackedFeature("", 1, 1, featureType, 10, new Date(12345));		
		assertTrue("Name is invalid", feature.getTrackedFeatureType().getTitle().equals("Feature type"));
		assertTrue("Notes are invalid", feature.getNotes().equals(""));
		assertTrue("ID is invalid", feature.getID() == 1);
	}
	
	@Test
	public void testFeature10()
	{
		TrackedFeature feature = new TrackedFeature("", 123, 1, featureType, 10, new Date(12345));		
		assertTrue("Name is invalid", feature.getTrackedFeatureType().getTitle().equals("Feature type"));
		assertTrue("Notes are invalid", feature.getNotes().equals(""));
		assertTrue("ID is invalid", feature.getID() == 123);
	}
	
	/** 	Test Mutators 	**/
	
	@Test
	public void testFeature11()
	{
		TrackedFeature feature = new TrackedFeature("Notes", 1, featureType, new Date(12345), 10);		
		TrackedFeatureType type = new TrackedFeatureType("Alpha");
		assertTrue("Name is invalid", feature.getTrackedFeatureType().getTitle().equals("Feature type"));	
		feature.setTrackedFeatureType(type);	
		assertTrue("Name is invalid", feature.getTrackedFeatureType().getTitle().equals("Alpha"));
	}
	
	@Test
	public void testFeature12()
	{
		TrackedFeature feature = new TrackedFeature("Notes", 1, featureType, new Date(12345), 10);		
		TrackedFeatureType type = new TrackedFeatureType("B");
		assertTrue("Name is invalid", feature.getTrackedFeatureType().getTitle().equals("Feature type"));	
		feature.setTrackedFeatureType(type);	
		assertTrue("Name is invalid", feature.getTrackedFeatureType().getTitle().equals("B"));
	}
	
	@Test
	public void testFeature13()
	{
		TrackedFeature feature = new TrackedFeature("Notes", 1, featureType, new Date(12345), 10);	
		assertTrue("Name is invalid", feature.getTrackedFeatureType().getTitle().equals("Feature type"));	
		feature.setTrackedFeatureType(null);	
		assertTrue("Name is invalid", feature.getTrackedFeatureType().getTitle().equals("Feature type"));
	}
}
