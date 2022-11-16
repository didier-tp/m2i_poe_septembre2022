package tp.appliSpring.web.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.appliSpring.converter.DtoConverter;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.exception.NotFoundException;
import tp.appliSpring.core.service.ServiceCompte;
import tp.appliSpring.dto.CompteDto;
import tp.appliSpring.dto.CompteDtoEx;
import tp.appliSpring.dto.Message;
import tp.appliSpring.dto.VirementDto;

@RestController //composant spring de type contrôleur pour Web Service REST
@RequestMapping(value="/bank-api/compte" , headers="Accept=application/json")
public class CompteRestCtrl {
	
	@Autowired
	private ServiceCompte serviceCompte;
	
	//A completer en mode POST,PUT,DELETE et à tester avec postman
	
	//URL= http://localhost:8080/appliSpringBoot/bank-api/compte/1
	@GetMapping("/{numCompte}")
	public CompteDto getCompteByNum(@PathVariable("numCompte") Long numCompte) throws NotFoundException {
		Compte compteEntity = serviceCompte.rechercherCompteParNumero(numCompte);
		CompteDto compteDto = DtoConverter.compteToCompteDto(compteEntity);
		return compteDto;
	}
	/*
	@GetMapping("/{numCompte}")
	public ResponseEntity<CompteDto> getCompteByNum(@PathVariable("numCompte") Long numCompte) {
		Compte compteEntity = serviceCompte.rechercherCompteParNumero(numCompte);
		if(compteEntity!=null) {
			CompteDto compteDto = DtoConverter.compteToCompteDto(compteEntity);
			return new ResponseEntity<CompteDto> (compteDto, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<CompteDto> (HttpStatus.NOT_FOUND);
		}
	}
	*/
	//URL= http://localhost:8080/appliSpringBoot/bank-api/compte/withDetails/1
	@GetMapping("/withDetails/{numCompte}")
	public CompteDtoEx getCompteByNumWithDetails(@PathVariable("numCompte") Long numCompte) {
			Compte compteEntity = serviceCompte.rechercherCompteAvecOperationsParNumero(numCompte);
			CompteDtoEx compteDtoEx = DtoConverter.compteToCompteDtoEx(compteEntity);
			return compteDtoEx;
	}
	
	//URL= http://localhost:8080/appliSpringBoot/bank-api/compte
	//ou   http://localhost:8080/appliSpringBoot/bank-api/compte?numClient=1
	//ou   http://localhost:8080/appliSpringBoot/bank-api/compte?soldeMini=50
	//ou   http://localhost:8080/appliSpringBoot/bank-api/compte?numClient=1&soldeMini=50
	@GetMapping("")
	public List<CompteDto> getComptesByCriteria(
			@RequestParam(name="numClient" , required=false) Long numClient,
			@RequestParam(name="soldeMini" , required=false) Double soldeMini){
		List<CompteDto> compteDtoList = new ArrayList<>();
		if(numClient==null && soldeMini == null) {
			compteDtoList = DtoConverter.compteListToCompteDtoList(serviceCompte.rechercherTousComptes());
		}
		else if(numClient != null) {
			compteDtoList = DtoConverter.compteListToCompteDtoList(serviceCompte.rechercherComptesDuClient(numClient));
			if(soldeMini !=null)
				compteDtoList=compteDtoList.stream().filter((c)->c.getSolde()>=soldeMini).collect(Collectors.toList());
		}
		else if(soldeMini != null) {
			compteDtoList = DtoConverter.compteListToCompteDtoList(serviceCompte.rechercherComptesViaSoldeMini(soldeMini));
		}
		return compteDtoList;
	}
	
	//URL= http://localhost:8080/appliSpringBoot/bank-api/compte
		//appelé en mode POST avec le corps de la requête HTTP comportant
		// { "numero" : null , "label" :  "compteQuiVaBien" , "solde" : 50 }
		@PostMapping("") 
		public CompteDto postCompteDto(@RequestBody CompteDto compteDto) {
			Compte compte = DtoConverter.compteDtoToCompte(compteDto);//dto-->entity
			serviceCompte.sauvegarderCompte(compte);//avec auto_incr du numero
			compteDto.setNumero(compte.getNumero());
		    return compteDto;
		}
		
		//URL= http://localhost:8080/appliSpringBoot/bank-api/compte
		//appelé en mode PUT avec le corps de la requête HTTP comportant
		// { "numero" : 5 , "label" :  "compteQuiVaEncoreBien" , "solde" : 150 }
		@PutMapping("") 
		public ResponseEntity<?> putCompteDto(@RequestBody CompteDto compteDto) {
			Compte compte = DtoConverter.compteDtoToCompte(compteDto);//dto-->entity
			try {
			Compte compteExistant = serviceCompte.rechercherCompteParNumero(compteDto.getNumero());
			serviceCompte.sauvegarderCompte(compte);
		     return new ResponseEntity<CompteDto>(compteDto, HttpStatus.OK);
			}
			catch(NotFoundException ex) {
				return new ResponseEntity<Message>( 
						new Message("impossible de mettre à jour les données du compte qui n'existe pas avec le numero=" 
								+ compteDto.getNumero()) 
						, HttpStatus.NOT_FOUND);
			}
			catch(Exception ex) {
				return new ResponseEntity<Message>( 
						new Message("une erreur technique a eu lieu, mise à jour pas effectuée" )
						, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		//URL= http://localhost:8080/appliSpringBoot/bank-api/compte/3
		//appelé en mode DELETE
		@DeleteMapping("/{numCompte}")
		public ResponseEntity<Message> deleteCompteByNum(@PathVariable("numCompte") Long numCompte) {
				try {
					serviceCompte.supprimerCompte(numCompte);
					return new ResponseEntity<Message>(new Message("compte bien supprimé"), HttpStatus.OK); //OK:200
					//return new ResponseEntity<Message>(new Message("compte bien supprimé"), HttpStatus.NO_CONTENT); //NO_CONTENT:204
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<Message>(new Message("suppression compte impossible"), HttpStatus.NOT_FOUND);
				}
			
		}

		//URL= http://localhost:8080/appliSpringBoot/bank-api/compte/virement
		//appelé en mode POST avec le corps de la requête HTTP comportant
		// { "montant" : 50.0 , "numCptDeb" :  1 , "numCptCred" : 2 }
		@PostMapping("virement") 
		public VirementDto postVirement(@RequestBody VirementDto virementDto) {
			try {
				serviceCompte.transferer(virementDto.getMontant(), 
						                 Long.parseLong(virementDto.getNumCptDeb()), 
						                 Long.parseLong(virementDto.getNumCptCred()));
				virementDto.setOk(true);
				virementDto.setMessage("virement bien effectué");
			} catch (NumberFormatException e) {
				virementDto.setOk(false);
				virementDto.setMessage("echec virement " + e.getMessage());
				e.printStackTrace();
			}  
			return virementDto;
		}
			
}
