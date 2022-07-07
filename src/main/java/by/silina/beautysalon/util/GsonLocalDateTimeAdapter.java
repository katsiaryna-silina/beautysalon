package by.silina.beautysalon.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class GsonLocalDateTimeAdapter {
    private static final String VALUE = "value";

    private GsonLocalDateTimeAdapter() {
    }

    public static Gson createGson() {
        return new GsonBuilder()
                .registerTypeAdapter(
                        LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                            @Override
                            public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
                                JsonObject json = new JsonObject();
                                json.addProperty(VALUE, localDateTime.toString());
                                return json;
                            }
                        }
                )
                .registerTypeAdapter(
                        LocalTime.class, new JsonSerializer<LocalTime>() {
                            @Override
                            public JsonElement serialize(LocalTime localTime, Type type, JsonSerializationContext jsonSerializationContext) {
                                JsonObject json = new JsonObject();
                                json.addProperty(VALUE, localTime.toString());
                                return json;
                            }
                        }
                )
                .registerTypeAdapter(
                        LocalDate.class, new JsonSerializer<LocalDate>() {
                            @Override
                            public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
                                JsonObject json = new JsonObject();
                                json.addProperty(VALUE, localDate.toString());
                                return json;
                            }
                        }
                )
                .create();
    }
}
