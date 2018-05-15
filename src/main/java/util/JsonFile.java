package util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class JsonFile {

    public static List<String> readFromFile(String filename) throws IOException {
        return Files.readAllLines(getPath(filename));
    }

    public static void writeJsonInFile(String path, String filename, String json) {
        try {
            Path filePath = createNewFile(path, filename);
            writeInFile(filePath, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Path createNewFile(String path, String filename) throws IOException {
//        Path newFilePath = Paths.get("src/test/resources/newFile_jdk7.txt");
        Path newFilePath = getPath(path + filename);
        return Files.createFile(newFilePath);
    }

    private static void writeInFile(Path path, String valor) throws IOException {
        byte[] strToBytes = valor.getBytes(StandardCharsets.UTF_8);
        Files.write(path, strToBytes, StandardOpenOption.APPEND);
    }

    private static Path getPath(String filename) {
        return Paths.get(filename);
    }


}
