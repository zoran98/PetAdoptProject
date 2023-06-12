package petadopt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import petadopt.model.Udomljavanje;
import petadopt.repository.UdomljavanjeRepository;
import petadopt.service.UdomljavanjeService;

@Service
public class JpaUdomljavanjeService implements UdomljavanjeService{
	
	@Autowired
	private UdomljavanjeRepository udomljavanjeRepository;

	@Override
	public Page<Udomljavanje> findAll(int pageNo) {
		return udomljavanjeRepository.findAll(PageRequest.of(pageNo, 2));
	}

	@Override
	public Udomljavanje findOne(Long id) {
		return udomljavanjeRepository.findOneById(id);
	}

	@Override
	public Udomljavanje save(Udomljavanje udomljavanje) {
		return udomljavanjeRepository.save(udomljavanje);
	}

	@Override
	public Udomljavanje delete(Long id) {
		Udomljavanje udomljavanje = findOne(id);
		if(udomljavanje != null) {
			udomljavanjeRepository.delete(udomljavanje);
			return udomljavanje;
		}
		return null;
	}

}
