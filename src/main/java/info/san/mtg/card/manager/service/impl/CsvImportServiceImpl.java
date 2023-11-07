package info.san.mtg.card.manager.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import info.san.mtg.card.manager.rest.dto.model.csvimport.CsvImportResponseDto;
import info.san.mtg.card.manager.service.ICsvImportService;

@Service
public class CsvImportServiceImpl implements ICsvImportService {

	@Override
	public CsvImportResponseDto importCsv(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

}
