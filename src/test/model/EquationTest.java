package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class EquationTest {
    private Equation eq;

    @BeforeEach
    public void setup() {
        eq = new Equation("x^2");
    }

    @Test
    public void substituteTest() {
        assertEquals(eq.substitute(0), 0);
        assertEquals(eq.substitute(2), 4);

        assertEquals(eq.substituteDouble(2.0), 4.0);
    }

    @Test
    public void getEquationTest() {
        assertEquals(eq.getEquation(), "x^2");
    }

    @Test
    public void setEquationTest() {
        eq.setEquation("2x-5");
        assertEquals(eq.getEquation(), "2x-5");
    }

    @Test
    public void graphEquationTest() {
    String plot = eq.graphEquation();
    String actual = "[                                                             ]" +
        "[                     *                 *                     ]" +
        "[                                                             ]" +
        "[                                                             ]" +
        "[                                                             ]" +
        "[                                                             ]" +
        "[                        *           *                        ]" +
        "[                                                             ]" +
        "[                                                             ]" +
        "[                           *     *                           ]" +
        "[                              *                              ]" +
        "[                                                             ]" +
        "[                                                             ]" +
        "[                                                             ]" +
        "[                                                             ]" +
        "[                                                             ]" +
        "[                                                             ]" +
        "[                                                             ]" +
        "[                                                             ]" +
        "[                                                             ]" +
        "[                                                             ]";

    }
}
