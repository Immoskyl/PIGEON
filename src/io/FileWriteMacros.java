package io;

import controller.Controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by immoskyl on 12/12/16.
 */
public class FileWriteMacros {

    public static void writeToFile(String address, String text) {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter(address);
            bw = new BufferedWriter(fw);
            bw.write(text);
        } catch (IOException e) {
            Controller.getInstance().getDisplay().cantWriteFile(address);
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                Controller.getInstance().getDisplay().cantWriteFile(address);
            }
        }
    }


    public static void clearTheFile(String address) {
        try {
            FileWriter fw = new FileWriter(address, false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            Controller.getInstance().getDisplay().cantWriteFile(address);
        }
    }


}
