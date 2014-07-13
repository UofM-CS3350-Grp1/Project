package tests.objects;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import objects.Client;
import objects.Client.ClientStatus;
import objects.Email;
import objects.PhoneNumber;

/*
 * tests the Client object; anything else that you feel the client should (not) be
 * please create the test here;
 */
public class ClientTest
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
	
	/** Creating Client Tests */
	
	@Test
	public void testClient1()
	{
		Client client = new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
		
		assertNotNull("Client is null", client);
		assertTrue("Name is invalid", client.getName().equals("Bill"));
		assertTrue("Business name is invalid", client.getBusinessName().equals("Wyld Stallyns"));
		assertTrue("Phone number is invalid", client.getPhoneNumber().equals(new PhoneNumber("2045551326")));
		assertTrue("Email is invalid", client.getEmail().equals(new Email("bill@test.com")));
		assertTrue("Address is invalid", client.getAddress().equals("San Dimas"));
		assertTrue("Client status is invalid", client.getStatus() == ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient2()
	{
		new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", "", ClientStatus.Active);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testClient3()
	{
		new Client(null, new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testClient4()
	{
		new Client("", new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient5()
	{
		new Client("Bill", null, new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient6()
	{
		new Client("Bill", null, new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient7()
	{
		new Client("Bill", new PhoneNumber("5551326"), new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient8()
	{
		new Client("Bill", new PhoneNumber("2045551326"), null, "San Dimas", "Wyld Stallyns", ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient9()
	{
		new Client("Bill", new PhoneNumber("2045551326"), null, "San Dimas", "Wyld Stallyns", ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient10()
	{
		new Client("Bill", new PhoneNumber("2045551326"), new Email("bill.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient11()
	{
		new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), null, "Wyld Stallyns", ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient12()
	{
		new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), "", "Wyld Stallyns", ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient13()
	{
		new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", null, ClientStatus.Active);
	}

	/** Client Mutator Tests */
	public void testMutator1()
	{
		Client client = new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
		assertNotNull("Null client", client != null);
		
		client.setName(null);
		assertTrue("Null name", client.getName() != null);
		assertTrue("Name changed", client.getName().equals("Bill"));
	}
	
	public void testMutator2()
	{
		Client client = new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
		assertNotNull("Null client", client != null);
		
		client.setName("");
		assertTrue("Null name", client.getName() != null);
		assertTrue("Name changed", client.getName().equals("Bill"));
	}
	
	public void testMutator3()
	{
		Client client = new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
		assertNotNull("Null client", client != null);
		
		client.setName("Ted");
		assertTrue("Null name", client.getName() != null);
		assertTrue("Name changed", client.getName().equals("Ted"));
	}
	
	public void testMutator4()
	{
		Client client = new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
		assertNotNull("Null client", client != null);
		
		client.setPhoneNumber(null);
		assertTrue("Null phone number", client.getPhoneNumber() != null);
		assertTrue("Phone number changed", client.getPhoneNumber().equals(new PhoneNumber("2045551326")));		
	}
	
	public void testMutator5()
	{
		Client client = new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
		assertNotNull("Null client", client != null);
		
		client.setPhoneNumber(new PhoneNumber(""));
		assertTrue("Null phone number", client.getPhoneNumber() != null);
		assertTrue("Phone number changed", client.getPhoneNumber().equals(new PhoneNumber("2045551326")));		
	}
	
	public void testMutator6()
	{
		Client client = new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
		assertNotNull("Null client", client != null);
		
		client.setPhoneNumber(new PhoneNumber("5551326"));
		assertTrue("Null phone number", client.getPhoneNumber() != null);
		assertTrue("Phone number changed", client.getPhoneNumber().equals(new PhoneNumber("2045551326")));		
	}
	
	public void testMutator7()
	{
		Client client = new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
		assertNotNull("Null client", client != null);
		
		client.setEmail(null);
		assertTrue("Null email", client.getEmail() != null);
		assertTrue("Email changed", client.getEmail().equals(new Email("bill@test.com")));		
	}
	
	public void testMutator8()
	{
		Client client = new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
		assertNotNull("Null client", client != null);
		
		client.setEmail(new Email(""));
		assertTrue("Null email", client.getEmail() != null);
		assertTrue("Email changed", client.getEmail().equals(new Email("bill@test.com")));		
	}
	
	public void testMutator9()
	{
		Client client = new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
		assertNotNull("Null client", client != null);
		
		client.setEmail(new Email("invalid.com"));
		assertTrue("Null email", client.getEmail() != null);
		assertTrue("Email changed", client.getEmail().equals(new Email("bill@test.com")));		
	}
	
	public void testMutator10()
	{
		Client client = new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
		assertNotNull("Null client", client != null);
		
		client.setEmail(new Email("@invalid.com"));
		assertTrue("Null email", client.getEmail() != null);
		assertTrue("Email changed", client.getEmail().equals(new Email("bill@test.com")));		
	}
	
	public void testMutator11()
	{
		Client client = new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
		assertNotNull("Null client", client != null);
		
		client.setAddress(null);
		assertTrue("Null address", client.getAddress() != null);
		assertTrue("Address changed", client.getAddress().equals("San Dimas"));		
	}
	
	public void testMutator12()
	{
		Client client = new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
		assertNotNull("Null client", client != null);
		
		client.setAddress("");
		assertTrue("Null address", client.getAddress() != null);
		assertTrue("Address changed", client.getAddress().equals("San Dimas"));		
	}
	
	public void testMutator13()
	{
		Client client = new Client("Bill", new PhoneNumber("2045551326"), new Email("bill@test.com"), "San Dimas", "Wyld Stallyns", ClientStatus.Active);
		assertNotNull("Null client", client != null);
		
		client.setBusinessName(null);
		assertTrue("Null business name", client.getBusinessName() != null);
		assertTrue("Business name changed", client.getBusinessName().equals("Wyld Stallyns"));		
	}
	
	
}