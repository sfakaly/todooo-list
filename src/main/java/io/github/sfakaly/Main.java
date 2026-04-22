package io.github.sfakaly;

import io.github.sfakaly.controller.TaskController;
import io.github.sfakaly.controller.UserInteraction;
import io.github.sfakaly.service.TaskService;

public class Main {
    public static void main(String[] args) {
        TaskService service = new TaskService();
        TaskController controller = new TaskController(service);
        UserInteraction ui = new UserInteraction(controller);
        ui.run();
    }
}
