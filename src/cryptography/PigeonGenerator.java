package cryptography;

import controller.Controller;
import demo.Packet;

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

    public PigeonGenerator(int length) {
        pigeonList = generateTab(length);
    } //PigeonGenerator

    public List<Double> getPigeonList() {
        return pigeonList;
    } //getPigeonList

    /**
     * generates the PIGEON key
     */
    private List<Double> generateTab (int length) {
        List<Double> array = new ArrayList<>(length);
        for (int i = 0; i != length; ++i) {
            array.add(i, pigeonFormula(i));
        }
        return array;
    } //generateTab()

    /**
     * this is actually the simplified function
     */
    private Double pigeonFormula(int x) {
        return Math.sqrt(Math.pow(Math.cos((double) x) * randomWithRange(0.6 , 1.5) + randomWithRange(1.1 , 1.4), 2));
    } //pigeonFormula

    private Double randomWithRange(Double min, Double max)
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
     * Parse a String to the correspondant List<Double> of the PIGEON key
     */
    public static List<Double> ParsePigeonKey(String str) {
        List<Double> pigeon = new ArrayList<>();
        String newline = System.getProperty("line.separator");

        try {
            for (String nextStrToDecrypt : str.split(newline)) {
                pigeon.add(Double.parseDouble(nextStrToDecrypt));
            }
        } catch (NumberFormatException e) {
            Controller.getInstance().getDisplay().badKey();
        }
        return pigeon;
    }


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
        System.out.println("pigeon crypté");
        System.out.println(testPacket.getText());

        decrypt.decryptPacket(testPacket);
        System.out.println(testPacket.getText());
        System.out.println(testPacket.getText().length() + " characters");
    } //psvm
}
