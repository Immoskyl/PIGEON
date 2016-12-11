package display;

/**
 * Created by immoskyl on 11/12/16.
 */
public class EnglishText extends ADisplayLanguage {

    public EnglishText(IDisplayType displayType) {
        super(displayType);
    }


    public void addClient() {
        display("Add a new Client");
    }

    public void addAnotherClient() {
        display("Add another new Client or leave blank to move on");
    }

    public void addMessage() {
        display("Add a message or leave blank to move on:");
    }

    public void whoIsTransmitter() {
        display("Who is the tansmitter (ID)?");
    }

    public void whoIsReceiver() {
        display("Who is the receiver (ID)?");
    }

    public void  writeMessageText() {
        display("Write the text of the message in one line");
    }

    public void  chooseEncryptionType() {
        display("Choose what encryption type will be used on the message");
        display("0: No encryption");
        display("1: P.I.G.E.O.N. encryption");
    }

    public void addNewMessage() {
        display("Add new message by giving a new transmitter or leave blank to move on");
    }

    public void clientReceivedOneMessage(String clientName) {
        display(clientName + " received 1 message:");
    }

    public void clientReceivedSeveralMessages(String clientName, int numberOfMessages) {
        display(clientName + " received " + numberOfMessages + " messages:");
    }

    public void clientSaidMessage(String clientName, String message) {
        display(clientName + " said: " + message);
        display("");
    }

    public void chooseFeature() {
        display("What do you want to do?");
        display("1: File decryption");
        display("2: File encryption");
        display("3: Launch demo");
        display("Any other number to stop");
    }

    public void greetings() {
        display("Thanks for testing P.I.G.E.O.N.!");
        display("Send any feedback or comment to user Immoskyl on GitHub");
    }

    public void askForFile() {
        display("Enter the address or the name of the file to decrypt");
    }
}
