package model;

import java.util.Arrays;

//represents a graph of an equation, as a grid of size width*height
public class Graph {

    private Equation equation; //graph equation
    private String[][] graph;  // ...
    private int width = 21;    //width of graph
    private int height = 21;   //height of graph


    //REQUIRES: a valid equation
    //EFFECTS: sets equation to given equation and creates an
    //         empty grid of size length * width
    public Graph(Equation equation) {
        this.equation = equation;
        graph = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                graph[i][j] = " ";
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: adds points to the graph
    public void addPointsToGraph() {

        for (int i = -1 * width / 2; i <= width / 2; i++) {

            int y = (height - 1) - equation.substitute(i) - height / 2;
            int x = i + width / 2;

            if ((y  >= 0) && (y < height)) {
                graph[y][x] = "*";
            }
        }
    }


    //EFFECTS: returns the graph as a string
    public String generateGraph() {
        addPointsToGraph();
        String graphPic = "";
        for (String[] row: graph) {
            graphPic += Arrays.toString(row).replace(",", " ") + "\n";
        }
        return graphPic;
    }


//    public String[][] getGraph() {
//        return graph;
//    }
//
//    public void getGraphEquation(String[][] g) {
//        graph = g;
//    }

}
