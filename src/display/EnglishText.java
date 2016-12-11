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

    public void whoIsTransmiter() {
        display("Who is the tansmitter (ID)?");
    }

    public void whoIsReceiver() {
        display("Qui est le récepteur (ID)?");
    }

    public void  writeMessageText() {
        display("Ecrivez le texte que contriendra le message en une ligne");
    }

    public void  chooseEncryptionType() {
        display("Choisissez le type de chiffrement utilisé pour ce message");
        display("0: Pas de chiffrement");
        display("1: Chiffrement P.I.G.E.O.N.");
    }

    public void addNewMessage() {
        display("Ajoutez un autre message en donnant le nouvel émetteur ou laissez vide pour passer");
    }

    public void clientReceivedOneMessage(String clientName) {
        display(clientName + " a recu 1 message:");
    }

    public void clientReceivedSeveralMessages(String clientName, int numberOfMessages) {
        display(clientName + " a recu " + numberOfMessages + " messages:");
    }

    public void clientSaidMessage(String clientName, String message) {
        display(clientName + " a dit : " + message);
        display("");
    }
}
