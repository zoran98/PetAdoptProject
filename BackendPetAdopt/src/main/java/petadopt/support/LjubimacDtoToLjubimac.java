package petadopt.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import petadopt.model.Ljubimac;
import petadopt.service.KategorijaService;
import petadopt.service.LjubimacService;
import petadopt.web.dto.LjubimacDTO;

@Component
public class LjubimacDtoToLjubimac implements Converter<LjubimacDTO, Ljubimac>{
	
	@Autowired
	private LjubimacService ljubimacService;
	
	@Autowired
	private KategorijaService kategorijaService;

	@Override
	public Ljubimac convert(LjubimacDTO dto) {
		Ljubimac lj;
		if(dto.getId() == null) {
			lj = new Ljubimac();
		} else {
			lj = ljubimacService.findOne(dto.getId());
		}
		
		if(lj != null) {
			lj.setIme(dto.getIme());
			lj.setStarost(dto.getStarost());
			lj.setVakcinisan(dto.getVakcinisan());
			lj.setPol(dto.getPol());
			lj.setTezina(dto.getTezina());
			lj.setOpis(dto.getOpis());
			lj.setKategorija(kategorijaService.findOne(dto.getKategorijaId()));
		}
		return lj;
	}

}
