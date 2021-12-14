package com.anzzapps.urlshortner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UrlControllerTest {

    @InjectMocks
    UrlController urlController;

    @Test
    void testPostEncryption() {
        String s = urlController.shortenUrlViaPost(
                "https://guava.dev/releases/23.0/api/docs/com/google/common/hash/Hashing.html");

        assertNotNull(s);
    }
}