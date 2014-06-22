package tests.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

import objects.*;
import persistence.DBController;

public class TestDBController {

	@Test
	public void testController1() 
	{
		DBController controller = new DBController("Test");

		assertNotNull("Controller is null.",controller);
	}
	
	@Test
	public void testQueries1()
	{
		DBController controller = new DBController("Test");
		ArrayList<String> clauses = new ArrayList<String>();
		ArrayList<String> clauses2 = new ArrayList<String>();
		ArrayList<String> clauses3 = new ArrayList<String>();
		ArrayList<String> clauses4 = new ArrayList<String>();
		ArrayList<ArrayList<String>> conditions = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> conditions2 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> conditions3 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> conditions4 = new ArrayList<ArrayList<String>>();
		
		Client samp = new Client(234, "Joe Doe", new PhoneNumber("2135552222"), new Email("joedoe@gmail.com"), "223 Main St.", "Joes Business", 1);
		
		clauses.add("ROW_ID");
		clauses.add("=");
		clauses.add("1");
		conditions.add(clauses);
		
		clauses2.add("ALL");
		conditions2.add(clauses2);
		
		clauses3.add("");
		conditions3.add(clauses3);
		
		clauses4.add(null);
		conditions4.add(clauses4);

		controller.connect();
		
		assertNotNull("BlindSQL -> Null", controller.blindQuery("SELECT * FROM CLIENTS"));
		assertTrue("BlindSQL -> Not Array List", controller.blindQuery("SELECT * FROM CLIENTS") instanceof ArrayList<?>);
		
		/*System.out.println();
		System.out.println();
		assertNotNull("Test a basic query", controller.query(samp.getTableName(), conditions2));
		ArrayList<ArrayList<String>> holder = controller.query(samp.getTableName(), conditions2);
		System.out.println();
		System.out.println();
		*/
		
		assertNotNull("Contracts -> Can't Run Basic Query", controller.queryContracts(conditions));
		assertNotNull("Services -> Can't Run Basic Query", controller.queryServices(conditions));
		assertNotNull("Clients -> Can't Run Basic Query", controller.queryClients(conditions));
		
		assertNotNull("Contracts -> Can't Run All Query", controller.queryContracts(conditions2));
		assertNotNull("Services -> Can't Run All Query", controller.queryServices(conditions2));
		assertNotNull("Clients -> Can't Run All Query", controller.queryClients(conditions2));

		assertNull("Contracts -> Not returning null on null.", controller.queryContracts(null));
		assertNull("Services -> Not returning null on null", controller.queryServices(null));
		assertNull("Clients -> Not returning null on null", controller.queryClients(null));
		
		assertNull("Contracts -> Not returning null on empty strings.", controller.queryContracts(conditions3));
		assertNull("Services -> Not returning null on empty strings", controller.queryServices(conditions3));
		assertNull("Clients -> Not returning null on empty strings", controller.queryClients(conditions3));
		
		assertNull("Contracts -> Not returning null on interior null array.", controller.queryContracts(conditions4));
		assertNull("Services -> Not returning null on interior null array", controller.queryServices(conditions4));
		assertNull("Clients -> Not returning null on interior null array", controller.queryClients(conditions4));
		
		controller.disconnect();
	}
	
	@Test
	public void testModify1()
	{
		DBController controller = new DBController("Test");
		
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
		
		clientTest = controller.queryClients(conditions).get(0);
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
