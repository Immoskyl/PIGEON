package controller;

/**
 * Created by immoskyl on 11/12/16.
 */
public class ControllerFactory {

    public static Controller CreateController() {
        return Controller.getInstance();
    } //createController()

    public static Demo CreateDemo() {
        return Demo.getInstance();
    } //createDemo()

    public static Read CreateRead() {
        return new Read();
    } //createRead()

    public static Write CreateWrite() {
        return new Write();
    } //Write()
}
