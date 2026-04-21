package io.github.sfakaly;

import io.github.sfakaly.controller.TaskController;
import io.github.sfakaly.controller.UserInteraction;

public class Main {
    public static void main(String[] args) {
        TaskController controller = new TaskController();
        UserInteraction ui = new UserInteraction(controller);
//        String title = ui.readString("Введите название");
//        System.out.println(title);
//        int id = ui.readInt("Введите айди");
//        System.out.println(id);
//        boolean isDone = ui.confirm("Выполнена ли задача (yes/no)");
//        System.out.println(isDone);
        ui.run();
    }
}
