package display;

/**
 * Created by immoskyl on 11/12/16.
 */
public class DisplayFactory {

    public static ConsoleLineDisplay createConsoleLineDisplay () {
        return new ConsoleLineDisplay();
    } //createConsoleLineDisplay()

    public static EnglishText createEnglishText(ConsoleLineDisplay display) {
        return new EnglishText(display);
    } //createEnglishText()

    public static FrenchText createFrenchText(ConsoleLineDisplay display) {
        return new FrenchText(display);
    } //createFrenchText()
}
