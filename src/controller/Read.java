package controller;

import cryptography.PigeonDecryption;
import cryptography.PigeonFactory;
import cryptography.PigeonGenerator;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by immoskyl on 11/12/16.
 */
public class Read implements FeatureStrategy {

    private PigeonDecryption decryption;

    private String getFileAddress() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    } //getFileAddress()

    private void askPigeon() {
        boolean success = false;

        Controller.getInstance().display().askForPigeon();

        while (!success) {
            try {
                decryption = PigeonFactory.CreatePigeonDecryption(PigeonGenerator.ParsePigeonKey(askFile()));
                success = true;
            } catch (IOException e) {
                Controller.getInstance().display().fileNotFound();
            } catch (NumberFormatException e) {
                Controller.getInstance().display().fileEmpty();
            }
        }
    }

    private void decryptFile() {
        boolean success = false;
        String text;

        Controller.getInstance().display().askForFile();

        while(!success) {
            try {
                text = decryption.decryptString(askFile());
                success = true;
                Controller.getInstance().display().decryptedText();
                Controller.getInstance().display().display(text);
            } catch (IOException e) {
                Controller.getInstance().display().fileNotFound();
            } catch (NumberFormatException e) {
               Controller.getInstance().display().fileEmpty();
            }
        }
    }

    private String askFile() throws IOException {
        return readFile(getFileAddress(), StandardCharsets.UTF_8);
    }

    private String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public void execute() {
        int intInput;
        Scanner scanner = new Scanner(System.in);

        askPigeon();
        decryptFile();

        do {

            Controller.getInstance().display().changePigeon();

            intInput = scanner.nextInt();
            switch (intInput) {
                case 0 :
                    askPigeon();
                    break;
                case 1 :
                    decryptFile();
                    break;
                default:
                    break;
            }
        } while (intInput != 0 && intInput != 1);

    }

}
