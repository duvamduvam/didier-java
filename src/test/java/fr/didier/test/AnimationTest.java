package fr.didier.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.ejml.simple.SimpleMatrix;
import org.junit.Test;

import fr.didier.face.ImgConverter;

public class AnimationTest {
	
	private final String testPath = "src/test/resources";

	@Test
	public void getRGB(){
		File file = new File(testPath);
		String path = file.getAbsolutePath()+"/visuals/bouche-ouvert2.png";
		ImgConverter imgConverter = new ImgConverter();
		SimpleMatrix matrix = imgConverter.getRGBMatrix(path, 24, 16);
		assertEquals(matrix.get(0, 0), -16777216,000 );
	}
	
}
