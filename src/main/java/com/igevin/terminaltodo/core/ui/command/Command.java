package com.igevin.terminaltodo.core.ui.command;

import java.util.stream.Stream;

public enum Command {
    UNKNOWN,
    LIST,
    ADD,
    CHECK,
    UNCHECK,
    UPDATE,
    DELETE,
    USER;

    public static Command parseFromInput(String line) {
        return Stream.of(values())
                .filter(command -> line.toUpperCase().startsWith(command.toString()))
                .findFirst().orElse(UNKNOWN);
    }

    public static final String UNKNOWN_NAME = "UNKNOWN";
    public static final String LIST_NAME = "LIST";
    public static final String ADD_NAME = "ADD";
    public static final String CHECK_NAME = "CHECK";
    public static final String UNCHECK_NAME = "UNCHECK";
    public static final String UPDATE_NAME = "UPDATE";
    public static final String DELETE_NAME = "DELETE";
    public static final String USER_NAME = "USER";
}
