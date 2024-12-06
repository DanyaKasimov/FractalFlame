package backend.academy.render;

import backend.academy.dto.Config;
import backend.academy.dto.Pixel;
import backend.academy.dto.Point;
import backend.academy.transfroms.AffineTransform;
import backend.academy.utils.ImageUtils;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("MagicNumber")
public class FractalRender {

    private final double xMin;
    private final double xMax;
    private final double yMin;
    private final double yMax;

    public FractalRender() {
        this.xMin = -3.0;
        this.xMax = 3.0;
        this.yMin = -2.0;
        this.yMax = 2.0;
    }

    public BufferedImage generateFractal(Config config) {
        Pixel[][] pixels = new Pixel[config.width()][config.height()];
        for (int i = 0; i < config.width(); i++) {
            for (int j = 0; j < config.height(); j++) {
                pixels[i][j] = new Pixel();
            }
        }

        if (config.isMultithreaded()) {
            ExecutorService executor = Executors.newFixedThreadPool(config.threads());
            for (int t = 0; t < config.threads(); t++) {
                executor.submit(() -> generatePoints(config, pixels));
            }
            executor.shutdown();
            while (!executor.isTerminated()) {
            }
        } else {
            generatePoints(config, pixels);
        }

        return ImageUtils.renderImage(pixels, config);
    }

    private void generatePoints(Config config, Pixel[][] pixels) {
        Random random = new Random();
        for (int n = 0; n < config.iterations() / config.threads(); n++) {
            double x = random.nextDouble() * (xMax - xMin) + xMin;
            double y = random.nextDouble() * (yMax - yMin) + yMin;

            for (int step = -20; step < config.iterations(); step++) {

                int transformIndex = random.nextInt(config.transforms().length);
                AffineTransform transform = config.transforms()[transformIndex];

                Point coords = transform.apply(x, y);
                x = coords.x();
                y = coords.y();

                Point transformed = config.type().apply(x, y);
                x = transformed.x();
                y = transformed.y();

                if (config.symmetry()) {
                    applySymmetry(x, y, pixels, config, transform);
                }

                if (step >= 0) {
                    applyPoint(x, y, pixels, config, transform);
                }
            }
        }
    }

    private void applySymmetry(double x, double y, Pixel[][] pixels, Config config, AffineTransform transform) {
        applyPoint(-x, y, pixels, config, transform);
        applyPoint(x, -y, pixels, config, transform);
        applyPoint(-x, -y, pixels, config, transform);
        applyPoint(y, x, pixels, config, transform);
    }

    private void applyPoint(double x, double y, Pixel[][] pixels, Config config, AffineTransform transform) {
        if (x >= xMin && x <= xMax && y >= yMin && y <= yMax) {
            int px = (int) ((x - xMin) / (xMax - xMin) * config.width());
            int py = (int) ((y - yMin) / (yMax - yMin) * config.height());
            if (px >= 0 && px < config.width() && py >= 0 && py < config.height()) {
                pixels[px][py].applyColor(transform.red(), transform.green(), transform.blue());
            }
        }
    }
}
