package petadopt.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import petadopt.model.Udomljavanje;
import petadopt.web.dto.UdomljavanjeDTO;

@Component
public class UdomljavanjeToUdomljavanjeDto implements Converter<Udomljavanje, UdomljavanjeDTO>{

	@Override
	public UdomljavanjeDTO convert(Udomljavanje u) {
		UdomljavanjeDTO dto = new UdomljavanjeDTO();
		dto.setId(u.getId());
		dto.setDatumUdomljavanjaLjubimca(u.getDatumUdomljavanjaLjubimca());
		dto.setLjubimacId(u.getLjubimac().getId());
		dto.setLjubimacIme(u.getLjubimac().getIme());
		return dto;
	}
	
	public List<UdomljavanjeDTO> convert(List<Udomljavanje> udomljavanja){
		List<UdomljavanjeDTO> dto = new ArrayList<UdomljavanjeDTO>();
		for(Udomljavanje u: udomljavanja) {
			dto.add(convert(u));
		}
		return dto;
		
	}

}
