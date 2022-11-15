package tp.appliSpring.core.service;

import static org.mockito.ArgumentMatchers.anyLong;

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

	//+ éventuel appel à MockitoAnnotations.openMocks(this); dans @BeforeEach
	
	@Test
	public void testVerifierPasDecouvert() {
		Long numCompte1 = 678L;
		//1.préparation du mock en arrière plan:
		Mockito.when(daoCompteMock.findById(numCompte1))
		       .thenReturn(Optional.of(new Compte(numCompte1, "ComptePositif", 256.0)));
		//2.appel de la méthode verifierPasDecouvert sur le service et test retour
		boolean bPasDecouvert = serviceCompte.verifierPasDecouvert(numCompte1);
		logger.debug("bPasDecouvert=" + bPasDecouvert);
		Assertions.assertTrue(bPasDecouvert);
		//3 (étape facultative) :verif service appelant 1 fois compteDao.findById() via aspect "spy" de Mockito:
		Mockito.verify(daoCompteMock, Mockito.times(1)).findById(anyLong());
	}
	
	@Test
	public void testVerifierPasDecouvertAvecCompteInconnu() {
		Long numCompteInconnu = 999L;
		Mockito.when(daoCompteMock.findById(numCompteInconnu))
		       .thenReturn(Optional.empty());
		try {
			serviceCompte.verifierPasDecouvert(numCompteInconnu);
			Assertions.fail("une exception aurait du remonter");
		} catch (Exception ex) {
			logger.debug("exception normalement attendue="+ex.getMessage());
			Assertions.assertTrue(ex.getClass().getSimpleName()
			 .equals("NotFoundException"));
		}
		Mockito.verify(daoCompteMock, Mockito.times(1)).findById(anyLong());
	}
	
	@Test
	public void testVerifierDecouvert() {
		Long numCompte2 = 652L;
		Mockito.when(daoCompteMock.findById(numCompte2))
		       .thenReturn(Optional.of(new Compte(numCompte2, "CompteNegatif", -256.0)));
		boolean bPasDecouvert2 = serviceCompte.verifierPasDecouvert(numCompte2);
		logger.debug("bPasDecouvert2=" + bPasDecouvert2);
		Assertions.assertFalse(bPasDecouvert2);
		Mockito.verify(daoCompteMock, Mockito.times(1)).findById(anyLong());
	}
}
