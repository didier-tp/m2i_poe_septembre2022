package tp.appliSpring.core.service.std.impl;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tp.appliSpring.converter.MyGenericConverter;
import tp.appliSpring.core.service.std.StdGenericServiceWithDto;

public class AbstractGenericStdServiceWithDto<DTO,ID,E,DAO extends CrudRepository<E, ID>> implements StdGenericServiceWithDto<DTO,ID> {

	private Class<E> entityClass;
	private Class<DTO> dtoClass;
	private DAO dao;
	
	public AbstractGenericStdServiceWithDto(Class<DTO> dtoClass,Class<E> entityClass, DAO dao) {
		this.entityClass=entityClass;
		this.dtoClass=dtoClass;
		this.dao=dao;
	}

	@Override
	public DTO searchById(ID id) {
		DTO dto= null;
		E entity = dao.findById(id).orElse(null);
		if(entity!=null) 
			dto=MyGenericConverter.map(entity, dtoClass);
		return dto;
	}

	@Override
	public List<DTO> searchAll() {
		return MyGenericConverter.map((List<E>)dao.findAll(), dtoClass);
	}

	@Override
	public DTO save(DTO dto) {
		E entity = MyGenericConverter.map(dto, entityClass);
		dao.save(entity);
		return MyGenericConverter.map(entity, dtoClass);
	}

	@Override
	public void deleteById(ID id) {
		dao.deleteById(id);

	}

}
