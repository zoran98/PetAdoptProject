package petadopt.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import petadopt.model.Udomljavanje;
import petadopt.service.LjubimacService;
import petadopt.service.UdomljavanjeService;
import petadopt.web.dto.UdomljavanjeDTO;

@Component
public class UdomljavanjeDtoToUdomljavanje implements Converter<UdomljavanjeDTO, Udomljavanje>{
	
	@Autowired
	private UdomljavanjeService udomljavanjeService;
	
	@Autowired
	private LjubimacService ljubimacService;

	@Override
	public Udomljavanje convert(UdomljavanjeDTO dto) {
		Udomljavanje u;
		if(dto.getId() == null) {
			u = new Udomljavanje();
		} else {
			u = udomljavanjeService.findOne(dto.getId());
		}
		
		if(u != null) {
			u.setDatumUdomljavanjaLjubimca(dto.getDatumUdomljavanjaLjubimca());
			u.setLjubimac(ljubimacService.findOne(dto.getLjubimacId()));
			
		}
		return u;
	}

}
