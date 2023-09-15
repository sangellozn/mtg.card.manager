package info.san.mtg.card.manager.rest.client.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScryfallCardDto implements Serializable {

	private static final long serialVersionUID = 7415648452037706635L;

	private String id;
	
	private PricesDto prices;
	
	@Getter
	@Setter
	public static final class PricesDto {

		private BigDecimal eur;

		@JsonProperty("eur_foil")
		private BigDecimal eurFoil;
		
		private BigDecimal usd;
		
		@JsonProperty("usd_foil")
		private BigDecimal usdFoil;
		
	}
	
}
