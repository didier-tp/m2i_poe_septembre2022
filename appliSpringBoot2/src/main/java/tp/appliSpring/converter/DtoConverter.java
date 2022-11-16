package tp.appliSpring.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import tp.appliSpring.core.entity.Client;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.entity.Operation;
import tp.appliSpring.dto.CompteDto;
import tp.appliSpring.dto.CompteDtoEx;
import tp.appliSpring.dto.Customer;
import tp.appliSpring.dto.OperationDto;

public class DtoConverter {
	
	//clientEntityToCustomerDto
	public static Customer clientToCustomer(Client client) {
		return new Customer(client.getNumero(),
				            client.getPrenom(),
				            client.getNom(),
				            client.getEmail(),
				            client.getAdresse());
	}
	
	public static Client customerToClient(Customer c) {
		return new Client(c.getNumber(),
	            c.getFirstName(),
	            c.getLastName(),
	            c.getEmail(),
	            c.getAddress());
	}
	
	public static CompteDto compteToCompteDto(Compte compte) {
		CompteDto compteDto = new CompteDto();
		/*
		compteDto.setNumero(compte.getNumero());
		compteDto.setLabel(compte.getLabel());
		compteDto.setSolde(compte.getSolde());
		*/
		BeanUtils.copyProperties(compte, compteDto);
		return compteDto;
	}
	
	public static List<Customer> clientListToCustomerList(List<Client> clients){
		return clients.stream()
			   .map((client)->clientToCustomer(client))
			   .collect(Collectors.toList());
	}
	
	public static List<CompteDto> compteListToCompteDtoList(List<Compte> comptes){
		return comptes.stream()
			   .map((compte)->compteToCompteDto(compte))
			   .collect(Collectors.toList());
	}

	public static CompteDtoEx compteToCompteDtoEx(Compte compteEntity) {
		CompteDtoEx compteDtoEx = new CompteDtoEx();
		BeanUtils.copyProperties(compteEntity, compteDtoEx);//recopie de compteEntity
		                                                    //vers compteDtoEx
		                                                    //toutes les parties de mêmes noms (.numero , .label , .solde)
		for(Operation op : compteEntity.getOperations()) {
			OperationDto opDto = new OperationDto(op.getLabel(),op.getMontant(),op.getDateOp().toString());
			compteDtoEx.getOperations().add(opDto);
		}
		return compteDtoEx;
	}

}
