package info.san.mtg.card.manager.service;

import java.io.IOException;

import net.sourceforge.tess4j.TesseractException;

public interface IOcrService {
	
	void doOcr(String setCode, String imageAsBase64) throws TesseractException, IOException;

}
