package cryptography;

import frontend.Packet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by immoskyl on 12/09/16.
 */
public class PigeonEncryption implements IEncryption {

    List<Double> pigeonList;

    public PigeonEncryption(List<Double> pigeonList) {
        this.pigeonList = pigeonList;
    }

    public void encryptPacket (Packet packet) {

        String encryptedText = "";
        char nextCharToCrypt;
        String newline = System.getProperty("line.separator");

        for (int i = 0; i != packet.getText().length(); ++i) {
            nextCharToCrypt = packet.getText().charAt(i);
            //test purposes
            //original
            //encryptedText += String.valueOf(Math.pow((int) nextCharToCrypt, pigeonList.get(i % pigeonList.size()))) + newline;
            System.out.println("encryption : " + pigeonList.get(i % pigeonList.size()));
            //ascii code
            encryptedText += String.valueOf((int) nextCharToCrypt) + newline;

        }
        packet.setText(encryptedText);
        packet.setEncryptionType(1);
    }
}
