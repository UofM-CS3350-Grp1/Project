package tests.integration.business;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import business.ProcessStorable;

public class ProcessStorableTest
{
	private ProcessStorable processStorable = new ProcessStorable();
	
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
	public void deleteTest()
	{
		assertFalse("Storable was deleted", processStorable.delete(null));
	}

	@Test
	public void insertTest()
	{
		assertFalse("Storable was inserted", processStorable.insert(null));
	}

	@Test
	public void updateTest()
	{
		assertFalse("Storable was updated", processStorable.update(null));
	}
}
