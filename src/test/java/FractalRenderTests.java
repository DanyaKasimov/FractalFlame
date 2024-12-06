import backend.academy.dto.Config;
import backend.academy.render.FractalRender;
import backend.academy.transfroms.Impl.HeartTransformation;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FractalRenderTests {

    @Test
    public void testFractalGenerationSingleThread() {
        FractalRender render = new FractalRender();
        Config config = Config.builder()
            .width(500)
            .height(500)
            .iterations(1000)
            .threads(1)
            .gamma(20)
            .symmetry(false)
            .isMultithreaded(false)
            .transforms(new HeartTransformation().getDefaultAffine())
            .type(new HeartTransformation())
            .build();

        BufferedImage image = render.generateFractal(config);
        assertNotNull(image);
        assertEquals(500, image.getWidth());
        assertEquals(500, image.getHeight());
    }

    @Test
    public void testFractalGenerationMultiThread() {
        FractalRender render = new FractalRender();
        Config config = Config.builder()
            .width(500)
            .height(500)
            .iterations(1000)
            .threads(4)
            .gamma(20)
            .symmetry(false)
            .isMultithreaded(true)
            .transforms(new HeartTransformation().getDefaultAffine())
            .type(new HeartTransformation())
            .build();

        BufferedImage image = render.generateFractal(config);
        assertNotNull(image);
        assertEquals(500, image.getWidth());
        assertEquals(500, image.getHeight());
    }
}
