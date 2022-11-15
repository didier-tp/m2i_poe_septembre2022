package tp.appliSpring.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

public class MyGenericConverter {
	

	public static <S,D> D map(S source,Class<D> destinationClass) {
		D destination = null;
		try {
			destination = destinationClass.getDeclaredConstructor().newInstance();
			BeanUtils.copyProperties(source, destination);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return destination;
	}
	
	
	public static <S,D> List<D> map(List<S> sourceList , Class<D> destinationClass){
		return  sourceList.stream()
			   .map((source)->map(source,destinationClass))
			   .collect(Collectors.toList());
	}

}
