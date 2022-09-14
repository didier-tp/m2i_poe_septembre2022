REM set JAVA_HOME=C:\Program Files\Java\jdk-11.0.12
set H2_PATH=C:\Users\d2fde\.m2\repository\com\h2database\h2\2.1.214
REM set H2_PATH=C:\Users\Administrateur\.m2\repository\com\h2database\h2\2.1.214
java -jar %H2_PATH%/h2-2.1.214.jar
REM jdbc:h2:~/mydb14 ou jdbc:h2:~/mydb à la place jdbc:h2:~/test