package cryptography;

import demo.Packet;
import java.util.List;

/**
 * Created by immoskyl on 12/09/16.
 * PigeonEncryption class
 */
public class PigeonEncryption implements IEncryption {

    List<Double> pigeonList;

    public PigeonEncryption(List<Double> pigeonList) {
        this.pigeonList = pigeonList;
    } //PigeonEncryption

    /**
     * encrypt the text of a given packet with the PIGEON formula, and the PigeonList Key
     * the formula is quite simple and van be improved
     * a lot of casting tho, careful about that
     */
    public void encryptPacket (Packet packet) {
        String encryptedText = "";
        char nextCharToCrypt;
        String newline = System.getProperty("line.separator");
        Double key;

        for (int i = 0; i != packet.getText().length(); ++i) {
            nextCharToCrypt = packet.getText().charAt(i);
            key = pigeonList.get(i % pigeonList.size());
            encryptedText += String.valueOf(Math.pow((int) nextCharToCrypt, key)) + newline; //easy formula, but hard to breack with a brute force attack
        }
        packet.setText(encryptedText);
        packet.setEncryptionType(1);
    } //encryptPacket
}
