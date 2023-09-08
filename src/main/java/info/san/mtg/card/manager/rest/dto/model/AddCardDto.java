package info.san.mtg.card.manager.rest.dto.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import info.san.mtg.card.manager.model.CardTypeEnum;
import info.san.mtg.card.manager.model.ConditionEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCardDto {

	@Min(0)
	private int qte;
	
	@NotNull
	private ConditionEnum condition;
	
	@NotNull
	private CardTypeEnum type;
	
	@NotEmpty
	private String cardUuid;
	
}
