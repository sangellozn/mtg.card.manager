package info.san.mtg.card.manager.rest.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import info.san.mtg.card.manager.rest.dto.model.imagedetection.ImageDetectionResultDto;
import info.san.mtg.card.manager.service.IImageDetectionService;

@RestController
@RequestMapping("api/image-detection")
public class ImageDetectionController {
	
	private final IImageDetectionService imageDetectionService;
	
	public ImageDetectionController(IImageDetectionService imageDetectionService) {
		this.imageDetectionService = imageDetectionService;
	}
	
	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ImageDetectionResultDto detect(@RequestPart(name = "setCode", required = false) String setCode, 
			@RequestPart(name = "imageData") String imageData) throws IOException {
		return imageDetectionService.detect(setCode, imageData);
	}
	
	@GetMapping("/{a}/{b}/{img}")
	public void testImage(@PathVariable("a") String a, @PathVariable("b") String b, @PathVariable("img") String img) {
		imageDetectionService.testImage(a + '/' + b + '/' + img);
	}
	
	@GetMapping("match")
	public void match() throws IOException {
		imageDetectionService.match();
	}
	
}
