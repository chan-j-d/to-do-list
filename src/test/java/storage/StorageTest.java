package storage;

import org.junit.jupiter.api.BeforeAll;
import util.TestUtil;

public class StorageTest {

    @BeforeAll
    public static void setLogsDirectory() {
        TestUtil.setLogsDirectory();
    }
}
