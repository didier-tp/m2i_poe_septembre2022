package tp.appliSpring.temp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.appliSpring.AppliSpringBootApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes={AppliSpringBootApplication.class}) // java config
@ActiveProfiles({"dev"})//pour tenir compte de application-dev.properties
//@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TestCalculateurSpring {
	
	private static Logger logger = LoggerFactory.getLogger(TestCalculateurSpring.class);
	
	@Autowired
	private CalculateurSpring calculateur; //à tester

	
	@Test
	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	public void testSomme() {
		logger.debug("testSomme() appelée , this="+this + " calculateur="+calculateur);
		calculateur.addVal(8);
		calculateur.addVal(2);
		double s  = calculateur.getSomme();
		Assertions.assertEquals(10.0, s , 0.0000001);
	}
	
	@Test
	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	public void testMoyenne() {
		logger.debug("testMoyenne() appelée , this="+this + " calculateur="+calculateur);
		calculateur.addVal(8);
		calculateur.addVal(6);
		double m  = calculateur.getMoyenne();
		Assertions.assertEquals(7, m , 0.0000001);
	}
}
