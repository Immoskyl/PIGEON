package controller;


import cryptography.PigeonEncryption;
import cryptography.PigeonFactory;
import cryptography.PigeonGenerator;
import display.ADisplayLanguage;
import io.FileReadMacros;
import io.FileWriteMacros;

import java.util.Scanner;

/**
 * Created by immoskyl on 11/12/16.
 */
public class Write implements FeatureStrategy {
    private PigeonEncryption encryption;
    private ADisplayLanguage display;

    public void setDisplay(ADisplayLanguage display) {
        this.display = display;
    }

    /**
     * sets the pigeon key for the client by whether importing it from a file or generating it
     */
    private void setKey() {
        int intInput;
        Scanner scanner = new Scanner(System.in);

        display.howToGetKey();

        intInput = scanner.nextInt();
        switch (intInput) {
            case 1 :
                importPigeon();
                break;
            case 2 :
                writePigeon(generatePigeon());
                break;
            default:
                //creates a default key if none has been declared yet
                if (encryption == null) {
                    PigeonGenerator pigeon = PigeonFactory.CreatePigeonGenerator();
                    encryption = PigeonFactory.CreatePigeonEncryption(pigeon.getPigeonList());
                    writePigeon(pigeon);
                }
                break;
        }
    } //setKey()

    /**
     * Writes the PIGEON key on a file specified by the user
     */
    private void writePigeon(PigeonGenerator pigeon) {
        String strInput;
        String newline = System.getProperty("line.separator");
        Scanner scanner = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();

        display.writePigeonName();

        strInput = scanner.nextLine();

        for (Double row : pigeon.getPigeonList()) {
            builder.append(row.toString());
            builder.append(newline);
        }
        FileWriteMacros.writeToFile(strInput, builder.toString());
    } //writePigeon()

    /**
     * asks the file having the pigeon key to the user, and loads the key
     */
    private void importPigeon() {
        display.askForPigeon();

        encryption = PigeonFactory.CreatePigeonEncryption(PigeonGenerator.ParsePigeonKey(FileReadMacros.getFileAddressAndText()[1]));
    } //importKey()

    /**
     * creates a new random key where the user has the choice of its length
     */
    private PigeonGenerator generatePigeon() {
        String strInput;
        Scanner scanner = new Scanner(System.in);
        PigeonGenerator pigeon;

        display.keyLength();

        strInput = scanner.nextLine();
        if (strInput.isEmpty()) {
             pigeon = PigeonFactory.CreatePigeonGenerator();
        }
        else {
            pigeon = PigeonFactory.CreatePigeonGenerator(Integer.parseInt(strInput));
        }
        encryption = PigeonFactory.CreatePigeonEncryption(pigeon.getPigeonList());
        return pigeon;
    } //generatePigeon()

    /**
     * asks the user for the name of a file, and tries to decrypt the message it contains
     * if it is successful, asks the user whether to override the file with the translation (aka. the decryption)
     */
    private void encryptFile() {
        display.askForFileToEncrypt();

        String[] values = FileReadMacros.getFileAddressAndText(); //values[0] = file address & values[1] = text
        values[1] = encryption.encryptString(values[1]);
        FileWriteMacros.writeToFile(values[0], values[1]);

        display.encryptionSuccessful();
        display.display(values[1]);
    } //encryptFile()

    /**
     * IFeatureStrategy implementation for execute
     * sets the key and encrypts one file,
     * then ask if the user wants to change key or continue encrypting with the previous one in a loop
     */
    public void execute() {
        int intInput;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        setKey();
        encryptFile();
        do {

            display.writeMenu();

            intInput = scanner.nextInt();
            switch (intInput) {
                case 0 :
                    setKey();
                case 1 :
                    encryptFile();
                    break;
                default:
                    loop = false;
                    break;
            }
        } while (loop);
    } //execute()
}
