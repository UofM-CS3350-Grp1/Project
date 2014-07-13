package tests.integration.business;

import static org.junit.Assert.*;

import org.junit.Test;

import persistence.DBInterface;
import business.ProcessUser;

// TO DO:  Derek
public class ProcessUserTest {

	private DBInterface database;
	private ProcessUser processUser; 

	public ProcessUserTest()
	{
		processUser = new ProcessUser();
		database = new DBInterface("db");
	}

	@Test
	public void processUserTest() {
		fail("Not yet implemented");
	}

	@Test
	public void getUserTest() {
		fail("Not yet implemented");
	}

	@Test
	public void validateUserTest() {
		fail("Not yet implemented");
	}

}
