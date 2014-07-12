@echo off

call setClasspath

echo.
echo Running tests...
java tests.RunTests > UnitTestResults.txt && type UnitTestResults.txt

pause