package io.github.sfakaly.controller.commands;

import io.github.sfakaly.controller.CommandRequest;
import io.github.sfakaly.controller.UserInteraction;
import io.github.sfakaly.exceptions.OperationCancelledException;
import io.github.sfakaly.service.TaskService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseAction implements Action {
    protected final TaskService service;
    protected final UserInteraction ui;

    @Override
    public final void execute(CommandRequest request) {
        if (isCancelCheckEnable() && request.getPartOfArgs(0).equals("0"))
            throw new OperationCancelledException("Действие отменено (введен 0 в аргументах)");

        protectedExecute(request);
    }

    protected boolean isCancelCheckEnable() {
        return false;
    }

    protected abstract void protectedExecute(CommandRequest request);
}
