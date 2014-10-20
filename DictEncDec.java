/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compress;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.UIManager.get;

/**
 *A class that inherits DicoderEncoder and overrides decode and encode methods 
 * using Lempel-Ziv encoding algorithm.
 * @author shams
 */
public class DictEncDec extends DicoderEncoder {
    private Dictionary Dict; 
    DictEncDec(){
        Dict = new Dictionary(); 
    }
    /**
     * encode is a function that encodes a given string. The output can be printed 
     * on standard output using function printEncoded() as a list of integers or can be received as a list 
     * of integers using  getEncoded()  or as a unicode string, probably in another language(s),
     * using getEncodedAsString().  
     * @param str  string to be encoded
     * 
     */
    @Override
    public void encode (String str){
        Dict = new Dictionary(); 
        input = str; 
        List<Integer> encoded = new ArrayList<Integer>();
        String current = String.valueOf(str.charAt(0));
        Dict.insertIfNtThere(current); 
        for(int i=1; i< str.length(); i++){ 
            String next = String.valueOf(str.charAt(i));
            int ret = Dict.lookUpIdx(current+next); 
            if(ret==-1){
                encoded.add(Dict.lookUpIdx(current)); 
                Dict.insert(current+next);
                current = next;
                Dict.insertIfNtThere(current);
            }else{
                current = current + next; 
            }
        
        } encoded.add(Dict.lookUpIdx(current));
    super.encoded = encoded;  
   }
 /**
     * decode is a function that decodes a given list of integers using the internally constructed
     * dictionary object during previous encoding. 
     * The output can be printed on standard output using function printDecoded as a string or can be received as a String 
     *  using  getDecoded. It can also be saved to a file using saveEncodedToFile.  
     * @param ls  list of integers to be encoded
     * 
     */
    @Override
   public void decode(List<Integer> ls){
       String ret = ""; 
      for(int i=0; i<ls.size(); i++){
          ret += Dict.lookUpValue(ls.get(i)); 

      }
       super.decoded = ret; 
       
   }
   /**
    * A function that prints the compression ratio of the input and output
    * Formula = size of input/(size of dictionary + size of encoded)
    * 
    */
   public void printCompressionRatio(){
        double ratio; 
        ratio = (input.length()+0.0)/(getEncodedAsString().length() + Dict.getDictionaryCharCount()+0.0);
        System.out.println("Compression ratio = " + String.valueOf(ratio));
   }
   /**
    * A function that compares input and decoded strings. 
    * 
    * @return returns true if Decoded and input are the same, and false if not. 
    */
   public boolean compareDecodedAndInput(){
       if(input.equals(decoded))
       {
           return true; 
       }else return false; 
   }
   public static void main(String [] args){
       //tests: 
       // abbaabbaababbaaaabaabba 
       // bccacbcccccccccccaccca
       //
       DictEncDec x = new DictEncDec(); 
       x.encode("abbaabbaababbaaaabaabba"); //Length of encoding should be 13
       x.printEncoded();
       x.decode(x.getEncoded());
       x.printDecoded();
       
       x.encode("bccacbcccccccccccaccca"); //Length of encoding should be 12
       x.printEncoded();
       x.decode(x.getEncoded());
       x.printDecoded();
       
       x.encode("O hai world!"); //Length of encoding should be 12 (because there are no repititions)
       x.printEncoded();
       x.decode(x.getEncoded());
       x.printDecoded();

       //now read a test text file. 
       
       String Data = "";  

        try{
            BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
            String line = null;
            while ((line = reader.readLine()) != null) {
            Data+=line; 
        }
        }catch(IOException e){
            System.out.println("Exception happend on input to file!");

        }
        x.encode(Data);   //string does not have repitions. Length of encoding should be same as input (12)
      //System.out.println(encoded);
        x.decode(x.getEncoded());
        String decoded = x.getDecoded();
      //System.out.println(decoded);
        
        x.SaveEncodedStringToFile("outputEncoded.txt");
        x.SaveDecodedToFile("outputDecoded.txt");
        System.out.println("One encoding: ");
        x.printCompressionRatio();
        System.out.println(x.compareDecodedAndInput());
       
        //encode text file multiple times: 
        System.out.println("Encode then decode multiple times: ");

        x.encode(Data); 
        x.decode(x.getEncoded()); 
        x.encode(Data); 
        x.decode(x.getEncoded()); 
        x.encode(Data); 
        x.decode(x.getEncoded()); 
            //check that input is the same as output
        x.printCompressionRatio();  
        System.out.println(x.compareDecodedAndInput());
       }
}
