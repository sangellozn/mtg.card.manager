package info.san.mtg.card.manager.service.impl;

import java.io.File;
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
import java.util.stream.Stream;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;

import info.san.mtg.card.manager.rest.dto.model.imagedetection.ImageDetectionResultDto;
import info.san.mtg.card.manager.service.IImageDetectionService;
import nu.pattern.OpenCV;

@Service
public class ImageDetectionServiceImpl implements IImageDetectionService {

	private static final float RATIO_HEIGHT = 0.6f;

	private static final int CROP_OFFSET = 50;

	private Random rng = new Random();

	static {
		OpenCV.loadShared();
	}

	@Override
	public ImageDetectionResultDto detect(String setCode, String imageData) throws IOException {
		byte[] decodedImage = Base64.getDecoder().decode(imageData.substring("data:image/png;base64,".length()));

		String uuid = UUID.randomUUID().toString();
		Path temp = Paths.get("E:\\temp\\tests", uuid + ".jpg");

		Files.write(temp, decodedImage);

		Mat image = Imgcodecs.imread(temp.toFile().getAbsolutePath());

		// Transformation en niveau de gris.
		Mat grayImage = new Mat();
		Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY, 0);

		Imgcodecs.imwrite("E:\\temp\\tests\\" + uuid + "-agrey.jpg", grayImage);

		Mat cannyImage = new Mat();
		Imgproc.Canny(grayImage, cannyImage, 50, 100, 3, false);

		Imgcodecs.imwrite("E:\\temp\\tests\\" + uuid + "-dcanny.jpg", cannyImage);

		List<MatOfPoint> contours = new ArrayList<>();
		Mat hierarchy = new Mat();
		Imgproc.findContours(cannyImage, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);

		Comparator<MatOfPoint> contourComparator = (left, right) -> {
			return Double.compare(Imgproc.contourArea(left), Imgproc.contourArea(right));
		};

		contours = contours.stream().sorted(contourComparator.reversed()).limit(50).toList();

		Mat contoursImage = Mat.zeros(cannyImage.size(), CvType.CV_8UC3);
		for (int i = 0; i < contours.size(); i++) {
			Scalar color = new Scalar(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
			Imgproc.drawContours(contoursImage, contours, i, color, 1);
		}

		Imgcodecs.imwrite("E:\\temp\\tests\\" + uuid + "-econtour.jpg", contoursImage);

		return new ImageDetectionResultDto();
	}

	@Override
	public void testImage(String path) {
		String uuid = UUID.randomUUID().toString();

		Mat image = Imgcodecs.imread(new File(
				"E:\\dev\\wksp-thread-organiser\\mtg.card.manager\\ui\\front-app\\src\\assets\\static\\images\\cards\\normal\\"
						+ path)
				.getAbsolutePath());

		image = image.submat(new Rect(CROP_OFFSET, (int) (CROP_OFFSET * 1.25f), image.width() - CROP_OFFSET * 2,
				(int) (image.height() * RATIO_HEIGHT) - (int) (CROP_OFFSET * 1.85f)));

		//Imgcodecs.imwrite("E:\\temp\\masters\\" + uuid + "-acrop.jpg", image);

		// Transformation en niveau de gris.
		Mat grayImage = new Mat();
		Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY, 0);

		//Imgcodecs.imwrite("E:\\temp\\masters\\" + uuid + "-agrey.jpg", grayImage);

		Mat cannyImage = new Mat();
		Imgproc.Canny(grayImage, cannyImage, 50, 100, 3, false);

		//Imgcodecs.imwrite("E:\\temp\\masters\\" + uuid + "-dcanny.jpg", cannyImage);

		List<MatOfPoint> contours = new ArrayList<>();
		Mat hierarchy = new Mat();
		Imgproc.findContours(cannyImage, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);

		Comparator<MatOfPoint> contourComparator = (left, right) -> {
			return Double.compare(Imgproc.contourArea(left), Imgproc.contourArea(right));
		};

		contours = contours.stream().sorted(contourComparator.reversed()).limit(50).toList();

		Mat contoursImage = Mat.zeros(cannyImage.size(), CvType.CV_8UC3);
		Scalar color = new Scalar(255, 255, 255);
		for (int i = 0; i < contours.size(); i++) {
			Imgproc.drawContours(contoursImage, contours, i, color, 1);
		}

		Imgcodecs.imwrite("E:\\temp\\masters\\" + uuid + "-econtour.jpg", contoursImage);
	}

	@Override
	public void match() throws IOException {
		
		long start = System.currentTimeMillis();
		
		File template = new File("E:\\temp\\tests\\54ba10f3-0fd7-4217-b1bc-96a036fde2af-dcanny.jpg");
		Mat tpl = Imgcodecs.imread(template.getAbsolutePath());

		try (Stream<Path> stream = Files.list(Paths.get("E:\\temp\\masters"))) {
			stream.filter(path -> !Files.isDirectory(path)).forEach(path -> {
				Mat image = Imgcodecs.imread(path.toFile().getAbsolutePath());
				Mat result = new Mat();

				Imgproc.matchTemplate(image, tpl, result, Imgproc.TM_CCORR_NORMED);
				Core.MinMaxLocResult mmr = Core.minMaxLoc(result);

				System.out.println(path.getFileName() + " - " + mmr.maxVal);
			});
		}
		
		System.out.println("Traitement en " + (System.currentTimeMillis() - start) + " ms");
	}

}
