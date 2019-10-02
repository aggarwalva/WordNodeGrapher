package com.company;

import org.graphstream.graph.EdgeRejectedException;
import org.graphstream.graph.Graph;
import org.graphstream.graph.IdAlreadyInUseException;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerPipe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //generateWordGraph();
        generateConnectionGraph();
        /*graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("AC", "A", "C");
        graph.getNode(0).setAttribute("ui.label", "I");
        graph.getNode(1).setAttribute("ui.label", "Remember");
        graph.setAttribute("ui.stylesheet", "node { fill-color:red;}");
        graph.setAttribute("ui.stylesheet", "node { fill-color:red;" +
                "text-size:24;}");
        graph.getNode(0).addAttribute("ui.style", " size:50px;");*/

    }

    public static void generateWordGraph(){
        Graph graph = new SingleGraph("Trial 1");

        InputReader input = new InputReader("C:\\Users\\Varun\\Desktop\\IdeaProjects\\TextWordAnalyzer\\wordsOutput - Copy.txt");
        ArrayList<WordNode> dataNodes = input.getNodes();

        graph.setAttribute("ui.stylesheet", "node { text-size:24;}");

        Random random = new Random();
        for(int i = 0; i < dataNodes.size(); i++){
            graph.addNode(dataNodes.get(i).text);
            int hue = random.nextInt(5) * 10 + 100;
            float saturation = random.nextFloat();
            float value = random.nextFloat();
            Color c = Color.getHSBColor(hue,saturation,value);
            graph.getNode(i).addAttribute("ui.style", " size:" + dataNodes.get(i).size + "px;" +
                    "fill-color: rgb(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() +");");
            graph.getNode(i).addAttribute("ui.label",dataNodes.get(i).text);
        }
        graph.display();
    }

    public static void generateConnectionGraph(){
        Graph graph = new SingleGraph("Trial 1");
        graph.addAttribute("ui.stylesheet", "graph { size: 100px, 400px;}");

        InputReader input = new InputReader("C:\\Users\\Varun\\Desktop\\IdeaProjects\\TextWordAnalyzer\\sonnets words.txt");
        ArrayList<WordNode> dataNodes = input.getNodes();
        input = new InputReader("C:\\Users\\Varun\\Desktop\\IdeaProjects\\TextWordAnalyzer\\sonnets connections.txt");
        ArrayList<ConnectionNode> connections = ConnectionNode.GenerateConnectionNodes(input.getNodes());

        graph.addAttribute("ui.stylesheet", "node { text-size:24;}");
        for(int i = 0; i < dataNodes.size(); i++){
            graph.addNode(dataNodes.get(i).text);
            graph.getNode(i).addAttribute("ui.style", " size:" + (dataNodes.get(i).size < 100 ? dataNodes.get(i).size : 100) + "px;" +
                    "fill-color: grey;");
            graph.getNode(i).addAttribute("ui.label", dataNodes.get(i).text);
            //graph.getNode(i).addAttribute("layout.weight", 1);
            System.out.println("Size: " + dataNodes.get(i).size);
        }

        int edgeCount = 0;
        for(int i = 0; i < dataNodes.size(); i++){
            String[] l1 = ConnectionNode.GetConnectionsFromArray(connections,dataNodes.get(i).text);
            String[] l2 = WordNode.ArrayContains(dataNodes, l1);

            for(String s : l2){
                try{
                    graph.addEdge(Integer.toString(edgeCount), graph.getNode(dataNodes.get(i).text), graph.getNode(s));
                   graph.getEdge(edgeCount).setAttribute("layout.weight", 0.4);
                    edgeCount++;
                } catch(IdAlreadyInUseException e){
                    //e.printStackTrace();
                } catch(EdgeRejectedException e){
                    e.printStackTrace();
                }
            }
        }

        /*Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        View view = viewer.addDefaultView(false);
        JFrame frame = new JFrame("Visualizer");
        ViewerPipe fromViewer = viewer.newViewerPipe();
        fromViewer.addSink(graph);

        frame.setSize(540,1080);
        frame.add((Component) view);

        frame.setVisible(true);*/
        graph.display();
    }
}
