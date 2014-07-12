package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.integration.business.TestAccessFinancialRecords;
import tests.integration.business.TestCalculateFeatureValue;
import tests.integration.business.TestDateTimeUtil;
import tests.integration.business.TestGenerateGraph;
import tests.integration.business.TestProcessAddFeature;
import tests.integration.business.TestProcessClient;
import tests.integration.business.TestProcessContract;
import tests.integration.business.TestProcessExpenses;
import tests.integration.business.TestProcessFeatureHistory;
import tests.integration.business.TestProcessService;
import tests.integration.business.TestProcessStorable;
import tests.integration.business.TestProcessUser;
import tests.integration.business.TestValidateTextbox;
import tests.integration.persistence.TestDBController;
import tests.integration.persistence.TestDBInterface;
import tests.integration.persistence.TestStubDBInterface;

@RunWith(Suite.class)
@SuiteClasses({
	
	TestDBController.class,
	TestDBInterface.class,
	TestStubDBInterface.class,
	TestAccessFinancialRecords.class,
	TestCalculateFeatureValue.class,
	TestDateTimeUtil.class,
	TestGenerateGraph.class,
	TestProcessAddFeature.class,
	TestProcessClient.class,
	TestProcessContract.class,
	TestProcessExpenses.class,
	TestProcessFeatureHistory.class,
	TestProcessService.class,
	TestProcessStorable.class,
	TestProcessUser.class,
	TestValidateTextbox.class,
})
public class TestIntegration {

}
