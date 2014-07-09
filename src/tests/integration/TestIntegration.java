package tests.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.integration.persistence.TestDBController;
import tests.integration.persistence.TestDBInterface;

@RunWith(Suite.class)
@SuiteClasses({
	
	TestDBController.class,
	TestDBInterface.class,
})
public class TestIntegration {

}
