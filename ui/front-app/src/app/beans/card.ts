import { CardForeignData } from "./card-foreign-data";
import { CardImagery } from "./card-imagery";
import { CardPrices } from "./card-prices";
import { CardUrls } from "./card-urls";
import { UserCard } from "./user-card";

export class Card {

    uuid: string;
    borderColor: string;
    colorIdentity: string;
    colors: string;
    finishes: string;
    flavorName: string;
    flavorText: string = '';
    hasFoil: boolean;
    hasNonFoil: boolean;
    language: string;
    layout: string;
    manaCost: string;
    manaValue: number;
    name: string;
    number: string;
    originalText: string;
    originalType: string;
    rarity: string;
    text: string = '';
    type: string = '';
    types: string = '';
    possessions : UserCard[];
    cardForeignData: CardForeignData = new CardForeignData;
    cardImagery: CardImagery = new CardImagery;
    cardPrice: CardPrices = new CardPrices;
    cardPurchaseUrls: CardUrls = new CardUrls;

}
