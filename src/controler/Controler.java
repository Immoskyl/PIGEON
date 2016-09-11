package controler;

import frontend.Client;
import frontend.Packet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by immoskyl on 06/09/16.
 */
public class Controler {

    private static List<Client> clientList = new ArrayList<>();

    public static String getClientName (int iD) {
        for (Client client : clientList) {
            if (client.getiD() == iD) {return client.getName();}
        }
        return "";
    }

    private static int getClientPlacement (int iD) {
        for (int i = 0; i != clientList.size(); ++i) {
            if (clientList.get(i).getiD() == iD) {return i;}
        }
        return 0;
    }

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
    }

    private static void createMessages() {
        String strInput;
        int intInput;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ajoutez un message ou laissez vide pour passer:");
        System.out.println("Qui est l'émetteur (ID)?");

        while (true) {

            for (Client client : clientList) {
                System.out.println(client.getiD() + ": " + client.getName());
            }

            strInput = scanner.nextLine();
            if (strInput.equals("")) {break;}
            else {
                Packet bufferPacket = new Packet();
                bufferPacket.setiDTransmitter(Integer.parseInt(strInput));

                System.out.println("Qui est le récepteur (ID)?");
                intInput = scanner.nextInt();
                scanner.nextLine();
                bufferPacket.setiDReceiver(intInput);

                System.out.println("Ecrivez le texte que contriendra le message en une ligne");
                strInput = scanner.nextLine();
                bufferPacket.setText(strInput);

                System.out.println("Choisissez le type de cryptage utilisé pour ce message");
                System.out.println("0: Pas de cryptage");
                intInput = scanner.nextInt();
                bufferPacket.setEncryptionType(intInput);

                clientList.get(getClientPlacement(bufferPacket.getiDTransmitter())).addPacketToSend(bufferPacket);

                System.out.println("Ajoutez un autre message en donnant le nouvel émetteur ou laissez vide pour passer");
            }
        }

    }

    private static void sendMessages() {
        Packet packetBuffer;
        Scanner scanner = new Scanner(System.in);

        for (Client client : clientList) {
            while (client.hasPacketToSend()) {
                packetBuffer = client.selectNextPacketToSend();
                client.removePacketToSend(packetBuffer);
                clientList.get((getClientPlacement(packetBuffer.getiDReceiver()))).addPacketReceived(packetBuffer);
            }
        }
    }

    private static void readMessages() {
        Packet packetBuffer;
        Scanner scanner = new Scanner(System.in);

        for (Client client : clientList) {

            switch (client.getPacketReceivedSize()) {
                case 0: break;

                case 1:
                    System.out.println(client.getName() + " a recu 1 message:");
                    packetBuffer = client.selectNextPacketReceived();
                    client.removePacketReceived(packetBuffer);
                    System.out.println(getClientName(packetBuffer.getiDTransmitter()) + " a dit: " + packetBuffer.getText());
                    System.out.println("");
                    break;

                default:
                    System.out.println(client.getName() + " a recu " + client.getPacketReceivedSize() + " messages");
                    while (client.hasReceivedPacket()) {
                        packetBuffer = client.selectNextPacketReceived();
                        client.removePacketReceived(packetBuffer);
                        System.out.println(getClientName(packetBuffer.getiDTransmitter()) + " a dit: " + packetBuffer.getText());
                        System.out.println("");
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {
        createClients();
        createMessages();
        sendMessages();
        readMessages();
    }
}
