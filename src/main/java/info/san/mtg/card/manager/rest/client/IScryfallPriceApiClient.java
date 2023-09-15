package info.san.mtg.card.manager.rest.client;

import info.san.mtg.card.manager.rest.client.dto.ScryfallCardDto;

public interface IScryfallPriceApiClient {
	
	ScryfallCardDto getCard(String uuid);

}
