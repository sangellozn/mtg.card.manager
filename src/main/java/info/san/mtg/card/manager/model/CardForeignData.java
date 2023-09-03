package info.san.mtg.card.manager.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cardForeignData")
public class CardForeignData {

	private String faceName;
	
	private String flavorText;
	
	private String language;
	
	private String name;
	
	private String text;
	
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "uuid")
	private Cards card;
	
}
