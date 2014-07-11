package tests.integration.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import persistence.IDQueryBuilder;

public class TestIDQueryBuilder {

	@Test
	public void testValid() 
	{
		IDQueryBuilder iqBuilder = new IDQueryBuilder();
		iqBuilder.connect();
		
		assertNotNull("Query builder client", iqBuilder.getClientByID(1));
		assertNotNull("Query builder service", iqBuilder.getServiceByID(1));
		assertNotNull("Query builder contract", iqBuilder.getContractByID(1));
		assertNotNull("Query builder expense", iqBuilder.getExpenseByID(1));
		assertNotNull("Query builder expense", iqBuilder.getTrackedFeatureByID(1));
		assertNotNull("Query builder expense", iqBuilder.getServiceTypeByID(1));
		assertNotNull("Query builder expense", iqBuilder.getTrackedFeatureTypeByID(1));
		
		iqBuilder.disconnect();
	}
	
	@Test
	public void testInvalid() 
	{
		IDQueryBuilder iqBuilder = new IDQueryBuilder();
		iqBuilder.connect();
		
		assertNull("Query builder client", iqBuilder.getClientByID(Integer.MAX_VALUE));
		assertNull("Query builder service", iqBuilder.getServiceByID(Integer.MAX_VALUE));
		assertNull("Query builder contract", iqBuilder.getContractByID(Integer.MAX_VALUE));
		assertNull("Query builder expense", iqBuilder.getExpenseByID(Integer.MAX_VALUE));
		assertNull("Query builder expense", iqBuilder.getTrackedFeatureByID(Integer.MAX_VALUE));
		assertNull("Query builder expense", iqBuilder.getServiceTypeByID(Integer.MAX_VALUE));
		assertNull("Query builder expense", iqBuilder.getTrackedFeatureTypeByID(Integer.MAX_VALUE));
		
		iqBuilder.disconnect();
	}

}
