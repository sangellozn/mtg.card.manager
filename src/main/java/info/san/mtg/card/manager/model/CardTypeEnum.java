package info.san.mtg.card.manager.model;

public enum CardTypeEnum {
	
	NORMAL("Normal"),
	
	FOIL("Foil (Premium)"),
	
	FOIL_ETCHED("Foil-Etched (Super premium)");
	
	private String label;
	
	private CardTypeEnum(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

}
