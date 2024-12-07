package backend.academy.transfroms.Impl;

import backend.academy.dto.Config;
import backend.academy.dto.Point;
import backend.academy.transfroms.AffineTransform;
import backend.academy.transfroms.Transformation;

@SuppressWarnings("MagicNumber")
public class SphericalTransformation implements Transformation {

    @Override
    public Point apply(double x, double y) {
        double newX = x / (x * x + y * y);
        double newY = y / (x * x + y * y);
        return new Point(newX, newY);
    }


    public Config getDefaultConfig(boolean isSymmetry) {
        int iterations = isSymmetry ? 3000 : 8000;
        return Config.builder()
            .width(1800)
            .height(1200)
            .threads(Runtime.getRuntime().availableProcessors() - 2)
            .iterations(iterations)
            .gamma(20)
            .symmetry(isSymmetry)
            .isMultithreaded(true)
            .transforms(getDefaultAffine())
            .type(new SphericalTransformation())
            .build();
    }

    public AffineTransform[] getDefaultAffine() {
        return new AffineTransform[] {
            new AffineTransform(0.7, -0.5, 0.5, 0.3, -0.5, 0.2, 1, 0, 0.5),
            new AffineTransform(0.6, 0.4, -0.3, -0.6, 0.4, 1, 1, 0, 1),
            new AffineTransform(0.5, 0.3, 0.2, -0.5, 0.5, -0.3, 1, 0, 0.7),
            new AffineTransform(0.4, 0.2, 0, 0.5, 0.3, 1, 1, 0, 0.6),
            new AffineTransform(0.2, 0.8, 1.2, 0.4, -0.3, 0.1, 1, 0, 0.8),
            new AffineTransform(0.5, -0.5, 0.4, 0.6, -0.4, 0.1, 0.6, 0, 1),
        };
    }

}
