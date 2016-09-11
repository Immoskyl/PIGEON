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

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //création des clients


        String strInput;

        System.out.println("Ajoutez un Client ou laissez vide pour passer");
        while (true) {
            strInput = scanner.nextLine();
            if (strInput.equals("")) {break;}
            else {
                clientList.add(new Client(strInput));
                System.out.println("Ajoutez un autre Client ou laissez vide pour passer");
            }
        }

        //création des messages

        int intInput;

        System.out.println("Ajoutez un message ou laissez vide pour passer:");
        for (Client client : clientList) {
            System.out.println(client.getiD() + ": " + client.getName());
        }

        System.out.println("Qui est l'émetteur (ID)?");
        for (int i = 1;; ++i) {


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

                clientList.get(getClientPlacement(bufferPacket.getiDTransmitter())).addPacketToSend(bufferPacket);

                System.out.println("Ajoutez un autre message en donnant le nouvel émetteur ou laissez vide pour passer");
            }
        }

        //envoi des messages

        {
            Packet packetBuffer;
            for (Client client : clientList) {
                while (client.hasPacketToSend()) {
                    packetBuffer = client.selectNextPacketToSend();
                    client.removePacketToSend(packetBuffer);
                    clientList.get((packetBuffer.getiDReceiver())).addPacketReceived(packetBuffer);
                }
            }
        }

        //lecture des messages

        {
            Packet packetBuffer;
            for (Client client : clientList) {

                if (client.getPacketReceivedSize() != 0) {
                    System.out.println(client.getName() + "a recu " + client.getPacketReceivedSize() + "messages");
                    while (client.hasReceivedPacket()) {
                        packetBuffer = client.selectNextPacketReceived();
                        client.removePacketReceived(packetBuffer);
                        System.out.println(getClientName(packetBuffer.getiDTransmitter()) + " a dit: " + packetBuffer.getText());
                    }
                }
            }
        }

    }
}
