package com.example.demo;

import com.example.demo.c.DemoController;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
	/**
	 * Rigorous Test :-)
	 */
	@Test
	public void t1() throws Exception {
		BufferedImage source1 = transparent(ImageIO.read(new FileInputStream(new File("C:\\Users\\SwordNoTrace\\Desktop\\bg.png"))));
//		BufferedImage source1up = transparent(ImageIO.read(new FileInputStream(new File("C:\\Users\\SwordNoTrace\\Desktop\\up.png"))));
//		BufferedImage source1down = transparent(ImageIO.read(new FileInputStream(new File("C:\\Users\\SwordNoTrace\\Desktop\\down.png"))));
//		BufferedImage source1repeat = transparent(ImageIO.read(new FileInputStream(new File("C:\\Users\\SwordNoTrace\\Desktop\\repeat.png"))));
		FileOutputStream output = new FileOutputStream(new File("C:\\Users\\SwordNoTrace\\Desktop\\down.png"));

//		// cut
		BufferedImage cut = cut(source1, 0, 600, -1, -1);
		ImageIO.write(cut, "png", output);

		// join
//		BufferedImage join = join(1, source1up, source1repeat, source1down);
//		ImageIO.write(join, "png", output);
	}

	/**
	 * 裁剪图片
	 * @param source 原图
	 * @param startX 开始X点
	 * @param startY 开始Y点
	 * @param endX 结束X点
	 * @param endY 结束Y点
	 * @return 裁剪后的图片
	 */
	public BufferedImage cut(BufferedImage source, int startX, int startY, int endX, int endY) {
		int width = source.getWidth();
		int height = source.getHeight();
		if (startX < 0) {
			startX = 0;
		}
		if (startY < 0) {
			startY = 0;
		}
		if (endX < 0) {
			endX = width;
		}
		if (endY < 0) {
			endY = height;
		}
		BufferedImage target = transparent(new BufferedImage(endX - startX, endY - startY, BufferedImage.TYPE_INT_RGB));
		for (int x = startX; x < endX; ++x) {
			for (int y = startY; y < endY; ++y) {
				int rgb = source.getRGB(x, y);
				target.setRGB(x - startX, y - startY, rgb);
			}
		}
		return target;
	}

	/**
	 * 将图片按照类型拼接在一起，横向拼接时高度必须一致，纵向拼接时宽度必须一致
	 * @param type 1-纵向，2-横向
	 * @param bufferedImages 图片
	 * @return 拼接成的图片
	 */
	public BufferedImage join(int type, BufferedImage... bufferedImages) throws Exception {
		if (type != 1 && type != 2) {
			throw new Exception("拼接类型错误，type=" + type);
		}
		if (bufferedImages == null || bufferedImages.length == 0) {
			return null;
		}
		// true-纵向，false-横向
		boolean direction = type == 1;
		// 图片总宽度
		int allWidth = 0;
		// 图片总高度
		int allHeight = 0;

		if (direction) {
			allWidth = bufferedImages[0].getWidth();
			for (BufferedImage bufferedImage : bufferedImages) {
				allHeight += bufferedImage.getHeight();
			}
		} else {
			allHeight = bufferedImages[0].getHeight();
			for (BufferedImage bufferedImage : bufferedImages) {
				allWidth += bufferedImage.getWidth();
			}
		}

		// 新图片
		BufferedImage target = transparent(new BufferedImage(allWidth, allHeight, BufferedImage.TYPE_INT_RGB));
		Graphics graphics = target.createGraphics();
		int distance = 0;
		if (direction) {
			for (BufferedImage bufferedImage : bufferedImages) {
				graphics.drawImage(bufferedImage, 0, distance, null);
				distance += bufferedImage.getHeight();
			}
		} else {
			for (BufferedImage bufferedImage : bufferedImages) {
				graphics.drawImage(bufferedImage, distance, 0, null);
				distance += bufferedImage.getWidth();
			}
		}
		graphics.dispose();
		return target;
	}

	/**
	 * 使图片保持透明
	 * @param source 原图
	 * @return 设置为透明之后的图片
	 */
	private BufferedImage transparent(BufferedImage source) {
		BufferedImage target = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_BGR)
				.createGraphics()
				.getDeviceConfiguration()
				.createCompatibleImage(source.getWidth(), source.getHeight(), Transparency.TRANSLUCENT);
		Graphics2D graphics = target.createGraphics();
		graphics.drawImage(source, 0, 0, null);
		return target;
	}

	@Test
	public void tt11() throws IOException, FontFormatException {
		DemoController.addFont("加大森林度假", "PingFangSC-Medium.ttf", "m");
		DemoController.addFont("加大森林度假", "PingFangSC-Regular.ttc", "r");
	}

	@Test
	public void t12() throws FileNotFoundException {
		File file = ResourceUtils.getFile("classpath:font/PingFangSC-Medium.ttf");
		System.out.println(file.getAbsolutePath());
	}
}
