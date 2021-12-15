package com.anzzapps.urlshortner;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/shorten")
public class UrlController {

    private static final String MY_URL_PREFIX = "http://short.it/";

    @Autowired
    DataBaseConfig dataBaseConfig;

    @GetMapping("/url?{url}")
    public String shortenUrlViaGet(@PathVariable String url) throws IOException {
        String shortUrl = validateAndShorten(url);
        dataBaseConfig.saveObject(url, shortUrl);
        return shortUrl;
    }

    @PostMapping
    public String shortenUrlViaPost(@RequestBody String req) throws IOException {
        String shortUrl = validateAndShorten(req);
        dataBaseConfig.saveObject(req, shortUrl);
        return shortUrl;
    }

    @GetMapping("/shortenedUrl?{url}")
    public String getActualurl(@PathVariable String url) {
        String actualurl = "";
        return actualurl;
    }

    private String validateAndShorten(String url) {
        UrlValidator urlValidator = new UrlValidator();
        if (urlValidator.isValid(url)) {
            return MY_URL_PREFIX + Hashing.adler32().hashString(url, StandardCharsets.UTF_8).toString();
        }
        throw new RuntimeException("The Urls is not Valid!!");
    }

}
