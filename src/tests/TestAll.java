package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.business.AccessFinancialRecordsTest;
import tests.business.CalcFeatureValueTest;
import tests.business.DateTimeUtilTest;
import tests.business.GenerateGraphTest;
import tests.business.ProcessClientTest;
import tests.business.ProcessContractTest;
import tests.business.ProcessExpenseTest;
import tests.business.ProcessFeatureTest;
import tests.business.ProcessHistoryTest;
import tests.business.ProcessServiceTest;
import tests.business.ProcessStorableTest;
import tests.business.ProcessUserTest;
import tests.objects.ClientTest;
import tests.objects.ContractTest;
import tests.objects.EmailTest;
import tests.objects.ExpenseTest;
import tests.objects.MonthReportTest;
import tests.objects.PhoneNumberTest;
import tests.objects.ServiceTest;
import tests.objects.TrackedFeatureTest;
import tests.objects.TrackedFeatureTypeTest;
import tests.objects.UserTest;

@RunWith(Suite.class)
@SuiteClasses(
		{
			ClientTest.class,
			ServiceTest.class,
			ContractTest.class,
			EmailTest.class,
			PhoneNumberTest.class,
			TrackedFeatureTest.class,
			MonthReportTest.class,
			UserTest.class,
			TrackedFeatureTypeTest.class,
			ExpenseTest.class,
			CalcFeatureValueTest.class,
			AccessFinancialRecordsTest.class,
			DateTimeUtilTest.class,
			GenerateGraphTest.class,
			ProcessFeatureTest.class,
			ProcessClientTest.class,
			ProcessContractTest.class,
			ProcessExpenseTest.class,
			ProcessHistoryTest.class,
			ProcessServiceTest.class,
			ProcessStorableTest.class,
			ProcessUserTest.class
		})
public class TestAll
{ }
