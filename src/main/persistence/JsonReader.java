package persistence;


import model.Equation;
import model.EquationList;

import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// This class references code from this [repo]
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo]

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public EquationList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseEquationList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses EquationList from JSON object and returns it
    private EquationList parseEquationList(JSONObject jsonObject) {
        EquationList list = new EquationList();
        addEquations(list, jsonObject);
        return list;
    }
    // MODIFIES: EquationList
    // EFFECTS: parses equation from JSON object and adds them to EquationList
    private void addEquations(EquationList list, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Equation List");

        for (Object json : jsonArray) {
            JSONObject nextEquation = (JSONObject) json;
            Equation eq = new Equation(nextEquation.getString("y="));
            list.addEquation(eq);
        }

    }

}


