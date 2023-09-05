package info.san.mtg.card.manager.rest.dto.model.sets;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetDto {
	
	private String code;
	
	private String name;
	
	private String parentCode;
	
	private LocalDate releaseDate;
	
	private Collection<SetDto> setsEnfants = new ArrayList<>();
	
}
