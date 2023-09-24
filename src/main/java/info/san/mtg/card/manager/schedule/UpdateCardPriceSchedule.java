package info.san.mtg.card.manager.schedule;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import info.san.mtg.card.manager.model.CardPrice;
import info.san.mtg.card.manager.model.Cards;
import info.san.mtg.card.manager.repository.CardPriceRepository;
import info.san.mtg.card.manager.repository.CardsRepository;
import info.san.mtg.card.manager.repository.PricesHistoryRepository;
import info.san.mtg.card.manager.rest.client.IScryfallPriceApiClient;
import info.san.mtg.card.manager.rest.client.dto.ScryfallCardDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UpdateCardPriceSchedule implements IUpdateCardPriceSchedule {
	
	private final IScryfallPriceApiClient scryfallPriceApiClient;

	private final CardPriceRepository cardPriceRepository;
	
	private final CardsRepository cardsRepository;
	
	private final PricesHistoryRepository pricesHistoryRepository;
	
	public UpdateCardPriceSchedule(IScryfallPriceApiClient scryfallPriceApiClient, 
			CardPriceRepository cardPriceRepository, CardsRepository cardsRepository, 
			PricesHistoryRepository pricesHistoryRepository) {
		this.scryfallPriceApiClient = scryfallPriceApiClient;
		this.cardPriceRepository = cardPriceRepository;
		this.cardsRepository = cardsRepository;
		this.pricesHistoryRepository = pricesHistoryRepository;
	}

	@Override
	@Transactional
	@Scheduled(cron = "0 0 2 * * *")
	public void updatePrices() throws InterruptedException {
		log.info("Mise à jour des prix des cartes...");
		
		Collection<Cards> cardsToUpdate = cardsRepository.findAllForPriceUpdate(Instant.now().minus(1, ChronoUnit.DAYS).truncatedTo(ChronoUnit.DAYS));
		
		log.info("{} carte(s) à mettre à jour.", cardsToUpdate.size());
		
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
			
			Thread.sleep(250);
		}
		
		pricesHistoryRepository.createHistory(LocalDate.now());
		
		log.info("Mise à jour effectuée !");
	}
	
}
