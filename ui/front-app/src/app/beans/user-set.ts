import { Card } from "./card";
import { Sets } from "./sets";

export class UserSet {

    set: Sets = new Sets;
    cards: Card[];
    cardsUniqueCount: number;
    totalValueEur: number;
    cardsCount: number;
    mostExpensiveCard?: Card;

}
