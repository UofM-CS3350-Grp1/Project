@echo off

call setClasspath

echo Restoring database...
call database/RestoreDB.bat > nul

@echo off
cd ..
echo Database restored.
echo.

echo Running all tests...
echo See AllTests.txt for test results

echo.
echo Running unit tests.

echo Unit Tests > AllTests.txt
echo. >> AllTests.txt
java tests.RunTests >> AllTests.txt

echo Unit tests completed.
echo.
echo Running integration tests.

echo.  >> AllTests.txt
echo Integration Tests  >> AllTests.txt
echo. >> AllTests.txt
java tests.RunIntegrationTests >> AllTests.txt

echo Integration tests completed.
echo.
echo Running acceptance tests.

echo. >> AllTests.txt
echo Acceptance Tests >> AllTests.txt
echo. >> AllTests.txt
java tests.RunAcceptanceTests >> AllTests.txt
echo. >> AllTests.txt

echo Acceptance tests completed.
echo.
echo Testing completed.

pause
