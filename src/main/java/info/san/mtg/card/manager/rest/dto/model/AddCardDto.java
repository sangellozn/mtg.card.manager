package info.san.mtg.card.manager.rest.dto.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import info.san.mtg.card.manager.model.ConditionEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCardDto {

	@Min(0)
	private int qte;
	
	@Min(0)
	private int qteFoil;
	
	@NotNull
	private ConditionEnum condition;
	
	@NotEmpty
	private String cardUuid;
	
}
