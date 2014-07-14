package tests.integration.business;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import business.ProcessUser;
import objects.User;

public class ProcessUserTest 
{
	private ProcessUser processUser= new ProcessUser(); 
	
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
	public void validateUserTest()
	{
		int id = 0;
		String name = "George";
		String password = "foreman";
		User newUser = new User(id, name, password);
		
		assertTrue(processUser.insert(newUser));
		assertTrue(processUser.validateUser(name, password));
		
		processUser.delete(newUser);
	}
}
