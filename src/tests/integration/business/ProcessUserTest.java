package tests.integration.business;

import static org.junit.Assert.*;

import org.junit.Test;

import business.ProcessUser;
import objects.User;

public class ProcessUserTest {

	private ProcessUser processUser; 

	public ProcessUserTest()
	{
		processUser = new ProcessUser();
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
