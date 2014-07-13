package tests.objects;

import java.util.Date;

import objects.Expense;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.junit.Assert.*;

public class ExpenseTest 
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
	
	private Date date = new Date(12345);
	
	/**	Testing Creation	**/
	
	@Test(expected=IllegalArgumentException.class)
	public void testExpense()
	{
		new Expense(0, null, 0, null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testExpense2()
	{
		new Expense(1, null, 0, null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testExpense3()
	{
		new Expense(1, "", 0, null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testExpense4()
	{
		new Expense(1, "supplier", 0, null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testExpense5()
	{
		new Expense(1, "supplier", 0, "", null);
	}
	
	/**	Testing Mutation 	**/
	
	@Test
	public void testExpense6()
	{
		Expense expense = new Expense(1, "supplier", 0, "Notes", date);
		expense.setSupplier(null);
		assertTrue("Supplier is invalid", expense.getSupplier().equals("supplier"));
	}
	
	@Test
	public void testExpense7()
	{
		Expense expense = new Expense(1, "supplier", 0, "Notes", date);
		expense.setSupplier("");
		assertTrue("Supplier is invalid", expense.getSupplier().equals("supplier"));
	}
	
	@Test
	public void testExpense8()
	{
		Expense expense = new Expense(1, "supplier", 0, "Notes", date);
		expense.setSupplier("supp");
		assertTrue("Supplier is invalid", expense.getSupplier().equals("supp"));
	}
	
	@Test
	public void testExpense9()
	{
		Expense expense = new Expense(1, "supplier", 0, "Notes", date);
		expense.setValue(2.34f);
		assertTrue("Value is invalid", expense.getValue() == 2.34f);
	}
	
	@Test
	public void testExpense10()
	{
		Expense expense = new Expense(1, "supplier", 0, "Notes", date);
		expense.setDetails(null);
		assertTrue("Details are invalid", expense.getDetails().equals("Notes"));
	}
	
	@Test
	public void testExpense11()
	{
		Expense expense = new Expense(1, "supplier", 0, "Notes", date);
		expense.setDetails("");
		assertTrue("Details are invalid", expense.getDetails().equals(""));
	}
	@Test
	public void testExpense12()
	{
		Expense expense = new Expense(1, "supplier", 0, "Notes", date);
		expense.setDetails("detail");
		assertTrue("Details are invalid", expense.getDetails().equals("detail"));
	}
	
	@Test
	public void testExpense13()
	{
		Expense expense = new Expense(1, "supplier", 0, "Notes", date);
		expense.setDate(null);
		assertTrue("Date is invalid", expense.getDateIncurred().getTime() == 12345);
	}
	
	@Test
	public void testExpense14()
	{
		Expense expense = new Expense(1, "supplier", 0, "Notes", date);
		expense.setDate(new Date(54321));
		assertTrue("Date is invalid", expense.getDateIncurred().getTime() == 54321);
	}
	
	@Test
	public void testExpense15()
	{
		Expense expense = new Expense(1, "supplier", 0, "Notes", date);
		expense.setServiceID(0);
		assertTrue("Service ID is invalid", expense.getServiceID() == 1);
	}
	
	@Test
	public void testExpense16()
	{
		Expense expense = new Expense(1, "supplier", 0, "Notes", date);
		expense.setServiceID(10);
		assertTrue("Service ID is invalid", expense.getServiceID() == 10);
	}
}
