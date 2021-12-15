package com.anzzapps.urlshortner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}