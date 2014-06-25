
############################################################################
###ATTENTION: 	Please switch your interface controllers to the new CachedDB
###	table as a bug has been found (full table wipe) when exceeding the limit
### of HSQLDB memory. Thanks.											
############################################################################


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


This is the Buzzin' Digital Marketing software development project.
This software is designed to manage various clients and services offered by Buzzin' Digital Marketing.
Future releases will also be able to build and store client contracts, and analyze the performance of clients over a given date range.


Since this is already on the front page if you have moment to update this with with a  brief summary of what you intend to be working on in the short term it would be greatly appreciated.  

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
