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
     * decrypt the text of a given packet with the PIGEON formula
     */
    public void decryptPacket (Packet packet) {

        packet.setText(decryptString(packet.getText()));
        packet.setEncryptionType(0);
    } //decryptPacket

    /**
     * decrypt a string with the PIGEON formula, and the PigeonList key.
     * note that the key has to be the same as the encryption one
     */
    public String decryptString(String str) {
        String decryptedText = "";
        Double key;
        int i = 0;

        for (Double nextDoubleToDecrypt : PigeonGenerator.ParsePigeonKey(str)) {
            key = pigeonList.get(i % pigeonList.size());
            decryptedText += (char) Math.round (Math.pow(nextDoubleToDecrypt, 1.0 / key)); //reverse PIGEON formula
            ++i;
        }
        return decryptedText;
    }
}
