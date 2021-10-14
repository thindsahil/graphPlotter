package ui;

import model.Equation;
import model.EquationList;


public class Main {
    public static void main(String[] args) {
        Equation eq1 = new Equation("2x");
        Equation eq2 = new Equation("1/(x^2+1)");
        EquationList l = new EquationList();

        l.addEquation(eq1);
        l.addEquation(eq2);

        System.out.println(l.viewEquations());

    }
}
