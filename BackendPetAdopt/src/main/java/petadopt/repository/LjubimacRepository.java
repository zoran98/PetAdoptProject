package petadopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import petadopt.model.Ljubimac;

@Repository
public interface LjubimacRepository extends JpaRepository<Ljubimac, Long>{
	
	Ljubimac findOneById(Long id);

}
