package info.san.mtg.card.manager.model.projection;

import java.math.BigDecimal;

public interface UserCardStatsProjection {
	
	String getCode();
	
	String getSetName();
	
	int getTotalCards();
	
	int getPossessedCards();
	
	BigDecimal getEstimatedValue();

}
