package tests.objects;

import static org.junit.Assert.*;

import org.junit.Test;

import objects.Client;
import objects.Client.ClientStatus;

/*
 * tests the Client object; anything else that you feel the client should (not) be
 * please create the test here;
 */
public class ClientTest
{
	@Test
	public void testClient1()
	{
		Client client = new Client("Bill", "2045551326", "bill@test.com", "San Dimas", "Wyld Stallyns", ClientStatus.Active);
		
		assertNotNull("Client is null", client);
		assertTrue("Name is invalid", client.getName().equals("Bill"));
		assertTrue("Business name is invalid", client.getBusinessName().equals("Wyld Stallyns"));
		assertTrue("Phone number is invalid", client.getPhoneNumber().equals("2045551326"));
		assertTrue("Email is invalid", client.getEmail().equals("bill@test.com"));
		assertTrue("Address is invalid", client.getAddress().equals("San Dimas"));
		assertTrue("Client status is invalid", client.getStatus() == ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient2()
	{
		new Client("Bill", "2045551326", "bill@test.com", "San Dimas", "", ClientStatus.Active);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testClient3()
	{
		new Client(null, "2045551326", "bill@test.com", "San Dimas", "Wyld Stallyns", ClientStatus.Active);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testClient4()
	{
		new Client("", "2045551326", "bill@test.com", "San Dimas", "Wyld Stallyns", ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient5()
	{
		new Client("Bill", null, "bill@test.com", "San Dimas", "Wyld Stallyns", ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient6()
	{
		new Client("Bill", "", "bill@test.com", "San Dimas", "Wyld Stallyns", ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient7()
	{
		new Client("Bill", "5551326", "bill@test.com", "San Dimas", "Wyld Stallyns", ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient8()
	{
		new Client("Bill", "2045551326", null, "San Dimas", "Wyld Stallyns", ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient9()
	{
		new Client("Bill", "2045551326", "", "San Dimas", "Wyld Stallyns", ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient10()
	{
		new Client("Bill", "2045551326", "bill.com", "San Dimas", "Wyld Stallyns", ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient11()
	{
		new Client("Bill", "2045551326", "bill@test.com", null, "Wyld Stallyns", ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient12()
	{
		new Client("Bill", "2045551326", "bill@test.com", "", "Wyld Stallyns", ClientStatus.Active);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClient13()
	{
		new Client("Bill", "2045551326", "bill@test.com", "San Dimas", null, ClientStatus.Active);
	}
}