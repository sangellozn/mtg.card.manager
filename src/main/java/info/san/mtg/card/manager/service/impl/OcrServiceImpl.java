package info.san.mtg.card.manager.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import info.san.mtg.card.manager.service.IOcrService;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import nu.pattern.OpenCV;

@Service
public class OcrServiceImpl implements IOcrService {
	
	private final Tesseract tesseract;
	
	static {
		OpenCV.loadShared();
	}
	
	public OcrServiceImpl(@Value("${tesseract.data.path}") String tesseractDataPath) {
		this.tesseract = new Tesseract();
		this.tesseract.setDatapath(tesseractDataPath);
		this.tesseract.setLanguage("fra");
		this.tesseract.setPageSegMode(1);
		this.tesseract.setOcrEngineMode(1);
		this.tesseract.setTessVariable("user_defined_dpi", "300");
	}

	@Override
	public void doOcr(String setCode, String imageAsBase64) throws TesseractException, IOException {
		String image = imageAsBase64.substring("data:image/png;base64,".length());
		
		Path tempFile = Files.createTempFile(null, ".png");
		
		Files.write(tempFile, Base64.getDecoder().decode(image));
		
		System.out.println(tesseract.doOCR(tempFile.toFile()));
	}

}
