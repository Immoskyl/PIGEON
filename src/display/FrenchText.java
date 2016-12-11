package display;

/**
 * Created by immoskyl on 11/12/16.
 */
public class FrenchText extends ADisplayLanguage {

    public FrenchText(IDisplayType displayType) {
        super(displayType);
    }

    public void addClient() {
        display("Ajoutez un Client ou laissez vide pour passer");
    }

    public void addAnotherClient() {
        display("Ajoutez un autre Client ou laissez vide pour passer");
    }

    public void addMessage() {
        display("Ajoutez un message ou laissez vide pour passer:");
    }

    public void whoIsTransmiter() {
        display("Qui est l'émetteur (ID)?");
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
}
