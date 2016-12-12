package controller;

import cryptography.PigeonFactory;
import cryptography.PigeonGenerator;
import demo.Client;
import demo.DemoFactory;
import demo.Packet;
import display.ADisplayLanguage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by immoskyl on 11/12/16.
 */
public class Demo implements FeatureStrategy {

    private static Demo instance;
    private ADisplayLanguage display;
    private List<Client> clientList = new ArrayList<>();
    private List<Double> pigeonList = new ArrayList<>();

    /**
     * IFeatureStrategy implementation of execute
     * pretty straight forward, just read the function for getting the behavior of the demo
     */
    public void execute() {
        createPigeon();
        createClients();
        createMessages();
        sendMessages();
        readMessages();
    } //execute(()

    /**
     * singleton (so the clients can access the client list and the pigeon key without having to have the demo object as an attribute
     */
    private Demo(){}

    public static Demo getInstance () {
        if (instance == null) {
           instance = new Demo();
        }
        return instance;
    } //getInstance()

    public void setDisplay(ADisplayLanguage display) {
        this.display = display;
    } //setDisplay()

    private String getClientName (int clientID) {
        for (Client client : clientList) {
            if (client.getiD() == clientID) {return client.getName();}
        }
        return "";
    } //getClientName()

    /**
     * return client position in Demo.clientList when client is reffered by id
     */
    private int getClientPlacement (int iD) {
        for (int i = 0; i != clientList.size(); ++i) {
            if (clientList.get(i).getiD() == iD) {return i;}
        }
        return 0;
    } //getClientPlacement()

    public List<Double> getPigeonList() {
        return pigeonList;
    }


    /**
     * populates the Demo.pigeonList with a PigeonGenerator instance
     */
    private void createPigeon() {
        PigeonGenerator pigeon = PigeonFactory.CreatePigeonGenerator();
        pigeonList = pigeon.getPigeonList();
    } //getPigeonList()

    private void createClients() {
        String strInput;
        Scanner scanner = new Scanner(System.in);

        display.addClient();

        while (true) {
            strInput = scanner.nextLine();
            if (strInput.equals("")) {break;}
            else {
                clientList.add(DemoFactory.CreateClient(strInput));

                display.addAnotherClient();

            }
        }
    } //createClients()

    /**
     * allow the user to create messages encapsulated in packets chosen clients will send
     */
    private void createMessages() {
        String strInput;
        int intInput;
        Scanner scanner = new Scanner(System.in);

        if (!clientList.isEmpty()) {

            display.addMessage();
            display.whoIsTransmitter();

            while (true) {
                for (Client client : clientList) {
                    display.display(client.getiD() + ": " + client.getName());
                }

                strInput = scanner.nextLine();
                if (strInput.equals("")) {
                    break;
                } else {
                    Packet bufferPacket = DemoFactory.CreatePacket();
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
    } //createMessages()

    /**
     * sends the messages of every client that has packet to send
     * in order of their creation (lower packet ID first and highest ID last)
     */
    private void sendMessages() {
        Packet packetBuffer;

        for (Client client : clientList) {
            while (client.hasPacketToSend()) {
                packetBuffer = client.selectNextPacketToSend();
                client.encryptPacket(packetBuffer);
                clientList.get((getClientPlacement(packetBuffer.getiDReceiver()))).addPacketReceived(packetBuffer);
                client.removePacketToSend(packetBuffer);
            }
        }
    } //sendMessages()

    /**
     * getDisplay of received packets depending on their number
     */
    private void readMessages() {
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
     * prompt getDisplay raw and decrypted message from first paquet received by a client
     */
    private void readMessage(Client client) {
        Packet packetBuffer;
        packetBuffer = client.selectNextPacketReceived();
        readEncryptedMessage(packetBuffer); //comment this line to hide encrypted messages
        client.decryptPacket(packetBuffer);

        display.clientSaidMessage(getClientName(packetBuffer.getiDTransmitter()), packetBuffer.getText());

        client.removePacketReceived(packetBuffer);
    } //readMessage()

    private void readEncryptedMessage(Packet packet) {
        display.display(packet.getText());
    } //readEncryptedMessage()
}
