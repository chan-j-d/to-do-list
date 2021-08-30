package storage;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import logging.LogsManager;

public abstract class JacksonStorageImpl<T> {

    private static final Logger LOGGER = LogsManager.getLogger(JacksonStorageImpl.class);
    private static final String MESSAGE_FIELD_NOT_FOUND = "At least one field was missing when loading.";

    private final ObjectMapper objectMapper;

    public JacksonStorageImpl() {
        objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    /**
     * Saves a {@code JsonStorer} object to the designated path.
     * This method handles missing directory paths specified by the {@code path}.
     * Throws {@code IOException} if it is unable to save the object.
     */
    public boolean save(JsonStorer<T> storer, Path path) throws IOException {
        boolean doesFileExist = Files.exists(path);
        Path parentDirectory = path.getParent();
        boolean doesParentDirectoryExist = Files.exists(parentDirectory);
        if (!doesFileExist && !doesParentDirectoryExist) {
            Files.createDirectory(parentDirectory);
        }

        objectMapper.writeValue(Paths.get(path.toString()).toFile(), storer);
        return true;
    }

    /**
     * Loads the object specified by {@code path} into the provided {@code classType}.
     * If there are missing fields in the save file compared to the {@code JsonStorer<T> type},
     * a message is logged and the field is given a default value.
     * Throws {@code IOException} if the load was unsuccessful for other reasons.
     */
    public T load(Class<? extends JsonStorer<T>> classType, Path path) throws IOException {
        JsonStorer<T> jsonStorer = null;
        try {
            jsonStorer = objectMapper.readValue(Paths.get(path.toString()).toFile(), classType);
        } catch (JsonMappingException jme) {
            LOGGER.log(Level.INFO, MESSAGE_FIELD_NOT_FOUND, jme);
        }
        return jsonStorer.toJavaType();
    }
}
