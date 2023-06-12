package petadopt.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ljubimci")
public class Ljubimac {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String ime;
	
	@Column
	private Integer starost;
	
	@Column(nullable = false)
	private Boolean vakcinisan;
	
	@Column(nullable = false)
	private String pol;
	
	@Column(nullable = false)
	private Double tezina;
	
	@Column(nullable = false)
	private String opis;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Kategorija kategorija;
	
	@OneToOne(mappedBy = "ljubimac")
	private Udomljavanje udomljavanje;

	public Ljubimac() {
		super();
	}

	public Ljubimac(Long id, String ime, Integer starost, Boolean vakcinisan, String pol, Double tezina, String opis,
			Kategorija kategorija, Udomljavanje udomljavanje) {
		super();
		this.id = id;
		this.ime = ime;
		this.starost = starost;
		this.vakcinisan = vakcinisan;
		this.pol = pol;
		this.tezina = tezina;
		this.opis = opis;
		this.kategorija = kategorija;
		this.udomljavanje = udomljavanje;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Integer getStarost() {
		return starost;
	}

	public void setStarost(Integer starost) {
		this.starost = starost;
	}

	public Boolean getVakcinisan() {
		return vakcinisan;
	}

	public void setVakcinisan(Boolean vakcinisan) {
		this.vakcinisan = vakcinisan;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public Double getTezina() {
		return tezina;
	}

	public void setTezina(Double tezina) {
		this.tezina = tezina;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public Udomljavanje getUdomljavanje() {
		return udomljavanje;
	}

	public void setUdomljavanje(Udomljavanje udomljavanje) {
		this.udomljavanje = udomljavanje;
	}
	
	
//	public void addUdomljavanje(Udomljavanje udomljavanje) {
//		
//	}
	
//	public void removeUdomljavanje(Long id) {
//		if (this.udomljavanje.getId() == id) {
//			this.udomljavanje.removeLjubimac(id);
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
		Ljubimac other = (Ljubimac) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Ljubimac [id=" + id + ", ime=" + ime + ", starost=" + starost + ", vakcinisan=" + vakcinisan + ", pol="
				+ pol + ", tezina=" + tezina + ", opis=" + opis + ", kategorija=" + kategorija.getNaziv() + ", udomljavanje="
				+ udomljavanje + "]";
	}

	
	
	

}
