package info.san.mtg.card.manager.rest.dto.model.imagedetection;

import info.san.mtg.card.manager.model.ConditionEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDetectionRequestDto {
	
	private String setCode;
	
	private ConditionEnum condition;
	
	private String language;

	private String imageData;
	

}
