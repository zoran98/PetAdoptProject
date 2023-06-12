package petadopt.web.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import petadopt.model.Udomljavanje;
import petadopt.service.UdomljavanjeService;
import petadopt.support.UdomljavanjeDtoToUdomljavanje;
import petadopt.support.UdomljavanjeToUdomljavanjeDto;
import petadopt.web.dto.UdomljavanjeDTO;

@RestController
@RequestMapping(value = "/api/udomljavanja", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class UdomljavanjeController {
	
	@Autowired
	private UdomljavanjeService udomljavanjeService;
	
	@Autowired
	private UdomljavanjeToUdomljavanjeDto toUdomljavanjeDto;
	
	@Autowired
	private UdomljavanjeDtoToUdomljavanje toUdomljavanje;
	
	//@PreAuthorize("hasRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
			@GetMapping
		    public ResponseEntity<List<UdomljavanjeDTO>> getAll(
		    		@RequestParam(value = "pageNo", defaultValue = "0") int pageNo){
				

				Page<Udomljavanje> page = udomljavanjeService.findAll(pageNo);
//					Page<Ljubimac> page = null;
//		        if(kategorijaId != null || polLjubimca != null || opisLjubimca != null) {
//		        	page = ljubimacService.search(kategorijaId, polLjubimca, opisLjubimca, pageNo);
//		        } else {
//		        	page = ljubimacService.findAll(pageNo);
//		        }

		        HttpHeaders headers = new HttpHeaders();
		        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

		        return new ResponseEntity<>(toUdomljavanjeDto.convert(page.getContent()),headers, HttpStatus.OK);
		    }
			

			//@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
			@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
		    public ResponseEntity<UdomljavanjeDTO> create(@Valid @RequestBody UdomljavanjeDTO udomljavanjeDTO){
		        Udomljavanje udomljavanje = toUdomljavanje.convert(udomljavanjeDTO);
		      
		       Udomljavanje sacuvanoUdomljavanje = udomljavanjeService.save(udomljavanje);

		        return new ResponseEntity<>(toUdomljavanjeDto.convert(sacuvanoUdomljavanje), HttpStatus.CREATED);
		    }
			
			//@PreAuthorize("hasRole('ROLE_ADMIN')")
			@DeleteMapping("/{id}")
		    public ResponseEntity<Void> delete(@PathVariable Long id){
		        Udomljavanje obrisanoUdomljavanje = udomljavanjeService.delete(id);

		        if(obrisanoUdomljavanje != null) {
		            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		        } else {
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		    }

}
