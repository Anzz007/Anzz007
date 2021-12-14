package com.anzzapps.urlshortner;

import com.google.common.hash.Hashing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.validator.routines.UrlValidator;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/shorten")
public class UrlController {

    @GetMapping("/url?{url}")
    public String shortenUrlViaGet(@PathVariable String url) {
        String shortenedUrl = "";
        return shortenedUrl;
    }

    @PostMapping
    public String shortenUrlViaPost(@RequestBody String req) {
        String shortenedUrl = "";
        UrlValidator urlValidator = new UrlValidator();
        if (urlValidator.isValid(req)) {
            shortenedUrl = Hashing.adler32().hashString(req, StandardCharsets.UTF_8).toString();
        }
        return shortenedUrl;
    }

    @GetMapping("/shortenedUrl?{url}")
    public String getActualurl(@PathVariable String url) {
        String actualurl = "";
        return actualurl;
    }

}
