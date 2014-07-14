@echo off

call setClasspath

echo.
echo Restoring database to factory defaults...
call database\RestoreDB.bat

cd ../
echo Running tests...
java tests.RunIntegrationTests

echo Press any key to continue..