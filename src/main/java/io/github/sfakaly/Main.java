package io.github.sfakaly;

import io.github.sfakaly.controller.TaskController;
import io.github.sfakaly.controller.UserInteraction;
import io.github.sfakaly.service.TaskService;

public class Main {
    public static void main(String[] args) {
        TaskService service = new TaskService();
        UserInteraction ui = new UserInteraction();
        TaskController controller = new TaskController(service, ui);
        controller.run();
    }
}
