package petadopt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import petadopt.model.Kategorija;
import petadopt.model.Ljubimac;
import petadopt.repository.LjubimacRepository;
import petadopt.service.LjubimacService;

@Service
public class JpaLjubimacService implements LjubimacService{
	
	@Autowired
	private LjubimacRepository ljubimacRepository;

	@Override
	public Page<Ljubimac> findAll(int pageNo) {
		return ljubimacRepository.findAll(PageRequest.of(pageNo, 2));
	}

	@Override
	public Ljubimac findOne(Long id) {
		return ljubimacRepository.findOneById(id);
	}

	@Override
	public Ljubimac save(Ljubimac ljubimac) {
		return ljubimacRepository.save(ljubimac);
	}

	@Override
	public Ljubimac update(Ljubimac ljubimac) {
		return ljubimacRepository.save(ljubimac);
	}

	@Override
	public Ljubimac delete(Long id) {
		Ljubimac ljubimac = findOne(id);
		if(ljubimac != null) {
			Kategorija k = ljubimac.getKategorija();
			k.obrisiLjubimca(ljubimac.getId());
			ljubimacRepository.delete(ljubimac);
			return ljubimac;
		}
		return null;
	}

}
