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

public class PersistencePanel extends JPanel implements ActionListener {

    JButton saveButton;
    JButton loadButton;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/list.json";

    private EquationList list;

    List<LoadEquationEvent> listeners;

    public PersistencePanel() {
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        add(saveButton);
        add(loadButton);

        saveButton.addActionListener(this);
        loadButton.addActionListener(this);

        listeners = new ArrayList<>();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            try {
                jsonWriter.open();
                jsonWriter.write(list);
                jsonWriter.close();
                System.out.println("Saved Equation list to " + JSON_STORE);
            } catch (FileNotFoundException exception) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        } else if (e.getSource() == loadButton) {
            try {
                list = jsonReader.read();
                System.out.println("Loaded equations from " + JSON_STORE);
            } catch (IOException exception) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
            //System.out.println(list.viewEquations());
            loadEquations();
        }
    }

    public void addLoadEquationsEventListener(LoadEquationEvent listener) {
        listeners.add(listener);
    }

    public void loadEquations() {
        for (LoadEquationEvent listener : listeners) {
            listener.loadEquation();
        }
    }

    public void setList(EquationList list) {
        this.list = list;
    }

    public EquationList getList() {
        return list;
    }
}
