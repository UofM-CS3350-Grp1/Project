package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.integration.business.TestAccessFinancialRecords;
import tests.integration.business.CalculateFeatureValueTest;
import tests.integration.business.TestDateTimeUtil;
import tests.integration.business.GenerateGraphTest;
import tests.integration.business.TestProcessAddFeature;
import tests.integration.business.TestProcessClient;
import tests.integration.business.ProcessContractTest;
import tests.integration.business.ProcessExpensesTest;
import tests.integration.business.ProcessFeatureHistoryTest;
import tests.integration.business.ProcessServiceTest;
import tests.integration.business.ProcessStorableTest;
import tests.integration.business.ProcessUserTest;
import tests.integration.business.ValidateTextboxTest;
import tests.integration.persistence.FinancialQueryBuilderTest;
import tests.integration.persistence.LoginValidatorTest;
import tests.integration.persistence.RelationalQueryBuilderTest;
import tests.integration.persistence.TestDBController;
import tests.integration.persistence.TestDBInterface;
import tests.integration.persistence.TestDBInterface2;
import tests.integration.persistence.TestIDQueryBuilder;
import tests.integration.persistence.TestTableDumper;
import tests.integration.persistence.TestTableUpdater;

@RunWith(Suite.class)
@SuiteClasses({
	
	TestDBController.class,
	TestDBInterface.class,
	TestDBInterface2.class,
	LoginValidatorTest.class,
	FinancialQueryBuilderTest.class,
	RelationalQueryBuilderTest.class,
	TestTableDumper.class,
	TestTableUpdater.class,
	TestIDQueryBuilder.class,
	TestAccessFinancialRecords.class,
	CalculateFeatureValueTest.class,
	TestDateTimeUtil.class,
	GenerateGraphTest.class,
	TestProcessAddFeature.class,
	TestProcessClient.class,
	ProcessContractTest.class,
	ProcessExpensesTest.class,
	ProcessFeatureHistoryTest.class,
	ProcessServiceTest.class,
	ProcessStorableTest.class,
	ProcessUserTest.class,
	ValidateTextboxTest.class,
})
public class TestIntegration {

}
