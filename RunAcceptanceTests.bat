REM @echo off

IF "%1" NEQ "" (SET SLEEP=%1) ELSE (SET SLEEP=1)

call database/RestoreDB
cd ../

call setClasspath

java -cp %CLASSPATH% tests.RunAcceptanceTests > AcceptanceTestResults.txt %SLEEP%

set SLEEP=

pause


