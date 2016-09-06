package controler;

import frontend.Client;
import frontend.Packet;

import java.util.List;
import java.util.Scanner;

/**
 * Created by immoskyl on 06/09/16.
 */
public class Controler {

    private static List<Client> clientList;

    public static String getClientName (int iD) {
        for (Client client : clientList) {
            if (client.getiD() == iD) {return client.getName()}
        }
    }

    private static int getClientPlacement (int iD) {
        for (int i = 0; i != clientList.size(); ++i) {
            if (clientList.get(i).getiD() == iD) {return i;}
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //création des clients


        String strInput;

        System.out.println("Ajoutez un Client ou laissez vide pour passer");
        while (true) {
            strInput = scanner.nextLine();
            if (strInput.equals("")) {break}
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
            strInput = scanner.nextLine();
            if (strInput.equals("")) {break}
            else {
                Packet bufferPacket = new Packet(i, "", 0, Integer.parseInt(strInput));

                System.out.println("Qui est le récepteur (ID)?");
                intInput = scanner.nextInt();
                bufferPacket.setiDReceiver(intInput);

                System.out.println("Ecrivez le texte que contriendra le message");
                strInput = scanner.nextLine();
                bufferPacket.setText(strInput);
                clientList.get(getClientPlacement()).addPacketToSend(bufferPacket);
                System.out.println("Ajoutez un autre Client ou laissez vide pour passer");
            }
        }
    }
}
