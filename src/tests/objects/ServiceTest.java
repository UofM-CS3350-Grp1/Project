package tests.objects;

import static org.junit.Assert.*;
import objects.Service;
import org.junit.Test;

/**
 * Runs tests on the service class
 */
public class ServiceTest
{		
	/** Testing Service Object Creation **/
	
	@Test
	public void testService1()
	{
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, "Type A");
		
		assertNotNull("Service is null", service);
		assertTrue("Title is invalid", service.getTitle().equals("Marketing"));
		assertTrue("Description name is invalid", service.getDescription().equals("Marketing Stuff"));
		assertTrue("Type of service is invalid", service.getType().equals("Type A"));
		assertTrue("Rate is invalid", service.getRate() == 10.0);
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
		Service service = new Service("Marketing", "Marketing Stuff", 0.0, "Type A");
		
		assertNotNull("Service is null", service);
		assertTrue("Title is invalid", service.getTitle().equals("Marketing"));
		assertTrue("Description name is invalid", service.getDescription().equals("Marketing Stuff"));
		assertTrue("Type of service is invalid", service.getType().equals("Type A"));
		assertTrue("Rate is invalid", service.getRate() == 0.0);
	}
	
	@Test
	public void testService9()
	{		
		Service service = new Service("Marketing", "Marketing Stuff", 123465, "Type A");
		
		assertNotNull("Service is null", service);
		assertTrue("Title is invalid", service.getTitle().equals("Marketing"));
		assertTrue("Description name is invalid", service.getDescription().equals("Marketing Stuff"));
		assertTrue("Type of service is invalid", service.getType().equals("Type A"));
		assertTrue("Rate is invalid", service.getRate() == 123465);
	}

	/** Testing Service Mutators **/
	
	@Test
	public void testMutator1()
	{
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, "Type A");		
		assertNotNull("Service is null", service);
		
		service.setTitle(null);
		assertTrue("Null title", service.getTitle() != null);
		assertTrue("Title changed", service.getTitle().equals("Marketing"));
	}
	
	@Test
	public void testMutator2()
	{		
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, "Type A");
		assertNotNull("Service is null", service);
		
		service.setTitle("");
		assertTrue("Null title", service.getTitle() != null);
		assertTrue("Title changed", service.getTitle().equals("Marketing"));
	}
	
	@Test
	public void testMutator3()
	{		
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, "Type A");
		assertNotNull("Service is null", service);
		
		service.setTitle("Welding");
		assertTrue("Null title", service.getTitle() != null);
		assertTrue("Title changed", service.getTitle().equals("Welding"));
	}
	
	@Test
	public void testMutator4()
	{
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, "Type A");
		assertNotNull("Service is null", service);
		
		service.setDescription(null);
		assertTrue("Null description", service.getDescription() != null);
		assertTrue("Description changed", service.getDescription().equals("Marketing Stuff"));
	}
	
	@Test
	public void testMutator5()
	{
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, "Type A");
		assertNotNull("Service is null", service);
		
		service.setDescription("");
		assertTrue("Null description", service.getDescription() != null);
		assertTrue("Description not changed", service.getDescription().equals(""));
	}
	
	@Test
	public void testMutator6()
	{
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, "Type A");
		assertNotNull("Service is null", service);
		
		service.setDescription("Welding Stuff");
		assertTrue("Null description", service.getDescription() != null);
		assertTrue("Description not changed", service.getDescription().equals("Welding Stuff"));
	}
	
	@Test
	public void testMutator7()
	{		
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, "Type A");
		assertNotNull("Service is null", service);
		
		service.setRate(-123.21);
		assertTrue("Negative rate", service.getRate() >= 0);
	}
	
	@Test
	public void testMutator8()
	{		
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, "Type A");
		assertNotNull("Service is null", service);
		
		service.setRate(-1);
		assertTrue("Negative rate", service.getRate() >= 0);
	}
	
	@Test
	public void testMutator9()
	{		
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, "Type A");
		assertNotNull("Service is null", service);
		
		service.setRate(0);
		assertTrue("Negative rate", service.getRate() >= 0);
	}
	
	@Test
	public void testMutator10()
	{
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, "Type A");
		assertNotNull("Service is null", service);
		
		service.setRate(123.21);
		assertTrue("Negative rate", service.getRate() >= 0);
	}
	
	@Test
	public void testMutator11()
	{
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, "Type A");
		assertNotNull("Service is null", service);
		
		service.setType(null);
		assertTrue("Null type", service.getType() != null);
		assertTrue("Empty type", !service.getType().equals(""));
		assertTrue("Type changed", service.getType().equals("Type A"));
	}
	
	@Test
	public void testMutator12()
	{
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, "Type A");
		assertNotNull("Service is null", service);
		
		service.setType("");
		assertTrue("Null type", service.getType() != null);
		assertTrue("Empty type", !service.getType().equals(""));
		assertTrue("Type changed", service.getType().equals("Type A"));
	}
	
	@Test
	public void testMutator13()
	{
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, "Type A");
		assertNotNull("Service is null", service);
		
		service.setType("Type B");
		assertTrue("Null type", service.getType() != null);
		assertTrue("Empty type", !service.getType().equals(""));
		assertTrue("Type changed", service.getType().equals("Type B"));
	}
}