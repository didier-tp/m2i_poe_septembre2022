package tp.appliSpring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tp.appliSpring.core.entity.Client;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.service.ServiceClient;
import tp.appliSpring.core.service.ServiceCompte;

@Controller //composant spring de type Crontroller spring-mvc
public class CompteController {
	
	@Autowired
	private ServiceCompte serviceCompte;
	
	@Autowired
	private ServiceClient serviceClient;

	@RequestMapping("/versLogin")
	 public String versLogin(Model model) {
	    return "login"; //aiguiller sur la vue "login"
	    //selon la config de application.properties jsp/login.jsp
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
}
