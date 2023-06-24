package petadopt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import petadopt.model.Ljubimac;

@Repository
public interface LjubimacRepository extends JpaRepository<Ljubimac, Long>{
	
	Ljubimac findOneById(Long id);
	
	@Query("SELECT lj FROM Ljubimac lj WHERE" +
			"(:kategorijaId = NULL OR lj.kategorija.id = :kategorijaId) AND " + 
			"(:pol = NULL OR lj.pol LIKE :pol) AND " +
			"(:opis = NULL OR lj.opis LIKE :opis)")
	Page<Ljubimac> search(@Param("kategorijaId") Long kategorijaId, @Param("pol") String pol, @Param("opis") String opis, Pageable pageable);

}
