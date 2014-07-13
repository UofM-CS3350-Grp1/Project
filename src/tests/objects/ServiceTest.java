package tests.objects;

import static org.junit.Assert.*;
import objects.Service;
import objects.ServiceType;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

/**
 * Runs tests on the service class
 */
public class ServiceTest
{		
	/** Testing Service Object Creation **/
	ServiceType serviceType = new ServiceType("Service type", "Service description");
	ServiceType serviceType2 = new ServiceType("Sv 2", "S Desc");
	
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
	public void testService1()
	{
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, serviceType);
		
		assertNotNull("Service is null", service);
		assertTrue("Title is invalid", service.getTitle().equals("Marketing"));
		assertTrue("Description name is invalid", service.getDescription().equals("Marketing Stuff"));
		assertTrue("Type of service is invalid", service.getServiceType().getType().equals("Service type"));
		assertTrue("Rate is invalid", service.getRate() == 10.0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testService2()
	{
		new Service(null, "Marketing Stuff", 10.0, serviceType);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testService3()
	{
		new Service("", "Marketing Stuff", 10.0, serviceType);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testService4()
	{
		new Service("Marketing", null, 10.0, serviceType);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testService5()
	{
		new Service("Marketing", "Marketing Stuff", 10.0, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testService6()
	{
		new Service("Marketing", "Marketing Stuff", 10.0, new ServiceType(null, null));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testService7()
	{
		new Service("Marketing", "Marketing Stuff", -1, serviceType);
	}
	
	@Test
	public void testService8()
	{
		Service service = new Service("Marketing", "Marketing Stuff", 0.0, serviceType);
		
		assertNotNull("Service is null", service);
		assertTrue("Title is invalid", service.getTitle().equals("Marketing"));
		assertTrue("Description name is invalid", service.getDescription().equals("Marketing Stuff"));
		assertTrue("Type of service is invalid", service.getServiceType().equals(serviceType));
		assertTrue("Rate is invalid", service.getRate() == 0.0);
	}
	
	@Test
	public void testService9()
	{		
		Service service = new Service("Marketing", "Marketing Stuff", 123465, serviceType2);
		
		assertNotNull("Service is null", service);
		assertTrue("Title is invalid", service.getTitle().equals("Marketing"));
		assertTrue("Description name is invalid", service.getDescription().equals("Marketing Stuff"));
		assertTrue("Type of service is invalid", service.getServiceType().equals(serviceType2));
		assertTrue("Rate is invalid", service.getRate() == 123465);
	}

	/** Testing Service Mutators **/
	
	@Test
	public void testMutator1()
	{
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, serviceType);		
		assertNotNull("Service is null", service);
		
		service.setTitle(null);
		assertTrue("Null title", service.getTitle() != null);
		assertTrue("Title changed", service.getTitle().equals("Marketing"));
	}
	
	@Test
	public void testMutator2()
	{		
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, serviceType);
		assertNotNull("Service is null", service);
		
		service.setTitle("");
		assertTrue("Null title", service.getTitle() != null);
		assertTrue("Title changed", service.getTitle().equals("Marketing"));
	}
	
	@Test
	public void testMutator3()
	{		
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, serviceType);
		assertNotNull("Service is null", service);
		
		service.setTitle("Welding");
		assertTrue("Null title", service.getTitle() != null);
		assertTrue("Title changed", service.getTitle().equals("Welding"));
	}
	
	@Test
	public void testMutator4()
	{
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, serviceType);
		assertNotNull("Service is null", service);
		
		service.setDescription(null);
		assertTrue("Null description", service.getDescription() != null);
		assertTrue("Description changed", service.getDescription().equals("Marketing Stuff"));
	}
	
	@Test
	public void testMutator5()
	{
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, serviceType);
		assertNotNull("Service is null", service);
		
		service.setDescription("");
		assertTrue("Null description", service.getDescription() != null);
		assertTrue("Description not changed", service.getDescription().equals(""));
	}
	
	@Test
	public void testMutator6()
	{
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, serviceType);
		assertNotNull("Service is null", service);
		
		service.setDescription("Welding Stuff");
		assertTrue("Null description", service.getDescription() != null);
		assertTrue("Description not changed", service.getDescription().equals("Welding Stuff"));
	}
	
	@Test
	public void testMutator7()
	{		
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, serviceType);
		assertNotNull("Service is null", service);
		
		service.setRate(-123.21);
		assertTrue("Negative rate", service.getRate() >= 0);
	}
	
	@Test
	public void testMutator8()
	{		
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, serviceType);
		assertNotNull("Service is null", service);
		
		service.setRate(-1);
		assertTrue("Negative rate", service.getRate() >= 0);
	}
	
	@Test
	public void testMutator9()
	{		
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, serviceType);
		assertNotNull("Service is null", service);
		
		service.setRate(0);
		assertTrue("Negative rate", service.getRate() >= 0);
	}
	
	@Test
	public void testMutator10()
	{
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, serviceType);
		assertNotNull("Service is null", service);
		
		service.setRate(123.21);
		assertTrue("Negative rate", service.getRate() >= 0);
	}
	
	@Test
	public void testMutator11()
	{
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, serviceType);
		assertNotNull("Service is null", service);
		
		service.setType(null);
		assertTrue("Null type", service.getServiceType() != null);
		assertTrue("Empty type", !service.getServiceType().equals(""));
		assertTrue("Type changed", service.getServiceType().equals(serviceType));
	}
	
	@Test
	public void testMutator12()
	{
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, serviceType);
		assertNotNull("Service is null", service);
		
		service.setType(serviceType2);
		assertTrue("Null type", service.getServiceType() != null);
		assertTrue("Empty type", !service.getServiceType().equals(""));
		assertTrue("Type changed", service.getServiceType().equals(serviceType2));
	}
	
	@Test
	public void testMutator13()
	{
		Service service = new Service("Marketing", "Marketing Stuff", 10.0, serviceType);
		assertNotNull("Service is null", service);
		
		service.setType(serviceType);
		assertTrue("Null type", service.getServiceType() != null);
		assertTrue("Empty type", !service.getServiceType().equals(""));
		assertTrue("Type changed", service.getServiceType().equals(serviceType));
	}
}