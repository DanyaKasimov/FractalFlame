import backend.academy.dto.Config;
import backend.academy.render.FractalRender;
import backend.academy.transfroms.Impl.HeartTransformation;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FractalPerformanceTests {

    @Test
    public void testSingleThreadPerformance() {
        FractalRender render = new FractalRender();
        Config config = Config.builder()
            .width(1000)
            .height(1000)
            .iterations(5000)
            .threads(1)
            .gamma(20)
            .symmetry(false)
            .isMultithreaded(false)
            .transforms(new HeartTransformation().getDefaultAffine())
            .type(new HeartTransformation())
            .build();

        long start = System.nanoTime();
        BufferedImage image = render.generateFractal(config);
        long duration = System.nanoTime() - start;

        assertNotNull(image);
        System.out.println("Single-thread duration: " + duration / 1000000 + " ms");
    }

    @Test
    public void testMultiThreadPerformance() {
        FractalRender render = new FractalRender();
        Config config = Config.builder()
            .width(1000)
            .height(1000)
            .iterations(5000)
            .threads(4)
            .gamma(20)
            .symmetry(false)
            .isMultithreaded(true)
            .transforms(new HeartTransformation().getDefaultAffine())
            .type(new HeartTransformation())
            .build();

        long start = System.nanoTime();
        BufferedImage image = render.generateFractal(config);
        long duration = System.nanoTime() - start;

        assertNotNull(image);
        System.out.println("Multi-thread duration: " + duration / 1000000 + " ms");
    }

    @Test
    public void testFasterCountThread() {
        FractalRender render = new FractalRender();
        Config configSingle = Config.builder()
            .width(1000)
            .height(1000)
            .iterations(5000)
            .threads(1)
            .gamma(20)
            .symmetry(false)
            .isMultithreaded(false)
            .transforms(new HeartTransformation().getDefaultAffine())
            .type(new HeartTransformation())
            .build();

        Config configMulti = Config.builder()
            .width(1000)
            .height(1000)
            .iterations(5000)
            .threads(4)
            .gamma(20)
            .symmetry(false)
            .isMultithreaded(false)
            .transforms(new HeartTransformation().getDefaultAffine())
            .type(new HeartTransformation())
            .build();

        long start = System.nanoTime();
        render.generateFractal(configSingle);
        long durationSingle = System.nanoTime() - start;

        start = System.nanoTime();
        render.generateFractal(configMulti);
        long durationMulti = System.nanoTime() - start;

        assertTrue(durationSingle > durationMulti);
    }
}
