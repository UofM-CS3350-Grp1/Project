package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunTests
{
	/**
	 * Run the tests externally
	 * @param args Arguments
	 */
	public static void main(String[] args) throws Exception
	{
		Result result = JUnitCore.runClasses(TestAll.class);
	      for(Failure failure : result.getFailures()) 
	      {
	    	  System.out.println(failure.toString());
	      }
	      System.out.println(result.wasSuccessful());
	   
	}
}
