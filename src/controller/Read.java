package controller;

import cryptography.PigeonDecryption;
import cryptography.PigeonFactory;
import cryptography.PigeonGenerator;
import display.ADisplayLanguage;
import io.FileReadMacros;
import io.FileWriteMacros;

import java.util.Scanner;


/**
 * Created by immoskyl on 11/12/16.
 */
public class Read implements FeatureStrategy {

    private PigeonDecryption decryption;
    private ADisplayLanguage display;

    public void setDisplay(ADisplayLanguage display) {
        this.display = display;
    }

    private void askPigeon() {
        display.askForPigeon();

        decryption = PigeonFactory.CreatePigeonDecryption(PigeonGenerator.ParsePigeonKey(FileReadMacros.getFileAddressAndText()[1]));
    }

    private void decryptFile() {
        String[] addressAndText;
        int intInput;
        Scanner scanner = new Scanner(System.in);

        display.askForFileToDecrypt();

        addressAndText = FileReadMacros.getFileAddressAndText();
        addressAndText[1] = decryption.decryptString(addressAndText[1]);

        display.decryptedText();
        display.display(addressAndText[1]);
        display.wantToWrite();

        intInput = scanner.nextInt();
        switch (intInput) {
            case 1 :
                FileWriteMacros.writeToFile(addressAndText[0], addressAndText[1]);
                break;
            default:
                break;
        }
    }

    public void execute() {
        int intInput;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        askPigeon();
        decryptFile();

        do {
            display.changePigeon();

            intInput = scanner.nextInt();
            switch (intInput) {
                case 0 :
                    askPigeon();
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
