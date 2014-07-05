package tests.business;

import static org.junit.Assert.*;
import objects.Service;
import objects.ServiceType;
import objects.TrackedFeature;
import objects.TrackedFeatureType;

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
		ServiceType serviceType = new ServiceType("Type of service","Description");
		Service trackable = new Service("Title", "Desc", 2.12, serviceType);
		double result = CalculateFeatureValue.calculateTotalValue(trackable, null);
		
		assertTrue("Result is invalid", result == 0);
	}
	
	@Test
	public void testCalcFeature3()
	{
		TrackedFeatureType featureType = new TrackedFeatureType("Feature type", "Feature title");
		TrackedFeature feature = new TrackedFeature("Feature", featureType);
		double result = CalculateFeatureValue.calculateTotalValue(null, feature);
		
		assertTrue("Result is invalid", result == 0);
	}
}
