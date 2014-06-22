@echo off

echo.
echo Clearing old class files...
erase /S /Q .\bin\*.class

echo.
echo Setting class path...
call setClasspath

echo.
echo Compiling src\objects\*.java
javac -d bin\ -cp %classpath% src\objects\*.java

echo.
echo Compiling project
javac -d bin\ -cp %classpath% src\objects\*.java src\business\*.java src\org\eclipse\wb\swt\*.java src\persistence\*.java src\presentation\*.java

echo.
echo Compiling src\tests\objects\*.java
javac -d bin\ -cp %classpath% src\tests\objects\*.java

echo.
echo Compiling src\tests\persistence\*.java
javac -d bin\ -cp %classpath% src\tests\persistence\*.java

echo.
echo Compiling src\tests\*.java
javac -d bin\ -cp %classpath% src\tests\*.java

pause
