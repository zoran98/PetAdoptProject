package petadopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import petadopt.model.Udomljavanje;

@Repository
public interface UdomljavanjeRepository extends JpaRepository<Udomljavanje, Long>{
	
	Udomljavanje findOneById(Long id);

}
