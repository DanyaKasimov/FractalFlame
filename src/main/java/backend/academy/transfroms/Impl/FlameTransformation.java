package backend.academy.transfroms.Impl;

import backend.academy.dto.Config;
import backend.academy.dto.Point;
import backend.academy.transfroms.AffineTransform;
import backend.academy.transfroms.Transformation;

@SuppressWarnings("MagicNumber")
public class FlameTransformation implements Transformation {

    @Override
    public Point apply(double x, double y) {
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);


        double turbulence = Math.sin(3 * theta + r) * Math.cos(2 * r - theta);

        double scale = 1 + 0.5 * turbulence;

        double newX = scale * r * Math.cos(theta + turbulence);
        double newY = scale * r * Math.sin(theta - turbulence);

        newX += 0.2 * Math.sin(5 * theta) * Math.cos(2 * r);
        newY += 0.2 * Math.cos(5 * theta) * Math.sin(2 * r);

        return new Point(newX, newY);
    }

    public  Config getDefaultConfig(boolean isSymmetry) {
        return Config.builder()
            .width(1800)
            .height(1200)
            .threads(Runtime.getRuntime().availableProcessors() - 2)
            .iterations(30000)
            .gamma(20)
            .symmetry(isSymmetry)
            .isMultithreaded(true)
            .transforms(getDefaultAffine())
            .type(new FlameTransformation())
            .build();
    }

    public  AffineTransform[] getDefaultAffine() {
        return new AffineTransform[]{
            new AffineTransform(0.6, -0.4, 0, 0.4, 0.6, 0, 0.8, 0.3, 0.2),
            new AffineTransform(0.5, 0.5, -0.2, -0.5, 0.5, 0, 1, 0.2, 0),
            new AffineTransform(0.4, 0.4, 0, -0.4, 0.4, -0.4, 1, 0.4, 0),
            new AffineTransform(0.6, 0.1, 0.9, 0.4, 0.2, 0, 0.3, 0.1, 0),
            new AffineTransform(0.1, 0.9, 1, 0.4, 0.2, 0, 1, 0.6, 0.1),
            new AffineTransform(0.5, -0.5, 0.3, 0.5, -0.5, 0, 0.5, 0, 0),

            new AffineTransform(-0.6, 0.2, 0, 0.2, 0.6, 0, 1, 0.4, 0),   // Красный
            new AffineTransform(0.7, 0.5, 0.4, 0.3, 0.7, 0, 0.9, 0.2, 0), // Оранжевый
            new AffineTransform(0.8, 0.1, -0.5, 0.1, -0.8, 0.5, 1, 0.6, 0.1), // Желтый
            new AffineTransform(0.6, 0.4, 0.4, -0.4, 0.6, 0.3, 0.8, 0.3, 0.2), // Светло-желтый

        };
    }
}
