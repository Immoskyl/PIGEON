package cryptography;

import frontend.Packet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by immoskyl on 20/09/16.
 */
public class PigeonGenerator {

    private List<Double> pigeonList;

    public PigeonGenerator() {
        pigeonList = generateTab(30);
    }

    public PigeonGenerator(int lenght) {
        pigeonList = generateTab(lenght);
    }

    public List<Double> getPigeonList() {
        return pigeonList;
    }

    private List<Double> generateTab (int lenght) {
        List<Double> array = new ArrayList<>(lenght);
        for (int i = 0; i != (lenght - 1); ++i) {
            array.add(i, pigeonFormula(i));
        }
        return array;
    }

    private Double pigeonFormula(int x) {
        return Math.sqrt(Math.pow(Math.cos((double) x) * randomWithRange(0.6 , 1.4) + randomWithRange(1.1 , 1.4), 2));
    }

    Double randomWithRange(Double min, Double max)
    {
        Double range = (max - min) + 1;
        return (Math.random() * range) + min;
    }

    private void displayPigeonArray () {

        for (int i = 0; i != pigeonList.size(); ++i) {
            System.out.println(pigeonList.get(i));
        }
    }



    //for test purpose

    public static void main(String[] args) {
        PigeonGenerator pigeon = new PigeonGenerator();
        pigeon.displayPigeonArray();

        PigeonEncryption encrypt = new PigeonEncryption(pigeon.getPigeonList());
        PigeonDecryption decrypt = new PigeonDecryption(pigeon.getPigeonList());

        String testString = "Hello World!";
        Packet testPacket = new Packet(testString, 0, 0);
        System.out.println(testString);

        encrypt.encryptPacket(testPacket);
        System.out.println(testPacket.getText());

        decrypt.decryptPacket(testPacket);
        System.out.println(testPacket.getText());
    }
}
