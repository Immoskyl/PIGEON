package controller;

import cryptography.PigeonDecryption;
import cryptography.PigeonFactory;
import cryptography.PigeonGenerator;

import java.io.IOException;
import java.nio.charset.Charset;
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

        Controller.getInstance().display().askForFile();

        return scanner.nextLine();
    } //getFileAddress()

    private void askPigeon() {

    }

    private void decryptFile() {
        try {
            decryption = PigeonFactory.CreatePigeonDecryption(PigeonGenerator.ParsePigeonKey(askFile()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String askFile() throws IOException{
        return readFile(getFileAddress(), Charset.defaultCharset());
    }

    private String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public void execute() {
        int intInput = 2;
        Scanner scanner = new Scanner(System.in);


        askPigeon();
        decryptFile();

        while (intInput != 0 && intInput != 1) {
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
        }

    }

}
