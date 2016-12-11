package display;

/**
 * Created by immoskyl on 11/12/16.
 */
public abstract class ADisplayLanguage {
    private IDisplayType displayType;

    public ADisplayLanguage(IDisplayType displayType) {
        this.displayType = displayType;
    }

    public void display (String s) {
        displayType.display(s);
    } //display()

    public static void ChooseLanguage () {
        System.out.println("Choose your language:");
        System.out.println("1: English");
        System.out.println("2: French");
    } //ChooseLanguage()
}
