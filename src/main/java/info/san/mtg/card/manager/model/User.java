package info.san.mtg.card.manager.model;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mcmUser")
public class User {
	
	@Id
	@Column(name = "uuid")
	private String uuid = UUID.randomUUID().toString();
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "name")
	private String name;

	@ManyToMany
	private Set<Sets> sets;
	
	@OneToMany(mappedBy = "user")
	private Set<UserCard> userCards;
	
}
