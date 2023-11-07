package info.san.mtg.card.manager.service;

import java.io.IOException;

import info.san.mtg.card.manager.rest.dto.model.imagedetection.ImageDetectionRequestDto;
import info.san.mtg.card.manager.rest.dto.model.imagedetection.ImageDetectionResultDto;

public interface IImageDetectionService {
	
	ImageDetectionResultDto detect(ImageDetectionRequestDto request) throws IOException;

}
