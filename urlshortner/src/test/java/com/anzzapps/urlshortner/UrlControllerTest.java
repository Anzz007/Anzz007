package com.anzzapps.urlshortner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = { "db.path=resources/localDataBaseTest.txt" })
@ExtendWith(MockitoExtension.class)
class UrlControllerTest {

    @Mock
    DataBaseConfig dataBaseConfig;

    @InjectMocks
    UrlController urlController;

    @Test
    void testPostEncryption() throws IOException {
        String s = urlController.shortenUrlViaPost("https://guava.dev/releases/23.0/api/docs/com/google/common/hash/Hashing.html");
        assertNotNull(s);
        assertEquals(s,"http://short.it/7c1b9414");
    }

    @Test
    void testGetEncryption() throws IOException {
        String s = urlController.shortenUrlViaGet("https://guava.dev/releases/23.0/api/docs/com/google/common/hash/Hashing.html");
        assertNotNull(s);
        assertEquals(s,"http://short.it/7c1b9414");
    }

    @Test
    void testRetrieval() throws IOException {
        when(dataBaseConfig.retrieveObject(anyString())).thenReturn("https://guava.dev/releases/23.0/api/docs/com/google/common/hash/Hashing.html");
        String s = urlController.getActualurl("http://short.it/a820536e");
        assertNotNull(s);
        assertEquals(s, "https://guava.dev/releases/23.0/api/docs/com/google/common/hash/Hashing.html");
    }
}