package tests.business;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import business.AccessFinancialRecords;

public class AccessFinancialRecordsTest 
{
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
	
	@Test
	public void testClientRevenue()
	{
		AccessFinancialRecords access = new AccessFinancialRecords();
		assertNull("Client yearly revenue is invalid", access.getYearRevenueForClient(null));
	}
	
	@Test
	public void testServiceRevenue()
	{
		AccessFinancialRecords access = new AccessFinancialRecords();
		assertNull("Service yearly revenue is invalid", access.getYearRevenueForService(null));
	}
	
	@Test
	public void testServiceExpense()
	{
		AccessFinancialRecords access = new AccessFinancialRecords();
		assertNull("Service yearly expense is invalid", access.getYearExpenseForService(null));
	}
	
	@Test
	public void testCalcClientRevenue()
	{
		AccessFinancialRecords access = new AccessFinancialRecords();
		assertTrue("Client revenue is invalid", access.calcClientRevenueToDate(null) == 0.0);
	}
	
	@Test
	public void testCalcClientExpense()
	{
		AccessFinancialRecords access = new AccessFinancialRecords();
		assertTrue("Client expenses is invalid", access.calcClientExpensesToDate(null) == 0.0);
	}
	
	@Test
	public void testCalcServiceRevenue()
	{
		AccessFinancialRecords access = new AccessFinancialRecords();
		assertTrue("Service revenue is invalid", access.calcServiceRevenueToDate(null) == 0.0);
	}
	
	@Test
	public void testCalcServiceExpense()
	{
		AccessFinancialRecords access = new AccessFinancialRecords();
		assertTrue("Service expenses is invalid", access.calcServiceExpensesToDate(null) == 0.0);
	}
}
