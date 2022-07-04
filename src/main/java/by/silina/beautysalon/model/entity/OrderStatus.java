package by.silina.beautysalon.model.entity;

public enum OrderStatus {
    WAITING_FOR_CONFIRMATION("Order is waiting for confirmation."),
    CONFIRMED("Order is confirmed."),
    COMPLETED("Order is completed."),
    DECLINED_BY_ADMIN("Order is declined by admin."),
    DECLINED_BY_CLIENT("Order is declined by client.");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
