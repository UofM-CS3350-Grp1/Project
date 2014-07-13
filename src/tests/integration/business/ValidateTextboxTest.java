package tests.integration.business;

import static org.junit.Assert.*;

import org.junit.Test;

import persistence.DBInterface;
import business.ValidateTextbox;

// TO DO:  Derek
public class ValidateTextboxTest {

	private DBInterface database;
	private ValidateTextbox validateTextbox; 

	public ValidateTextboxTest()
	{
		validateTextbox = new ValidateTextbox();
		database = new DBInterface("db");
	}

	@Test
	public void testVerifyNumericTextbox() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerifyMonetaryValue() {
		fail("Not yet implemented");
	}

}
