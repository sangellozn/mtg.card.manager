package info.san.mtg.card.manager.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import info.san.mtg.card.manager.model.CardImagery;
import info.san.mtg.card.manager.model.Cards;
import info.san.mtg.card.manager.repository.CardImageryRepository;
import info.san.mtg.card.manager.repository.CardsRepository;
import info.san.mtg.card.manager.service.IAsynCardImageryDownloadService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AsynCardImageryDownloadServiceImpl implements IAsynCardImageryDownloadService {
	
	@Value("${assets.card.imagery.path}")
	private String assetCardImagePath;
	
	@Value("${assets.card.imagery.folder}")
	private String assetsCardImageryFolder;
	
	@Value("${scryfall.cards.url}")
	private String scryfallCardUrl;
	
	private final CardImageryRepository cardImageryRepository;
	
	private final CardsRepository cardsRepository;
	
	public AsynCardImageryDownloadServiceImpl(CardImageryRepository cardImageryRepository,
			CardsRepository cardsRepository) {
		this.cardImageryRepository = cardImageryRepository;
		this.cardsRepository = cardsRepository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void downloadImagery(Cards card) {
		CardImagery cardImagery = card.getCardImagery();

		if (cardImagery == null) {
			cardImagery = new CardImagery();
			cardImagery.setCard(card);
			card.setCardImagery(cardImagery);

			cardImageryRepository.save(cardImagery);
			cardsRepository.save(card);
		}

		String scryfallId = card.getCardIdentifiers().getScryfallId();

		String pathNormal = String.join("/", assetCardImagePath, "normal", String.valueOf(scryfallId.charAt(0)),
				String.valueOf(scryfallId.charAt(1)));
		String pathSmall = String.join("/", assetCardImagePath, "small", String.valueOf(scryfallId.charAt(0)),
				String.valueOf(scryfallId.charAt(1)));

		cardImagery.setUrlNormal(String.join("/", pathNormal, scryfallId + ".jpg"));
		cardImagery.setUrlSmall(String.join("/", pathSmall, scryfallId + ".jpg"));
		
		cardImageryRepository.save(cardImagery);

		Path savePathNormal = Path.of(assetsCardImageryFolder, pathNormal);
		Path savePathSmall = Path.of(assetsCardImageryFolder, pathSmall);

		try {
			savePathNormal = Files.createDirectories(savePathNormal);
			savePathSmall = Files.createDirectories(savePathSmall);

			Path saveImageNormalPath = Path.of(assetsCardImageryFolder, pathNormal, scryfallId + ".jpg");
			Path saveImageSmallPath = Path.of(assetsCardImageryFolder, pathSmall, scryfallId + ".jpg");

			// Sauvegarde format "normal"
			try (ReadableByteChannel normalByteChannel = Channels.newChannel(new URL(scryfallCardUrl + "/normal/front/"
					+ scryfallId.charAt(0) + "/" + scryfallId.charAt(1) + "/" + scryfallId + ".jpg").openStream());
					FileOutputStream savedImage = new FileOutputStream(saveImageNormalPath.toFile());
					FileChannel savedImageChannel = savedImage.getChannel()) {
				savedImageChannel.transferFrom(normalByteChannel, 0, Long.MAX_VALUE);
			}

			// Sauvegarde format "small"
			try (ReadableByteChannel smallByteChannel = Channels.newChannel(new URL(scryfallCardUrl + "/small/front/"
					+ scryfallId.charAt(0) + "/" + scryfallId.charAt(1) + "/" + scryfallId + ".jpg").openStream());
					FileOutputStream savedImage = new FileOutputStream(saveImageSmallPath.toFile());
					FileChannel savedImageChannel = savedImage.getChannel()) {
				savedImageChannel.transferFrom(smallByteChannel, 0, Long.MAX_VALUE);
			}

			Thread.sleep(new Random().nextInt(150) + 75);

		} catch (IOException | InterruptedException e) {
			log.warn("Cannot save image for card uuid = {}", card.getUuid());
		}

	}

}
