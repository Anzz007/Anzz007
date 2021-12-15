package com.anzzapps.urlshortner;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Configuration
@Data
public class DataBaseConfig {

    @Value("${db.path}")
    String dbFileLocation;

    @Bean
    public void initializeDbLocally() throws IOException {
        dbFileLocation = System.getProperty("user.home") + dbFileLocation;
        if(!Files.exists(Paths.get(dbFileLocation))) {
            Files.createFile(Paths.get(dbFileLocation));
        }
    }

    public void saveObject(String url, String shortUrl) throws IOException {
        Path fileName = Path.of(this.dbFileLocation);
        Files.lines(fileName).filter(s -> s.contains(url)).findAny().orElseGet(() -> {
            try {
                Files.writeString(fileName, url + "|" + shortUrl + "\n", StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return String.valueOf(fileName);
        });
        System.out.println(this.dbFileLocation);
    }

    public String retrieveObject(String url) throws IOException {
        Path fileName = Path.of(this.dbFileLocation);
        String entry = Files.lines(fileName).filter(s -> s.contains(url)).findAny().orElse("Url Not Found!");
        return entry.split("\\|")[0];
    }
}
