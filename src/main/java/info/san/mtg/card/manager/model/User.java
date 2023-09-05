package info.san.mtg.card.manager.model;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mcmUser")
public class User implements Serializable {

	@Id
	@Column(name = "uuid", nullable = false, length = 36)
	private String uuid = UUID.randomUUID().toString();

	@Column(name = "login", nullable = false, length = 50)
	private String login;

	@Column(name = "name", nullable = false, length = 150)
	private String name;

	@ManyToMany
	@JoinTable(name = "mcmUser_sets", joinColumns = @JoinColumn(name = "mcmUser_uuid"), inverseJoinColumns = @JoinColumn(name = "sets_code"))
	private Set<Sets> sets;

	@OneToMany(mappedBy = "user")
	private Set<UserCard> userCards;

}
