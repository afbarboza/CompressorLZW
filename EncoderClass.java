package lzw;

import java.util.*;
import java.io.*;

/**
 * @author Alex Frederico Ramos Barboza.
 * @Nº USP: 7986480
 * @observation: this algorithm was developed based on the entry for LZW at
 *               Wikipedia and the referenced link for YouTube video.
 * @bibliography: http://pt.wikipedia.org/wiki/LZW.
 *                https://www.youtube.com/watch?v=j2HSd3HCpDs
 * 
 */
public class EncoderClass extends LempelZivWelch {

    private ArrayList<Integer> codedBuffer;
    private FileInputStream myFile; /*Reads the text file to be compressed*/

    private FileOutputStream fs; /*Creates the binary file, which is the text file compressed*/

    private DataOutputStream os;

    public EncoderClass(String newFileTextSource, String newFileBinarySource) {
        super(newFileTextSource, newFileBinarySource);
    }

    /*bufferizes an text file to the ram memory*/
    private String readFile() throws IOException {
        String bufferedText = "";
        char tmpInputChar;
        int tmpInputInt;

        myFile = new FileInputStream(this.fileTextSource);
        while ((tmpInputInt = myFile.read()) != -1) {
            tmpInputChar = (char) tmpInputInt;
            bufferedText += tmpInputChar;
        }

        return bufferedText;
    }

    /*encodes an text file, using the LZW compression algorithm*/
    public void encodeFile() throws IOException {
        fs = new FileOutputStream(this.fileBinarySource);
        os = new DataOutputStream(fs);
        String streamFile = readFile();

        int i;
        char c;
        String Ic;
        String I = String.valueOf(streamFile.charAt(0));
        codedBuffer = new ArrayList<>();
        /*checks whether the EOF has been reached*/
        for (i = 1; i < streamFile.length(); i++) {
            c = streamFile.charAt(i);        
            Ic = I + String.valueOf(c);
            
            /*Ic is in the dictio?*/
            Entry EntryIC = this.tableWords.isInTheDictionary(Ic);
            if (EntryIC != null) { /*yes. Ic is in the dictio*/                
                I = Ic;
            } else { /*no. Ic is not in the dictio*/
                
                /*sends I to the output stream*/
                Entry tmpEntry = this.tableWords.isInTheDictionary(I);
                codedBuffer.add(tmpEntry.getCode());
                
                /*adds Ic to the dictio*/
                this.tableWords.appendNewEntry(Ic);
                
                /*makes an assignemnt to I*/
                I = String.valueOf(c);
            }           
            
        }
        
        /*output the code for I*/
        Entry tmpEntry = this.tableWords.isInTheDictionary(I);
        codedBuffer.add(tmpEntry.getCode());
        
        /*the writing file stuff*/
        fs = new FileOutputStream(this.fileBinarySource);
        os  = new DataOutputStream(fs);
        for (Integer code : codedBuffer) {            
            os.writeChar(code.intValue());
        }
       
       os.close();
    }
}
