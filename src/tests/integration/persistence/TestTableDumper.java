package tests.integration.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import persistence.TableDumper;

public class TestTableDumper 
{

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
