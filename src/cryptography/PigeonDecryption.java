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

        String decryptedText = "";
        Double nextDoubleToDecrypt;
        String newline = System.getProperty("line.separator");
        int i =0;

        for (String nextStrToDecrypt: packet.getText().split(newline)) {
            ++i;
            nextDoubleToDecrypt = Double.parseDouble(nextStrToDecrypt);
            decryptedText += (char) (int) Math.round (Math.pow(nextDoubleToDecrypt, 1.0 / pigeonList.get(i % pigeonList.size())));
        }
        packet.setText(decryptedText);
        packet.setEncryptionType(0);
    }
}
