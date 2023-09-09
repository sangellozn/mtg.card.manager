package info.san.mtg.card.manager.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cardIdentifiers")
public class CardIdentifiers {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid = UUID.randomUUID().toString();
	
	@Column(name = "scryfallId")
	private String scryfallId;
	
	@OneToOne
	@JoinColumn(name = "uuid")
	private Cards card;

}
