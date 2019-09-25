package com.company;

import java.util.ArrayList;

public class WordNode {
    int size;
    String text;

    public WordNode(String text, int size){
        this.text = text;
        this.size = size;
    }

    public static String[] ArrayContains(ArrayList<WordNode> nodes, String strs){
        ArrayList<String> temp = new ArrayList<String>();
        for(WordNode node : nodes){
            if(node.text.equals(strs)) temp.add(node.text);
        }
        return temp.toArray(new String[0]);
    }

    public static String[] ArrayContains(ArrayList<WordNode> nodes, String[] strs){
        ArrayList<String> temp = new ArrayList<String>();
        for(String s : strs){
            for(WordNode node : nodes){
                if(node.text.equals(s)) temp.add(node.text);
            }
        }
        return temp.toArray(new String[0]);
    }
}
