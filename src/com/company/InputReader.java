package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class InputReader {
    File raw;
    FileReader rawIn = null;
    Scanner s = null;

    public InputReader(String path){
        this.raw = new File(path);
    }

    public ArrayList<WordNode> getNodes(){
        initializeReader();
        ArrayList<WordNode> out = new ArrayList<WordNode>();

        while(s.hasNext()){
            String input = s.nextLine();
            int tempSize;
            String tempText;
            try{
                tempSize = Integer.parseInt(input.substring(input.length() - 2, input.length()));
                tempText = input.substring(0, input.length()-5);
            } catch(NumberFormatException e){
                tempSize = Integer.parseInt(input.substring(input.length() - 1, input.length()));
                tempText = input.substring(0, input.length()-4);
            }

            out.add(new WordNode(tempText,tempSize));
        }
        return out;
    }

    public void initializeReader() {
        try {
            rawIn = new FileReader(raw);
            s = new Scanner(rawIn);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
