package tests.objects;

import static org.junit.Assert.*;
import objects.Email;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class EmailTest
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
	
	/** 	Testing Creation	**/
	
	@Test
	public void testEmail1()
	{
		Email email = new Email("test@local.ca");
		
		assertNotNull("Email is null", email);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmail2()
	{
		new Email(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmail3()
	{
		new Email("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmail4()
	{
		new Email("hello");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmail5()
	{
		new Email("@");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmail6()
	{
		new Email("@test");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmail7()
	{
		new Email("@test.com");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmail8()
	{
		new Email(".com");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmail9()
	{
		new Email("test.ca");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmail10()
	{
		new Email("t@.com");
	}
	
	@Test
	public void testEmail11()
	{
		Email email = new Email("someone@someplace.ca");
		
		assertNotNull("Email is null", email);
		assertTrue("Email does not match", email.getEmail().equals("someone@someplace.ca"));
	}
	
	@Test
	public void testEmail12()
	{
		Email email = new Email("abc123@somedomain.ca");
		
		assertNotNull("Email is null", email);
		assertTrue("Email does not match", email.getEmail().equals("abc123@somedomain.ca"));
	}
}
