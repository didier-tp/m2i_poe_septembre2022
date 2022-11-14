package tp.appliSpring.web.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.appliSpring.converter.DtoConverter;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.exception.NotFoundException;
import tp.appliSpring.core.service.ServiceCompte;
import tp.appliSpring.dto.CompteDto;
import tp.appliSpring.dto.CompteDtoEx;

@RestController //composant spring de type contrôleur pour Web Service REST
@RequestMapping(value="/bank-api/compte" , headers="Accept=application/json")
public class CompteRestCtrl {
	
	@Autowired
	private ServiceCompte serviceCompte;
	
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


}
