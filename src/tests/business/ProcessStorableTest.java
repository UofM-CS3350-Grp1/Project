package tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import business.ProcessStorable;
import static org.junit.Assert.*;

public class ProcessStorableTest 
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
	
	@Test
	public void testInsert()
	{
		ProcessStorable process = new ProcessStorable();
		assertFalse("Storable was inserted", process.insert(null));
	}
	
	@Test
	public void testDelete()
	{
		ProcessStorable process = new ProcessStorable();
		assertFalse("Storable was deleted", process.delete(null));
	}
	
	@Test
	public void testUpdate()
	{
		ProcessStorable process = new ProcessStorable();
		assertFalse("Storable was updated", process.update(null));
	}
}
