package tp.appliSpring.core.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tp.appliSpring.core.dao.DaoCompte;
import tp.appliSpring.core.entity.Compte;

@ExtendWith(MockitoExtension.class) //for JUnit 5
public class TestServiceCompteMockito {
	
	@InjectMocks
	/* @InjectMocks pour demander à "Mockito" (sans spring) de :
	- créer une instance normale de cette classe (ici new ServiceCompteImpl(...))
	- d'injecter le ou les @Mock(s) de cette classe de test dans la classe ServiceCompteImpl()
	via un constructeur adéquat
	*/
	private ServiceCompteImpl serviceCompte; // à tester
	
	@Mock
	private DaoCompte daoCompteMock;
	
	private static Logger logger = LoggerFactory.getLogger(TestServiceCompteMockito.class);

	
	@Test
	public void testVerifierPasDecouvert() {
		Long numCompte1 = 678L;
		Mockito.when(daoCompteMock.findById(numCompte1))
		       .thenReturn(Optional.of(new Compte(numCompte1, "ComptePositif", 256.0)));
		boolean bPasDecouvert = serviceCompte.verifierPasDecouvert(numCompte1);
		logger.debug("bPasDecouvert=" + bPasDecouvert);
		Assertions.assertTrue(bPasDecouvert);
		
		Long numCompte2 = 656L;
		Mockito.when(daoCompteMock.findById(numCompte2))
		       .thenReturn(Optional.of(new Compte(numCompte2, "CompteNegatif", -256.0)));
		boolean bPasDecouvert2 = serviceCompte.verifierPasDecouvert(numCompte2);
		logger.debug("bPasDecouvert2=" + bPasDecouvert2);
		Assertions.assertFalse(bPasDecouvert2);
	}
}
