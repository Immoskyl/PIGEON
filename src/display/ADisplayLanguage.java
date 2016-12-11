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


    //-------------------------------------------------------------
    //-------------------------------------------------------------


    public abstract void addClient();
    public abstract void addAnotherClient();
    public abstract void addMessage();
    public abstract void whoIsTransmiter();
    public abstract void whoIsReceiver();
    public abstract void writeMessageText();
    public abstract void chooseEncryptionType();
    public abstract void addNewMessage();
    public abstract void clientReceivedSeveralMessages(String clientName, int numberOfMessages);
    public abstract void clientReceivedOneMessage(String clientName);
    public abstract void clientSaidMessage(String clientName, String message);
}
