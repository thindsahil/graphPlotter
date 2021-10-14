package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    private Graph graph;

    @BeforeEach
    public void setup() {
        Equation eq = new Equation("2x");
        graph = new Graph(eq);
    }

    @Test
    public void pointOnGraphTest() {
        graph.addPointsToGraph();
        assertTrue(graph.pointOnGraph(-3,-6));
        assertTrue(graph.pointOnGraph(0,0));
        assertTrue(graph.pointOnGraph(2,4));
    }

    @Test
    void addPointsToGraphTest() {
        assertFalse(graph.pointOnGraph(0,0));
        assertFalse(graph.pointOnGraph(-4,-8));

        graph.addPointsToGraph();

        assertTrue(graph.pointOnGraph(0,0));
        assertTrue(graph.pointOnGraph(-4,-8));
        assertFalse(graph.pointOnGraph(4,-8));

    }

    @Test
    void generateGraphTest() {
        String graphPlot = graph.generateGraph();
        String actual = "[                                             *               ]" + "\n" +
                        "[                                                             ]" + "\n" +
                        "[                                          *                  ]" + "\n" +
                        "[                                                             ]" + "\n" +
                        "[                                       *                     ]" + "\n" +
                        "[                                                             ]" + "\n" +
                        "[                                    *                        ]" + "\n" +
                        "[                                                             ]" + "\n" +
                        "[                                 *                           ]" + "\n" +
                        "[                                                             ]" + "\n" +
                        "[                              *                              ]" + "\n" +
                        "[                                                             ]" + "\n" +
                        "[                           *                                 ]" + "\n" +
                        "[                                                             ]" + "\n" +
                        "[                        *                                    ]" + "\n" +
                        "[                                                             ]" + "\n" +
                        "[                     *                                       ]" + "\n" +
                        "[                                                             ]" + "\n" +
                        "[                  *                                          ]" + "\n" +
                        "[                                                             ]" + "\n" +
                        "[               *                                             ]" + "\n";

        assertEquals(actual, graphPlot);
    }
}