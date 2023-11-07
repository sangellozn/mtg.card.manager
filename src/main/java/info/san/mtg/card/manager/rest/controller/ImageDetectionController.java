package info.san.mtg.card.manager.rest.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.san.mtg.card.manager.rest.dto.model.imagedetection.ImageDetectionRequestDto;
import info.san.mtg.card.manager.rest.dto.model.imagedetection.ImageDetectionResultDto;
import info.san.mtg.card.manager.service.IImageDetectionService;

@RestController
@RequestMapping("api/image-detection")
public class ImageDetectionController {
	
	private final IImageDetectionService imageDetectionService;
	
	public ImageDetectionController(IImageDetectionService imageDetectionService) {
		this.imageDetectionService = imageDetectionService;
	}
	
	@PostMapping
	public ImageDetectionResultDto detect(@RequestBody ImageDetectionRequestDto imageDetectionRequestDto) throws IOException {
		return imageDetectionService.detect(imageDetectionRequestDto);
	}
	
}
