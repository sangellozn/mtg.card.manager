package info.san.mtg.card.manager.service.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import info.san.mtg.card.manager.model.Cards;
import info.san.mtg.card.manager.repository.CardsRepository;
import info.san.mtg.card.manager.rest.dto.model.admin.CardImagerySetsUpdateDto;
import info.san.mtg.card.manager.service.IAdministrationService;
import info.san.mtg.card.manager.service.IAsynCardImageryDownloadService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdministrationServiceImpl implements IAdministrationService {
	
	private final CardsRepository cardsRepository;
	
	private final IAsynCardImageryDownloadService asynCardImageryDownloadService;
	
	public AdministrationServiceImpl(CardsRepository cardsRepository, IAsynCardImageryDownloadService asynCardImageryDownloadService) {
		this.cardsRepository = cardsRepository;
		this.asynCardImageryDownloadService = asynCardImageryDownloadService;
	}

	@Override
	@Transactional
	public void updateCardImagery(boolean force, CardImagerySetsUpdateDto cardImagerySetsUpdateDto) {
		Collection<Cards> cards;
		
		if (force) {
			cards = cardsRepository.findAll();
		} else if (!cardImagerySetsUpdateDto.getSets().isEmpty()) {
			cards = cardsRepository.findAllBySetCodeIn(cardImagerySetsUpdateDto.getSets());
		} else {
			cards = cardsRepository.findAllByCardImageryIsNull();
		}
		
		log.info("Imagerie : {} cartes à traiter", cards.size());
		int idx = 0;
		
		for (Cards card : cards) {
			log.info("Traitement de la carte {}/{}", ++idx, cards.size());
			asynCardImageryDownloadService.downloadImagery(card);
		}
		
	}

}
