package tests.integration.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import business.ValidateTextbox;

public class ValidateTextboxTest
{
	@Rule
	public TestName testName = new TestName();
	
	@Before
	public void before()
	{
		System.out.println("Running test: " + this.getClass().toString() + "::" + testName.getMethodName());
	}
	
	@After
	public void after()
	{
		System.out.println("Finished test.\n");
	}
	
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
