package petadopt.service;

import org.springframework.data.domain.Page;

import petadopt.model.Ljubimac;

public interface LjubimacService {
	
	Page<Ljubimac> findAll(int pageNo);
	Ljubimac findOne(Long id);
	Ljubimac save(Ljubimac ljubimac);
	Ljubimac update(Ljubimac ljubimac);
	Ljubimac delete(Long id);

}
