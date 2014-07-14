package tests.integration.business;

import static org.junit.Assert.*;

import org.junit.Test;

import business.ValidateTextbox;

public class ValidateTextboxTest {

	private ValidateTextbox validateTextbox; 

	public ValidateTextboxTest()
	{
		validateTextbox = new ValidateTextbox();
	}

	@Test
	public void verifyMonetaryValueTest()
	{
		validateTextbox.verifyMonetaryValue(null);
	}

	@Test
	public void verifyNumericTextboxTest()
	{
		validateTextbox.verifyNumericTextbox(null, -1);
	}
}
