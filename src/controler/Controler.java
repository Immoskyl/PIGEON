package controler;

import cryptography.PigeonGenerator;
import display.ADisplayLanguage;
import display.DisplayFactory;
import frontend.Client;
import frontend.Packet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The purpose of Controler is to encapsulate all the other objects of the demo and to manage them to simulate independant behaviours
 * It contains the psvm
 * For these reasons, all of its methods are static, and there is no instance on contoler
 */
public class Controler {

    private static List<Client> clientList = new ArrayList<>();
    private static List<Double> pigeonList = new ArrayList<>();
    private static ADisplayLanguage display;

    public static String getClientName (int iD) {
        for (Client client : clientList) {
            if (client.getiD() == iD) {return client.getName();}
        }
        return "";
    } //getClientName

    /**
     * return client position in Controler.clientList when client is reffered by id
     */
    private static int getClientPlacement (int iD) {
        for (int i = 0; i != clientList.size(); ++i) {
            if (clientList.get(i).getiD() == iD) {return i;}
        }
        return 0;
    } //getClientPlacement

    public static List<Double> getPigeonList() {
        return pigeonList;
    }


    /**
     * allow the user to choose their favourite language
     * (and easy possibility to extend on a display type choice as well)
     */
    private static void chooseLanguage() {
        int intInput;
        Scanner scanner = new Scanner(System.in);

        ADisplayLanguage.ChooseLanguage();

        intInput = scanner.nextInt();

        switch (intInput) {
            case 1:
                DisplayFactory.createEnglishText(DisplayFactory.createConsoleLineDisplay());
                break;
            case 2:
                DisplayFactory.createFrenchText(DisplayFactory.createConsoleLineDisplay());
                break;
            default:
                DisplayFactory.createEnglishText(DisplayFactory.createConsoleLineDisplay());
                break;
        }
    } //chooseLanguage()

    /**
     * populates the Controler.pigeonList with a PigeonGenerator instance
     */
    private static void createPigeon() {
        PigeonGenerator pigeon= new PigeonGenerator();
        pigeonList = pigeon.getPigeonList();
    } //getPigeonList

    private static void createClients() {
        String strInput;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ajoutez un Client ou laissez vide pour passer");
        while (true) {
            strInput = scanner.nextLine();
            if (strInput.equals("")) {break;}
            else {
                clientList.add(new Client(strInput));
                System.out.println("Ajoutez un autre Client ou laissez vide pour passer");
            }
        }
    } //createClients

    private static void createMessages() {
        String strInput;
        int intInput;
        Scanner scanner = new Scanner(System.in);

        if (!clientList.isEmpty()) {
            System.out.println("Ajoutez un message ou laissez vide pour passer:");
            System.out.println("Qui est l'émetteur (ID)?");

            while (true) {
                for (Client client : clientList) {
                    System.out.println(client.getiD() + ": " + client.getName());
                }

                strInput = scanner.nextLine();
                if (strInput.equals("")) {
                    break;
                } else {
                    Packet bufferPacket = new Packet();
                    bufferPacket.setiDTransmitter(Integer.parseInt(strInput));

                    System.out.println("Qui est le récepteur (ID)?");
                    intInput = scanner.nextInt();
                    scanner.nextLine();
                    bufferPacket.setiDReceiver(intInput);

                    System.out.println("Ecrivez le texte que contriendra le message en une ligne");
                    strInput = scanner.nextLine();
                    bufferPacket.setText(strInput);

                    System.out.println("Choisissez le type de chiffrement utilisé pour ce message");
                    System.out.println("0: Pas de chiffrement");
                    System.out.println("1: Chiffrement P.I.G.E.O.N.");
                    intInput = scanner.nextInt();
                    scanner.nextLine();
                    bufferPacket.setEncryptionType(intInput);

                    clientList.get(getClientPlacement(bufferPacket.getiDTransmitter())).addPacketToSend(bufferPacket);

                    System.out.println("Ajoutez un autre message en donnant le nouvel émetteur ou laissez vide pour passer");

                }
            }
        }
    }

    private static void sendMessages() {
        Packet packetBuffer;

        for (Client client : clientList) {
            while (client.hasPacketToSend()) {
                packetBuffer = client.selectNextPacketToSend();
                client.encryptPacket(packetBuffer);
                clientList.get((getClientPlacement(packetBuffer.getiDReceiver()))).addPacketReceived(packetBuffer);
                client.removePacketToSend(packetBuffer);
            }
        }
    }

    /**
     * display of received packets depending on their number
     */
    private static void readMessages() {
        for (Client client : clientList) {

            switch (client.getPacketReceivedSize()) {
                case 0: break;

                case 1:
                    System.out.println(client.getName() + " a recu 1 message:");
                    readMessage(client);
                    break;

                default:
                    System.out.println(client.getName() + " a recu " + client.getPacketReceivedSize() + " messages");
                    while (client.hasReceivedPacket()) {
                        readMessage(client);
                    }
                    break;
            }
        }
    }

    /**
     *
     * prompt display raw and decrypted message from first paquet received by a client
     */
    private static void readMessage(Client client) {
        Packet packetBuffer;
        packetBuffer = client.selectNextPacketReceived();
        readUndecryptedMessage(packetBuffer); //comment this line to hide crypted messages
        client.decryptPacket(packetBuffer);
        System.out.println(getClientName(packetBuffer.getiDTransmitter()) + " a dit: " + packetBuffer.getText());
        System.out.println("");
        client.removePacketReceived(packetBuffer);
    } //readMessage

    private static void readUndecryptedMessage (Packet packet) {
        System.out.println(packet.getText());
    } //readUndecryptedMessage

    public static void main(String[] args) {
        createPigeon();
        createClients();
        createMessages();
        sendMessages();
        readMessages();
    } //main
}
