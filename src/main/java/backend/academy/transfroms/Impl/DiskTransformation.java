package backend.academy.transfroms.Impl;

import backend.academy.dto.Config;
import backend.academy.dto.Point;
import backend.academy.transfroms.AffineTransform;
import backend.academy.transfroms.Transformation;

@SuppressWarnings("MagicNumber")
public class DiskTransformation implements Transformation {

    @Override
    public Point apply(double x, double y) {
        double newX = 1 / Math.PI * Math.atan(y / x) * Math.sin(Math.PI * Math.sqrt(x * x + y * y));
        double newY = 1 / Math.PI * Math.atan(y / x) * Math.cos(Math.PI * Math.sqrt(x * x + y * y));
        return new Point(3 * newX, 3 * newY);
    }


    public Config getDefaultConfig(boolean isSymmetry) {
        return Config.builder()
            .width(1800)
            .height(1200)
            .threads(Runtime.getRuntime().availableProcessors() - 2)
            .iterations(30000)
            .gamma(20)
            .symmetry(isSymmetry)
            .isMultithreaded(true)
            .transforms(getDefaultAffine())
            .type(new DiskTransformation())
            .build();
    }

    public AffineTransform[] getDefaultAffine() {
        return new AffineTransform[] {
            new AffineTransform(0.6, -0.4, 0, 0.4, 0.6, 0, 1, 0.1, 0),
            new AffineTransform(0.5, 0.5, -0.2, -0.5, 0.5, 0, 0, 0, 1),
            new AffineTransform(0.4, 0.4, 0, -0.4, 0.4, -0.4, 1, 0, 0.5),
            new AffineTransform(0.6, 0.1, 0.9, 0.4, 0.2, 0, 1, 0, 2),
            new AffineTransform(0.1, 0.9, 1, 0.4, 0.2, 0, 1, 0, 0.5),
        };


    }

}
