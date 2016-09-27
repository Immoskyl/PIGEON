package cryptography;

import frontend.Packet;

import java.util.List;

/**
 * Created by immoskyl on 12/09/16.
 */
public class PigeonDecryption implements IDecryption {
    List<Double> pigeonList;

    public PigeonDecryption(List<Double> pigeonList) {
        this.pigeonList = pigeonList;
    }

    public void decryptPacket (Packet packet) {

        //List<Double> encryptedText = new ArrayList<>(packet.getText().length());
        String decryptedText = "";
        char nextCharToDecrypt
        Double nextDoubleToDecrypt
        String newline = System.getProperty("line.separator");

        for (int i = 0; i != packet.getText().length(); ++i) {
            nextCharToDecrypt = packet.getText().charAt(i);

            for (String nextStrToDecrypt: packet.getText().split(System.getProperty("line.separator"))) {
                nextDoubleToDecrypt = nextStrToDecrypt;
            }

            //encryptedText.add(Math.pow((int) nextCharToDecrypt, pigeonList.get(i % pigeonList.size()));
            //decryptedText += String.valueOf(Math.pow((int) nextCharToDecrypt, pigeonList.get(i % pigeonList.size()));
            decryptedText += (char)(Math.pow(nextCharToDecrypt, 1.0 / pigeonList.get(i % pigeonList.size())));
        }
        packet.setText(decryptedText);
        packet.setEncryptionType(0);
    }
}
