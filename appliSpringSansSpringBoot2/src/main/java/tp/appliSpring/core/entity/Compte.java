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
import javax.persistence.Table;

@Entity
@Table(name="compte")
@NamedQuery(name="Compte.findAll",query="SELECT c FROM Compte c")
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
	 
	public Compte() {
		super();
	}
	
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

	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Double getSolde() {
		return solde;
	}
	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	 
	 

}
