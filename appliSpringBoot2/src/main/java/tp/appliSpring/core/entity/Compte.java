package tp.appliSpring.core.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="compte")
@NamedQuery(name="Compte.findAll",query="SELECT c FROM Compte c")
@NamedQuery(name="Compte.findByCustomerNumber",
            query="SELECT c FROM Compte c JOIN c.clients cli WHERE cli.numero = ?1")
@NamedQuery(name="Compte.findWithOperationsById",
             query="SELECT c FROM Compte c LEFT JOIN FETCH c.operations WHERE c.numero = ?1")
@Getter @Setter @NoArgsConstructor
public class Compte {
	
	 @ManyToMany(mappedBy = "comptes")//coté secondaire avec mappedBy="nomJavaRelationInverse"
	 private List<Client> clients = new ArrayList<>();
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	    //@GeneratedValue pour que le id auto_incrémenté par mysql ou h2 ou ...
	    //remonte bien en mémoire dans .numero de l"onjet java                     
	 private Long numero;
	 
	 @Column(name="label" , length = 64)  //VARCHAR(64) 
	 private String label;
	 
	 private Double solde;
	 
	 @OneToMany(mappedBy="compte")
	 private List<Operation> operations;
	 
	
	
	public Compte(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}
	
	
	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
	}

	
	 
	 

}
