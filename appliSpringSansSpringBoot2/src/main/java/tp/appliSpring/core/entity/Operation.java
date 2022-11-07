package tp.appliSpring.core.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/* 
 * DaoOperation, DaoOperationJpa 
 * ServiceOperation facultatif
 * dans TestServiceCompte , ajouter @Autowired private DaoOperation daoOperation;
 * dans  testRechercherCompte() à améliorer:
 *   - sauvegarder des operations rattachées au compteXy
 *   - tout relire et afficher
 */

public class Operation {
	private Long numOp;  //id, auto_incr
	private String label; //ex: "achat xy" 
	private Double montant; //ex: -50 ou +60
	
	@Temporal(TemporalType.DATE)
	private Date dateOp; //ex: 2022-11-07 en base
	
	//plusieurs opérations pour un compte   (@ManyToOne, @OneToMany)
	//avec clef_etrangère (fk) : numCompte
	private Compte compte;
}
