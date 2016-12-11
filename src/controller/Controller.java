package controler;

import display.ADisplayLanguage;
import display.DisplayFactory;

import java.util.Scanner;

/**
 *
 */
public class Controller {

    private static Controller instance = null;
    private ADisplayLanguage display;
    private FeatureStrategy feature;


    private Controller() {}

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    } //getInstance()

    /**
     * getDisplay() method, but I found this name more ergonomic and logic to use in the current context
     * @return display
     */
    public ADisplayLanguage display() {
        return display;
    }

    public FeatureStrategy getFeatureStrategy() {
        return feature;
    } //getFeatureStrategy()

    public static FeatureStrategy GetFeatureStrategy() {
        return instance.getFeatureStrategy();
    } //getFeatureStrategy()

    public void executeFeature() {
        feature.execute();
    } //executeFeature()

    /**
     * allow the user to choose their favourite language
     * (and easy possibility to extend on a display type choice as well)
     */
    private void chooseLanguage() {
        int intInput;
        Scanner scanner = new Scanner(System.in);

        ADisplayLanguage.ChooseLanguage();

        intInput = scanner.nextInt();

        switch (intInput) {
            case 1:
                display = DisplayFactory.createEnglishText(DisplayFactory.createConsoleLineDisplay());
                break;
            case 2:
                display = DisplayFactory.createFrenchText(DisplayFactory.createConsoleLineDisplay());
                break;
            default:
                display = DisplayFactory.createEnglishText(DisplayFactory.createConsoleLineDisplay());
                break;
        }
    } //chooseLanguage()

    private void chooseFeatureStrategy() {
        int intInput;
        Scanner scanner = new Scanner(System.in);

        ADisplayLanguage.ChooseLanguage();

        intInput = scanner.nextInt();

        switch (intInput) {
            case 1:
                feature = new Read();
                break;
            case 2:
                feature = new Write();
                break;
            case 3:
                feature = new Demo();
                break;
            default:
                feature = null;
                break;
        }
    } //chooseFeatureStrategy()

    public static void main(String[] args) {
        Controller controller = new Controller();

        controller.chooseLanguage();
        controller.chooseFeatureStrategy();

        while (controller.getFeatureStrategy() != null) {
            controller.executeFeature();
            controller.chooseFeatureStrategy();
        }
    } //main()
}
