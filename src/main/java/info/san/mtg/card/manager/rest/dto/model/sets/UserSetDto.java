package info.san.mtg.card.manager.rest.dto.model.sets;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
			
			public int getTotalQty() {
				return qteEX + qteGD + qteLP + qteM + qteNM + qtePL + qtePoor;
			}
			
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
	
	public int getCardsCount() {
		return this.cards.stream().flatMap(cardDto -> cardDto.getPossessions().stream())
				.mapToInt(cardPossession -> cardPossession.getTotalQty())
				.sum();
	}
	
	public int getCardsUniqueCount() {
		return this.cards.stream().flatMap(cardDto -> cardDto.getPossessions().stream())
				.mapToInt(cardPossession -> {
					if (cardPossession.getTotalQty() >= 1) {
						return 1;
					}
					
					return 0;
				})
				.sum();
	}
	
	public BigDecimal getTotalValueEur() {
		return this.cards.stream().map(card -> {
			if (card.getCardPrice() != null) {
				return card.getPossessions().stream()
						.map(possession -> {
							if (possession.getType().equals(CardTypeEnum.NORMAL.name())) {
								BigDecimal valEur = card.getCardPrice().getValEur();
								if (valEur != null) {
									return valEur.multiply(BigDecimal.valueOf(possession.getTotalQty()));
								}
								
								return BigDecimal.ZERO;
							}
							
							BigDecimal valEurFoil = card.getCardPrice().getValEurFoil();
							if (valEurFoil != null) {
								return valEurFoil.multiply(BigDecimal.valueOf(possession.getTotalQty()));
							}
							
							return BigDecimal.ZERO;
							
						}).reduce(BigDecimal.ZERO, BigDecimal::add);
			}
			
			return BigDecimal.ZERO;
		}).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
	}
	
	public CardDto getMostExpensiveCard() {
		Comparator<? super CardDto> comparator = (left, right) -> {
			BigDecimal leftPrice, rightPrice;
			boolean leftFoil = left.getPossessions().stream().anyMatch(possession -> possession.getTotalQty() > 0 && !possession.getType().equals(CardTypeEnum.NORMAL.name()));
			boolean rightFoil = right.getPossessions().stream().anyMatch(possession -> possession.getTotalQty() > 0 && !possession.getType().equals(CardTypeEnum.NORMAL.name()));
			
			if (leftFoil && rightFoil) {
				leftPrice = Optional.ofNullable(left.getCardPrice().getValEurFoil()).orElse(left.getCardPrice().getValEur());
				rightPrice = Optional.ofNullable(right.getCardPrice().getValEurFoil()).orElse(right.getCardPrice().getValEur());
			} else if (leftFoil && !rightFoil) {
				leftPrice = Optional.ofNullable(left.getCardPrice().getValEurFoil()).orElse(left.getCardPrice().getValEur());
				rightPrice = Optional.ofNullable(right.getCardPrice().getValEur()).orElse(right.getCardPrice().getValEurFoil());
			} else if (!leftFoil && rightFoil) {
				leftPrice = Optional.ofNullable(left.getCardPrice().getValEur()).orElse(left.getCardPrice().getValEurFoil());
				rightPrice = Optional.ofNullable(right.getCardPrice().getValEurFoil()).orElse(right.getCardPrice().getValEur());
			} else {
				leftPrice = Optional.ofNullable(left.getCardPrice().getValEur()).orElse(left.getCardPrice().getValEurFoil());
				rightPrice = Optional.ofNullable(right.getCardPrice().getValEur()).orElse(right.getCardPrice().getValEurFoil());
			}
			
			return leftPrice.compareTo(rightPrice);
			
		};
		return this.cards.stream()
				.filter(card -> card.getCardPrice() != null 
						&& (card.getCardPrice().getValEur() != null || card.getCardPrice().getValEurFoil() != null)
						&& card.getPossessions().stream().anyMatch(possession -> possession.getTotalQty() > 0))
				.sorted(comparator.reversed())
				.findFirst().orElse(null);
	}
	
}
