package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.objects.ClientTest;
import tests.objects.ContractTest;
import tests.objects.EmailTest;
import tests.objects.PhoneNumberTest;
import tests.objects.ServiceTest;
import tests.objects.TrackedFeatureTest;
import tests.persistence.TestDBController;

@RunWith(Suite.class)
@SuiteClasses(
		{
			ClientTest.class,
			ServiceTest.class,
			ContractTest.class,
			EmailTest.class,
			PhoneNumberTest.class,
			TrackedFeatureTest.class,
			TestDBController.class
		})
public class TestAll
{ }
