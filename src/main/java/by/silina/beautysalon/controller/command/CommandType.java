package by.silina.beautysalon.controller.command;

import by.silina.beautysalon.controller.command.impl.*;
import by.silina.beautysalon.controller.command.impl.admin.*;
import by.silina.beautysalon.controller.command.impl.order.*;

import java.util.Arrays;

public enum CommandType {
    REGISTRATION(new RegistrationCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    CHANGE_PASSWORD(new ChangeUserPasswordCommand()),
    SHOW_ALL_USERS(new ShowAllUsersCommand()),
    CHANGE_USER_ROLE(new ChangeUserRoleCommand()),
    UPDATE_USER(new UpdateUserCommand()),
    CHANGE_DISCOUNT(new ChangeDiscountCommand()),
    CHANGE_USER_STATUS(new ChangeUserStatusCommand()),
    GET_USERS_JSON(new GetUsersJsonCommand()),
    PICK_SERVICE_IN_ORDER(new PickServiceInOrderCommand()),
    PICK_DATE_IN_ORDER(new PickDateInOrderCommand()),
    PICK_TIME_IN_ORDER(new PickTimeInOrderCommand()),
    SHOW_NEW_ORDER_DATA(new ShowNewOrderDataCommand()),
    CREATE_NEW_ORDER(new CreateOrderCommand()),
    SHOW_ALL_ORDERS_FOR_ADMIN(new ShowAllOrdersCommand()),
    GET_ORDERS_FOR_ADMIN_JSON(new GetAllOrdersJsonCommand()),
    UPDATE_ORDER_BY_ADMIN(new UpdateOrderStatusByAdminCommand()),
    CHANGE_ORDER_STATUS(new ChangeOrderStatusCommand()),
    SHOW_CLIENT_ORDERS(new ShowClientOrdersCommand()),
    GET_ORDERS_FOR_USER_JSON(new GetUserOrdersJsonCommand()),
    UPDATE_ORDER_BY_CLIENT(new UpdateOrderStatusByClientCommand()),
    SHOW_ALL_SERVICES_BY_ADMIN(new ShowAllServicesCommand()),
    GET_ALL_SERVICES_JSON(new GetAllServicesJsonCommand()),
    DELETE_SERVICE(new DeleteServiceCommand()),
    UPDATE_SERVICE(new UpdateServiceCommand()),
    SHOW_FEEDBACK(new ShowFeedbackCommand()),
    UPDATE_FEEDBACK(new UpdateFeedbackCommand()),
    DELETE_USER(new DeleteUserCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
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
