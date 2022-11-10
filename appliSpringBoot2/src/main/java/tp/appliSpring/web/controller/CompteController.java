package tp.appliSpring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import tp.appliSpring.core.entity.Client;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.service.ServiceClient;
import tp.appliSpring.core.service.ServiceCompte;

@Controller //composant spring de type Crontroller spring-mvc
@SessionAttributes( value={"client"} )
//noms des "modelAttributes" qui sont EN PLUS récupérés/stockés
// en SESSION HTTP au niveau de la page de rendu 
// --> visibles en requestScope ET en sessionScope
public class CompteController {
	
	@Autowired
	private ServiceCompte serviceCompte;
	
	@Autowired
	private ServiceClient serviceClient;
	
	@ModelAttribute("client") //NB: cette méthode n'est pas appelée/déclenchée
	//si "client" est déjà présent en session (et par copie) dans le modèle
	public Client addClientAttributeInModel() {
	   return new Client() ;
	}


	@RequestMapping("/versLogin")
	 public String versLogin(Model model) {
	    return "login"; //aiguiller sur la vue "login"
	    //selon la config de application.properties jsp/login.jsp
	 }
	
	@RequestMapping("/versVirement")
	 public String versVirement(Model model) {
	    return "virement"; //aiguiller sur la vue "virement"
	    //selon la config de application.properties jsp/virement.jsp
	 }
	
	@RequestMapping("/verifLogin")
	 public String verifLogin(Model model,@RequestParam(name="numClient")  Long numClient) {
		List<Compte> comptes = serviceCompte.rechercherComptesDuClient(numClient);
		Client client = serviceClient.rechercherClientParNumero(numClient);
	    model.addAttribute("client", client);
	    model.addAttribute("comptes", comptes);
	    return "comptes"; //aiguiller sur la vue "comptes"
	    //selon la config de application.properties jsp/comptes.jsp
	 }
	
	@RequestMapping("/effectuerVirement")
	 public String effectuerVirement(Model model,
			   @RequestParam(name="montant")  Double montant,
			   @RequestParam(name="numCptDeb")  Long numCptDeb,
			   @RequestParam(name="numCptCred")  Long numCptCred) {
		String message="";
		try {
			serviceCompte.transferer(montant, numCptDeb, numCptCred);
			message="virement bien effectué";
		} catch (Exception e) {
			message="echec virement: " + e.getMessage();
			e.printStackTrace();
		}
		Client client = (Client) model.getAttribute("client");
		List<Compte> comptes = serviceCompte.rechercherComptesDuClient(client.getNumero());
	    model.addAttribute("client", client);
	    model.addAttribute("comptes", comptes);
	    model.addAttribute("message", message);
	    return "comptes"; //aiguiller sur la vue "comptes"
	    //selon la config de application.properties jsp/comptes.jsp
	 }
}
