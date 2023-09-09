package info.san.mtg.card.manager.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cardsImagery")
public class CardImagery {

	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid = UUID.randomUUID().toString();
	
	@Column(name = "url_normal")
	private String urlNormal;
	
	@Column(name = "url_small")
	private String urlSmall;
	
	@OneToOne
	private Cards card;
	
}
