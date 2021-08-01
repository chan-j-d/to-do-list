package util;

import java.io.File;
import java.nio.file.Path;

public class FileUtil {

    public static void deleteFile(Path path) {
        File file = path.toFile();
        file.delete();
    }

}
