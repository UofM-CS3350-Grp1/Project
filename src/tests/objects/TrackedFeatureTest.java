package tests.objects;

import static org.junit.Assert.*;
import objects.TrackedFeature;

import org.junit.Test;

public class TrackedFeatureTest
{
	/** 	Testing Creation	**/
		
	@Test(expected=IllegalArgumentException.class)
	public void testFeature1()
	{
		new TrackedFeature(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFeature2()
	{
		new TrackedFeature("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFeature3()
	{
		new TrackedFeature(null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFeature4()
	{
		new TrackedFeature("Some feature", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFeature5()
	{
		new TrackedFeature("Feature", "Notes", -23);
	}
	
	@Test
	public void testFeature6()
	{
		TrackedFeature feature = new TrackedFeature("Feature");		
		assertTrue("Name is invalid", feature.getFeatureName().equals("Feature"));
	}
	
	@Test
	public void testFeature7()
	{
		TrackedFeature feature = new TrackedFeature("Feature", "");		
		assertTrue("Name is invalid", feature.getFeatureName().equals("Feature"));
		assertTrue("Notes are invalid", feature.getNotes().equals(""));
	}
	
	@Test
	public void testFeature8()
	{
		TrackedFeature feature = new TrackedFeature("Feature", "Notes");		
		assertTrue("Name is invalid", feature.getFeatureName().equals("Feature"));
		assertTrue("Notes are invalid", feature.getNotes().equals("Notes"));
	}
	
	@Test
	public void testFeature9()
	{
		TrackedFeature feature = new TrackedFeature("Feature", "", 0);		
		assertTrue("Name is invalid", feature.getFeatureName().equals("Feature"));
		assertTrue("Notes are invalid", feature.getNotes().equals(""));
		assertTrue("ID is invalid", feature.getID() == 0);
	}
	
	@Test
	public void testFeature10()
	{
		TrackedFeature feature = new TrackedFeature("Feature", "", 123);		
		assertTrue("Name is invalid", feature.getFeatureName().equals("Feature"));
		assertTrue("Notes are invalid", feature.getNotes().equals(""));
		assertTrue("ID is invalid", feature.getID() == 123);
	}
	
	/** 	Test Mutators 	**/
	
	@Test
	public void testFeature11()
	{
		TrackedFeature feature = new TrackedFeature("Feature");		
		feature.setFeatureName("A new feature");
		assertTrue("Name is invalid", feature.getFeatureName().equals("A new feature"));
	}
	
	@Test
	public void testFeature12()
	{
		TrackedFeature feature = new TrackedFeature("Feature");		
		feature.setFeatureName("");
		assertTrue("Name is invalid", feature.getFeatureName().equals("Feature"));
	}
	
	@Test
	public void testFeature13()
	{
		TrackedFeature feature = new TrackedFeature("Feature");		
		feature.setFeatureName(null);
		assertTrue("Name is invalid", feature.getFeatureName().equals("Feature"));
	}
	
	@Test
	public void testFeature14()
	{
		TrackedFeature feature = new TrackedFeature("Feature");		
		feature.setNotes("Notes");
		assertTrue("Notes are invalid", feature.getNotes().equals("Notes"));
	}
	
	@Test
	public void testFeature15()
	{
		TrackedFeature feature = new TrackedFeature("Feature");		
		feature.setNotes(null);
		assertTrue("Notes are invalid", feature.getNotes().equals(""));
	}
	
	@Test
	public void testFeature16()
	{
		TrackedFeature feature = new TrackedFeature("Feature", "Notes");		
		feature.setNotes(null);
		assertTrue("Notes are invalid", feature.getNotes().equals("Notes"));
	}
	
	@Test
	public void testFeature17()
	{
		TrackedFeature feature = new TrackedFeature("Feature", "Notes");		
		feature.setNotes("");
		assertTrue("Notes are invalid", feature.getNotes().equals(""));
	}
}
