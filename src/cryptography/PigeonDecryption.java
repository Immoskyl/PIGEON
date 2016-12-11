package cryptography;

import demo.Packet;
import java.util.List;

/**
 * Created by immoskyl on 12/09/16.
 * PigeonDecryption class
 */
public class PigeonDecryption implements IDecryption {
    List<Double> pigeonList;

    public PigeonDecryption(List<Double> pigeonList) {
        this.pigeonList = pigeonList;
    } //PigeonDecryption

    /**
     * decrypt the text of a given packet with the PIGEON formula, and the PigeonList key.
     * note that the key has to be the same as the encryption one
     */
    public void decryptPacket (Packet packet) {
        String decryptedText = "";
        Double nextDoubleToDecrypt;
        Double key;
        String newline = System.getProperty("line.separator");
        int i = 0;

        for (String nextStrToDecrypt: packet.getText().split(newline)) {
            nextDoubleToDecrypt = Double.parseDouble(nextStrToDecrypt);
            key = pigeonList.get(i % pigeonList.size());
            decryptedText += (char) Math.round (Math.pow(nextDoubleToDecrypt, 1.0 / key)); //reverse PIGEON formula
            ++i;
        }
        packet.setText(decryptedText);
        packet.setEncryptionType(0);
    } //decryptPacket
}
