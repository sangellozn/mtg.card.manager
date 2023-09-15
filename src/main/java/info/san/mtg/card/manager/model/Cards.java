package info.san.mtg.card.manager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cards")
public class Cards implements Serializable {
	
	@Id
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "borderColor")
	private String borderColor;
	
	@Column(name = "colorIdentity")
	private String colorIdentity;
	
	@Column(name = "colors")
	private String colors;
	
	@Column(name = "finishes")
	private String finishes;
	
	@Column(name = "flavorName")
	private String flavorName;
	
	@Column(name = "flavorText")
	private String flavorText;
	
	@Column(name = "hasFoil")
	private boolean hasFoil;
	
	@Column(name = "hasNonFoil")
	private boolean hasNonFoil;
	
	@Column(name = "language")
	private String language;
	
	@Column(name = "layout")
	private String layout;
	
	@Column(name = "manaCost")
	private String manaCost;
	
	@Column(name = "manaValue")
	private int manaValue;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "originalText")
	private String originalText;
	
	@Column(name = "originalType")
	private String originalType;
	
	@Column(name = "rarity")
	private String rarity;
	
	@Column(name = "text")
	private String text;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "types")
	private String types;
	
	@ManyToOne
	@JoinColumn(name = "setCode")
	private Sets set;
	
	@OneToOne(mappedBy = "card")
	private CardImagery cardImagery;
	
	@OneToOne(mappedBy = "card")
	private CardIdentifiers cardIdentifiers;
	
	@OneToOne(mappedBy = "card")
	private CardPrice cardPrice;
	
}
