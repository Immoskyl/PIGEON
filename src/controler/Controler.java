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

        display.addClient();

        while (true) {
            strInput = scanner.nextLine();
            if (strInput.equals("")) {break;}
            else {
                clientList.add(new Client(strInput));

                display.addAnotherClient();

            }
        }
    } //createClients

    private static void createMessages() {
        String strInput;
        int intInput;
        Scanner scanner = new Scanner(System.in);

        if (!clientList.isEmpty()) {

            display.addMessage();
            display.whoIsTransmiter();

            while (true) {
                for (Client client : clientList) {
                    display.display(client.getiD() + ": " + client.getName());
                }

                strInput = scanner.nextLine();
                if (strInput.equals("")) {
                    break;
                } else {
                    Packet bufferPacket = new Packet();
                    bufferPacket.setiDTransmitter(Integer.parseInt(strInput));

                    display.whoIsReceiver();

                    intInput = scanner.nextInt();
                    scanner.nextLine();
                    bufferPacket.setiDReceiver(intInput);

                    display.writeMessageText();

                    strInput = scanner.nextLine();
                    bufferPacket.setText(strInput);

                    display.chooseEncryptionType();

                    intInput = scanner.nextInt();
                    scanner.nextLine();
                    bufferPacket.setEncryptionType(intInput);

                    clientList.get(getClientPlacement(bufferPacket.getiDTransmitter())).addPacketToSend(bufferPacket);

                    display.addNewMessage();
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
                    display.clientReceivedOneMessage(client.getName());

                    readMessage(client);
                    break;

                default:
                    display.clientReceivedSeveralMessages(client.getName(), client.getPacketReceivedSize());

                    while (client.hasReceivedPacket()) {
                        readMessage(client);
                    }
                    break;
            }
        }
    } //readMessages()

    /**
     *
     * prompt display raw and decrypted message from first paquet received by a client
     */
    private static void readMessage(Client client) {
        Packet packetBuffer;
        packetBuffer = client.selectNextPacketReceived();
        readUndecryptedMessage(packetBuffer); //comment this line to hide crypted messages
        client.decryptPacket(packetBuffer);

        display.clientSaidMessage(getClientName(packetBuffer.getiDTransmitter()), packetBuffer.getText());

        client.removePacketReceived(packetBuffer);
    } //readMessage

    private static void readUndecryptedMessage (Packet packet) {
        display.display(packet.getText());
    } //readUndecryptedMessage

    public static void main(String[] args) {
        createPigeon();
        createClients();
        createMessages();
        sendMessages();
        readMessages();
    } //main
}
