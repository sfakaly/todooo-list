package io.github.sfakaly.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class DataStorage {
    private final List<Task> tasks;
    private final int lastId;

    public DataStorage() {
        this.tasks = new ArrayList<>();
        this.lastId = 100;
    }
}
