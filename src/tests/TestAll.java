package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.objects.ClientTest;

@RunWith(Suite.class)
@SuiteClasses(
		{
			ClientTest.class
		})
public class TestAll
{ }
