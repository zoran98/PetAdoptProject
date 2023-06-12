package petadopt.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import petadopt.model.Ljubimac;
import petadopt.web.dto.LjubimacDTO;

@Component
public class LjubimacToLjubimacDto implements Converter<Ljubimac, LjubimacDTO>{

	@Override
	public LjubimacDTO convert(Ljubimac lj) {
		LjubimacDTO dto = new LjubimacDTO();
		dto.setId(lj.getId());
		dto.setIme(lj.getIme());
		dto.setStarost(lj.getStarost());
		dto.setVakcinisan(lj.getVakcinisan());
		dto.setPol(lj.getPol());
		dto.setTezina(lj.getTezina());
		dto.setOpis(lj.getOpis());
		dto.setKategorijaId(lj.getKategorija().getId());
		dto.setKategorijaNaziv(lj.getKategorija().getNaziv());
		return dto;
	}
	
	public List<LjubimacDTO> convert(List<Ljubimac> ljubimci){
		List<LjubimacDTO> dto = new ArrayList<LjubimacDTO>();
		
		for(Ljubimac lj: ljubimci) {
			dto.add(convert(lj));
		}
		return dto;
		
	}

}
