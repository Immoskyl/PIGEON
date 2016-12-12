package controller;


import cryptography.PigeonEncryption;
import cryptography.PigeonFactory;
import cryptography.PigeonGenerator;
import io.FileReadMacros;

import java.util.Scanner;

/**
 * Created by immoskyl on 11/12/16.
 */
public class Write implements FeatureStrategy {
    private PigeonEncryption encryption;

    /**
     * TODO: creer toutes les strigns de display
     *      + refactor TOUS les displays du programme
     */

    private void setKey() {
        int intInput;
        Scanner scanner = new Scanner(System.in);

        //que voulez vous faire pour générer la clé de chiffrmeent?

        intInput = scanner.nextInt();
        switch (intInput) {
            case 0 :
                //importer pigeon
                importPigeon();
                break;
            case 1 :
                //générer pigeon
                generatePigeon();
                break;
            default:
                //crée une clé par defaut si aucune clé n'a déjà été créée
                if (encryption == null) {
                    PigeonGenerator pigeon = PigeonFactory.CreatePigeonGenerator();
                    encryption = PigeonFactory.CreatePigeonEncryption(pigeon.getPigeonList());

                    //TODO print the pigeon in a file
                }
                break;
        }
    }

    private void importPigeon() {
        Controller.getInstance().display().askForPigeon();

        encryption = PigeonFactory.CreatePigeonEncryption(PigeonGenerator.ParsePigeonKey(FileReadMacros.getFileAddressAndText()[1]));
    }

    private void generatePigeon() {
        String strInput;
        Scanner scanner = new Scanner(System.in);
        PigeonGenerator pigeon;

        //quelle est la longueur de la clé?
        //laisser vide pour une longueur par default (30)

        strInput = scanner.nextLine();
        if (strInput.isEmpty()) {
             pigeon = PigeonFactory.CreatePigeonGenerator();
        }
        else {
            pigeon = PigeonFactory.CreatePigeonGenerator(Integer.parseInt(strInput));
        }
        encryption = PigeonFactory.CreatePigeonEncryption(pigeon.getPigeonList());


        //TODO print the pigeon in a file
    }

    private void encryptFile() {
        Controller.getInstance().display().askForFileToEncrypt();

        String[] values = FileReadMacros.getFileAddressAndText();
        values[1] = encryption.encryptString(values[1]);

        //
        //TODO write on the file
        //

        Controller.getInstance().display().encryptionSuccessful();
    }

    public void execute() {
        int intInput;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        setKey();
        encryptFile();

        do {

            //que voulez vous faire?

            intInput = scanner.nextInt();
            switch (intInput) {
                case 0 :
                    setKey();
                    break;
                case 1 :
                    encryptFile();
                    break;
                default:
                    loop = false;
                    break;
            }
        } while (loop);
    }
}
