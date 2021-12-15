package com.anzzapps.urlshortner;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/shorten")
public class UrlController {

    private static final String MY_URL_PREFIX = "http://short.it/";
    private static final String NOT_VALID = "The Url is not Valid!!";
    private static final String NOT_FOUND = "Url Not Found!";

    @Autowired
    DataBaseConfig dataBaseConfig;

    @GetMapping(value="/url")
    public String shortenUrlViaGet(@RequestParam("u") String url) throws IOException {
        String shortUrl = validateAndShorten(url);
        if (!NOT_VALID.equalsIgnoreCase(shortUrl)) {
            dataBaseConfig.saveObject(url, shortUrl);
        }
        return shortUrl;
    }

    @RequestMapping(value="/ping",method= RequestMethod.GET)
    public String ping(){
        return "Pinging...";
    }

    @PostMapping
    public String shortenUrlViaPost(@RequestBody String req) throws IOException {
        String shortUrl = validateAndShorten(req);
        dataBaseConfig.saveObject(req, shortUrl);
        return shortUrl;
    }

    @RequestMapping(value="/shorturl",method= RequestMethod.GET)
    public String getActualurl(@RequestParam("u") String url) throws IOException {
        UrlValidator urlValidator = new UrlValidator();
        if (urlValidator.isValid(url)) {
            return dataBaseConfig.retrieveObject(url);
        }
        return NOT_FOUND;
    }

    private String validateAndShorten(String url) {
        UrlValidator urlValidator = new UrlValidator();
        if (urlValidator.isValid(url)) {
            return MY_URL_PREFIX + Hashing.adler32().hashString(url, StandardCharsets.UTF_8).toString();
        }
        return NOT_VALID;
    }

}
