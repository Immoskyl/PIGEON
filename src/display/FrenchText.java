package display;

/**
 * Created by immoskyl on 11/12/16.
 */
public class FrenchText extends ADisplayLanguage {

    public FrenchText(IDisplayType displayType) {
        super(displayType);
    }

    public void addClient() {
        display("Ajoutez un nom de Client");
    }

    public void addAnotherClient() {
        display("Ajoutez un autre Client ou laissez vide pour passer");
    }

    public void addMessage() {
        display("Ajoutez un message ou laissez vide pour passer:");
    }

    public void whoIsTransmitter() {
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

    public void clientSaidMessage(String clientName, String message) {
       display(clientName + " a dit : " + message);
       display("");
    }

    public void chooseFeature() {
        display("Que voulez-vous faire?");
        display("1: Déchiffrement de fichier");
        display("2: Chiffrement de fichier");
        display("3: Lancer la démo");
        display("Tout autre nombre pour arrêter");
    }

    public void greetings() {
        display("Merci d'avoir testé le P.I.G.E.O.N.!");
        display("Donnez vos impressions et commentaires à l'utilisateur Immoskyl sur GitHub!");
    }

    public void askForFile() {
        display("Rentrez l'addresse ou le nom du fichier à décrypter");
    }

    public void askForPigeon() {
        display("Rentrez l'addresse ou le nom du fichier contenant la clé P.I.G.E.O.N.");
    }

    public void changePigeon() {
        display("Voulez-vous changer la clé P.I.G.E.O.N.?");
        display("0: Changer la clé de chiffrement");
        display("1: Déchiffrer un autre fichier avec la même clé");
        display("Tout autre nombre pour arrêter");
    }

    public void fileNotFound() {
        display("Ce fichier ne semble pas exister. Vérifiez et réésayez");
    }

    public void decryptedText() {
        display("Texte déchiffré: ");
    }

    public void fileEmpty() {
        display("Ce fichier est vide! Choississez un autre fichier");
    }
}
