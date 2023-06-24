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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import petadopt.model.Ljubimac;
import petadopt.service.LjubimacService;
import petadopt.support.LjubimacDtoToLjubimac;
import petadopt.support.LjubimacToLjubimacDto;
import petadopt.web.dto.LjubimacDTO;

@RestController
@RequestMapping(value = "/api/ljubimci", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class LjubimacController {
	
	@Autowired
	private LjubimacService ljubimacService;
	
	@Autowired
	private LjubimacToLjubimacDto toLjubimacDto;
	
	@Autowired
	private LjubimacDtoToLjubimac toLjubimac;
	
	
	//@PreAuthorize("hasRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
		@GetMapping
	    public ResponseEntity<List<LjubimacDTO>> getAll(@RequestParam(value = "kategorijaId", required = false) Long kategorijaId,
				@RequestParam(value = "pol", required = false) String pol,
				@RequestParam(value = "opis", required = false) String opis,
	    		@RequestParam(value = "pageNo", defaultValue = "0") int pageNo){
			

	//		Page<Ljubimac> page = ljubimacService.findAll(pageNo);
				Page<Ljubimac> page = null;
	        if(kategorijaId != null || pol != null || opis != null) {
	        	page = ljubimacService.search(kategorijaId, pol, opis, pageNo);
	        } else {
	        	page = ljubimacService.findAll(pageNo);
	        }

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

	        return new ResponseEntity<>(toLjubimacDto.convert(page.getContent()),headers, HttpStatus.OK);
	    }
		
		//@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
		@GetMapping("/{id}")
	    public ResponseEntity<LjubimacDTO> getOne(@PathVariable Long id){
	        Ljubimac ljubimac = ljubimacService.findOne(id);

	        if(ljubimac != null) {
	            return new ResponseEntity<>(toLjubimacDto.convert(ljubimac), HttpStatus.OK);
	        }else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
		
		//@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
		@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<LjubimacDTO> create(@Valid @RequestBody LjubimacDTO ljubimacDTO){
	        Ljubimac ljubimac = toLjubimac.convert(ljubimacDTO);
	      //  lj.setBrojDostupnihFlasa(0);
	       Ljubimac sacuvanLjubimac = ljubimacService.save(ljubimac);

	        return new ResponseEntity<>(toLjubimacDto.convert(sacuvanLjubimac), HttpStatus.CREATED);
	    }
		
		//@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
		@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<LjubimacDTO> update(@PathVariable Long id, @Valid @RequestBody LjubimacDTO ljubimacDTO){

	        if(!id.equals(ljubimacDTO.getId())) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Ljubimac ljubimac = toLjubimac.convert(ljubimacDTO);
	        Ljubimac sacuvanLjubimac = ljubimacService.update(ljubimac);

	        return new ResponseEntity<>(toLjubimacDto.convert(sacuvanLjubimac),HttpStatus.OK);
	    }
		
		//@PreAuthorize("hasRole('ROLE_ADMIN')")
		@DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id){
	        Ljubimac obrisanLjubimac = ljubimacService.delete(id);

	        if(obrisanLjubimac != null) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

}
