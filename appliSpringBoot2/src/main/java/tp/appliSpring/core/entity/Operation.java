package tp.appliSpring.core.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

/* 
 * DaoOperation, DaoOperationJpa 
 * ServiceOperation facultatif
 * dans TestServiceCompte , ajouter @Autowired private DaoOperation daoOperation;
 * dans  testRechercherCompte() à améliorer:
 *   - sauvegarder des operations rattachées au compteXy
 *   - tout relire et afficher
 */

@Entity
@Table(name="operation")
@NamedQuery(name="Operation.findAll",query="SELECT o FROM Operation o")
@NamedQuery(name="Operation.findByAccountNumber",
            query="SELECT o FROM Operation o WHERE o.compte.numero = ?1")
@Getter @Setter
public class Operation {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numOp;  //id, auto_incr
	private String label; //ex: "achat xy" 
	private Double montant; //ex: -50 ou +60
	
	@Temporal(TemporalType.DATE)
	private Date dateOp; //ex: 2022-11-07 en base
	

	@ManyToOne//plusieurs opérations pour un compte   (@ManyToOne, @OneToMany)
	 @JoinColumn(name = "numCompte")//avec clef_etrangère (fk) : numCompte
	private Compte compte;
	
	


	public Operation() {
	    this.dateOp = new Date();
	}


	public Operation(Long numOp, String label, Double montant) {
		super();
		this.numOp = numOp;
		this.label = label;
		this.montant = montant;
		this.dateOp = new Date();
	}
	
	


	@Override
	public String toString() {
		return "Operation [numOp=" + numOp + ", label=" + label + ", montant=" + montant + ", dateOp=" + dateOp + "]";
	}


	
	
	
}
