package tests.objects;

import static org.junit.Assert.*;
import objects.Service;
import org.junit.Test;

/**
 * Runs tests on the service class
 */
public class ServiceTest
{		
	@Test
	public void testService1()
	{
		System.out.println("\nStarting testService1:");
		
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, "Type A");
		
		assertNotNull("Service is null", service);
		assertTrue("Title is invalid", service.getTitle().equals("Marketing"));
		assertTrue("Description name is invalid", service.getDescription().equals("Marketing Stuff"));
		assertTrue("Type of service is invalid", service.getType().equals("Type A"));
		assertTrue("Rate is invalid", service.getRate() == 10.0);
		
		System.out.println("\nFinished testService1");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testService2()
	{
		new Service(null, "Marketing Stuff", 10.0, "Type A");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testService3()
	{
		new Service("", "Marketing Stuff", 10.0, "Type A");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testService4()
	{
		new Service("Marketing", null, 10.0, "Type A");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testService5()
	{
		new Service("Marketing", "Marketing Stuff", 10.0, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testService6()
	{
		new Service("Marketing", "Marketing Stuff", 10.0, "");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testService7()
	{
		new Service("Marketing", "Marketing Stuff", -1, "Type");
	}
	
	@Test
	public void testService8()
	{
		System.out.println("\nStarting testService8:");
		
		Service service = new Service("Marketing", "Marketing Stuff", 0.0, "Type A");
		
		assertNotNull("Service is null", service);
		assertTrue("Title is invalid", service.getTitle().equals("Marketing"));
		assertTrue("Description name is invalid", service.getDescription().equals("Marketing Stuff"));
		assertTrue("Type of service is invalid", service.getType().equals("Type A"));
		assertTrue("Rate is invalid", service.getRate() == 0.0);
		
		System.out.println("\nFinished testService8");
	}
	
	@Test
	public void testService9()
	{
		System.out.println("\nStarting testService9:");
		
		Service service = new Service("Marketing", "Marketing Stuff", 123465, "Type A");
		
		assertNotNull("Service is null", service);
		assertTrue("Title is invalid", service.getTitle().equals("Marketing"));
		assertTrue("Description name is invalid", service.getDescription().equals("Marketing Stuff"));
		assertTrue("Type of service is invalid", service.getType().equals("Type A"));
		assertTrue("Rate is invalid", service.getRate() == 123465);
		
		System.out.println("\nFinished testService9");
	}
}