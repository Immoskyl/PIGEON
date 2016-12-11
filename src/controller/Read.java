package controller;

import cryptography.PigeonDecryption;
import cryptography.PigeonFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by immoskyl on 11/12/16.
 */
public class Read implements FeatureStrategy {

    private PigeonDecryption decryption;

    private String getFile() {
        Scanner scanner = new Scanner(System.in);

        Controller.getInstance().display().askForFile();

        return scanner.nextLine();
    } //getFile()

    private void askPigeon() {

    }

    private void decryptFile() {
        decryption = PigeonFactory.CreatePigeonDecryption(askFile());


    }
    private void askFile() {
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(getFile());

            String sCurrentLine;

            br = new BufferedReader(new FileReader(getFile()));

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
    }

    public void execute() {
        int intInput;
        Scanner scanner = new Scanner(System.in);


        askPigeon();
        decryptFile();

        while (intInput != 0 && intInput != 1)) {
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
