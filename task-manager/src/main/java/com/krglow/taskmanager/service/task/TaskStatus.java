package com.krglow.taskmanager.service.task;

import java.util.Objects;
import java.util.stream.Stream;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TaskStatus {

    NULL(0L), NEW(1L), IN_PROGRESS(2L), COMPLETED(3L), ON_HOLD(4L);

    private Long id;

    public static TaskStatus findById(Long id) {
        return Stream.of(TaskStatus.values()).filter(v -> Objects.equals(v.id, id)).findFirst().orElseThrow(() -> new IllegalArgumentException(Objects.toString(id)));
    }

}
