package petadopt.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import petadopt.model.Kategorija;
import petadopt.service.KategorijaService;
import petadopt.web.dto.KategorijaDTO;

@Component
public class KategorijaDtoToKategorija implements Converter<KategorijaDTO, Kategorija>{
	
	@Autowired
	private KategorijaService kategorijaService;

	@Override
	public Kategorija convert(KategorijaDTO kDTO) {
		Kategorija k;
		if(kDTO.getId() == null) {
			k = new Kategorija();
		}else {
			k = kategorijaService.findOne(kDTO.getId());
		}
		
		if(k != null) {
			k.setNaziv(kDTO.getNaziv());
		}
		return k;
	}

}
