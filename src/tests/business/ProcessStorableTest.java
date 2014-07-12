package tests.business;

import org.junit.Test;

import business.ProcessStorable;
import static org.junit.Assert.*;

public class ProcessStorableTest 
{
	@Test
	public void testInsert()
	{
		ProcessStorable process = new ProcessStorable();
		assertFalse("Storable was inserted", process.insert(null));
	}
	
	@Test
	public void testDelete()
	{
		ProcessStorable process = new ProcessStorable();
		assertFalse("Storable was deleted", process.delete(null));
	}
	
	@Test
	public void testUpdate()
	{
		ProcessStorable process = new ProcessStorable();
		assertFalse("Storable was updated", process.update(null));
	}
}
