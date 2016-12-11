package demo;

import controller.Demo;
import cryptography.PigeonDecryption;
import cryptography.PigeonEncryption;
import cryptography.PigeonFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Simulates basically the behaviour of a client of a communication protocol
 * Every client has a list of packets to send and a list of packet received, and is able to encrypt/decrypt the packets
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
    }//Client

    private void incrementID () {
        ++sharedID;
        this.iD = sharedID;
    } //incrementID

    public int getiD() {
        return iD;
    } //getiD

    public String getName() {
        return name;
    } //getName

    public void setName(String name) {
        this.name = name;
    } //setName

    public void addPacketReceived(Packet packet) {
        this.packetReceived.add(packet);
    } //addPacketReceived

    public void addPacketToSend(Packet packet) {
        this.packetToSend.add(packet);
    } //addPacketToSend

    public void removePacketReceived(Packet packet) {
        packetReceived.remove(packet);
    } //removePacketReceived

    public void removePacketToSend(Packet packet) {
        packetToSend.remove(packet);
    } //removePacketToSend

    //other methods

    public Packet selectNextPacketToSend() {
        return selectLowerIDPacket(this.packetToSend);
    } //selectNextPacketToSend

    public Packet selectNextPacketReceived() {
        return selectLowerIDPacket(this.packetReceived);
    } //selectNextPacketReceived

    public boolean hasPacketToSend() {
        return this.packetToSend.size() != 0;
    } //hasPacketToSend

    public boolean hasReceivedPacket() {
        return this.packetReceived.size() != 0;
    } //hasPacketReceived

    public int getPacketReceivedSize() {
        return packetReceived.size();
    } //getReceivedSize

    /**
     * little research algorithm here
     */
    private Packet selectLowerIDPacket(List<Packet> list) {
        Packet firstPacket = new Packet();
        firstPacket.setiD(0); //l'ID des packets ne peut pas etre 0
        for (Packet packet : list) {
            if (firstPacket.getiD() != 0) {
                if (firstPacket.getiD() > packet.getiD()) {firstPacket = packet;}
            } else {firstPacket = packet;}
        }
        return firstPacket;
    } //selectLowerIDPacket

    public void encryptPacket(Packet packet) {
        switch (packet.getEncryptionType()) {
            case 0:
                break;

            case 1:
                PigeonEncryption pigeon = PigeonFactory.CreatePigeonEncryption(Demo.getInstance().getPigeonList());
                pigeon.encryptPacket(packet);
                break;
            default:
                packet.setErrorCode(1);
                break;
        }
    } //encryptPacket

    public void decryptPacket(Packet packet) {
        switch (packet.getEncryptionType()) {
            case 0:
                break;

            case 1:
                PigeonDecryption pigeon = PigeonFactory.CreatePigeonDecryption(Demo.getInstance().getPigeonList());
                pigeon.decryptPacket(packet);
                break;
            default:
                packet.setErrorCode(1);
                break;
        }

    } //decryptPacket
}
