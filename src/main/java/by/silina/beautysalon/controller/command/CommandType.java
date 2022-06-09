package by.silina.beautysalon.controller.command;

import by.silina.beautysalon.controller.command.impl.DefaultCommand;
import by.silina.beautysalon.controller.command.impl.LoginCommand;
import by.silina.beautysalon.controller.command.impl.LogoutCommand;
import by.silina.beautysalon.controller.command.impl.RegistrationCommand;

import java.util.Arrays;

public enum CommandType {
    REGISTRATION(new RegistrationCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command of(String commandStr) {
        if (commandStr != null) {
            String commandTypeName = commandStr.toUpperCase();
            CommandType commandType = Arrays.stream(values())
                    .filter(el -> el.name().equals(commandTypeName))
                    .findFirst().orElse(DEFAULT);
            return commandType.command;
        } else {
            return DEFAULT.command;
        }
    }
}
