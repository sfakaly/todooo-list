package io.github.sfakaly.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DataStorage {
    private final List<Task> tasks;
    private final int lastId;

    public DataStorage() {
        this.tasks = new ArrayList<>();
        this.lastId = 100;
    }
}
