package tests.integration.business;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.widgets.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import business.DateTimeUtil;

public class TestDateTimeUtil {

	DateTimeUtil date;
	Date dateTime;
	
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
	public void testGetDateLong() {
		date = new DateTimeUtil();
		dateTime = date.getDate(2014, 07, 27, 10, 30, 30);
		Calendar newCal = Calendar.getInstance();
		newCal.set(2014, 07, 27, 10, 30, 30);
		Date newDate = newCal.getTime();
		assertEquals(newDate, dateTime);
	}

}
