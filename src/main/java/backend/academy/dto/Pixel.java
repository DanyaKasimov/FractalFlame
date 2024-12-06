package backend.academy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pixel {
    private double red;
    private double green;
    private double blue;
    private int counter;

    public Pixel() {
    }

    public void applyColor(double r, double g, double b) {
        if (this.counter == 0) {
            this.red = r;
            this.green = g;
            this.blue = b;
        } else {
            this.red = (this.red + r) / 2;
            this.green = (this.green + g) / 2;
            this.blue = (this.blue + b) / 2;
        }
        this.counter++;
    }
}
