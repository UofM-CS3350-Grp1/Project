package tests.objects;

import objects.ServiceType;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.junit.Assert.*;

public class ServiceTypeTest
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
	
	/**	Test Creation	**/
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreation()
	{
		new ServiceType(null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreation2()
	{
		new ServiceType("", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreation3()
	{
		new ServiceType(null, "");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreation4()
	{
		new ServiceType("", "");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreation5()
	{
		new ServiceType("Title", "");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreation6()
	{
		new ServiceType(-1, "Title", "Desc");
	}
	
	@Test
	public void testCreation7()
	{
		ServiceType type = new ServiceType("Title", "Desc");
		assertNotNull(type);
	}
	
	@Test
	public void testCreation8()
	{
		ServiceType type = new ServiceType(0, "Title", "Desc");
		assertNotNull(type);
	}
	
	/**	Testing mutation	**/
	
	@Test
	public void testMutation()
	{
		ServiceType type = new ServiceType("Title", "Desc");
		type.setType(null);
		assertTrue(type.getType().equals("Title"));
	}
	
	@Test
	public void testMutation2()
	{
		ServiceType type = new ServiceType("Title", "Desc");
		type.setType("");
		assertTrue(type.getType().equals("Title"));
	}
	
	@Test
	public void testMutation3()
	{
		ServiceType type = new ServiceType("Title", "Desc");
		type.setType("New Title");
		assertTrue(type.getType().equals("New Title"));
	}
	
	@Test
	public void testMutation4()
	{
		ServiceType type = new ServiceType("Title", "Desc");
		type.setDescription(null);
		assertTrue(type.getDescription().equals("Desc"));
	}
	
	@Test
	public void testMutation5()
	{
		ServiceType type = new ServiceType("Title", "Desc");
		type.setDescription("");
		assertTrue(type.getDescription().equals("Desc"));
	}
	
	@Test
	public void testMutation6()
	{
		ServiceType type = new ServiceType("Title", "Desc");
		type.setDescription("New Desc");
		assertTrue(type.getDescription().equals("New Desc"));
	}
}
