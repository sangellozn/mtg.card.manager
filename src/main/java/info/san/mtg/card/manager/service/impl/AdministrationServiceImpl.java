package info.san.mtg.card.manager.service.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import info.san.mtg.card.manager.model.Cards;
import info.san.mtg.card.manager.repository.CardsRepository;
import info.san.mtg.card.manager.service.IAdministrationService;
import info.san.mtg.card.manager.service.IAsynCardImageryDownloadService;

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
	public void updateCardImagery(boolean force) {
		Collection<Cards> cards;
		
		if (force) {
			cards = cardsRepository.findAll();
		} else {
			cards = cardsRepository.findAllByCardImageryIsNull();
		}
		
		for (Cards card : cards) {
			asynCardImageryDownloadService.downloadImagery(card);
		}
		
	}
	
	

}
