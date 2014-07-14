package tests.integration.business;

import org.junit.Test;

import business.ValidateTextbox;

public class ValidateTextboxTest
{
	@Test
	public void verifyMonetaryValueTest()
	{
		ValidateTextbox.verifyMonetaryValue(null);
	}

	@Test
	public void verifyNumericTextboxTest()
	{
		ValidateTextbox.verifyNumericTextbox(null, -1);
	}
}
