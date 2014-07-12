@echo off

call setClasspath

echo.
echo Running tests...
java tests.RunIntegrationTests

call database\RestoreDB.bat

echo Press any key to continue..