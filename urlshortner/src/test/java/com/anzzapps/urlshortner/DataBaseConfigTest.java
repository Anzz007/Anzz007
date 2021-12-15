package com.anzzapps.urlshortner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties = {"db.path=/localDataBaseTest.txt"})
@ExtendWith(MockitoExtension.class)
class DataBaseConfigTest {
    @InjectMocks
    DataBaseConfig dataBaseConfig;

    private static final String TEST_DB_LOCATION = System.getProperty("user.home") + "/localDataBaseTest.txt";

    @Test
    void testSaveObject() throws IOException {
        dataBaseConfig.setDbFileLocation(TEST_DB_LOCATION);
        dataBaseConfig.saveObject("https://stackoverflow.com/questions/31777991/custom-exception-checked-or-unchecked", "http://short.it/7c1b9414");

        assertTrue(Files.exists(Paths.get(TEST_DB_LOCATION)));
        assertTrue(Files.lines(Paths.get(TEST_DB_LOCATION)).findAny().isPresent());
    }

    @Test
    void testRetrieveObject() throws IOException {
        dataBaseConfig.setDbFileLocation(TEST_DB_LOCATION);
        String url = dataBaseConfig.retrieveObject("http://short.it/7c1b9414");

        assertNotNull(url);
        assertEquals("https://stackoverflow.com/questions/31777991/custom-exception-checked-or-unchecked", url);
    }

}