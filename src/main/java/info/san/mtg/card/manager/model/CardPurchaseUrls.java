package info.san.mtg.card.manager.model;

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
@Table(name = "cardPurchaseUrls")
public class CardPurchaseUrls {
	
	@Id
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "cardmarket")
	private String cardmarket;
	
	@OneToOne
	@JoinColumn(name = "uuid")
	private Cards card;

}
