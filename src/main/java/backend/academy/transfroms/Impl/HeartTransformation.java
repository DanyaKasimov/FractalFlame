package backend.academy.transfroms.Impl;

import backend.academy.dto.Config;
import backend.academy.dto.Point;
import backend.academy.transfroms.AffineTransform;
import backend.academy.transfroms.Transformation;

@SuppressWarnings("MagicNumber")
public class HeartTransformation implements Transformation {

    @Override
    public Point apply(double x, double y) {
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);
        double newX = r * Math.sin(theta * r);
        double newY = -r * Math.cos(theta * r);
        return new Point(newX, newY);
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
            .type(new HeartTransformation())
            .build();
    }

    public  AffineTransform[] getDefaultAffine() {
        return new AffineTransform[] {
            new AffineTransform(0.6, -0.4, 0, 0.4, 0.6, 0, 1, 0, 0),  // Теплый градиент
            new AffineTransform(0.5, 0.5, -0.2, -0.5, 0.5, 0, 1, 0, 1), // Зеленый акцент
            new AffineTransform(0.4, 0.4, 0, -0.4, 0.4, -0.4, 1, 0, 0.5), // Полупрозрачные линии
            new AffineTransform(0.6, 0.1, 0.9, 0.4, 0.2, 0, 1, 0, 0.5), // Полупрозрачные линии
            new AffineTransform(0.1, 0.9, 1, 0.4, 0.2, 0, 1, 0, 0.5), // Полупрозрачные линии
            new AffineTransform(0.5, -0.5, 0.3, 0.5, -0.5, 0, 0.5, 0, 1), // Ромбовидная структура
        };
    }

}
