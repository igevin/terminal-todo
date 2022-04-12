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

    public static final String UNKNOWN_NAME = "UNKNOWN";
    public static final String LIST_NAME = "LIST";
    public static final String NEW_NAME = "NEW";
    public static final String CHECK_NAME = "CHECK";
    public static final String UNCHECK_NAME = "UNCHECK";
    public static final String UPDATE_NAME = "UPDATE";
    public static final String DELETE_NAME = "DELETE";
}
