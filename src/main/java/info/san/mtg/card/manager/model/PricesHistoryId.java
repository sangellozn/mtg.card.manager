package info.san.mtg.card.manager.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PricesHistoryId implements Serializable {

	private static final long serialVersionUID = 1484305705031696591L;
	
	@Column(name = "setCode")
	private String setCode;
	
	@Column(name = "date_history")
	private LocalDate dateHistory;

}
