package tp.appliSpring.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.appliSpring.converter.DtoConverter;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.service_with_dto.ServiceCompteWithDto;
import tp.appliSpring.dto.CompteDto;

@RestController //composant spring de type contrôleur pour Web Service REST
@RequestMapping(value="/bank-api/comptev2" , headers="Accept=application/json")
public class CompteRestCtrlV2 {
	
	@Autowired
	private ServiceCompteWithDto serviceCompteWithDto;
	
	//URL= http://localhost:8080/appliSpringBoot/bank-api/comptev2/1
	@GetMapping("/{numCompte}")
	public CompteDto getCompteByNum(@PathVariable("numCompte") Long numCompte) {
		return serviceCompteWithDto.rechercherCompteParNumero(numCompte);
	}
	
	//URL= http://localhost:8080/appliSpringBoot/bank-api/comptev2
	//ou   http://localhost:8080/appliSpringBoot/bank-api/comptev2?numClient=1
	//ou   http://localhost:8080/appliSpringBoot/bank-api/comptev2?soldeMini=50
	@GetMapping("")
	public List<CompteDto> getComptesByCriteria(
			@RequestParam(name="numClient" , required=false) Long numClient,
			@RequestParam(name="soldeMini" , required=false) Double soldeMini){
		List<CompteDto> compteDtoList = new ArrayList<>();
		if(numClient==null && soldeMini == null) {
			compteDtoList = serviceCompteWithDto.rechercherTousComptes();
		}
		else if(numClient != null) {
			compteDtoList = serviceCompteWithDto.rechercherComptesDuClient(numClient);
		}
		else if(soldeMini != null) {
			compteDtoList = serviceCompteWithDto.rechercherComptesViaSoldeMini(soldeMini);
		}
		return compteDtoList;
	}


}
