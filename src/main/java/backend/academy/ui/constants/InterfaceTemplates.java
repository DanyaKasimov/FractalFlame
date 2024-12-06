package backend.academy.ui.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class InterfaceTemplates {

    public static final String LOADING =
        Styles.GREEN_TEXT + Styles.BOLD + "Изображение генерируется. Пожалуйста, подождите." + Styles.RESET;

    public static final String MENU_START = """

        1. Выбрать готовые генерации
        2. Ввести свои настройки
        """;

    public static final String IMAGES_LIST = """

        1. Изображение сердца
        2. Сферическое изображение
        3. Пламенное изображение
        4. Синусоидальное изображение
        5. Изображение диска
        """;

    public static final String MENU_COMPLETE =  Styles.PURPLE_TEXT + "\nПараметры по умолчанию \n\n" + Styles.RESET
        + """
        Разрешение: 1800×1200
        Потоки (многопоточность включена): (Физические потоки процессора) - 2
        Яркость: 20

        1. Изображение сердца            Итерации: 30000
        2. Сферическое изображение       Итерации: 8000
        3. Пламенное изображение         Итерации: 30000
        4. Синусоидальное изображение    Итерации: 18000
        5. Изображение диска             Итерации: 30000

        """
        + Styles.GREEN_TEXT + "С симметрией \n" + Styles.RESET +  """
        6. Изображение сердца            Итерации: 30000
        7. Сферическое изображение       Итерации: 3000
        8. Пламенное изображение         Итерации: 30000
        9. Синусоидальное изображение    Итерации: 6000
        10. Изображение диска            Итерации: 30000
        """;

    public static final String FILE_READY =
        Styles.GREEN_TEXT + Styles.BOLD + "Изображение создано: fractal_flame.png" +  Styles.RESET;
}
