package info.san.mtg.card.manager.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sets implements Serializable {

	@Id
	@Column(name = "code")
	private String code;

	@Column(name = "baseSetSize")
	private int baseSetSize;
	
	@Column(name = "isFoilOnly")
	private boolean foilOnly;
	
	@Column(name = "languages")
	private String availableInLanguages;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "parentCode")
	private String parentCode;
	
	@Column(name = "releaseDate")
	private String releaseDate;
	
	@Column(name = "totalSetSize")
	private int totalSetSize;
	
	@Column(name = "type")
	private String type;
	
	@OneToMany(mappedBy = "set")
	private Set<Cards> cards;
	
	@ManyToMany(mappedBy = "sets")
	private Set<User> users;
	
}
