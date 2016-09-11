package frontend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by immoskyl on 06/09/16.
 */
public class Client {

    // attributes

    private int iD;
    private static int sharedID = 0;

    private String name;
    private List<Packet> packetReceived = new ArrayList<>();
    private List<Packet> packetToSend = new ArrayList<>();

    //constructor + getters & setters

    public Client(String name) {
        this.name = name;
        incrementID();
    }

    private void incrementID () {
        ++sharedID;
        this.iD = sharedID;
    }
    public int getiD() {
        return iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPacketReceived(Packet packet) {
        this.packetReceived.add(packet);
    }

    public void addPacketToSend(Packet packet) {
        this.packetToSend.add(packet);
    }

    //other methods

    //envoie le packet avec le plus petit ID
    public Packet selectNextPacket() {
        return selectLowerIDPacket();
    }

    public boolean hasPacketToSend() {
        return this.packetToSend.size() != 0;
    }

    private Packet selectLowerIDPacket() {
        Packet firstPacket = new Packet();
        firstPacket.setiD(0); //l'ID des packets ne peut pas etre 0
        for (Packet packet : this.packetToSend) {
            if (firstPacket.getiD() != 0) {
                if (firstPacket.getiD() > packet.getiD()) {firstPacket = packet;}
            } else {firstPacket = packet;}
        }
        return firstPacket;
    }

    private void encryptPacket() {

    }

    private void decryptPacket() {

    }

    private void readReceivedPackets() {
        for (Packet packet : packetReceived) {
            System.out.println(controler.Controler.getClientName(packet.getiDTransmitter()) + " a dit à " + this.name + ": " + packet.getText());
        }
    }
}
