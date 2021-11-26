package ui;

import model.EquationList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Represents a panel with save and load buttons
public class PersistencePanel extends JPanel implements ActionListener {

    JButton saveButton;
    JButton loadButton;

    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private static final String JSON_STORE = "./data/list.json";

    private EquationList list;

    private List<LoadEquationObserver> observers;

    //EFFECTS: Constructs a panel with save/load buttons and a jsonWriter/jsonReader
    public PersistencePanel() {
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        add(saveButton);
        add(loadButton);

        saveButton.addActionListener(this);
        loadButton.addActionListener(this);

        observers = new ArrayList<>();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            try {
                jsonWriter.open();
                jsonWriter.write(list);
                jsonWriter.close();
                //System.out.println("Saved Equation list to " + JSON_STORE);
            } catch (FileNotFoundException exception) {
                //System.out.println("Unable to write to file: " + JSON_STORE);
            }
        } else if (e.getSource() == loadButton) {
            try {
                list = jsonReader.read();
                //System.out.println("Loaded equations from " + JSON_STORE);
            } catch (IOException exception) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
            //System.out.println(list.viewEquations());
            notifyLoadEquationObservers();
        }
    }

    //MODIFIES: listeners
    //EFFECTS: adds LoadEquationUpdate to listeners list
    public void addLoadEquationObserver(LoadEquationObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    //MODIFIES: listeners
    //EFFECTS: updates every LoadEquationUpdate in listeners
    public void notifyLoadEquationObservers() {
        for (LoadEquationObserver observer : observers) {
            observer.loadEquations();
        }
    }

    public void setList(EquationList list) {
        this.list = list;
    }

    public EquationList getList() {
        return list;
    }
}
