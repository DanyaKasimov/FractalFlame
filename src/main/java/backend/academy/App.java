package backend.academy;

import backend.academy.controller.UserController;
import backend.academy.controller.UserControllerImpl;
import backend.academy.dto.Config;
import backend.academy.render.FractalRender;
import backend.academy.ui.UserInterface;
import backend.academy.ui.constants.InterfaceTemplates;
import backend.academy.ui.constants.Styles;
import backend.academy.utils.ImageUtils;
import java.awt.image.BufferedImage;
import java.io.IOException;

@SuppressWarnings("MagicNumber")
public class App {

    public void run() throws IOException {
        UserController controller = new UserControllerImpl(System.in, System.out);
        UserInterface userInterface = new UserInterface(controller);
        Config config = userInterface.handle();
        FractalRender render = new FractalRender();
        long start = System.nanoTime();
        BufferedImage bf = render.generateFractal(config);
        long finish = System.nanoTime();

        long durationInNanos = finish - start;
        long seconds = durationInNanos / 1000000000;
        long milliseconds = (durationInNanos % 1000000000) / 1000000;

        String output = seconds + " секунд " + milliseconds + " мс.";
        controller.write(Styles.PURPLE_TEXT + "Время: " + output + Styles.RESET);
        ImageUtils.saveToFile(bf);
        controller.write(InterfaceTemplates.FILE_READY);
    }
}
