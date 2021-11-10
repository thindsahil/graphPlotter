package persistence;

import model.EquationList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// This class references code from this [repo]
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo]

public class JsonReaderTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader jsonReader = new JsonReader("./data/invalidFile.json");
        try {
            EquationList list = jsonReader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //no exception expected
        }
    }

    @Test
    public void readEquationListCorrectTest() {
        JsonReader jsonReader = new JsonReader("./data/testReader.json");
        try {
            EquationList list = jsonReader.read();
            assertEquals("x^2",list.getEquation(1).getEquation());
        } catch (IOException e) {
            fail("No exception expected");
        }
    }

    @Test
    public void readEquationListIncorrectTest() {
        JsonReader jsonReader = new JsonReader("./data/testReader.json");
        try {
            EquationList list = jsonReader.read();
            assertNotEquals("2x", list.getEquation(1).getEquation());
        } catch (IOException e) {
            fail("No exception expected");
        }
    }
}
