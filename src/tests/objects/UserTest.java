package tests.objects;

import objects.User;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class UserTest 
{
	@Rule
	public TestName testName = new TestName();
	
	@Before
	public void before()
	{
		System.out.println("Running test: " + testName.getMethodName());
	}
	
	@After
	public void after()
	{
		System.out.println("Finished test.\n");
	}
	
	/**		Testing Creation 	**/
	
	@Test(expected=IllegalArgumentException.class)
	public void testUser()
	{
		new User(-1, null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUser2()
	{
		new User(0, null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUser3()
	{
		new User(1, "", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUser4()
	{
		new User(0, "user", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUser5()
	{
		new User(1, "user", "");
	}
	
	/**	Testing Mutation **/
	
	@Test
	public void testUser6()
	{
		User user = new User(4, "user", "pass");
		user.updateUsername(null);
		assertTrue("Username is invalid", user.getUserName().equals("user"));
	}
	
	@Test
	public void testUser7()
	{
		User user = new User(4, "user", "pass");
		user.updateUsername("");
		assertTrue("Username is invalid", user.getUserName().equals("user"));
	}
	
	@Test
	public void testUser8()
	{
		User user = new User(4, "user", "pass");
		user.updateUsername("newUser");
		assertTrue("Username is invalid", user.getUserName().equals("newUser"));
	}
	
	@Test
	public void testUser9()
	{
		User user = new User(4, "user", "pass");
		user.updateUsername("one two");
		assertTrue("Username is invalid", user.getUserName().equals("user"));
	}
	
	@Test
	public void testUser10()
	{
		User user = new User(4, "user", "pass");
		user.updateUsername("<b>Something</b>");
		assertTrue("Username is invalid", user.getUserName().equals("user"));
	}
	
	@Test
	public void testUser11()
	{
		User user = new User(4, "user", "pass");
		user.updateUsername("myvalidUser$");
		assertTrue("Username is invalid", user.getUserName().equals("user"));
	}
	
	@Test
	public void testUser12()
	{
		User user = new User(4, "user", "pass");
		user.updatePassword(null);
		assertTrue("Password is invalid", user.getPassword().equals("pass"));
	}
	
	@Test
	public void testUser13()
	{
		User user = new User(4, "user", "pass");
		user.updatePassword("");
		assertTrue("Password is invalid", user.getPassword().equals("pass"));
	}
	
	@Test
	public void testUser14()
	{
		User user = new User(4, "user", "pass");
		user.updatePassword("newPass");
		assertTrue("Password is invalid", user.getPassword().equals("newPass"));
	}
	
	@Test
	public void testUser15()
	{
		User user = new User(4, "user", "pass");
		user.updatePassword("'OR 1=1");
		assertTrue("Password is invalid", user.getPassword().equals("pass"));
	}
	
	@Test
	public void testUser16()
	{
		User user = new User(4, "user", "pass");
		user.updatePassword("pAssw0rd");
		assertTrue("Password is invalid", user.getPassword().equals("pAssw0rd"));
	}
	
	@Test
	public void testUser17()
	{
		User user = new User(4, "user", "pass");
		user.updatePassword("/pass");
		assertTrue("Password is invalid", user.getPassword().equals("pass"));
	}
	
	@Test
	public void testUser18()
	{
		User user = new User(4, "user", "pass");
		user.updatePassword("he+lo");
		assertTrue("Password is invalid", user.getPassword().equals("pass"));
	}
}
