package model;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

//represents an equation
public class Equation {
    private String equation;

    //REQUIRES: equation has non-zero length, and only contains:
    // [0,9],+,-,*,/,(,),.
    //EFFECTS: sets this.equation to the given equation
    public Equation(String equation) {
        this.equation = equation;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String eq) {
        equation = eq;
    }

    //EFFECTS: returns a string that represents a graph of this equation
    public String graphEquation() {
        Graph graph = new Graph(this);
        return graph.generateGraph();
    }

    //EFFECTS: substitutes given x in the equation, returns the result as an int
    public int substitute(int x) {
        Expression ex = new ExpressionBuilder(equation)
                .variable("x")
                .build()
                .setVariable("x", x);

        double result = ex.evaluate();

        if (result > 0) {
            return (int) (result + 0.5);
        } else {
            return (int) (result - 0.5);
        }

    }

    //EFFECTS: substitutes given x in the equation, returns the result as an int
    public double substituteDouble(double x) {
        Expression ex = new ExpressionBuilder(equation)
                .variable("x")
                .build()
                .setVariable("x", x);

        return ex.evaluate();

    }

}
