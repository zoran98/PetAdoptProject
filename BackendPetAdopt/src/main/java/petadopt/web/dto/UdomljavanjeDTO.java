package petadopt.web.dto;

import java.time.LocalDateTime;

public class UdomljavanjeDTO {
	
	private Long id;
	private LocalDateTime datumUdomljavanjaLjubimca;
	
	private Long ljubimacId;
	private String ljubimacIme;
	
	public UdomljavanjeDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDatumUdomljavanjaLjubimca() {
		return datumUdomljavanjaLjubimca;
	}

	public void setDatumUdomljavanjaLjubimca(LocalDateTime datumUdomljavanjaLjubimca) {
		this.datumUdomljavanjaLjubimca = datumUdomljavanjaLjubimca;
	}

	public Long getLjubimacId() {
		return ljubimacId;
	}

	public void setLjubimacId(Long ljubimacId) {
		this.ljubimacId = ljubimacId;
	}

	public String getLjubimacIme() {
		return ljubimacIme;
	}

	public void setLjubimacIme(String ljubimacIme) {
		this.ljubimacIme = ljubimacIme;
	}
	
	

}
