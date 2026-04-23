package io.github.sfakaly.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.sfakaly.model.DataStorage;

import java.io.IOException;
import java.nio.file.Path;

public class JsonHandler {
    private final ObjectMapper mapper;
    private final Path BATH_PATH = Path.of("db/json/tasks.json");

    public JsonHandler() {
        this.mapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .registerModule(new JavaTimeModule());
    }

    // TODO: сделать какой-нибудь класс для управления файлами, папками и тд...
    public void save(DataStorage storage) {
        try {
            mapper.writeValue(BATH_PATH.toFile(), storage);
            System.out.println("Изменения успешно были сохранены в tasks.json");
        } catch (IOException ioe) {
            System.out.println("[!] Ошибка! " + ioe.getMessage());
        }
    }

    private DataStorage load() {
        try {
            System.out.println("Данные успешно были выгружены из tasks.json");
            return mapper.readValue(BATH_PATH.toFile(), DataStorage.class);
        } catch (IOException ioe) {
            System.out.println("[!] Ошибка! " + ioe.getMessage());
            return new DataStorage();
        }
    }
}
