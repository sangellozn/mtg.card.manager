package info.san.mtg.card.manager.schedule;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import info.san.mtg.card.manager.model.CardPrice;
import info.san.mtg.card.manager.model.Cards;
import info.san.mtg.card.manager.repository.CardPriceRepository;
import info.san.mtg.card.manager.repository.CardsRepository;
import info.san.mtg.card.manager.rest.client.IScryfallPriceApiClient;
import info.san.mtg.card.manager.rest.client.dto.ScryfallCardDto;

@Component
public class UpdateCardPriceSchedule implements IUpdateCardPriceSchedule {
	
	private final IScryfallPriceApiClient scryfallPriceApiClient;

	private final CardPriceRepository cardPriceRepository;
	
	private final CardsRepository cardsRepository;
	
	public UpdateCardPriceSchedule(IScryfallPriceApiClient scryfallPriceApiClient, 
			CardPriceRepository cardPriceRepository, CardsRepository cardsRepository) {
		this.scryfallPriceApiClient = scryfallPriceApiClient;
		this.cardPriceRepository = cardPriceRepository;
		this.cardsRepository = cardsRepository;
	}

	@Override
	@Transactional
	@Scheduled(fixedDelay = 6 *  60 * 60 * 1000)
	public void updatePrices() throws InterruptedException {
		System.out.println("Mise à jour des prix des cartes...");
		
		Collection<Cards> cardsToUpdate = cardsRepository.findAllForPriceUpdate(Instant.now().minus(2, ChronoUnit.DAYS));
		
		System.out.println(cardsToUpdate.size() + " carte(s) à mettre à jour.");
		
		for (Cards card : cardsToUpdate) {
			CardPrice cardPrice = card.getCardPrice();
			if (cardPrice == null) {
				cardPrice = new CardPrice();
				cardPrice.setCard(card);
				cardPrice.setLastUpdated(Instant.now());
				
				cardPriceRepository.save(cardPrice);
			} else {
				cardPrice.setLastUpdated(Instant.now());
			}
			
			ScryfallCardDto cardPriceFromScryfall = scryfallPriceApiClient.getCard(card.getCardIdentifiers().getScryfallId());
			
			cardPrice.setValEur(cardPriceFromScryfall.getPrices().getEur());
			cardPrice.setValEurFoil(cardPriceFromScryfall.getPrices().getEurFoil());
			cardPrice.setValUsd(cardPriceFromScryfall.getPrices().getUsd());
			cardPrice.setValUsdFoil(cardPriceFromScryfall.getPrices().getUsdFoil());
			
			cardPriceRepository.save(cardPrice);
			
			Thread.sleep(100);
		}
		
		System.out.println("Mise à jour effectuée !");
		
	}
	
}
