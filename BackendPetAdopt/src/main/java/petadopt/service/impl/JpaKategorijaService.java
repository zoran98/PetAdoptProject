package petadopt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import petadopt.model.Kategorija;
import petadopt.repository.KategorijaRepository;
import petadopt.service.KategorijaService;

@Service
public class JpaKategorijaService implements KategorijaService{
	
	@Autowired
	private KategorijaRepository kategorijaRepository;

	@Override
	public Kategorija findOne(Long id) {
		return kategorijaRepository.findOneById(id);
	}

	@Override
	public List<Kategorija> findAll() {
		return kategorijaRepository.findAll();
	}

	@Override
	public Page<Kategorija> findAll(int pageNo) {
		return kategorijaRepository.findAll(PageRequest.of(pageNo, 2));
	}

	@Override
	public Kategorija save(Kategorija kategorija) {
		// TODO Auto-generated method stub
		return kategorijaRepository.save(kategorija);
	}

	@Override
	public Kategorija delete(Long id) {
		Kategorija kategorija = findOne(id);
		if(kategorija != null) {
	//		kategorija.getLjubimci().remove(kategorija);
			kategorijaRepository.delete(kategorija);
			return kategorija;
		}
		return null;
	}

}
