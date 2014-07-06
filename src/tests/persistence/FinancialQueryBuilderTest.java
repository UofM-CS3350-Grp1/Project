package tests.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import persistence.FinancialQueryBuilder;

public class FinancialQueryBuilderTest 
{

	@Test
	public void testValid() 
	{
		FinancialQueryBuilder finBuild = new FinancialQueryBuilder();
		finBuild.connect();
		assertNotNull("LastYearReturns Check",finBuild.getLastYearReturns(finBuild.getClientByID(1)));
		assertTrue(finBuild.getClientCurrentExpenses(finBuild.getClientByID(1)) <= 0);
		assertTrue(finBuild.getClientCurrentRevenue(finBuild.getClientByID(1)) >= 0);
		finBuild.disconnect();
	}
	
	@Test
	public void testInvalid()
	{
		FinancialQueryBuilder finBuild = new FinancialQueryBuilder();
		finBuild.connect();
		assertNull("LastYearReturns Check",finBuild.getLastYearReturns(finBuild.getClientByID(Integer.MAX_VALUE)));
		assertTrue(finBuild.getClientCurrentExpenses(finBuild.getClientByID(Integer.MAX_VALUE)) == -1);
		assertTrue(finBuild.getClientCurrentRevenue(finBuild.getClientByID(Integer.MAX_VALUE)) == -1);
		finBuild.disconnect();
	}
}