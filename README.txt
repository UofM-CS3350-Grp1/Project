###########
TEST STATUS
###########

CalculateFeatureValueTest	- CLEAR

GenerateGraphTest		- CLEAR

ProcessContractTest		- INTEGRITY ERRORS

ProcessExpensesTest			- CLEAR

Process Feature History Test		- CLEAR

ProcessServiceTest			- CLEAR

TestAccessFinancialRecords		- CLEAR

TestDateTimeUtil			- CLEAR

TestProcessAddFeature		- CLEAR

TestProcessClient			- CLEAR

##############################################



Repository URL: https://github.com/UofM-CS3350-Grp1/Project


To run this program:

Compile:
	run Compile.bat

Run:
	run Run.bat
		- if you receive error: "Cannot load 32-bit SWT libraries on 64-bit JVM"
			- run swt_jar_64-bit.bat
			- re-run Run.bat
		- if you receive error: "Cannot load 64-bit SWT libraries on 32-bit JVM"
			- run swt_jar_32-bit.bat
			- re-run Run.bat

Run tests:
	run RunUnitTests.bat
		- Once tests are completed run RestoreDB.bat in /databases to restore
		  tables, as rows may be inserted/updated/deleted that would invalidate 
		  future tests.


This is the Buzzin' Digital Marketing software development project.
This software is designed to manage various clients and services offered by Buzzin' Digital Marketing.
Future releases will also be able to build and store client contracts, and analyze the performance of clients over a given date range.


Directory:
		/src
			-/business 		-> Business Logic/Controllers.
			-/objects 		-> Objects/Models.
			-/persistence 		-> DBMS Persistence.
			-/presentation		-> Widgets/GUIs
		/lib
			-> Jar files, JUnit, other build essentials.
		/database
			-> DBMS implementation, SQL editor


Major changes this iteration:
	Added service creation
	Added service feature creation
	Added Contract creation
	Added History tracking to clients and services
	Added performance tracking based on the history of clients and services
	Switched from stub database to a real database
	Refactored user interface by beraking it up into serveral smaller partitions
	
	
	
