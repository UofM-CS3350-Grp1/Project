package tests.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

import objects.*;
import persistence.DBController;
import persistence.DBInterface;

public class TestDBController
{

	@Test
	public void testController1() 
	{
		DBController controller = new DBController("Test");

		assertNotNull("Controller is null.",controller);
	}

	
	@Test
	public void testModify1()
	{
		DBInterface iFace = new DBInterface("CacheDB");
		iFace.connect();
		DBController controller = iFace.getController();
		
		Client clientTest = new Client(234, "Joe Doe", new PhoneNumber("2135552222"), new Email("joedoe@gmail.com"), "223 Main St.", "Joes Business", 1);
		ArrayList<String> clauses = new ArrayList<String>();
		ArrayList<ArrayList<String>> conditions = new ArrayList<ArrayList<String>>();

		clauses.add("ROW_ID");
		clauses.add("=");
		clauses.add("4");
		conditions.add(clauses);
		
		controller.connect();
		
		assertTrue("Controller failed to insert and returned index of -1.",controller.insert("CLIENTS", clientTest) != -1);
		assertFalse("Controller believed it could insert into a null table.", controller.insert(null, clientTest) != -1);
		assertFalse("Controller believed it could insert into an inexistant tale.", controller.insert("", clientTest) != -1);
		assertFalse("Controller believed it could insert a null object.", controller.update("CLIENTS", null) == true);
		
		clientTest = iFace.getClientByID(4);
		assertTrue("Controller failed to update and has returned false.", controller.update("CLIENTS", clientTest));
		assertFalse("Controller believed it could update a null table.", controller.update(null, clientTest) == true);
		assertFalse("Controller believed it could update a null object.", controller.update("CLIENTS", null) == true);
		assertFalse("Controller believed it could update an inexistant tale.", controller.update("", clientTest) == true);
		
		assertTrue("Controller failed to drop client and has returned false.", controller.drop("CLIENTS", 4));
		assertFalse("Controller believed it could drop from a null table.", controller.drop(null, 4) == true);
		assertFalse("Controller believed it could drop from an inexistant tale.", controller.drop("", 4) == true);
		assertFalse("Controller believed it could drop from an inexistant ID.", controller.drop("CLIENTS", 9999) == true);
		controller.disconnect();
	}

}
