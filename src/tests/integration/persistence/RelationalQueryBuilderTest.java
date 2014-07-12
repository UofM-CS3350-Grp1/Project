package tests.integration.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import persistence.RelationalQueryBuilder;

public class RelationalQueryBuilderTest {

	@Test
	public void testValid() 
	{
		RelationalQueryBuilder rqb = new RelationalQueryBuilder();
		rqb.connect();
		assertNotNull("Get Service by Contract", rqb.getServiceByContract(rqb.getContractByID(1)));
		assertNotNull("Get Service by Client", rqb.getServiceByClient(rqb.getClientByID(1)));
		assertNotNull("Get Client By Feature", rqb.getClientByFeature(rqb.getTrackedFeatureByID(2)));
		assertNotNull("Tracked features by service", rqb.getTrackedFeaturesByClient(rqb.getClientByID(2)));
		assertNotNull("Expense by service", rqb.getExpensesByService(rqb.getServiceByID(3)));
		assertNotNull("ServiceType by ID", rqb.getServiceTypesByType("Web Design"));
		assertNotNull("FeatureType", rqb.getTrackedFeatureTypesByTitle("Page Hits"));
		rqb.disconnect();
	}
	
	@Test
	public void testInvalid()
	{
		RelationalQueryBuilder rqb = new RelationalQueryBuilder();
		rqb.connect();
		assertNull("Get Service by Contract", rqb.getServiceByContract(rqb.getContractByID(Integer.MAX_VALUE)));
		assertNull("Get Service by Client", rqb.getServiceByClient(rqb.getClientByID(Integer.MAX_VALUE)));
		assertNull("Get Client By Feature", rqb.getClientByFeature(rqb.getTrackedFeatureByID(Integer.MAX_VALUE)));
		assertNull("Tracked features by service", rqb.getTrackedFeaturesByClient(rqb.getClientByID(Integer.MAX_VALUE)));
		assertNull("Expense by service", rqb.getExpensesByService(rqb.getServiceByID(Integer.MAX_VALUE)));
		assertNull("ServiceType by ID", rqb.getServiceTypesByType("dasdada"));
		assertNull("FeatureType", rqb.getTrackedFeatureTypesByTitle("fdbdfgfd"));
		rqb.disconnect();
	}

}
