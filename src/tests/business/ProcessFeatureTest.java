package tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import business.ProcessAddFeature;
import static org.junit.Assert.*;

public class ProcessFeatureTest
{
	@Rule
	public TestName testName = new TestName();
	
	@Before
	public void before()
	{
		System.out.println("Running test: " + this.getClass().toString() + "::" + testName.getMethodName());
	}
	
	@After
	public void after()
	{
		System.out.println("Finished test.\n");
	}
	
	/** 	Testing avoiding the database	**/
	
	@Test
	public void testInsert()
	{
		ProcessAddFeature process = new ProcessAddFeature();
		assertFalse("Feature was inserted", process.insert(null));
	}
	
	@Test
	public void testDelete()
	{
		ProcessAddFeature process = new ProcessAddFeature();
		assertFalse("Feature was deleted", process.delete(null));
	}
	
	@Test
	public void testUpdate()
	{
		ProcessAddFeature process = new ProcessAddFeature();
		assertFalse("Feature was updated", process.update(null));
	}
	
	@Test
	public void testTypeByTitle()
	{
		ProcessAddFeature process = new ProcessAddFeature();
		assertNotNull("Feature type list is null", process.getFeatureTypeByTitle(null));
	}
	
	@Test
	public void testGetByID()
	{
		ProcessAddFeature process = new ProcessAddFeature();
		assertNull("ID is invalid", process.getFeatureByID(-1));
	}
	
	@Test
	public void testGetTypeByID()
	{
		ProcessAddFeature process = new ProcessAddFeature();
		assertNull("ID is invalid", process.getFeatureTypeByID(-1));
	}
	
	@Test
	public void testByClient()
	{
		ProcessAddFeature process = new ProcessAddFeature();
		assertNotNull("Feature list is null", process.getFeaturesByClient(null));
	}
	
	@Test
	public void testTypesByClient()
	{
		ProcessAddFeature process = new ProcessAddFeature();
		assertNotNull("Feature type list is null", process.getFeatureTypesByClient(null));
	}
}
