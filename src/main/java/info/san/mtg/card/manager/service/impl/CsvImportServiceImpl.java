package info.san.mtg.card.manager.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import info.san.mtg.card.manager.model.CardTypeEnum;
import info.san.mtg.card.manager.model.Cards;
import info.san.mtg.card.manager.model.ConditionEnum;
import info.san.mtg.card.manager.repository.CardsRepository;
import info.san.mtg.card.manager.rest.dto.model.AddCardDto;
import info.san.mtg.card.manager.rest.dto.model.csvimport.CsvImportResponseDto;
import info.san.mtg.card.manager.rest.dto.model.csvimport.CsvImportResponseDto.CsvImportResponseItemDto;
import info.san.mtg.card.manager.service.ICsvImportService;
import info.san.mtg.card.manager.service.IUserService;

@Service
public class CsvImportServiceImpl implements ICsvImportService {
	
	private static final String[] HEADERS = {
			"Name",
			"Collector's number",
			"Condition",
			"Edition CODE",
			"Foil"
	};
	
	private final CardsRepository cardsRepository;
	
	private final IUserService userService;
	
	public CsvImportServiceImpl(CardsRepository cardsRepository, IUserService userService) {
		this.cardsRepository = cardsRepository;
		this.userService = userService;
	}

	@Override
	public CsvImportResponseDto importCsv(MultipartFile file) throws IOException {
		CsvImportResponseDto result = new CsvImportResponseDto();
		
		Reader reader = new InputStreamReader(file.getInputStream());
		
		CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
		        .setHeader(HEADERS)
		        .setSkipHeaderRecord(true)
		        .setRecordSeparator('\n')
		        .build();
		
		Iterable<CSVRecord> records = csvFormat.parse(reader);
		
		for (CSVRecord record : records) {
			CsvImportResponseItemDto item = new CsvImportResponseItemDto();
			String cardNumber = record.get("Collector's number");
			String setCode = record.get("Edition CODE");
			
			item.setCardName(record.get("Name"));
			item.setCardNumber(cardNumber);
			
			String condition = record.get("Condition");
			if (StringUtils.isNotBlank(condition)) {
				item.setCondition(ConditionEnum.getEquivalent(condition));
			}
			item.setSetCode(setCode);
			item.setFoil("Foil".equals(record.get("Foil")));

			Optional<Cards> cards = cardsRepository.findBySetCodeAndNumber(setCode, cardNumber);
			
			cards.ifPresent(card -> {
				item.setCardUuid(card.getUuid());
				item.setSetName(card.getSet().getName());
				if (card.getCardImagery() != null) {
					item.setCardImagePath(card.getCardImagery().getUrlSmall());
				}
			});

			result.getItems().add(item);
		}
		
		return result;
		
	}

	@Override
	public void importCards(String userUuid, Collection<CsvImportResponseItemDto> items) {
		for (CsvImportResponseItemDto item : items) {
			if (item.getCardUuid() != null) {
				AddCardDto addCardDto = new AddCardDto();
				
				addCardDto.setCardUuid(item.getCardUuid());
				if (item.getCondition() == null) {
					addCardDto.setCondition(ConditionEnum.M);
				} else {
					addCardDto.setCondition(item.getCondition());
				}
				addCardDto.setQte(1);
				addCardDto.setType(item.isFoil() ? CardTypeEnum.FOIL : CardTypeEnum.NORMAL);
				
				userService.addCard(userUuid, addCardDto);
			}
		}
	}

}
