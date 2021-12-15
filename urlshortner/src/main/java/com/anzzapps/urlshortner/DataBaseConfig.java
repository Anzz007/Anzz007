package com.anzzapps.urlshortner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Configuration
public class DataBaseConfig {
    String dbFileLocation;

    @Bean
    public void initializeDbLocally() throws IOException {
        Path tempDirWithPrefix = Files.createDirectories(Paths.get(System.getProperty("user.home") + "/temp"));
        Path fileToCreatePath = Paths.get(System.getProperty("user.home") + "/temp" + "/localDataBase.txt");
        File file = fileToCreatePath.toFile();
        if(!Files.exists(fileToCreatePath)) {
            file = Files.createFile(fileToCreatePath).toFile();
        }
        this.dbFileLocation = file.toString();
    }

    public void saveObject(String req, String shortUrl) throws IOException {
        Path fileName = Path.of(this.dbFileLocation);
        Files.lines(fileName).filter(s -> s.contains(req)).findAny().orElseGet(() -> {
            try {
                Files.writeString(fileName, req + "|" + shortUrl + "\n", StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return String.valueOf(fileName);
        });
        System.out.println(this.dbFileLocation);
    }
}
