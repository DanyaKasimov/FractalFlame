package backend.academy.transfroms;

import backend.academy.dto.Config;
import backend.academy.dto.Point;

public interface Transformation {
    Point apply(double x, double y);

    Config getDefaultConfig(boolean symmetry);

    AffineTransform[] getDefaultAffine();
}
