package tp.appliSpring.core.service.std;

import java.util.List;

public interface StdGenericServiceWithDto<DTO,ID> {
	DTO searchById(ID id);
	List<DTO> searchAll();
	DTO save(DTO dto);
	void deleteById(ID id);
}
