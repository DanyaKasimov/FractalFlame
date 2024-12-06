import backend.academy.dto.Point;
import backend.academy.transfroms.Impl.DiskTransformation;
import backend.academy.transfroms.Impl.FlameTransformation;
import backend.academy.transfroms.Impl.HeartTransformation;
import backend.academy.transfroms.Impl.SinusTransformation;
import backend.academy.transfroms.Impl.SphericalTransformation;
import backend.academy.transfroms.Transformation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformationTests {

    @Test
    public void testHeartTransformation() {
        Transformation transformation = new HeartTransformation();
        Point result = transformation.apply(1.0, 1.0);
        assertEquals(1.26, result.x(), 0.01);
        assertEquals(-0.62, result.y(), 0.01);
    }

    @Test
    public void testSphericalTransformation() {
        Transformation transformation = new SphericalTransformation();
        Point result = transformation.apply(1.0, 1.0);
        assertEquals(0.5, result.x(), 0.01);
        assertEquals(0.5, result.y(), 0.01);
    }

    @Test
    public void testFlameTransformation() {
        Transformation transformation = new FlameTransformation();
        Point result = transformation.apply(1.0, 1.0);
        assertEquals(1.0, result.x(), 0.1);
        assertEquals(0.75, result.y(), 0.1);
    }

    @Test
    public void testSinusTransformation() {
        Transformation transformation = new SinusTransformation();
        Point result = transformation.apply(1.0, 1.0);
        assertEquals(5 * Math.sin(1.0), result.x(), 0.01);
        assertEquals(5 * Math.sin(1.0), result.y(), 0.01);
    }

    @Test
    public void testDiskTransformation() {
        Transformation transformation = new DiskTransformation();
        Point result = transformation.apply(1.0, 1.0);
        assertEquals(-0.72, result.x(), 0.01);
        assertEquals(-0.19, result.y(), 0.01);
    }
}
