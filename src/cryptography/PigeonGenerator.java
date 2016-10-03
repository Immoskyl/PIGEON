package cryptography;

import frontend.Packet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The PigeonGenerator creates and populates a PigeonList with random values, matching the pigeon head bobbing over time representative function
 * The final goal would be to use different real tables for each used key, but the first step was simulating with this algorithm
 */
public class PigeonGenerator {

    private List<Double> pigeonList;

    public PigeonGenerator() {
        pigeonList = generateTab(30);
    } //PigeonGenerator

    public PigeonGenerator(int lenght) {
        pigeonList = generateTab(lenght);
    } //PigeonGenerator

    public List<Double> getPigeonList() {
        return pigeonList;
    } //getPigeonList

    private List<Double> generateTab (int lenght) {
        List<Double> array = new ArrayList<>(lenght);
        for (int i = 0; i != (lenght - 1); ++i) {
            array.add(i, pigeonFormula(i));
        }
        return array;
    } //generateTab

    /**
     * this is actually the simplified function
     */
    private Double pigeonFormula(int x) {
        return Math.sqrt(Math.pow(Math.cos((double) x) * randomWithRange(0.6 , 1.4) + randomWithRange(1.1 , 1.4), 2));
    } //pigeonFormula

    Double randomWithRange(Double min, Double max)
    {
        Double range = (max - min) + 1;
        return (Math.random() * range) + min;
    } //randomWithRange

    /**
     * for test purposes
     */
    private void displayPigeonArray () {

        for (int i = 0; i != pigeonList.size(); ++i) {
            System.out.println(pigeonList.get(i));
        }
    } //displayPigeonArray



    /**
     * for test purposes
     */
    public static void main(String[] args) {
        PigeonGenerator pigeon = new PigeonGenerator();
        pigeon.displayPigeonArray();

        PigeonEncryption encrypt = new PigeonEncryption(pigeon.getPigeonList());
        PigeonDecryption decrypt = new PigeonDecryption(pigeon.getPigeonList());

        String testString = "Hello World!";
        Packet testPacket = new Packet(testString, 0, 0);
        System.out.println(testString);
        System.out.println(testPacket.getText().length() + " characters");

        encrypt.encryptPacket(testPacket);

        //uncomment the following to print crypted pigeon
        //System.out.println("pigeon crypté");
        //System.out.println(testPacket.getText());

        decrypt.decryptPacket(testPacket);
        System.out.println(testPacket.getText());
        System.out.println(testPacket.getText().length() + " characters");
    } //psvm
}
