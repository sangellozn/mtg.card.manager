package info.san.mtg.card.manager.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mcmUserCard")
public class UserCard implements Serializable {
	
	@Id
	@Column(name = "uuid", length = 36, nullable = false)
	private String uuid = UUID.randomUUID().toString();
	
	@Column(name = "qte", nullable = false)
	private int qte;
	
	@Column(name = "cond", nullable = false, length = 10)
	private String condition;
	
	@Column(name = "card_type", nullable = false, length = 25)
	private String type;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "cards_uuid")
	private Cards card;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "mcmUser_uuid")
	private User user;

}
