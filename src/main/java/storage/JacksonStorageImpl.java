package storage;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class JacksonStorageImpl<T> {

    private final ObjectMapper objectMapper;

    public JacksonStorageImpl() {
        objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

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

    public T load(Class<? extends JsonStorer<T>> classType, Path path) throws IOException {
        JsonStorer<T> jsonStorer = objectMapper.readValue(Paths.get(path.toString()).toFile(), classType);
        return jsonStorer.toJavaType();
    }
}
