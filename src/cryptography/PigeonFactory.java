package cryptography;

import java.util.List;

/**
 * Created by immoskyl on 11/12/16.
 */
public class PigeonFactory {

    public static PigeonGenerator CreatePigeonGenerator() {
       return new PigeonGenerator();
    } //CreatePigeonGenerator()

    public static PigeonEncryption CreatePigeonEncryption(List<Double> pigeonList) {
        return new PigeonEncryption(pigeonList);
    } //CreatePigeonEncryption()

    public static PigeonDecryption CreatePigeonDecryption (List<Double> pigeonList) {
        return new PigeonDecryption(pigeonList);
    } //CreatePigeonDecryption()
}
