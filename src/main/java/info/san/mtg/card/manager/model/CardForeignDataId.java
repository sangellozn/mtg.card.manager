package info.san.mtg.card.manager.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class CardForeignDataId implements Serializable {

	private String uuid;
	
	private String language;

}
