/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compress;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *An abstarct encoding and decoding class. The subclass must implement the
 * abstract encode and decode functions or be abstarct. 
 * @author shams
 */
public abstract class DicoderEncoder {
     List<Integer> encoded; 
     String decoded,input;
    DicoderEncoder(){
        encoded = new ArrayList<Integer>();  
        decoded = "" ; 
    }
    /**
     * A function that prints the encoded string to standard output
     */
    public void printEncoded(){
        System.out.println(encoded);
    }
    /**
     * A function that prints the decoded string to standard output
     */
    public void printDecoded(){
        System.out.println(decoded); 
    }
    /**
     * A function that gets the encoded list of integer indices of the dictionary
     * @return a list of integers of indices of words in dictionary
     */
    public List<Integer> getEncoded(){
        return encoded; 
    
    }
    /**
     * A function that gets the decoded string
     * @return a string of decoded characters
     */
    public String getDecoded(){
        return decoded; 
    }
    /**
     * A function that returns the encoded list, but casted as a string for display purposes. 
     * @return String of casted indices 
    */
    public String getEncodedAsString(){
       String ret = ""; 
        for (int num :encoded){
            ret+=String.valueOf((char)num); 
        }
        return ret; 
    }
    /**
     * A function that saves the encoded string to file (with integers casted)
     * @param filename name of output file
     */
    public void SaveEncodedStringToFile(String filename){
        try{
            PrintWriter writerEncoded = new PrintWriter(filename, "UTF-8");
            writerEncoded.write(getEncodedAsString());
            writerEncoded.close();
        }catch(IOException ex){
            System.out.println("Exception happend on output to file!");
        }
    
    }
    /**
     * A funtion that saves the decoded string to file
     * @param filename the output file name
     */
    public void SaveDecodedToFile(String filename){
        try{
            PrintWriter writerDecoded = new PrintWriter(filename, "UTF-8");
            writerDecoded.write(decoded);
            writerDecoded.close();
        }catch(IOException ex){
            System.out.println("Exception happend on output to file!");
        }

    }

    /**
     *A function to encode a string. 
     * @param str the string to be encoded
     * 
     */
    abstract public void encode(String str); 
    /**
     * A function to decode an integer list. 
     * @param lst the integer list to be decoded. 
     */
    abstract public void decode (List<Integer> lst); 
}
