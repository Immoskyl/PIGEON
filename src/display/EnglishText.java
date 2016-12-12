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

    public void askForFileToDecrypt() {
        display("Enter the name of the file to decrypt");
    }

    public void askForFileToEncrypt() {
        display("Rentrez le nom du fichier à chiffrer");
    }

    public void askForPigeon() {
        display("Enter the name of the file containing the P.I.G.E.O.N. key");
    }

    public void changePigeon() {
        display("Do you want to change P.I.G.E.O.N. key?");
        display("0: Change encryption key");
        display("1: Decrypt another file with the same key");
        display("Any other number to stop");
    }

    public void fileNotFound() {
        display("Such a file does not seem to exist. Verify and try again");
    }

    public void decryptedText() {
        display("Decrypted text: ");
    }

    public void fileEmpty() {
        display("This file is empty! Choose another file");
    }

    public void encryptionSuccessful() {
        display("Encryption Successful!");
        display("");
    }

    public void cantWriteFile(String address) {
        display("Cannot print on the file " + address + " !!!");
    }

    public void howToGetKey() {
        display("How to get encryption key?");
        display("1: Import already existing key");
        display("2: Generate a new key");
        display("Any other number to generate a default key, if none has been recorded yet");
    }

    public void writePigeonName() {
        display("Write a name for creating the file wich will contain the key");
    }

    public void keyLength() {
        display("What size do you want the key to be?");
        display("Leave blank for default size (30)");
    }

    public void writeMenu() {
        display("Do you want to change P.I.G.E.O.N. key?");
        display("0: Change encryption key");
        display("1: Encrypt another file with the same key");
        display("Any other number to stop");
    }
}
