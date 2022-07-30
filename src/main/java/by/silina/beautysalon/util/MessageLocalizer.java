package by.silina.beautysalon.util;


import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.LOCALE_RU;

/**
 * The MessageLocalizer class is responsible for processes messages depending on localization.
 * This class uses properties files.
 *
 * @author Silina Katsiaryna
 */
public class MessageLocalizer {
    private static final String BUNDLE_PATH_AND_BASE_NAME = "locale/pagecontent";
    private static final String LOCALE_RU_RU = "ru-RU";
    private static final String LOCALE_EN_US = "en-US";

    /**
     * Initializes a new MessageLocalizer.
     */
    private MessageLocalizer() {
    }

    public static void localize(Map<String, String> errorMap, String sessionLocale) {
        //in order to use jsp and Locale both,
        //cause Locale.forLanguageTag("en_US").toString() return null and is deprecated from Java 8
        String validLocale;
        if (LOCALE_RU.equals(sessionLocale)) {
            validLocale = LOCALE_RU_RU;
        } else {
            validLocale = LOCALE_EN_US;
        }

        var locale = Locale.forLanguageTag(validLocale);
        var messages = ResourceBundle.getBundle(BUNDLE_PATH_AND_BASE_NAME, locale);

        errorMap.entrySet().forEach(el -> el.setValue(messages.getString(el.getValue())));
    }
}
