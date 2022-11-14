cd /d "%~dp0/target"
REM NB: in META-INF/MANIFEST.MF of .jar
REM Main-Class: org.springframework.boot.loader.PropertiesLauncher
REM Start-Class: tp.appliSpring.AppliSpringApplication
java -Dspring.profiles.active=reInit,embeddedDB -jar appliSpring-0.0.1-SNAPSHOT.jar
REM java -Dspring.profiles.active=prod,withSecurity,remoteDB -jar appliSpring-0.0.1-SNAPSHOT.jar
pause