package tp.appliSpring.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import tp.appliSpring.core.entity.Client;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.dto.CompteDto;
import tp.appliSpring.dto.Customer;

public class DtoConverterV2 {
	
	
	
	public static <E,DTO> DTO entityToDto(E entity,Class<DTO> dtoClass) {
		DTO dto = null;
		if(dtoClass.getSimpleName()=="CompteDto") {
			dto = (DTO) new CompteDto();
		}
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}
	
	
	/*
	public static <E,DTO> List<DTO> compteListToCompteDtoList(List<E> entityList){
		return (List<DTO>) entityList.stream()
			   .map((entity)->entityToDto(entity))
			   .collect(Collectors.toList());
	}*/

}
