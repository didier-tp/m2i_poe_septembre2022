package tp.appliSpring.web.jsf;

import java.util.List;

import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.service.ServiceCompte;

public class CompteBean {
	private Long numClient; //à saisir
	private List<Compte> comptes; //à afficher
	
	//@Autowired ou @Inject ou ...
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
