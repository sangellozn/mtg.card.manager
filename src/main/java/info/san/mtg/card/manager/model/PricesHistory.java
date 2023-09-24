package info.san.mtg.card.manager.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pricesHistory")
public class PricesHistory implements Serializable {
	
	private static final long serialVersionUID = 2907074203762803148L;
	
	@EmbeddedId
	private PricesHistoryId pricesHistoryId;

	@Column(name = "val_eur")
	private BigDecimal valEur;
	
	@Column(name = "val_usd")
	private BigDecimal valUsd;

}
