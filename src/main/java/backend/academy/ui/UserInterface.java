package backend.academy.ui;

import backend.academy.controller.UserController;
import backend.academy.dto.Config;
import backend.academy.transfroms.Impl.DiskTransformation;
import backend.academy.transfroms.Impl.FlameTransformation;
import backend.academy.transfroms.Impl.HeartTransformation;
import backend.academy.transfroms.Impl.SinusTransformation;
import backend.academy.transfroms.Impl.SphericalTransformation;
import backend.academy.transfroms.Transformation;
import backend.academy.ui.constants.InterfaceTemplates;

@SuppressWarnings({"MultipleStringLiterals", "MagicNumber"})
public class UserInterface {

    private final UserController controller;

    public UserInterface(UserController controller) {
        this.controller = controller;
    }

    public Config handle() {
        controller.write(InterfaceTemplates.MENU_START);

        String input = controller.readValidatedInput(
            "Выберите пункт: ",
            i -> i.matches("[12]"),
            "Пожалуйста, введите 1 или 2."
        );

        return switch (input) {
            case "1" -> completeSettings();
            case "2" -> customSettings();
            default -> null;
        };
    }

    public Transformation getType(int type) {
        return switch (type) {
            case 1 -> new HeartTransformation();
            case 2 -> new SphericalTransformation();
            case 3 -> new FlameTransformation();
            case 4 -> new SinusTransformation();
            case 5 -> new DiskTransformation();
            default -> null;
        };
    }

    private Config completeSettings() {
        controller.write(InterfaceTemplates.MENU_COMPLETE);
        String input = controller.readValidatedInput(
            "Выберите изображение (1-10): ",
            i -> controller.idValidNumeric(i, 10),
            "Пожалуйста, введите число от 1 до 10."
        );

        controller.write(InterfaceTemplates.LOADING);
        return switch (input) {
            case "1" -> new HeartTransformation().getDefaultConfig(false);
            case "2" -> new SphericalTransformation().getDefaultConfig(false);
            case "3" -> new FlameTransformation().getDefaultConfig(false);
            case "4" -> new SinusTransformation().getDefaultConfig(false);
            case "5" -> new DiskTransformation().getDefaultConfig(false);

            case "6" -> new HeartTransformation().getDefaultConfig(true);
            case "7" -> new SphericalTransformation().getDefaultConfig(true);
            case "8" -> new FlameTransformation().getDefaultConfig(true);
            case "9" -> new SinusTransformation().getDefaultConfig(true);
            case "10" -> new DiskTransformation().getDefaultConfig(true);
            default -> throw new IllegalStateException("Unexpected value: " + input);
        };
    }

    private Config customSettings() {
        boolean isMultithreaded = controller.readValidatedInput(
            "Добавить многопоточность? 1 - Да, 2 - Нет: ",
            i -> i.matches("[12]"),
            "Введите 1 (Да) или 2 (Нет)."
        ).equals("1");

        int width = Integer.parseInt(controller.readValidatedInput(
            "Введите ширину изображения: ",
            i -> i.matches("\\d+") && Integer.parseInt(i) > 0,
            "Пожалуйста, введите положительное число."
        ));

        int height = Integer.parseInt(controller.readValidatedInput(
            "Введите высоту изображения: ",
            i -> i.matches("\\d+") && Integer.parseInt(i) > 0,
            "Пожалуйста, введите положительное число."
        ));

        int iterations = Integer.parseInt(controller.readValidatedInput(
            "Введите количество итераций: ",
            i -> i.matches("\\d+") && Integer.parseInt(i) > 0,
            "Пожалуйста, введите положительное число."
        ));

        int gamma = Integer.parseInt(controller.readValidatedInput(
            "Введите яркость: ",
            i -> i.matches("\\d+") && Integer.parseInt(i) > 0,
            "Пожалуйста, введите положительное число."
        ));

        boolean symmetry = controller.readValidatedInput(
            "Добавить симметрию? 1 - Да, 2 - Нет: ",
            i -> i.matches("[12]"),
            "Введите 1 (Да) или 2 (Нет)."
        ).equals("1");

        int threads = isMultithreaded ? Integer.parseInt(controller.readValidatedInput(
            "Введите количество потоков: ",
            i -> i.matches("\\d+") && Integer.parseInt(i) > 0,
            "Пожалуйста, введите положительное число."
        )) : 1;

        controller.write(InterfaceTemplates.IMAGES_LIST);
        int type = Integer.parseInt(controller.readValidatedInput(
            "Введите тип изображения (1-5): ",
            i -> controller.idValidNumeric(i, 5),
            "Введите число от 1 до 5."
        ));

        Transformation transformation = getType(type);

        Config config = Config.builder()
            .width(width)
            .height(height)
            .iterations(iterations)
            .gamma(gamma)
            .symmetry(symmetry)
            .threads(threads)
            .isMultithreaded(isMultithreaded)
            .transforms(transformation.getDefaultAffine())
            .type(transformation)
            .build();

        controller.write(InterfaceTemplates.LOADING);

        return config;
    }

}
