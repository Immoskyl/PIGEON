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
    private List<Packet> packetReceived;
    private List<Packet> packetToSend;

    //constructor + getters & setters

    public Client(String name) {
        this.name = name;
        this.iD += sharedID;
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

    public void addPacketToSend(Packet packet, int clientID) {
        this.packetToSend.add(packet);
    }

    //other methods
    public Packet sendPacket(Packet packet) {
       return packet;
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
