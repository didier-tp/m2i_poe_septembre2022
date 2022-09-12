set JAVA_HOME=C:\Program Files\Java\jdk-11.0.12
set PATH=%JAVA_HOME%\bin;%PATH%
REM java -version

set CLASSPATH=target\tpJava.jar
REM java com.m2i.tp.App
java -Daff=majuscule com.m2i.tp.App2

REM java -jar target\tpJava.jar

pause