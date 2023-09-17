package info.san.mtg.card.manager.rest.dto.model.sets;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import info.san.mtg.card.manager.model.CardTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSetDto {

	private SetDto set;
	
	private List<CardDto> cards = new ArrayList<>();
	
	public void fillCardPossession() {
		cards.forEach(CardDto::initCardPossession);
	}
	
	@Getter
	@Setter
	public static final class CardDto {
		
		private String uuid;
		
		private String borderColor;
		
		private String colorIdentity;
		
		private String colors;
		
		private String finishes;
		
		private String flavorName;
		
		private String flavorText;
		
		private boolean hasFoil;
		
		private boolean hasNonFoil;
		
		private String language;
		
		private String layout;
		
		private String manaCost;
		
		private int manaValue;
		
		private String name;
		
		private String number;
		
		private String originalText;
		
		private String originalType;
		
		private String rarity;
		
		private String text;
		
		private String type;
		
		private String types;
		
		private Collection<UserCardDto> possessions = new ArrayList<>();
		
		private CardForeignDataDto cardForeignData;
		
		private CardImageryDto cardImagery;
		
		private CardPriceDto cardPrice;
		
		private CardPurchaseUrlsDto cardPurchaseUrls;
		
		public void initCardPossession() {
			this.possessions.add(new UserCardDto(this.uuid, CardTypeEnum.NORMAL));
			this.possessions.add(new UserCardDto(this.uuid, CardTypeEnum.FOIL));
			this.possessions.add(new UserCardDto(this.uuid, CardTypeEnum.FOIL_ETCHED));
		}
		
		@Getter
		@Setter
		public static final class CardPurchaseUrlsDto {
			
			private String cardmarket;
			
		}
		
		@Getter
		@Setter
		public static final class CardPriceDto {
			
			private BigDecimal valEur;

			private BigDecimal valEurFoil;
			
			private BigDecimal valUsd;

			private BigDecimal valUsdFoil;
			
			private Instant lastUpdated;
			
		}
		
		@Getter
		@Setter
		public static final class CardImageryDto {
			
			private String urlNormal;
			
			private String urlSmall;
			
		}
		
		@Getter
		@Setter
		public static final class UserCardDto {
			
			public UserCardDto(String cardUuid, CardTypeEnum type) {
				this.cardUuid = cardUuid;
				this.type = type.name();
				this.typeLabel = type.getLabel();
			}
			
			private String cardUuid;
			
			private String typeLabel;
			
			private String type;
			
			private int qteM;
			
			private int qteNM;
			
			private int qteEX;
			
			private int qteGD;
			
			private int qteLP;
			
			private int qtePL;
			
			private int qtePoor;
			
		}
		
		@Getter
		@Setter
		public static final class CardForeignDataDto {
			
			private String faceName;
			
			private String flavorText;
			
			private String name;
			
			private String text;
			
			private String type;
			
		}
		
		public UserCardDto getUserCardByType(CardTypeEnum type) {
			return possessions.stream().filter(p -> p.getType().equals(type.name())).findFirst().orElseThrow();
		}
		
	}
	
	public CardDto getCardByUuid(String uuid) {
		return cards.stream().filter(card -> card.getUuid().equals(uuid)).findFirst().orElseThrow();
	}
	
}
