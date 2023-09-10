package info.san.mtg.card.manager.rest.dto.model.admin;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardImagerySetsUpdateDto {
	
	private Collection<String> sets = new ArrayList<>();

}
