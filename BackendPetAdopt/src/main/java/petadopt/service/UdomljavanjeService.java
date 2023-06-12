package petadopt.service;

import org.springframework.data.domain.Page;

import petadopt.model.Udomljavanje;

public interface UdomljavanjeService {
	
	Page<Udomljavanje> findAll(int pageNo);
	Udomljavanje findOne(Long id);
	Udomljavanje save(Udomljavanje udomljavanje);
	Udomljavanje delete(Long id);

}
