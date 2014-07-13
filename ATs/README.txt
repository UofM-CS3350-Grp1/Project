
Acceptance test README.

The purpose of this file is to instruct users on the ordering of the automated and manual acceptance tests.

Running RunAcceptanceTests.bat will restore the database to it's original state.
This is required for acceptance tests to pass their tests.


After running the automated acceptance tests, complete the manual tests in the following order:


1. loginBlocking.txt
2. createContract.txt
3. deleteService.txt
4. deleteClient.txt