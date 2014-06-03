package tests.objects;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;

import junit.framework.TestCase;
import objects.Client;
import objects.Client.ClientStatus;

/*
 * tests the Client object; anything else that you feel the client should (not) be
 * please create the test here;
 */
@RunWith(Parameterized.class)
public class ClientTest extends TestCase
{
	private Client client;
	private String name;
	private String businessName;
	private String phoneNumber;
	private String email;
	private String address;
	private ClientStatus status;
	
	@Parameters
	public static Collection<Object[]> data() 
	{
		return Arrays.asList(new Object[][] 
		{
			{ new Client("Bob", "2046452100", "asd@asd.com", "123 Main St.", "ABC Co.", ClientStatus.Potential), 
				"Bob", "2046452100", "asd@asd.com", "123 Main St.", "ABC Co.", ClientStatus.Potential }
		});
	}
	
	public ClientTest(Client client, String name, String phoneNumber, 
			String email, String address, String business, ClientStatus status)
	{
		this.client = client;
		this.name = name;
		this.businessName = business;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.status = status;
	}
	
	@Test
	public void testClient1()
	{
		System.out.println("\nStarting testClient1:");
		
		assertNotNull("Client is null", client);
		assertTrue("Name is invalid", client.getName().equals(name));
		assertTrue("Business name is invalid", client.getBusinessName().equals(businessName));
		assertTrue("Phone number is invalid", client.getPhoneNumber().equals(phoneNumber));
		assertTrue("Email is invalid", client.getEmail().equals(email));
		assertTrue("Address is invalid", client.getAddress().equals(address));
		assertTrue("Client status is invalid", client.getStatus().equals(status));
		
		System.out.println("\nFinished testClient1");
	}
}