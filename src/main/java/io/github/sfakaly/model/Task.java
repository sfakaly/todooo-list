package io.github.sfakaly.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class Task {
    private int id;
    private String title;
    private LocalDateTime createdAt = LocalDateTime.now();
    private boolean isCompleted = false;

    public Task (String title) {
        this.title = title;
    }

    @JsonProperty("isCompleted")
    public boolean isCompleted() {
        return isCompleted;
    }

    @JsonProperty("setCompleted")
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
