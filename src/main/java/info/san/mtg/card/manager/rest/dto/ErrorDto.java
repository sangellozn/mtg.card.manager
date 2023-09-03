package info.san.mtg.card.manager.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {
    
    private int code;
    
    private String message;
    
}
