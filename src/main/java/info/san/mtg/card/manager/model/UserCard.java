package info.san.mtg.card.manager.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mcmUserCard")
public class UserCard {
	
	@Id
	@Column(name = "uuid")
	private String uuid = UUID.randomUUID().toString();
	
	@Column(name = "qte")
	private int qte;
	
	@Column(name = "qteFoil")
	private int qteFoil;
	
	@Column(name = "condition")
	private ConditionEnum condition;
	
	@ManyToOne
	private Cards card;
	
	@ManyToOne
	private User user;

}
