package lzw;

import java.io.*;
import java.util.*;

/**
 * @author Alex Frederico Ramos Barboza.
 * @Nº USP: 7986480
 * @observation: this algorithm was developed based on the entry for LZW at
 *               Wikipedia and the referenced link for YouTube video.
 * @bibliography: http://pt.wikipedia.org/wiki/LZW.
 *                https://www.youtube.com/watch?v=j2HSd3HCpDs
 * 
 */
public class DecoderClass extends LempelZivWelch {
     private ArrayList<Integer> compressedText; /*stores the compressed string*/
     private FileInputStream fis; /*defines the binary compressed file to be decompressed*/
     private DataInputStream dis; /*defines the interface for reading binary information*/
     private BufferedWriter bw; /*defines the interface for writing at the hard drive*/
     private FileWriter fw; /*defines the interface for the file that will be write*/
     private File decompressedTextFile;  /*defines the interface for */
     private String decompressedText = ""; /*stores the decompressed file*/
     
    public DecoderClass (String newFileTextSource, String newFileBinarySource) {
        super(newFileTextSource, newFileBinarySource);
    }
    
    /*decode an compressed binary file, using the LZW decompression algorithm*/
    public void decodeFile() throws IOException {
        try {
            char tmpInput2Byte;
            
            /*the reading binary file auxiliar stuff*/
            compressedText = new ArrayList<>();
            fis = new FileInputStream(this.getFileBinarySource());
            dis = new DataInputStream(fis);
            
            /*initializes with the fist code in the binary file*/
            tmpInput2Byte = dis.readChar();
            
            /*we store at the buffer the compressed code*/
            while (true) {
                compressedText.add((int)tmpInput2Byte);                
                tmpInput2Byte = dis.readChar(); 
            }
        } catch (EOFException eof) {
            int i;
            int cW = compressedText.get(0).intValue();
            Entry entryForCW = this.tableWords.isInTheDictionary(cW);
            decompressedText += entryForCW.getWord();
            
            /*iterates over an compressed  and bufferized string the core of the algortithm*/
            for (i = 1; i < compressedText.size(); i++) {
                /*pW stores the last code read*/
                int pW = cW;
                
                /*cW receives the next char from the compressed stream*/
                cW = compressedText.get(i).intValue();
                entryForCW = this.tableWords.isInTheDictionary(cW);
                
                /*cW is in the dicio?*/
                if (entryForCW != null) { /*yes. cW is in the dictio.*/
                    /*cocatenates the decompressed string and the string(cW)*/
                    decompressedText += entryForCW.getWord();
                    
                    /*then we add pW and cW[0] at the dictionary*/
                    Entry entryForP = this.tableWords.isInTheDictionary(pW);
                    this.tableWords.appendNewEntry(entryForP.getWord() + entryForCW.getWord().charAt(0));                    
                } else { /*no. cW is in the dictio.*/
                    /*LZW provides a guarantee that entryForP != null, always*/
                    Entry entryForP = this.tableWords.isInTheDictionary(pW);
                    
                    /*outputs string(pW) + string(pW[0]) */
                    decompressedText += (entryForP.getWord() + entryForP.getWord().charAt(0));
                    
                    /*adds string(pW) + string(pW)[0] at the dictionary*/
                    this.tableWords.appendNewEntry(entryForP.getWord() + entryForP.getWord().charAt(0));
                }                
            }
            
            /*the writing decompressed file stuff: just saves the decompressed string*/
            decompressedTextFile = new File(this.getFileTextSource());
            if (!decompressedTextFile.exists()) {
                decompressedTextFile.createNewFile();
            }           
            fw = new FileWriter(decompressedTextFile.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            
            for (char c: decompressedText.toCharArray()) {
                bw.write(c);
            }
            bw.close();
        }
        
    }
}
