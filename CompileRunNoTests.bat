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


call setClasspath
echo Running program...
java presentation.Startup

pause
