package fr.didier.face;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.ejml.simple.SimpleMatrix;

public class ImgConverter {

	private static final Logger LOGGER = Logger.getLogger(SimpleMatrix.class);

	public SimpleMatrix getRGBMatrix(String path, int raw, int col) {

		BufferedImage image;
		SimpleMatrix matrix = new SimpleMatrix(raw, col);
		try {
			image = ImageIO.read(new File(path));
			for (int x = 0; x < raw; x++) {
				for (int y = 0; y < col; y++) {
					double color = image.getRGB(x, y);
					matrix.set(x, y, color);
				}
			}
			LOGGER.trace(matrix.toString());
			return matrix;
		} catch (IOException e) {
			LOGGER.error(e);
		}

		LOGGER.error("no image with path : " + path);
		return null;
	}

}
