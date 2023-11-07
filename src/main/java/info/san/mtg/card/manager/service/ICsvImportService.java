package info.san.mtg.card.manager.service;

import java.io.IOException;
import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;

import info.san.mtg.card.manager.rest.dto.model.csvimport.CsvImportResponseDto;
import info.san.mtg.card.manager.rest.dto.model.csvimport.CsvImportResponseDto.CsvImportResponseItemDto;

public interface ICsvImportService {
	
	CsvImportResponseDto importCsv(MultipartFile file) throws IOException;
	
	void importCards(String userUuid, Collection<CsvImportResponseItemDto> items);

}
