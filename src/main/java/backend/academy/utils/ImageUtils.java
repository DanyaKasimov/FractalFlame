package backend.academy.utils;


import backend.academy.dto.Config;
import backend.academy.dto.Pixel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("MagicNumber")
public class ImageUtils {
    public static BufferedImage renderImage(Pixel[][] pixels, Config config) {
        BufferedImage image = new BufferedImage(config.width(), config.height(), BufferedImage.TYPE_INT_RGB);
        double maxLog = 0.0;

        for (int i = 0; i < config.width(); i++) {
            for (int j = 0; j < config.height(); j++) {
                if (pixels[i][j].counter() > 0) {
                    pixels[i][j].counter((int) Math.log10(pixels[i][j].counter()));
                    maxLog = Math.max(maxLog, pixels[i][j].counter());
                }
            }
        }

        for (int i = 0; i < config.width(); i++) {
            for (int j = 0; j < config.height(); j++) {
                if (pixels[i][j].counter() > 0) {
                    double intensity = pixels[i][j].counter() / maxLog;
                    int r = (int) (pixels[i][j].red() * Math.pow(intensity, 1.0 / config.gamma()) * 255);
                    int g = (int) (pixels[i][j].green() * Math.pow(intensity, 1.0 / config.gamma()) * 255);
                    int b = (int) (pixels[i][j].blue() * Math.pow(intensity, 1.0 / config.gamma()) * 255);
                    image.setRGB(i, j, (r << 16) | (g << 8) | b);
                }
            }
        }

        return image;
    }

    public static void saveToFile(BufferedImage image) throws IOException {
        ImageIO.write(image, "png", new File("fractal_flame.png"));
    }
}
