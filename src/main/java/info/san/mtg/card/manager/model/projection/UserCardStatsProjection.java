package info.san.mtg.card.manager.model.projection;

public interface UserCardStatsProjection {
	
	String getCode();
	
	String getSetName();
	
	int getTotalCards();
	
	int getPossessedCards();

}
