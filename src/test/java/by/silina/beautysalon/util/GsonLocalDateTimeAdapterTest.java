package by.silina.beautysalon.util;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * The GsonLocalDateTimeAdapterTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class GsonLocalDateTimeAdapterTest {

    /**
     * Test creation a new Gson.
     */
    @Test
    void createGson() {
        Gson gson = GsonLocalDateTimeAdapter.createGson();
        Assertions.assertEquals(Gson.class, gson.getClass());
    }
}