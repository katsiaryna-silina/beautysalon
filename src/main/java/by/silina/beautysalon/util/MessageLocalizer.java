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

    /**
     * Localizes error map.
     *
     * @param errorMap      Map of String.
     * @param sessionLocale String. Current locale from session.
     */
    public static void getLocalizedMessage(Map<String, String> errorMap, String sessionLocale) {
        var validLocale = getValidLocale(sessionLocale);
        var locale = Locale.forLanguageTag(validLocale);
        var messages = ResourceBundle.getBundle(BUNDLE_PATH_AND_BASE_NAME, locale);

        errorMap.entrySet().forEach(el -> el.setValue(messages.getString(el.getValue())));
    }

    /**
     * Gets localized message by key.
     *
     * @param keyFromProperty String. Key of the message.
     * @param sessionLocale   String. Current locale from session.
     * @return String
     */
    public static String getLocalizedMessage(String keyFromProperty, String sessionLocale) {
        var validLocale = getValidLocale(sessionLocale);
        var locale = Locale.forLanguageTag(validLocale);
        var messages = ResourceBundle.getBundle(BUNDLE_PATH_AND_BASE_NAME, locale);

        return messages.getString(keyFromProperty);
    }

    /**
     * Gets valid locale.
     *
     * @param sessionLocale String. Current locale from session.
     * @return String
     */
    private static String getValidLocale(String sessionLocale) {
        //in order to use jsp and Locale both,
        //cause Locale.forLanguageTag("en_US").toString() return null and is deprecated from Java 8
        if (LOCALE_RU.equals(sessionLocale)) {
            return LOCALE_RU_RU;
        } else {
            return LOCALE_EN_US;
        }
    }
}
