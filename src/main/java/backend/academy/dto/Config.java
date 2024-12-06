package backend.academy.dto;

import backend.academy.transfroms.AffineTransform;
import backend.academy.transfroms.Transformation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@SuppressWarnings("ParameterNumber")
public class Config {
    private int width;
    private int height;
    private int iterations;
    private int threads;
    private double gamma;
    private boolean symmetry;
    private boolean isMultithreaded;
    private AffineTransform[] transforms;
    private Transformation type;

    public Config(
        int width,
        int height,
        int iterations,
        int threads,
        double gamma,
        boolean symmetry,
        boolean isMultithreaded,
        AffineTransform[] transforms,
        Transformation type
    ) {
        this.width = width;
        this.height = height;
        this.iterations = iterations;
        this.threads = threads;
        this.gamma = gamma;
        this.symmetry = symmetry;
        this.isMultithreaded = isMultithreaded;
        this.transforms = transforms;
        this.type = type;
    }

    public Config() {
    }
}
