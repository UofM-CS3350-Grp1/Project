package tests.business;

import static org.junit.Assert.*;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import business.DateTimeUtil;

@SuppressWarnings("deprecation")
public class DateTimeUtilTest
{
	private Composite composite = new Composite(new Shell(), SWT.BORDER);
	
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
	public void testDate()
	{
		DateTime dt = new DateTime(composite, SWT.BORDER); 
		dt.setMonth(2);
		dt.setDay(14);
		dt.setYear(1987);
		Date date = DateTimeUtil.getDate(dt);
		
		assertTrue("Month is invalid", date.getMonth() == 2);
		assertTrue("Day is invalid", date.getDate() == 14);
		assertTrue("Year is invalid", date.getYear() + 1900 == 1987);
	}
	
	@Test
	public void testDate2()
	{
		DateTime dt = new DateTime(composite, SWT.BORDER); 
		dt.setMonth(11);
		dt.setDay(31);
		dt.setYear(2009);
		Date date = DateTimeUtil.getDate(dt);
		
		assertTrue("Month is invalid", date.getMonth() == 11);
		assertTrue("Day is invalid", date.getDate() == 31);
		assertTrue("Year is invalid", date.getYear() + 1900 == 2009);
	}
	
	@Test
	public void testDate3()
	{
		DateTime dt = new DateTime(composite, SWT.BORDER); 
		dt.setMonth(0);
		dt.setDay(1);
		dt.setYear(2009);
		Date date = DateTimeUtil.getDate(dt);
		
		assertTrue("Month is invalid", date.getMonth() == 0);
		assertTrue("Day is invalid", date.getDate() == 1);
		assertTrue("Year is invalid", date.getYear() + 1900 == 2009);
	}
	
	@Test
	public void testDate4()
	{
		Date date = DateTimeUtil.getDate(null);
		assertNull("date is null", date);
	}
	
	@Test
	public void testDate5()
	{
		Date date = DateTimeUtil.getDate(2014, 6, 8, 12, 15, 15);
		
		assertTrue("Month is invalid", date.getMonth() == 6);
		assertTrue("Day is invalid", date.getDate() == 8);
		assertTrue("Year is invalid", date.getYear() + 1900 == 2014);
	}
	
	@Test
	public void testDate6()
	{
		Date date = DateTimeUtil.getDate(1987, 0, 31, 12, 15, 15);
		
		assertTrue("Month is invalid", date.getMonth() == 0);
		assertTrue("Day is invalid", date.getDate() == 31);
		assertTrue("Year is invalid", date.getYear() + 1900 == 1987);
	}
}
