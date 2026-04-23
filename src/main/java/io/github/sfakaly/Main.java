package io.github.sfakaly;

import io.github.sfakaly.controller.TaskController;
import io.github.sfakaly.controller.UserInteraction;
import io.github.sfakaly.repository.JsonHandler;
import io.github.sfakaly.service.TaskService;

public class Main {
    public static void main(String[] args) {
        JsonHandler jsonHandler = new JsonHandler();
        TaskService service = new TaskService(jsonHandler);
        UserInteraction ui = new UserInteraction();
        TaskController controller = new TaskController(service, ui);
        controller.run();
    }
}
