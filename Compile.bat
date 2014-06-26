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
echo Compiling src\persistence\*.java
javac -d bin\ -cp %classpath% src\persistence\*.java

echo.
echo Compiling src\business\*.java
javac -d bin\ -cp %classpath% src\business\*.java

echo.
echo Compiling src\org\eclipse\wb\swt\*.java
javac -d bin\ -cp %classpath% src\org\eclipse\wb\swt\*.java

echo.
echo Compiling src\presentation\*.java
javac -d bin\ -cp %classpath% src\presentation\*.java

echo.
echo Compiling src\tests\objects\*.java
javac -d bin\ -cp %classpath% src\tests\objects\*.java

echo.
echo Compiling src\tests\persistence\*.java
javac -d bin\ -cp %classpath% src\tests\persistence\*.java

echo.
echo Compiling src\tests\*.java
javac -d bin\ -cp %classpath% src\tests\*.java

echo.
pause
