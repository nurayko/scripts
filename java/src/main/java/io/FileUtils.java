package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static boolean fileExists(String filePath) {
        return Files.exists(Path.of(filePath));
    }

    public static String getBaseDirectory() {
        return Path.of("").toAbsolutePath().toString();
    }

    public static Boolean isAccessible(Path path) {
        return Files.isRegularFile(path) && Files.isReadable(path);
    }

    public static String readFile(String filePath) throws IOException {
        Path path = Path.of(filePath);
        if (!isAccessible(path)) {
            return "";
        }
        return Files.readString(path);
    }

    public static List<String> readLines(String filePath) throws IOException {
        Path path = Path.of(filePath);
        if (!isAccessible(path)) {
            return new ArrayList<>();
        }
        return Files.readAllLines(Path.of(filePath));
    }

    public static String readFileBuffered(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(Path.of(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return stringBuilder.toString();
    }


    public static void writeFile(String filePath, String content) throws IOException {
        Files.writeString(Path.of(filePath), content);
    }

    public static void writeList(String filePath, List<String> elements) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String element : elements) {
            stringBuilder.append(element).append(System.lineSeparator());
        }
        try {
            writeFile(filePath, stringBuilder.toString());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static void printFilesInDirectory(String filePath) throws IOException {
        Path path = Path.of(filePath);
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(path)) {
            for (Path p : ds) {
                if (Files.isRegularFile(p)) {
                    System.out.println(p);
                }
            }
        }
    }

    public static void printFilesUnderDirectory(String filePath) throws IOException {
        Path path = Path.of(filePath);

        FileVisitor<Path> visitor = new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("File visited:" + file);
                return FileVisitResult.CONTINUE;
            }
        };

        Files.walkFileTree(path, visitor);
    }
}
