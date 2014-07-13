package tests.objects;

import static org.junit.Assert.*;
import objects.PhoneNumber;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class PhoneNumberTest
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
	
	/** 	Testing Creation	**/
	
	@Test
	public void testPhoneNumber1()
	{
		PhoneNumber number = new PhoneNumber("1234567890");
		
		assertNotNull("Phone number is null", number);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPhoneNumber2()
	{
		new PhoneNumber(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPhoneNumber3()
	{
		new PhoneNumber("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPhoneNumber4()
	{
		new PhoneNumber("123");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPhoneNumber5()
	{
		new PhoneNumber("123456");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPhoneNumber6()
	{
		new PhoneNumber("1234567");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPhoneNumber7()
	{
		new PhoneNumber("123-4567");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPhoneNumber8()
	{
		new PhoneNumber("890-123-4567");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPhoneNumber9()
	{
		new PhoneNumber("(123) 456-7890");
	}
	
	@Test
	public void testPhoneNumber10()
	{
		PhoneNumber number = new PhoneNumber("1234567890");
		assertTrue("Invalid area code", number.getAreaCode().equals("123"));
		assertTrue("Invalid prefix", number.getPrefix().equals("456"));
		assertTrue("Invalid line number", number.getLineNumber().equals("7890"));
	}
	
	@Test
	public void testPhoneNumber11()
	{
		PhoneNumber number = new PhoneNumber("1234567890");
		assertTrue("Invalid format", number.formattedPhoneNumber().equals("(123) 456-7890"));
	}
}
