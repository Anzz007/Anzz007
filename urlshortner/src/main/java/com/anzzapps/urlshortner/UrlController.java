package com.anzzapps.urlshortner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shorten")
public class UrlController {

    @GetMapping ("/url?{url}")
    public String shortenUrlViaGet(@PathVariable String url) {
        String shortenedUrl = "";
        return shortenedUrl;
    }

    @PostMapping
    public String shortenUrlViaPost(@RequestBody String req) {
        String shortenedUrl = "";

        return shortenedUrl;
    }

    @GetMapping ("/shortenedUrl?{url}")
    public String getActualurl(@PathVariable String url) {
        String actualurl = "";
        return actualurl;
    }



}
