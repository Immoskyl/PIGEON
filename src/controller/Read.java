package controller;

import cryptography.PigeonDecryption;
import cryptography.PigeonFactory;
import cryptography.PigeonGenerator;
import io.FileReadMacros;

import java.util.Scanner;


/**
 * Created by immoskyl on 11/12/16.
 */
public class Read implements FeatureStrategy {

    private PigeonDecryption decryption;

    private void askPigeon() {
        Controller.getInstance().display().askForPigeon();

        decryption = PigeonFactory.CreatePigeonDecryption(PigeonGenerator.ParsePigeonKey(FileReadMacros.getFileAddressAndText()[1]));
    }

    private void decryptFile() {
        String decryptedText;

        Controller.getInstance().display().askForFileToDecrypt();

        decryptedText = decryption.decryptString(FileReadMacros.getFileAddressAndText()[1]);

        Controller.getInstance().display().decryptedText();
        Controller.getInstance().display().display(decryptedText);
    }

    public void execute() {
        int intInput;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

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
                    loop = false;
                    break;
            }
        } while (loop);
    }
}
