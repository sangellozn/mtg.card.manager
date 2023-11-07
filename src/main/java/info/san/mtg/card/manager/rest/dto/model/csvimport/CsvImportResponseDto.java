package info.san.mtg.card.manager.rest.dto.model.csvimport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import info.san.mtg.card.manager.model.ConditionEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsvImportResponseDto {

	private Collection<CsvImportResponseItemDto> items = new ArrayList<>();
	
	@Getter
	@Setter
	public static final class CsvImportResponseItemDto {
		
		private String uuid = UUID.randomUUID().toString();
		
		private String cardUuid;
		
		private String cardNumber;
		
		private String setCode;
		
		private String setName;
		
		private String cardName;
		
		private ConditionEnum condition;
		
		private boolean foil;
		
		private String cardImagePath;
		
		public boolean isFound() {
			return cardUuid != null;
		}
		
	}
	
}
