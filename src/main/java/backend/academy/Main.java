package backend.academy;

import backend.academy.ui.constants.Styles;
import java.io.IOException;
import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("RegexpSinglelineJava")
public class Main {
    public static void main(String[] args) {
        App app = new App();
        try {
            app.run();
        } catch (IOException e) {
            System.out.println(Styles.RED_TEXT + "Ошибка записи в файл: " + e.getMessage());
        }
    }
}
