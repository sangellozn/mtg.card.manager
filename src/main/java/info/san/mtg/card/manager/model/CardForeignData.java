package info.san.mtg.card.manager.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cardForeignData")
public class CardForeignData implements Serializable {
	
	@EmbeddedId
	private CardForeignDataId id;

	private String faceName;
	
	private String flavorText;
	
	private String name;
	
	private String text;
	
	private String type;
	
}
