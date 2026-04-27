package io.github.sfakaly.config;

import io.github.sfakaly.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream is = Main.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (is == null) System.out.println("Файл application.properties не найден. Используем дефолтные значения.");
            else properties.load(is);
        } catch (IOException ioe) {
            throw new RuntimeException("Ошибка: " + ioe.getMessage());
        }
    }

    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
