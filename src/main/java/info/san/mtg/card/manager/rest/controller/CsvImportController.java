package info.san.mtg.card.manager.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import info.san.mtg.card.manager.rest.dto.model.csvimport.CsvImportResponseDto;
import info.san.mtg.card.manager.service.ICsvImportService;

@RestController
@RequestMapping("api/csv-import")
public class CsvImportController {

	private final ICsvImportService csvImportService;
	
	public CsvImportController(ICsvImportService csvImportService) {
		this.csvImportService = csvImportService;
	}
	
	@PostMapping
	public CsvImportResponseDto importCsv(@RequestParam("file") MultipartFile file) {
		return csvImportService.importCsv(file);
	}
	
}
