package io.github.sfakaly.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.sfakaly.exceptions.JsonStorageException;
import io.github.sfakaly.model.DataStorage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class JsonHandler {
    private final Path basePath;
    private final ObjectMapper mapper;

    public JsonHandler(String path) {
        this.basePath = Path.of(path);
        DirectoryManager.ensureDirectoryExists(path);

        this.mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public void saveStorage(DataStorage storage) {
        try {
            mapper.writeValue(basePath.toFile(), storage);
        } catch (IOException ioe) {
            throw new JsonStorageException("Ошибка сохранения в JSON-файл!");
        }
    }

    public DataStorage loadStorage() {
        File file = basePath.toFile();

        // проверка на наличие файла
        if (!file.exists()) return new DataStorage();

        // проверка на пустой файл
        if (file.length() == 0) return new DataStorage();

        try {
            return mapper.readValue(file, DataStorage.class);
        } catch (IOException ioe) {
            throw new JsonStorageException("Ошибка чтения JSON-файла!");
        }
    }
}
