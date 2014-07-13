package tests.integration.persistence;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.LoginValidator;

public class LoginValidatorTest {

	@Before
	public void before()
	{
		System.out.println("Running test: " + this.getClass().toString());
	}
	
	@After
	public void after()
	{
		System.out.println("Exiting test: " + this.getClass().toString());
	}
	
	@Test
	public void testValid() 
	{
		LoginValidator logIn = new LoginValidator();
		logIn.connect();
		assertTrue("Login Works",logIn.userLogin("Adrian", "password"));
		logIn.disconnect();
	}
	
	@Test
	public void testInvalid() 
	{
		LoginValidator logIn = new LoginValidator();
		logIn.connect();
		assertFalse("Bad login rejected", logIn.userLogin("Adrian", "notpassword"));
		logIn.disconnect();
	}

}
