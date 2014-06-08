package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.objects.ClientTest;
import tests.objects.ContractTest;
import tests.objects.ServiceTest;
import tests.persistence.TestDBController;

@RunWith(Suite.class)
@SuiteClasses(
		{
			ClientTest.class,
			ServiceTest.class,
			ContractTest.class,
			TestDBController.class
		})
public class TestAll
{ }
