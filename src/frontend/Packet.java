package frontend;

/**
 * Created by immoskyl on 06/09/16.
 */
public class Packet {

    //attributes

    private int iD;
    private static int sharedID = 0;
    private int encryptionType = 0;
    private String text;
    private int errorCode = 0;
    private int iDTransmitter;
    private int iDReceiver;

    //constructor + getters & setters

    public Packet() {
        incrementID();
    }

    public Packet(String text, int iDTransmitter, int iDReceiver) {
        incrementID();
        this.text = text;
        this.iDTransmitter = iDTransmitter;
        this.iDReceiver = iDReceiver;
    }

    private void incrementID () {
        ++sharedID;
        this.iD = sharedID;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public int getEncryptionType() {
        return encryptionType;
    }

    public void setEncryptionType(int encryptionType) {
        this.encryptionType = encryptionType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getiDTransmitter() {
        return iDTransmitter;
    }

    public void setiDTransmitter(int iDTransmitter) {
        this.iDTransmitter = iDTransmitter;
    }

    public int getiDReceiver() {
        return iDReceiver;
    }

    public void setiDReceiver(int iDReceiver) {
        this.iDReceiver = iDReceiver;
    }
}
