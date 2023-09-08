package info.san.mtg.card.manager.rest.dto.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserCardInfoDto {

	@NotEmpty
	private String cardUuid;
	
	@Min(0)
	private int qteEX;
	
	@Min(0)
	private int qteGD;
	
	@Min(0)
	private int qteLP;
	
	@Min(0)
	private int qteM;
	
	@Min(0)
	private int qteNM;
	
	@Min(0)
	private int qtePL;
	
	@Min(0)
	private int qtePoor;
	
	@NotEmpty
	private String type;
	
}
