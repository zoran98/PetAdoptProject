package petadopt.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import petadopt.model.Kategorija;
import petadopt.service.KategorijaService;
import petadopt.support.KategorijaDtoToKategorija;
import petadopt.support.KategorijaToKategorijaDto;
import petadopt.web.dto.KategorijaDTO;

@RestController
@RequestMapping(value = "/api/kategorije", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class KategorijaController {
	
	@Autowired
	private KategorijaService kategorijaService;
	
	@Autowired
	private KategorijaToKategorijaDto toKategorijaDto;
	
	@Autowired
	private KategorijaDtoToKategorija toKategorija;
	
	//@PreAuthorize("hasRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<KategorijaDTO>> getAll(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo){
		Page<Kategorija> page = kategorijaService.findAll(pageNo);
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

        return new ResponseEntity<>(toKategorijaDto.convert(page.getContent()),headers, HttpStatus.OK);
	}
	
	//@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
		@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<KategorijaDTO> create(@Valid @RequestBody KategorijaDTO kategorijaDTO){
	        Kategorija kategorija = toKategorija.convert(kategorijaDTO);
	       Kategorija sacuvanaKategorija = kategorijaService.save(kategorija);

	        return new ResponseEntity<>(toKategorijaDto.convert(sacuvanaKategorija), HttpStatus.CREATED);
	    }
		
		@PreAuthorize("hasRole('ROLE_ADMIN')")
		@DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id){
	        Kategorija obrisanaKategorija = kategorijaService.delete(id);

	        if(obrisanaKategorija != null) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

}
