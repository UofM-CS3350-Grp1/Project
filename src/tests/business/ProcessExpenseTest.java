package tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import business.ProcessExpenses;
import static org.junit.Assert.*;

public class ProcessExpenseTest
{
	@Rule
	public TestName testName = new TestName();
	
	@Before
	public void before()
	{
		System.out.println("Running test: " + testName.getMethodName());
	}
	
	@After
	public void after()
	{
		System.out.println("Finished test.\n");
	}
	
	@Test
	public void testInsert()
	{
		ProcessExpenses process = new ProcessExpenses();
		assertFalse("Expense was inserted", process.insertExpense(null));
	}
	
	@Test
	public void testGetByClient()
	{
		ProcessExpenses process = new ProcessExpenses();
		assertTrue("Client is invalid", process.getExpensesByClient(null) == 0);
	}
	
	@Test
	public void testGetByService()
	{
		ProcessExpenses process = new ProcessExpenses();
		assertTrue("Service is invalid", process.getExpensesByService(null) == 0);
	}
	
	@Test
	public void testGetByServiceType()
	{
		ProcessExpenses process = new ProcessExpenses();
		assertTrue("Service type is invalid", process.getExpensesByServiceType(null) == 0);
	}
	
	@Test
	public void testGetByContract()
	{
		ProcessExpenses process = new ProcessExpenses();
		assertTrue("Contract is invalid", process.getExpensesByContract(null) == 0);
	}
}
