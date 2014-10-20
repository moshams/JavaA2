/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compress;
import java.util.Arrays;
/**
 *A trivial dictionary class with O(1) insertion and O(n) lookup used for Lempel-Ziv 
 * compression. 
 * @author shams
 */
public class Dictionary  {
     private String arr[]; 
    private int currIdx; 
    Dictionary(){
    arr = new String[4096];  
    currIdx = 0; 
//    for(int i=0; i<26; i++){
//        arr[i] = String.valueOf((char) (i + 65)); 
//        arr[i] = arr[i].toLowerCase(); 
//        currIdx++; 
//    }
//    arr[26] = String.valueOf((char)(20)); 
//    currIdx++; 
}
    /**
     * A function that inserts a string to dictionary
     * @param str string to be inserted to dictionary
     */
    public void insert(String str){
        arr[currIdx] =  str; 
        currIdx++;
    }
    /**
     * A function that looks up the index of a given string. 
     * 
     * @param str string to find index for 
     * @return returns integer index for the string
     */
    public int lookUpIdx(String str){
        for(int i=0; i<currIdx; i++){
            if(str.equals(arr[i]))
                return i; 
        }
        return -1; 
    }
    
    /**
     * A function that inserts the string if not found in the dictionary
     * @param str string to be inserted. 
     */
    public void insertIfNtThere(String str){
        if(lookUpIdx(str)==-1){
            insert(str); 
        }
    }
    /**
     * A function that returns a string at a given index in the dictionary
     * @param idx the index to look up in the dictionary
     * @return the string in index idx
     */
    public String lookUpValue(int idx){
        return arr[idx]; 
    }
    /**
     * A function that length of all the strings in the string concatenated. 
     * @return the length of all strings in dictionary
     */
    public int getDictionaryCharCount(){
            
        String ret =""; 
        for(String item : arr){
            ret += item; 
        }
        return ret.length(); 
    }
}
