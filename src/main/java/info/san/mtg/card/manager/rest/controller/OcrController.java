package info.san.mtg.card.manager.rest.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import info.san.mtg.card.manager.service.IOcrService;
import net.sourceforge.tess4j.TesseractException;

@RestController
@RequestMapping("api/ocr")
public class OcrController {

	private final IOcrService ocrService;
	
	public OcrController(IOcrService ocrService) {
		this.ocrService = ocrService;
	}
	
	@PostMapping
	public void doOcr(@RequestParam("imageAsBase64") String imageAsBase64) throws TesseractException, IOException {
		ocrService.doOcr(null, imageAsBase64);
	}
	
	@PostMapping("/{setCode}")
	public void doOcr(@PathVariable("setCode") String extension, @RequestParam("imageAsBase64") String imageAsBase64) throws TesseractException, IOException {
		ocrService.doOcr(extension, imageAsBase64);
	}

}
