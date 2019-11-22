package com.project.shishafan.utils;
/* Created by Kuyan Kirill
 *  Email: progingisfun@gmail.com
 *  Date: 19.11.2019
 */

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WriterUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void writeListAsJsonDataToFile(String filename, List list) {
        Path dataPath = Paths.get(PropertiesUtils.properties().getProperty("data_location"));
        Path file = Paths.get(dataPath.toString() + "\\" + filename + ".json");

        if (!Files.isDirectory(dataPath)) {
            try {
                Files.createDirectory(dataPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
            String s = objectMapper.writeValueAsString(list);
            writer.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
