package io;

import controller.Controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by immoskyl on 12/12/16.
 */
public class FileReadMacros {

    public static String[] getFileAddressAndText() {
        String[] values = new String[2];
        boolean success = false;
        values[0] = getFileAddress();
        while(!success) {
            try {
                values[1] = ReadFile(values[0], StandardCharsets.UTF_8);
                success = true;
            } catch (IOException e) {
                Controller.getInstance().display().fileNotFound();
            } catch (NumberFormatException e) {
                Controller.getInstance().display().fileEmpty();
            }
        }
        return values;
    }

    public static String getFileAddress() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    } //getFileAddress()

    public static String ReadFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
