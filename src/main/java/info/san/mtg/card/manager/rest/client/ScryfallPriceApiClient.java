package info.san.mtg.card.manager.rest.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import info.san.mtg.card.manager.rest.client.dto.ScryfallCardDto;

@Component
public class ScryfallPriceApiClient implements IScryfallPriceApiClient {
	
	@Value("${scryfall.api.url}")
	private String scryfallApiUrl;
	
	@Override
	public ScryfallCardDto getCard(String uuid) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(scryfallApiUrl + "/cards/" + uuid, ScryfallCardDto.class);
	}
	
}
