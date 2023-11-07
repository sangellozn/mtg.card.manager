package info.san.mtg.card.manager.model;

public enum ConditionEnum {
	
	UNKNOWN,
	
	POOR,
	
	PL,
	
	LP,
	
	GD,
	
	EX,
	
	NM,
	
	M;
	
	public static ConditionEnum getEquivalent(String condition) {
		if ("Near Mint".equals(condition)) {
			return NM;
		}
		
		if ("Slightly Played".equals(condition)) {
			return LP;
		}
		
		if ("Moderately Played".equals(condition)) {
			return PL;
		}
		
		if ("Heavily Played".equals(condition)) {
			return POOR;
		}
		
		return UNKNOWN;
	}

}
