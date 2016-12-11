package frontend;

/**
 * Contains all the informations useful for communication purposes
 * Uses Client.iD instead of physical addresses
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
    } //Packet

    /**
     * for test purposes
     */
    public Packet(String text, int iDTransmitter, int iDReceiver) {
        incrementID();
        this.text = text;
        this.iDTransmitter = iDTransmitter;
        this.iDReceiver = iDReceiver;
    } //Packet

    private void incrementID () {
        ++sharedID;
        this.iD = sharedID;
    } //incrementiD

    public int getiD() {
        return iD;
    } //getiD

    public void setiD(int iD) {
        this.iD = iD;
    } //setiD

    public int getEncryptionType() {
        return encryptionType;
    } //getEncryptionType

    public void setEncryptionType(int encryptionType) {
        this.encryptionType = encryptionType;
    } //setEncryptiontype

    public String getText() {
        return text;
    } //getText

    public void setText(String text) {
        this.text = text;
    } //setText

    public int getErrorCode() {
        return errorCode;
    } //getErrorCode

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    } //setErrorCode

    public int getiDTransmitter() {
        return iDTransmitter;
    } //getiDTransmitter

    public void setiDTransmitter(int iDTransmitter) {
        this.iDTransmitter = iDTransmitter;
    } //setiDTransmitter

    public int getiDReceiver() {
        return iDReceiver;
    } //getiDReceiver

    public void setiDReceiver(int iDReceiver) {
        this.iDReceiver = iDReceiver;
    } //setiDReceiver
}
