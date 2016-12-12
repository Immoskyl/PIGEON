package io;

import controller.Controller;
import cryptography.PigeonFactory;
import cryptography.PigeonGenerator;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by immoskyl on 12/12/16.
 */
public class FileRead {

    public static List<Double> GetPigeon() {
        boolean success = false;
        List<Double> pigeon = new ArrayList<>();

        Controller.getInstance().display().askForPigeon();

        while (!success) {
            try {
                pigeon = PigeonGenerator.ParsePigeonKey(askFile());
                success = true;
            } catch (IOException e) {
                Controller.getInstance().display().fileNotFound();
            } catch (NumberFormatException e) {
                Controller.getInstance().display().fileEmpty();
            }
        }
        return pigeon;
    }

    private static String askFile() throws IOException {
        return ReadFile(getFileAddress(), StandardCharsets.UTF_8);
    }

    private static String getFileAddress() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    } //getFileAddress()

    public static String ReadFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
