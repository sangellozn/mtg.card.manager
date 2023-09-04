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
	@Column(name = "uuid", length = 36, nullable = false)
	private String uuid = UUID.randomUUID().toString();
	
	@Column(name = "qte", nullable = false)
	private int qte;
	
	@Column(name = "qteFoil", nullable = false)
	private int qteFoil;
	
	@Column(name = "condition", nullable = false, length = 4)
	private ConditionEnum condition;
	
	@ManyToOne(optional = false)
	private Cards card;
	
	@ManyToOne(optional = false)
	private User user;

}
