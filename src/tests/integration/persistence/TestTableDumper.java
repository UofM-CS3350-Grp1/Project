package tests.integration.persistence;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.TableDumper;

public class TestTableDumper 
{

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
		TableDumper tableDump = new TableDumper();
		tableDump.connect();
		
		assertNotNull("Table dump, Services", tableDump.dumpServices());
		assertNotNull("Table dump, Contracts", tableDump.dumpContracts());
		assertNotNull("Table dump, Clients", tableDump.dumpClients());
		assertNotNull("Table dump, Features", tableDump.dumpTrackedFeatures());
		assertNotNull("Table dump, Histories", tableDump.dumpExpenses());
		assertNotNull("Table dump, Histories", tableDump.dumpTrackedFeatureTypes());
		assertNotNull("Table dump, Histories", tableDump.dumpServiceTypes());
		
		tableDump.disconnect();
	}

}
