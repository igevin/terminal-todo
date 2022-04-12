package com.igevin.terminaltodo.core.ui.command;

import java.util.stream.Stream;

public enum Command {
    UNKNOWN,
    LIST,
    NEW,
    CHECK,
    UNCHECK,
    UPDATE,
    DELETE;

    public static Command parseFromInput(String line) {
        return Stream.of(values())
                .filter(command -> line.toUpperCase().startsWith(command.toString()))
                .findFirst().orElse(UNKNOWN);
    }
}
