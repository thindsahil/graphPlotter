package persistence;

import model.EquationList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// This class references code from this [repo]
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo]

// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private String destination;
    private PrintWriter writer;

    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    public void write(EquationList list) {
        JSONObject json = list.toJson();
        saveToFile(json.toString(5));
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
