package tests.business;

import static org.junit.Assert.*;
import objects.Service;
import objects.TrackedFeature;

import org.junit.Test;

import business.CalculateFeatureValue;

public class CalcFeatureValueTest
{
	/**		Testing Basic Logic 	**/
	
	@Test
	public void testCalcFeature1()
	{
		double result = CalculateFeatureValue.calculateTotalValue(null, null);
		
		assertTrue("Result is invalid", result == 0);
	}
	
	@Test
	public void testCalcFeature2()
	{
		Service trackable = new Service("Title", "Desc", 2.12, "Type");
		double result = CalculateFeatureValue.calculateTotalValue(trackable, null);
		
		assertTrue("Result is invalid", result == 0);
	}
	
	@Test
	public void testCalcFeature3()
	{
		TrackedFeature feature = new TrackedFeature("Feature");
		double result = CalculateFeatureValue.calculateTotalValue(null, feature);
		
		assertTrue("Result is invalid", result == 0);
	}
}
