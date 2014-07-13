package tests.integration.business;

import static org.junit.Assert.*;

import org.junit.Test;

import persistence.DBInterface;
import business.ProcessStorable;

// TO DO:  Derek
public class ProcessStorableTest {

	private DBInterface database;
	private ProcessStorable processStorable; 

	public ProcessStorableTest()
	{
		processStorable = new ProcessStorable();
		database = new DBInterface("db");
	}

	@Test
	public void processStorableTest() {
		fail("Not yet implemented");
	}

	@Test
	public void insertTest() {
		fail("Not yet implemented");
	}

	@Test
	public void updateTest() {
		fail("Not yet implemented");
	}

	@Test
	public void deleteTest() {
		fail("Not yet implemented");
	}

}
