package by.silina.beautysalon.controller.command;

import com.google.gson.JsonElement;

/**
 * The Router class is responsible for store data about page, router type, json element.
 *
 * @author Silina Katsiaryna
 */
public class Router {
    private String page;
    private Type type;
    private JsonElement jsonElement;

    /**
     * Initializes a Router class.
     *
     * @param page String. The page path.
     * @param type Type. The router type.
     */
    public Router(String page, Type type) {
        this.page = page;
        this.type = type;
    }

    /**
     * Initializes a Router class.
     *
     * @param jsonElement JsonElement. The json element.
     * @param type        Type. The router type.
     */
    public Router(JsonElement jsonElement, Type type) {
        this.jsonElement = jsonElement;
        this.type = type;
    }

    /**
     * Gets the page path.
     *
     * @return String. The page path.
     */
    public String getPage() {
        return page;
    }

    /**
     * Sets the page path.
     *
     * @param page String. The page path.
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * Gets the router type.
     *
     * @return Type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the page path.
     *
     * @param type Type. The router type.
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Gets the json element.
     *
     * @return JsonElement
     */
    public JsonElement getJsonElement() {
        return jsonElement;
    }

    /**
     * Sets the json element.
     *
     * @param jsonElement JsonElement
     */
    public void setJsonElement(JsonElement jsonElement) {
        this.jsonElement = jsonElement;
    }

    /**
     * The router Type Enum.
     */
    public enum Type {
        FORWARD,
        REDIRECT,
        JSON
    }
}
