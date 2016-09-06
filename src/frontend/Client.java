package frontend;

import java.util.List;

/**
 * Created by immoskyl on 06/09/16.
 */
public class Client {

    // attributes

    private int iD;
    private static int sharedID;

    private String name;
    private List<Packet> packetReceived;
    private List<Packet> packetToSend;

    //constructor + getters & setters

    public Client(String name) {
        this.name = name;
        this.iD += sharedID;
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

    private void bind() {

    }

    private void sendPacket() {

    }

    private void encryptPacket() {

    }

    private void decryptPacket() {

    }

    private void readReceivedPackets() {
    }
}
