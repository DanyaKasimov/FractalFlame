package backend.academy.transfroms;

import backend.academy.dto.Point;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("ParameterNumber")
public class AffineTransform {
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;
    private double red;
    private double green;
    private double blue;

    public AffineTransform(
        double a,
        double b,
        double c,
        double d,
        double e,
        double f,
        double red,
        double green,
        double blue
    ) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Point apply(double x, double y) {
        double newX =  this.a * x + this.b * y + this.c;
        double newY = this.d * x + this.e * y + this.f;
        return new Point(newX, newY);
    }
}
