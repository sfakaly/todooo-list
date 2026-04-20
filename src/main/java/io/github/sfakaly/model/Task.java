package io.github.sfakaly.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private int id;
    private String title;
    private boolean isDone;
    private LocalDateTime createdAt;
}
