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
    } //getDisplay()




    //-------------------------------------------------------------
    //-------------------------------------------------------------

    //general
    public abstract void greetings();
    public void blank() {
        for (int i = 0; i != 10; ++i) {
            display("");
        }
    }

    public static void ChooseLanguage () {
        System.out.println("Choose your language:");
        System.out.println("1: English");
        System.out.println("2: French");
    } //ChooseLanguage()

    //in Demo
    public abstract void addClient();
    public abstract void addAnotherClient();
    public abstract void addMessage();
    public abstract void whoIsTransmitter();
    public abstract void whoIsReceiver();
    public abstract void writeMessageText();
    public abstract void chooseEncryptionType();
    public abstract void addNewMessage();
    public abstract void clientReceivedSeveralMessages(String clientName, int numberOfMessages);
    public abstract void clientReceivedOneMessage(String clientName);
    public abstract void clientSaidMessage(String clientName, String message);
    public abstract void chooseFeature();

    //in Read
    public abstract void askForFileToDecrypt();
    public abstract void askForPigeon();
    public abstract void changePigeon();
    public abstract void fileNotFound();
    public abstract void fileEmpty();
    public abstract void decryptedText();
    public abstract void wantToWrite();

    //in Write
    public abstract void askForFileToEncrypt();
    public abstract void encryptionSuccessful();
    public abstract void cantWriteFile(String address);
    public abstract void howToGetKey();
    public abstract void writePigeonName();
    public abstract void keyLength();
    public abstract void writeMenu();
}
