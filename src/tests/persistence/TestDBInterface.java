package tests.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

import objects.*;
import persistence.DBController;
import persistence.DBInterface;

public class TestDBInterface {

	@Test
	public void testRetrieval() 
	{
		DBInterface mainFace = new DBInterface("Test");
		mainFace.connect();
		
		assertNotNull("Basic query implementation for services", mainFace.getServiceByID(1));
		
		mainFace.disconnect();
		
	}

}
