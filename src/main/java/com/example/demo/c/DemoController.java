package com.example.demo.c;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author 無痕剑
 * @date 2019/4/8 20:56
 */
@RestController
public class DemoController {

	@RequestMapping("/t1")
	public String t1(@RequestParam("a") String a, @RequestParam("f") String f, @RequestParam("n") String n) {
		try {
			addFont(a, f, n);
		} catch (FontFormatException | IOException e) {
			try (StringWriter stringWriter = new StringWriter();
			     PrintWriter printWriter = new PrintWriter(stringWriter);) {
				e.printStackTrace(printWriter);
				return stringWriter.toString();
			} catch (IOException e1) {
				e1.printStackTrace();
				return "错上加错！";
			}
		}
		return "success";
	}

	public static void addFont(String a, String f, String n) throws IOException, FontFormatException {
		BufferedImage bufferedImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		g2d.setBackground(Color.WHITE);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillRect(0, 0, 500, 500);
		g2d.setColor(Color.RED);
		Font font = Font.createFont(Font.TRUETYPE_FONT, new ClassPathResource("classpath:font/" + f).getInputStream())
				.deriveFont(Font.BOLD, 40);
		g2d.setFont(font);
		g2d.drawString(a, 100, 100);
		g2d.dispose();

		ImageIO.write(bufferedImage, "png", new FileOutputStream(new File(n + ".png")));
	}

	@RequestMapping("/family")
	public String family() {
		StringBuilder stringBuilder = new StringBuilder();
		for (String font : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()) {
			stringBuilder.append(font).append("\n");
		}
		return stringBuilder.toString();
	}

	@RequestMapping("/font")
	public String font() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Font font : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()) {
			stringBuilder.append(font.getFontName()).append("\n");
		}
		return stringBuilder.toString();
	}
}
