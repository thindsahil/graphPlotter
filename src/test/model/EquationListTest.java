package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EquationListTest {
    EquationList list;
    Equation eq1;
    Equation eq2;

    @BeforeEach
    public void setup() {
        list = new EquationList();
         eq1 = new Equation("x");
         eq2 = new Equation("(x-1)^2");

    }

    @Test
    public void lengthTest() {
        assertEquals(list.length(), 0);

        list.addEquation(eq1);
        assertEquals(list.length(), 1);
    }

    @Test
    public void addEquationTest() {
        assertEquals(list.length(), 0);

        list.addEquation(eq2);
        assertEquals(list.length(), 1);

        assertEquals(list.getEquation(0), eq2);
    }

    @Test
    public void removeEquationTest() {
        list.addEquation(eq1);
        list.addEquation(eq2);

        assertEquals(list.length(), 2);

        list.removeEquation(0);

        assertEquals(list.length(), 1);
        assertEquals(list.getEquation(0), eq2);

    }

    @Test
    public void updateEquationTest() {
        list.addEquation(eq1);
        list.addEquation(eq2);

        Equation eq3 = new Equation("1/2*x");

        assertEquals(list.getEquation(1), eq2);

        list.updateEquation(0,eq3);

        String eq2String = list.getEquation(0).getEquation();

        assertEquals(list.length(), 2);
        assertEquals(eq2String, "1/2*x");
    }

    @Test
    public void viewEquationsOneTest() {
        Equation eq3 = new Equation("1/2*x");
        list.addEquation(eq3);

        String expected = list.viewEquations();
        String actual = "1. y= 1/2*x" + "\n";

        assertEquals(expected, actual);
    }

    @Test
    public void viewEquationTwoTest() {
        list.addEquation(eq1);
        list.addEquation(eq2);

        String expected = list.viewEquations();
        String actual = "1. y= x" + "\n" +
                        "2. y= (x-1)^2" +"\n";

        assertEquals(expected, actual);
    }

    @Test
    public void viewGraphsOneTest() {
        list.addEquation(eq1);

        String expected = list.viewGraphs();
        String actual = "1. y= x" + "\n" +
                        list.getEquation(0).graphEquation() + "\n";


        assertEquals(expected, actual);
    }

    @Test
    public void viewGraphsTwoTest() {
        list.addEquation(eq1);
        list.addEquation(eq2);

        String expected = list.viewGraphs();
        String actual = "1. y= x" + "\n" +
                list.getEquation(0).graphEquation() + "\n" +
                "2. y= (x-1)^2" + "\n" +
                list.getEquation(1).graphEquation() + "\n";

        assertEquals(expected, actual);
    }

}
