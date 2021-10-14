package model;

import java.util.ArrayList;

//represents a list of equations
public class EquationList {
    ArrayList<Equation> graphList; //list of equations

    //EFFECTS: creates an empty list of equations
    public EquationList() {
        graphList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds given equation to list
    public void addEquation(Equation eq) {
        graphList.add(eq);
    }

    //EFFECTS: returns a string representing all graphs in list
    public String viewGraphs() {
        int counter = 1;
        String graphsPic = "";

        for (Equation eq: graphList) {
            graphsPic += counter + ". y= " + eq.getEquation()
                    + "\n"
                    + eq.graphEquation()
                    + "\n";
            counter++;
        }

        return graphsPic;
    }

    //EFFECTS: returns a string containing all equations
    public String viewEquations() {
        int counter = 1;
        String list = "";

        if (length() < 1) {
            return "empty";
        } else {
            for (Equation eq: graphList) {
                list += counter + ". y= " + eq.getEquation() + "\n";
                counter++;
            }
        }

        return list;
    }

    //REQUIRES: index > 0 and smaller than length of list
    //MODIFIES: this
    //EFFECTS: removes equation from list at given index - 1
    public void removeEquation(int index) {
        graphList.remove(index - 1);
    }

    //REQUIRES: index > 0 and smaller than length of list
    //MODIFIES: this
    //EFFECTS: changes equation at index + 1 in list to eq
    public void updateEquation(int index, Equation eq) {
        graphList.set(index - 1, eq);
    }

    //EFFECTS: returns length of list
    public int length() {
        return graphList.size();
    }

    //REQUIRES: index > 0 and smaller than length of list
    //EFFECTS: returns equation at given index - 1
    public Equation getEquation(int index) {
        return graphList.get(index - 1);
    }
}
