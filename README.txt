Repository URL: https://github.com/UofM-CS3350-Grp1/Project


IMPORTANT: To log into the program, you must use one of the following username : password combinations
Karl	password
Adrian	password
Dell	password
Derek	password
Tim		password
Jason	password

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
	run RunTests.bat
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
	Added login functionality
	Added Job Cost Cards (JCC)
	Added Contract Editing
	Added integration tests
	Added acceptance tests
	Refactored GUI menu
	Refactored Domain objects
	Refactored Database interface
	Refactored program start location
	
	
Outstanding issues:
	Piecharts only show expenses if contract is signed, should also show when contract is cancelled or terminated
	
