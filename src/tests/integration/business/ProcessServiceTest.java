package tests.integration.business;

import static org.junit.Assert.*;

import org.junit.Test;

import business.ProcessFeatureHistory;
import business.ProcessService;
import persistence.DBInterface;

// TO DO:  Derek
public class ProcessServiceTest {

	private DBInterface database;
	private ProcessService processService; 

	public ProcessServiceTest()
	{
		processService = new ProcessService();
		database = new DBInterface("db");
	}

	@Test
	public void processServiceTest() {
		fail("Not yet implemented");
	}

	@Test
	public void getServiceTypeByIDTest() {
		fail("Not yet implemented");
	}

	@Test
	public void getServiceTypesTest() {
		fail("Not yet implemented");
	}

	@Test
	public void getNextServiceTest() {
		fail("Not yet implemented");
	}

	@Test
	public void getServiceByIDTest() {
		fail("Not yet implemented");
	}

	@Test
	public void getServiceByTitleTest() {
		fail("Not yet implemented");
	}

	@Test
	public void getServiceByClientTest() {
		fail("Not yet implemented");
	}

}
