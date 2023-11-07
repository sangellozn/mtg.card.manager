package info.san.mtg.card.manager.service;

import org.springframework.web.multipart.MultipartFile;

import info.san.mtg.card.manager.rest.dto.model.csvimport.CsvImportResponseDto;

public interface ICsvImportService {
	
	CsvImportResponseDto importCsv(MultipartFile file);

}
