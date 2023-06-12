package petadopt.service;

import java.util.List;

import org.springframework.data.domain.Page;

import petadopt.model.Kategorija;

public interface KategorijaService {
	
	Kategorija findOne(Long id);
	List<Kategorija> findAll();
	Page<Kategorija> findAll(int pageNo);
	Kategorija save(Kategorija kategorija);
	Kategorija delete(Long id);

}
