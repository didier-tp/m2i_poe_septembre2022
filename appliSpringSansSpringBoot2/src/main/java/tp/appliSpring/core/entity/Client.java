package tp.appliSpring.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="client")
@NamedQuery(name="Client.findAll",query="SELECT c FROM Client c")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero;
	private String prenom;
	private String nom;
	private String adresse;
	private String email;
	//...
	
	
	public Client(Long numero, String prenom, String nom, String adresse, String email) {
		super();
		this.numero = numero;
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Client [numero=" + numero + ", prenom=" + prenom + ", nom=" + nom + ", adresse=" + adresse + ", email="
				+ email + "]";
	}

	public Client() {
		super();
	}

	public Long getNumero() {
		return numero;
	}
	
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
