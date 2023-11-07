package info.san.mtg.card.manager.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.CLAHE;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;

import info.san.mtg.card.manager.rest.dto.model.imagedetection.ImageDetectionRequestDto;
import info.san.mtg.card.manager.rest.dto.model.imagedetection.ImageDetectionResultDto;
import info.san.mtg.card.manager.service.IImageDetectionService;
import nu.pattern.OpenCV;

@Service
public class ImageDetectionServiceImpl implements IImageDetectionService {
	
	private Random rng = new Random();
	
	static {
		OpenCV.loadShared();
	}

	@Override
	public ImageDetectionResultDto detect(ImageDetectionRequestDto request) throws IOException {
		byte [] decodedImage = Base64.getDecoder().decode(request.getImageData());
		
		String uuid = UUID.randomUUID().toString();
		Path temp = Paths.get("E:\\temp", uuid + ".jpg");
		
		Files.write(temp, decodedImage);
		
		Mat image = Imgcodecs.imread(temp.toFile().getAbsolutePath());
		
		// Transformation en niveau de gris.
		Mat grayImage = new Mat();
        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);
        Imgproc.blur(grayImage, grayImage, new Size(3, 3));
        
        Imgcodecs.imwrite("E:\\temp\\" + uuid + "-agrey.jpg", grayImage);
        
        // Correction de l'histogramme.
        CLAHE clahe = Imgproc.createCLAHE();
		Mat histoImage = new Mat();
		clahe.apply(grayImage, histoImage);
		
		Imgcodecs.imwrite("E:\\temp\\" + uuid + "-bhisto.jpg", histoImage);
        
		// Threashold pour les contours.
        Mat thresholdImage = new Mat();
        Imgproc.threshold(histoImage, thresholdImage, 127, 255, Imgproc.THRESH_BINARY_INV);
        
        Imgcodecs.imwrite("E:\\temp\\" + uuid + "-cthresh.jpg", thresholdImage);
        
        Mat cannyImage = new Mat();
        Imgproc.Canny(histoImage, cannyImage, 100, 100 * 2);
        
        Imgcodecs.imwrite("E:\\temp\\" + uuid + "-dcanny.jpg", cannyImage);
        
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(cannyImage, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        
        Comparator<MatOfPoint> contourComparator = (left, right) -> {
        	return Double.compare(Imgproc.contourArea(left), Imgproc.contourArea(right));
        };
        
        contours = contours.stream().sorted(contourComparator.reversed())
        	.limit(50).toList();
        
        Mat contoursImage = Mat.zeros(cannyImage.size(), CvType.CV_8UC3);
        for (int i = 0; i < contours.size(); i++) {
            Scalar color = new Scalar(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
            Imgproc.drawContours(contoursImage, contours, i, color, 1);
        }
		
        Imgcodecs.imwrite("E:\\temp\\" + uuid + "-econtour.jpg", contoursImage);
        
		return new ImageDetectionResultDto();
	}

}
