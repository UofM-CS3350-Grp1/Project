@echo off

echo.
echo Clearing old class files...
erase /S /Q .\bin\*.class

echo.
echo Setting class path...
call setClasspath

echo.
echo Compiling project source...
javac -d bin\ -cp %classpath% src\objects\*.java src\persistence\*.java src\business\*.java src\org\eclipse\wb\swt\*.java src\presentation\*.java

echo.
echo Compiling src\tests\objects\*.java
javac -d bin\ -cp %classpath% src\tests\objects\*.java

echo.
echo Compiling src\tests\business\*.java
javac -d bin\ -cp %classpath% src\tests\business\*.java

echo.
echo Compiling src\tests\integration\persistence\*.java
javac -d bin\ -cp %classpath% src\tests\integration\persistence\*.java

echo.
echo Compiling src\tests\*.java
javac -d bin\ -cp %classpath% src\tests\*.java

echo.
pause
