package tests.objects;

import static org.junit.Assert.*;
import objects.TrackedFeatureType;
import org.junit.Test;

public class TrackedFeatureTypeTest
{
	/**	Testing Creation	**/
	@Test(expected=IllegalArgumentException.class)
	public void testFeatureType()
	{
		new TrackedFeatureType(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFeatureType2()
	{
		new TrackedFeatureType("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFeatureType3()
	{
		new TrackedFeatureType(-1, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFeatureType4()
	{
		new TrackedFeatureType(0, null);
	}
	
	/** 	Testing Mutation	**/
	@Test
	public void testFeatureType5()
	{
		TrackedFeatureType featureType = new TrackedFeatureType("Title");
		featureType.setTitle(null);
		assertTrue("Title is invalid", featureType.getTitle().equals("Title"));
	}
	
	@Test
	public void testFeatureType6()
	{
		TrackedFeatureType featureType = new TrackedFeatureType("Title");
		featureType.setTitle("");
		assertTrue("Title is invalid", featureType.getTitle().equals("Title"));
	}
	
	@Test
	public void testFeatureType7()
	{
		TrackedFeatureType featureType = new TrackedFeatureType("Title");
		featureType.setTitle("avada");
		assertTrue("Title is invalid", featureType.getTitle().equals("avada"));
	}
}
