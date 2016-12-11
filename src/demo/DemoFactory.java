package demo;

/**
 * Created by immoskyl on 11/12/16.
 */
public class DemoFactory {

    public static Client CreateClient (String clientName) {
        return new Client(clientName);
    } //CreateClient()

    public static Packet CreatePacket () {
        return new Packet();
    } //CreatePacket()
}
