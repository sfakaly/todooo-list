package io.github.sfakaly;

import io.github.sfakaly.controller.TaskController;
import io.github.sfakaly.controller.UserInteraction;
import io.github.sfakaly.repository.JsonHandler;
import io.github.sfakaly.repository.JsonTaskRepository;
import io.github.sfakaly.service.TaskService;

public class Main {
    public static void main(String[] args) {
        JsonHandler jsonHandler = new JsonHandler();
        JsonTaskRepository repository = new JsonTaskRepository(jsonHandler);
        TaskService service = new TaskService(repository);
        UserInteraction ui = new UserInteraction();
        TaskController controller = new TaskController(service, ui);
        controller.run();
    }
}
