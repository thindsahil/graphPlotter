package persistence;

import model.EquationList;
import org.json.JSONArray;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// This class references code from this [repo]
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo]

// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private final String destination;
    private PrintWriter writer;

    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    public void write(EquationList list) {
        JSONArray json = list.equationsToJson();
        saveToFile(json.toString(1));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
