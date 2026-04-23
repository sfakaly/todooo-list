package io.github.sfakaly.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.sfakaly.model.DataStorage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class JsonHandler {
    private final ObjectMapper mapper;
    private final Path BATH_PATH = Path.of("db/json/tasks.json");

    public JsonHandler() {
        this.mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    // TODO: сделать какой-нибудь класс для управления файлами, папками и тд...
    public void saveStorage(DataStorage storage) {
        try {
            mapper.writeValue(BATH_PATH.toFile(), storage);
        } catch (IOException ioe) {
            System.out.println("[!] Ошибка сохранения в JSON-файл! " + ioe.getMessage());
        }
    }

    public DataStorage loadStorage() {
        File file = BATH_PATH.toFile();

        // проверка на наличие файла
        if (!file.exists()) {
            return new DataStorage();
        }

        // проверка на пустой файл
        if (file.length() == 0) {
            return new DataStorage();
        }

        try {
            return mapper.readValue(file, DataStorage.class);
        } catch (IOException ioe) {
            System.out.println("[!] Ошибка чтения JSON-файла! " + ioe.getMessage());
            return new DataStorage();
        }
    }
}
