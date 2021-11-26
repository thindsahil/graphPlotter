package ui;

import model.Equation;
import model.EquationList;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


// This class references code from this [repo]
// Link: [https://github.students.cs.ubc.ca/CPSC210/TellerApp]

//Graph Plotting Application
public class GraphPlotterConsole {
    private EquationList list;
    private Scanner input;
    String command = "";
    private static final String JSON_STORE = "./data/list.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the graph plotter application
    public GraphPlotterConsole() {
        runGraphPlotter();
    }

    //MODIFIES: this
    //EFFECTS: reads and processes user input
    public void runGraphPlotter() {
        boolean running = true;
        init();

        System.out.println("This application graphs simple polynomial equation");
        while (running) {
            showMenu();

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                running = false;
            } else {
                processCommand(command);
            }
        }

    }

    //MODIFIES: this
    //EFFECTS: initializes an empty list of equations
    private void init() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        list = new EquationList();
        input = new Scanner(System.in);
    }

    //EFFECTS: displays an options menu to user
    public void showMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t a -> add equation to list");
        System.out.println("\t u -> update existing equation");
        System.out.println("\t d -> delete existing equation");
        System.out.println("\t s -> show equations in list");
        System.out.println("\t g -> graph equations in list");
        System.out.println("\t p -> save equation list");
        System.out.println("\t r -> load equation list");
    }

    //MODIFIES: this
    //EFFECTS: processes user commands
    private void processCommand(String command) {
        if (command.equals("a")) {
            addEquationToList();
        } else if (command.equals("s")) {
            showEquationList();
        } else if (command.equals("d")) {
            deleteEquation();
        } else if (command.equals("u")) {
            updateEquation();
        } else if (command.equals("g")) {
            graphEquation();
        }  else if (command.equals("p")) {
            saveEquationList();
        } else if (command.equals("r")) {
            loadEquationList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //MODIFIES: this
    //EFFECTS: adds user entered equation to Equation list
    private void addEquationToList() {
        Equation eq;

        System.out.print("Enter an equation to add to list: y= ");
        String stringEq = input.next();

        eq = new Equation(stringEq);
        list.addEquation(eq);

        System.out.println("Added y= " + stringEq + " to list.");


    }

    //EFFECTS: displays a list of equations to user
    private void showEquationList() {
        if (list.length() == 0) {
            System.out.println("Equation list is empty. Add an equation first.");
        } else {
            System.out.println("Equation List:");
            System.out.println(list.viewEquations());
        }
    }

    //MODIFIES: this
    //EFFECTS: removes equation from list of equation
    private void deleteEquation() {
        if (list.length() < 1) {
            System.out.println("Equation list is empty. Nothing to delete.");
        } else {
            System.out.println("Equation List:");
            System.out.println(list.viewEquations());
            System.out.print("Enter the number of which equation to delete: ");

            int index = input.nextInt() - 1;
            while (!checkIndex(index)) {
                System.out.print("There is no equation at given number. Try Again: ");
                index = input.nextInt();
            }
            list.removeEquation(index);

            System.out.println("Updated Equation List:");
            System.out.println(list.viewEquations());

        }
    }

    //MODIFIES: this
    //EFFECTS: asks user for which equation to change, and to what
    private void updateEquation() {
        if (list.length() < 1) {
            System.out.println("Equation list is empty. Nothing to update.");
        } else {
            System.out.println("Equation List:");
            System.out.println(list.viewEquations());
            System.out.print("Enter the number of which equation to update: ");

            int index = input.nextInt() - 1;
            while (!checkIndex(index)) {
                System.out.print("There is no equation at given number. Try Again: ");
                index = input.nextInt();
            }

            System.out.print("Enter new equation y= ");
            String eqString = input.next();

            Equation eq = new Equation(eqString);
            list.updateEquation(index, eq);

            System.out.println("Updated Equation List:");
            System.out.println(list.viewEquations());
        }

    }

    //EFFECTS: displays a graph of an equation or all equations
    private void graphEquation() {
        if (list.length() < 1) {
            System.out.println("Equation list is empty. Nothing to graph.");
        } else {
            System.out.println("Equation List:");
            System.out.println(list.viewEquations());
            System.out.print("Enter \"one\" to display one graph or enter \"all\" to display all: ");

            String type = input.next();
            while (!(type.equals("one") || type.equals("all"))) {
                System.out.print("Invalid option. Try Again: ");
                type = input.next();
            }
            if (type.equals("all")) {
                System.out.println(list.viewGraphs());
            } else {
                printSingleGraph();
            }

        }
    }

    //EFFECTS: helper method for graphEquation method
    //         graphs a single equation
    private void printSingleGraph() {
        System.out.print("Enter the number of which equation to graph: ");
        int index = input.nextInt() - 1;
        while (!checkIndex(index)) {
            System.out.print("There is no equation at given number. Try Again: ");
            index = input.nextInt();
        }

        String graphPlot = list.getEquation(index).graphEquation()
                + "Graph of y= "
                + list.getEquation(index).getEquation();
        System.out.println(graphPlot);

    }

    //EFFECTS: checks if given equation of index exists in list of equations
    private boolean checkIndex(int i) {
        return (i < list.length()) && (i >= 0);
    }

    // EFFECTS: saves the workroom to file
    private void saveEquationList() {
        try {
            jsonWriter.open();
            jsonWriter.write(list);
            jsonWriter.close();
            System.out.println("Saved " + list.toString() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS: loads the equations from file to EquationList
    private void loadEquationList() {
        try {
            list = jsonReader.read();
            System.out.println("Loaded equations from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        System.out.println(list.viewEquations());
    }
}
