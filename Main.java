package lzw;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author barboza
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args[0] == null || args[1] == null) {
            System.out.println("ERROR: usage error. Invalid name for file.\n");
            System.exit(1);
        }
        
        if (args[0].equals("encode")) {
            EncoderClass ec = new EncoderClass(args[0], args[1]);
            try {
                ec.encodeFile();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (args[0].equals("decode")) {
            DecoderClass dc = new DecoderClass( "./decompressed.txt", "./compressed.bin");
            try {
                dc.decodeFile();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("ERROR: usage error. Invalid command\n");
            System.exit(1);
        }       
        
    }
    
}
