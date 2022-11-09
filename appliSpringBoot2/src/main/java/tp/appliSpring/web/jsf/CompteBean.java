package tp.appliSpring.web.jsf;

import java.util.List;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.RequestScope;

import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.service.ServiceCompte;

@ManagedBean
@RequestScope
//@SessionScope
public class CompteBean {
	private Long numClient; //à saisir
	private List<Compte> comptes; //à afficher
	
	@Autowired
	private ServiceCompte serviceCompte;//à injecter
	
	public String doLogin() {
		comptes = serviceCompte.rechercherComptesDuClient(numClient);
		return null; //rester sur meme page
	}

	public Long getNumClient() {
		return numClient;
	}

	public void setNumClient(Long numClient) {
		this.numClient = numClient;
	}

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	public ServiceCompte getServiceCompte() {
		return serviceCompte;
	}

	public void setServiceCompte(ServiceCompte serviceCompte) {
		this.serviceCompte = serviceCompte;
	}
	
	

}
