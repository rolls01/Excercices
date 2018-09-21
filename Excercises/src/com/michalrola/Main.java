package com.michalrola;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        HashString hashString = new HashString();
        System.out.println(hashString.GetHash("marzenie"));
        System.out.println(hashString.getMessage(25157672851591L));
        System.out.println(hashString.getMessage(hashString.decodeMessageRecursive(25157672851591L)));


        System.out.println(hashString.decodeMessage(25157672851591L));
        System.out.println(hashString.getMessage(hashString.decodeMessage(25157672851591L)));

    }
}


class HashString {

    private Integer firstNumber = 7;
    private Integer secondNumber = 37;

    private List<Integer> decoded = new ArrayList<Integer>();
    private String dictionary = "acegilmnoprstuwxyz";
    private Boolean newDecode = false;

    HashString(){}

    HashString(
            Integer firstNumber,
            Integer secondNumber){
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    HashString(
            Integer firstNumber,
            Integer secondNumber,
            String dictionary){
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.dictionary = dictionary;
    }

    public String getMessage(long hash) {
        String result = "";
        List<Integer> values = decodeMessageRecursive(hash);
        for (int k : values) {
            result = dictionary.toCharArray()[k] + result;
        }
        return result;
    }

    public String getMessage(List<Integer> values) {
        String result = "";
        for (int k : values) {
            result = dictionary.toCharArray()[k] + result;
        }
        return result;
    }

    public List<Integer> decodeMessageRecursive(long hash) {
        if(newDecode){
            newDecode = false;
            decoded = new ArrayList<Integer>();
        }

        if (hash - firstNumber != 0)
            for (int k = 0; k < dictionary.length(); k++) {
                if (((((double) hash) - k) / secondNumber) % 1 == 0.0) {
                    hash = ((hash) - k) / secondNumber;
                    decoded.add(k);
                    k = dictionary.length();
                    decodeMessageRecursive(hash);
                }
            }
        else
            newDecode = true;
//        }

        return decoded;
    }



    public List<Integer> decodeMessage(long hash) {
            List<Integer> decoded = new ArrayList<Integer>();

        if (hash - firstNumber != 0)
            for (int i = 0; i < hash - firstNumber; i++) {
                for (int k = 0; k < dictionary.length(); k++) {
                    if (((((double) hash) - k) / secondNumber) % 1 == 0.0) {
                        hash = ((hash) - k) / secondNumber;
                        decoded.add(k);
                        k = dictionary.length();
                    }
                }
            }
//        else
//            newDecode = true;


        return decoded;
    }


    public long GetHash(String s) {
        long h = 7;
        for (int i = 0; i < s.length(); i++)
            h = h * 37 +
                    "acegilmnoprstuwxyz".indexOf(s.toCharArray()[i]);
        return h;
    }
}
