package tests.objects;

import java.util.Date;

import objects.MonthReport;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class MonthReportTest
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
	
	private Date date = new Date(12345);
	
	/**			Testing Creation		 **/
	@Test(expected=IllegalArgumentException.class)
	public void testReport()
	{
		new MonthReport(null, 0);
	}
	
	/**			Testing Access		**/
	public void testReport2()
	{
		MonthReport report = new MonthReport(date, 0);		
		assertTrue("Date is invalid", report.getPeriod().getTime() == 12345);
	}
		
	public void testReport3()
	{
		MonthReport report = new MonthReport(date, 1232);		
		assertTrue("Value is invalud", report.getValue() == 1232);
	}
	
	public void testReport4()
	{
		MonthReport report = new MonthReport(date, 1.254);		
		assertTrue("Value is invalud", report.getValue() == 1.254);
	}
	
	public void testReport5()
	{
		MonthReport report = new MonthReport(date, -12.45);		
		assertTrue("Value is invalud", report.getValue() == -12.45);
	}
}
