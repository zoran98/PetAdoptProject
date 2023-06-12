package petadopt.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "kategorije")
public class Kategorija {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String naziv;
	
	@OneToMany(mappedBy = "kategorija", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Ljubimac> ljubimci = new ArrayList<Ljubimac>();

	public Kategorija() {
		super();
	}

	public Kategorija(Long id, String naziv, List<Ljubimac> ljubimci) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.ljubimci = ljubimci;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Ljubimac> getLjubimci() {
		return ljubimci;
	}

	public void setLjubimci(List<Ljubimac> ljubimci) {
		this.ljubimci = ljubimci;
	}
	
	public void addLjubimca(Ljubimac ljubimac) {
		this.ljubimci.add(ljubimac);
	}
	
	public void obrisiLjubimca(Long id) {
		for(Ljubimac lj : this.ljubimci) {
			if(lj.getId() == id) {
				this.ljubimci.remove(lj);
				return;
			}
		}
	}

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
		Kategorija other = (Kategorija) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Kategorija [id=" + id + ", naziv=" + naziv + ", ljubimci=" + ljubimci + "]";
	}
	
	

}
