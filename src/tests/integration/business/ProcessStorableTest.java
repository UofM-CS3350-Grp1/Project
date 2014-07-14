package tests.integration.business;

import static org.junit.Assert.*;

import org.junit.Test;

import persistence.DBInterface;
import business.ProcessStorable;

public class ProcessStorableTest {

	private DBInterface database;
	private ProcessStorable processStorable; 

	public ProcessStorableTest()
	{
		processStorable = new ProcessStorable();
		database = new DBInterface("db");
	}

	@Test
	public void deleteTest()
	{
		assertFalse("Storable was deleted", processStorable.delete(null));
	}

	@Test
	public void insertTest()
	{
		assertFalse("Storable was inserted", processStorable.insert(null));
	}

	@Test
	public void updateTest()
	{
		assertFalse("Storable was updated", processStorable.update(null));
	}
}
