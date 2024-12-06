package backend.academy.transfroms.Impl;

import backend.academy.dto.Config;
import backend.academy.dto.Point;
import backend.academy.transfroms.AffineTransform;
import backend.academy.transfroms.Transformation;

@SuppressWarnings("MagicNumber")
public class SinusTransformation implements Transformation {

    @Override
    public Point apply(double x, double y) {
        double newX = 5 * Math.sin(x);
        double newY = 5 * Math.sin(y);
        return new Point(newX, newY);
    }


    public  Config getDefaultConfig(boolean isSymmetry) {
        int iterations = isSymmetry ? 6000 : 18000;
        return Config.builder()
            .width(1800)
            .height(1200)
            .threads(Runtime.getRuntime().availableProcessors() - 2)
            .iterations(iterations)
            .gamma(20)
            .symmetry(isSymmetry)
            .isMultithreaded(true)
            .transforms(getDefaultAffine())
            .type(new SinusTransformation())
            .build();
    }

    public  AffineTransform[] getDefaultAffine() {
        return new AffineTransform[] {
            new AffineTransform(-2, -0.4, 1, 0.4, 0.6, 0, 1, 0, 0),
            new AffineTransform(-2, 0.5, -0.2, -0.5, 0.5, 0, 1, 0, 1),
            new AffineTransform(0.4, 0.4, 1, -0.4, 0.4, -0.4, 1, 0, 0.5),
            new AffineTransform(-0.6, 0.1, 0.9, 0.4, 0.2, 0, 1, 0, 0.5),
            new AffineTransform(0.1, 0.9, 1, 0.4, 0.2, 0, 1, 0, 0.5),
            new AffineTransform(0.5, -0.5, 0.3, 0.5, -0.5, 0, 0.5, 0, 1),
        };
    }

}
