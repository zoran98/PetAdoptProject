package petadopt.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import petadopt.model.Kategorija;
import petadopt.web.dto.KategorijaDTO;

@Component
public class KategorijaToKategorijaDto implements Converter<Kategorija, KategorijaDTO>{

	@Override
	public KategorijaDTO convert(Kategorija k) {
		KategorijaDTO dto = new KategorijaDTO();
		dto.setId(k.getId());
		dto.setNaziv(k.getNaziv());
		return dto;
	}
	
	public List<KategorijaDTO> convert(List<Kategorija> kategorije){
		List<KategorijaDTO> dto = new ArrayList<KategorijaDTO>();
		
		for(Kategorija k: kategorije) {
			dto.add(convert(k));
		}
		return dto;
		
	}

}
