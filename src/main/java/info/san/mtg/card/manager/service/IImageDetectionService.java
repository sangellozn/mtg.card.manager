package info.san.mtg.card.manager.service;

import java.io.IOException;

import info.san.mtg.card.manager.rest.dto.model.imagedetection.ImageDetectionResultDto;

public interface IImageDetectionService {
	
	ImageDetectionResultDto detect(String setCode, String imageData) throws IOException;

	void testImage(String path);

	void match() throws IOException;

}
