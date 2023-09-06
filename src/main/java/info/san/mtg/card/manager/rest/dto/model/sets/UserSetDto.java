package info.san.mtg.card.manager.rest.dto.model.sets;

import java.util.ArrayList;
import java.util.Collection;

import info.san.mtg.card.manager.model.ConditionEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSetDto {

	private SetDto set;
	
	private Collection<CardDto> cards = new ArrayList<>();
	
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
		
		@Getter
		@Setter
		public static final class UserCardDto {
			
			private String uuid;
			
			private int qte;
			
			private int qteFoil;
			
			private ConditionEnum condition;
			
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
		
	}
	
	public CardDto getCardByUuid(String uuid) {
		return cards.stream().filter(card -> card.getUuid().equals(uuid)).findFirst().orElseThrow();
	}
	
}
