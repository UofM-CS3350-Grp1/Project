package tests.integration.business;

import static org.junit.Assert.*;

import org.junit.Test;

import business.ProcessFeatureHistory;
import persistence.DBInterface;

// TO DO:  Derek
public class ProcessFeatureHistoryTest {

	private DBInterface database;
	private ProcessFeatureHistory processFeatureHistory; 

	public ProcessFeatureHistoryTest()
	{
		processFeatureHistory = new ProcessFeatureHistory();
		database = new DBInterface("db");
	}

	@Test
	public void ProcessFeatureHistoryTest() {
		fail("Not yet implemented");
	}

	@Test
	public void getNextHistoryTest() {
		fail("Not yet implemented");
	}

	@Test
	public void getHistoryByIDTest() {
		fail("Not yet implemented");
	}

	@Test
	public void getNextHistoryForFeatureTest() {
		fail("Not yet implemented");
	}

	@Test
	public void getHistoryListForFeatureTest() {
		fail("Not yet implemented");
	}

	@Test
	public void insertFeatureTest() {
		fail("Not yet implemented");
	}

}
