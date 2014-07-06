package tests.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import persistence.LoginValidator;

public class LoginValidatorTest {

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
