package model;

import java.util.ArrayList;

import org.json.JSONArray;

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
        EventLog.getInstance().logEvent(new Event("An equation y = "
                + eq.getEquation() + " added to List"));
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

        for (Equation eq: graphList) {
            list += counter + ". y= " + eq.getEquation() + "\n";
            counter++;
        }


        return list;
    }

    //REQUIRES: index > 0 and smaller than length of list
    //MODIFIES: this
    //EFFECTS: removes equation from list at given index - 1
    public void removeEquation(int index) {
        graphList.remove(index);
        EventLog.getInstance().logEvent(new Event("Equation at index " + index + " removed from list."));
    }

    //REQUIRES: index > 0 and smaller than length of list
    //MODIFIES: this
    //EFFECTS: changes equation at index + 1 in list to eq
    public void updateEquation(int index, Equation eq) {
        graphList.set(index, eq);
        EventLog.getInstance().logEvent(new Event("Equation at index " + index
                + " updated to: y=" + eq.getEquation()));
    }

    //EFFECTS: returns length of list
    public int length() {
        return graphList.size();
    }

    //REQUIRES: index > 0 and smaller than length of list
    //EFFECTS: returns equation at given index
    public Equation getEquation(int index) {
        return graphList.get(index);
    }

    // EFFECTS: returns equations in EquationList as a JSON array
    public JSONArray equationsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Equation eq :graphList) {
            jsonArray.put(eq.getEquation());
        }

        return jsonArray;
    }

}
