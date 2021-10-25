package persistence;

import model.Equation;
import model.EquationList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

// This class references code from this [repo]
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo]

public class JsonWriterTest {

    private String destination;
    private PrintWriter writer;

    @BeforeEach
    public void setup() {
        destination = "./data/testWriter.json";
    }
    @Test
    public void openTest() {
        try {
            JsonWriter jsonWriter = new JsonWriter(destination);
            jsonWriter.open();
        } catch (IOException e) {
            fail("No Exception was expected!");
        }
    }

    @Test
    public void openInvalidTest() {
        try {
            destination = "./data/\0invalidname.json";
            JsonWriter jsonWriter = new JsonWriter(destination);
            jsonWriter.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // Exception expected
        }
    }

    @Test
    public void writeEquationListTest() {
        try {
            EquationList list = new EquationList();
            Equation eq = new Equation("x^2");
            list.addEquation(eq);
            JsonWriter jsonWriter = new JsonWriter(destination);
            jsonWriter.open();
            jsonWriter.write(list);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader(destination);
            list = jsonReader.read();
            assertEquals("x^2", list.getEquation(1).getEquation());
        } catch (IOException e) {
            fail("No Exception was expected!");
        }
    }
}
