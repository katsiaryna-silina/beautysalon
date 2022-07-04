package by.silina.beautysalon.controller.command;

import com.google.gson.JsonElement;

public class Router {
    private String page;
    private Type type;
    private JsonElement jsonElement;

    public Router(String page, Type type) {
        this.page = page;
        this.type = type;
    }

    public Router(JsonElement jsonElement, Type type) {
        this.jsonElement = jsonElement;
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public JsonElement getJsonElement() {
        return jsonElement;
    }

    public void setJsonElement(JsonElement jsonElement) {
        this.jsonElement = jsonElement;
    }

    public enum Type {
        FORWARD,
        REDIRECT,
        JSON
    }
}
