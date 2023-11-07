package info.san.mtg.card.manager.rest.controller;

import java.io.IOException;
import java.util.Collection;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import info.san.mtg.card.manager.rest.dto.model.csvimport.CsvImportResponseDto;
import info.san.mtg.card.manager.rest.dto.model.csvimport.CsvImportResponseDto.CsvImportResponseItemDto;
import info.san.mtg.card.manager.service.ICsvImportService;

@RestController
@RequestMapping("api/csv-import")
public class CsvImportController {

	private final ICsvImportService csvImportService;
	
	public CsvImportController(ICsvImportService csvImportService) {
		this.csvImportService = csvImportService;
	}
	
	@PostMapping
	public CsvImportResponseDto importCsv(@RequestParam("file") MultipartFile file) throws IOException {
		return csvImportService.importCsv(file);
	}
	
	@PostMapping("{userUuid}/cards")
	public void importCards(@PathVariable("userUuid") String userUuid, @RequestBody Collection<CsvImportResponseItemDto> items) {
		csvImportService.importCards(userUuid, items);
	}
	
}
