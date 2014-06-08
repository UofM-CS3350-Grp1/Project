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

TODO(Dell):

TODO(Timothy):
		- Testing
		- Refactoring Stub UI

TODO(Derek):
		- Create scripts to build/run program

TODO(Karl):
		- Refactor Stub UI into single window application

TODO(Jason):
		- Final data collection for services / clients / contracts
		- Familiarize myself with GIT and SourceTree (having difficutly uploading/committing files)
		- Implementation of exporting contracts to docx/pdf format
		- Implementation of the logging in feature
			
TODO(Adrian):
		- Testing.
		- Familiarize myself with GUI structure.
		- Persistence in DBMS.
		- Modify SERVICES table/model to reflect changes in new client data.
