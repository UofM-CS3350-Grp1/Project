
Acceptance test README.

IMPORTANT: To log into the program, you must use one of the following username : password combinations
Karl	password
Adrian	password
Dell	password
Derek	password
Tim		password
Jason	password

The purpose of this file is to instruct users on the ordering of the automated and manual acceptance tests.

Running RunAcceptanceTests.bat will restore the database to it's original state.
This is required for acceptance tests to pass their tests.


After running the automated acceptance tests, complete the manual tests in the following order:


1. loginBlocking.txt
2. createContract.txt
3. addExpense.txt
4. addFeature.txt
5. historyAnalytics.txt
6. deleteService.txt
7. deleteClient.txt


Please note that these manual tests could not be implemented using ATR. 
Also, the historyAnalytics.txt test is a visual test that covers analytics of performance and financial tracking over time, and so a new test client could not be used.