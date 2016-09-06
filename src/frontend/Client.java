package frontend;

import java.util.List;

/**
 * Created by immoskyl on 06/09/16.
 */
public class Client {

    private int iD;
    private static int sharedID;

    private String name;
    private List<Packet> packetReceived;
    private List<Packet> packetToSend;



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
