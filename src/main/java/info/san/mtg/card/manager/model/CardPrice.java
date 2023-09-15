package info.san.mtg.card.manager.model;

import java.math.BigDecimal;
import java.time.Instant;
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
@Table(name = "cardsPrices")
public class CardPrice {

	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid = UUID.randomUUID().toString();
	
	@Column(name = "val_eur")
	private BigDecimal valEur;

	@Column(name = "val_eur_foil")
	private BigDecimal valEurFoil;
	
	@Column(name = "val_usd")
	private BigDecimal valUsd;

	@Column(name = "val_usd_foil")
	private BigDecimal valUsdFoil;
	
	@Column(name = "last_updated")
	private Instant lastUpdated;
	
	@OneToOne
	private Cards card;
	
}
