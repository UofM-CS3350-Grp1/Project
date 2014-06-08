@echo off

echo.
echo Clearing old class files...
erase /S /Q .\bin\*.class

echo.
echo Setting class path...
call setClasspath

echo.
echo Comiling src\objects\*.java
javac -d bin\ -cp %classpath% src\objects\*.java

echo.
echo Comiling src\persistence\*.java
javac -d bin\ -cp %classpath% src\persistence\*.java

echo.
echo Comiling src\presentation\*.java
javac -d bin\ -cp %classpath% src\presentation\*.java

REM Tests are failing to compile properly...
echo.
echo Comiling src\tests\*.java
javac -d bin\ -cp %classpath% src\tests\*.java

echo.
echo Comiling src\tests\objects\*.java
javac -d bin\ -cp %classpath% src\tests\objects\*.java

echo.
echo Comiling src\tests\persistence\*.java
javac -d bin\ -cp %classpath% src\tests\persistence\*.java

pause
