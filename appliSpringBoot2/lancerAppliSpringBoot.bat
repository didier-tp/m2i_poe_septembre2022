cd /d "%~dp0/target"
REM NB: in META-INF/MANIFEST.MF of .jar
REM Main-Class: org.springframework.boot.loader.PropertiesLauncher
REM Start-Class: tp.appliSpring.AppliSpringBootApplication
java -Dspring.profiles.active=dev -jar appliSpringBoot2-0.0.1-SNAPSHOT.jar
REM java -Dspring.profiles.active=prod -jar appliSpringBoot2-0.0.1-SNAPSHOT.jar
pause