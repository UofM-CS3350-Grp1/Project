package tests;

import objects.Client;
import objects.Client.ClientStatus;

/*
 * tests the Client object; anything else that you feel the client should (not) be
 * please create the test here;
 */
public class TestClient {
	public static void main( String[] args ) {
		try {
			new Client( null, null, null, null, null, ClientStatus.Potential );
			System.out.println("  FAIL: a nameless client wouldn't be too useful");
		}
		catch ( IllegalArgumentException iae ) {
			System.out.println("+ PASS: a client without a name has been rejected");
		}
	}
}