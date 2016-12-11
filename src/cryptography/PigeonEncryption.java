package cryptography;

import demo.Packet;
import java.util.List;

/**
 * Created by immoskyl on 12/09/16.
 * PigeonEncryption class
 */
public class PigeonEncryption implements IEncryption {
    private List<Double> pigeonList;

    public PigeonEncryption(List<Double> pigeonList) {
        this.pigeonList = pigeonList;
    } //PigeonEncryption

    /**
     * encrypt the text of a given packet with the PIGEON formula
     */
    public void encryptPacket (Packet packet) {
        packet.setText(encryptString(packet.getText()));
        packet.setEncryptionType(1);
    } //encryptPacket()

    /**
     * encrypt a string with the PIGEON formula, and the PigeonList Key
     * the formula is quite simple and van be improved
     * a lot of casting tho, careful about that
     */
    public String encryptString (String str) {
        String encryptedText = "";
        char nextCharToCrypt;
        String newline = System.getProperty("line.separator");
        Double key;

        for (int i = 0; i != str.length(); ++i) {
            nextCharToCrypt = str.charAt(i);
            key = pigeonList.get(i % pigeonList.size());
            encryptedText += String.valueOf(Math.pow((int) nextCharToCrypt, key)) + newline; //easy formula, but hard to break with a brute force attack
        }
        return encryptedText;
    } //encryptString()
}
