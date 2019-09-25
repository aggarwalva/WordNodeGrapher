package com.company;

import java.util.ArrayList;

public class ConnectionNode {
    int size;
    String[] text;

    public ConnectionNode(String[] text, int size){
        this.text = text;
        this.size = size;
    }

    public boolean contains(String s){
        for(String str : text){
            if(str.equals(s)) return true;
        }
        return false;
    }

    public String getConnection(String s){
        String out = "";
        for(int i = 0; i < text.length; i++){
            if(!s.equals(text[i])) out += (text[i]);
        }
        return out;
    }

    public static ArrayList<ConnectionNode> GenerateConnectionNodes(ArrayList<WordNode> words) {
        ArrayList<ConnectionNode> out = new ArrayList<ConnectionNode>();
        for(WordNode word : words){
            out.add(ConnectionNode.wordNodeToConnectionNode(word));
        }
        return out;
    }

    public static ConnectionNode wordNodeToConnectionNode(WordNode word){
        String[] text = word.text.split(" ", 0);
        return new ConnectionNode(text, word.size);
    }

    public void printNode(){
        for(String s : text){
            System.out.print(s + " ");
        }
        System.out.print(": " + size + "\n");
    }

    public static void printArray(ArrayList<ConnectionNode> nodes){
        for(ConnectionNode node : nodes){
            node.printNode();
        }
    }

    public static String[] GetConnectionsFromArray(ArrayList<ConnectionNode> nodes, String s){
        ArrayList<String> temp = new ArrayList<String>();
        for(ConnectionNode node : nodes){
            if(node.contains(s)) temp.add(node.getConnection(s));
        }
        return temp.toArray(new String[0]);
    }
}
