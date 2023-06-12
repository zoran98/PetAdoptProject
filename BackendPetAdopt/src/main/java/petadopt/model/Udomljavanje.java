package petadopt.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "udomljavanja")
public class Udomljavanje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDateTime datumUdomljavanjaLjubimca;
	
	@OneToOne
	@JoinColumn
	private Ljubimac ljubimac;
	
	public Udomljavanje() {
		super();
	}

	public Udomljavanje(Long id, LocalDateTime datumUdomljavanjaLjubimca, Ljubimac ljubimac) {
		super();
		this.id = id;
		this.datumUdomljavanjaLjubimca = datumUdomljavanjaLjubimca;
		this.ljubimac = ljubimac;
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

	public Ljubimac getLjubimac() {
		return ljubimac;
	}

	public void setLjubimac(Ljubimac ljubimac) {
		this.ljubimac = ljubimac;
	}
	
//	public void addLjubimca(Ljubimac ljubimac) {
//		this.ljubimac
//	}
	
//	public void removeLjubimac(Long id) {
//		if(this.ljubimac.getId() == id) {
//			
//		}
//	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Udomljavanje other = (Udomljavanje) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Udomljavanje [id=" + id + ", datumUdomljavanjeLjubimca=" + datumUdomljavanjaLjubimca + ", ljubimac="
				+ ljubimac.getIme() + "]";
	}
	
	

}
