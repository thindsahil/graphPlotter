# Simple Graph Plotter
Plots equations like *y=2x^2 + 2x -log(e)*, *y=sin(tan(x))*, etc.
<!--
## About

**What?**

This application is a simple polynomial graph plotter. It allows the user to input a simple equation like
*y=2x^2 + 2* and then display a graph corresponding to the equation.

**Who?**

People who study anything related to math can find this tool useful. Graphing an equation can quickly show many 
useful properties like:
- domain and range
- intercepts
- shape
- behaviour at large values of x,y, etc.
- and many more...

**Why?**

I am interested in making this project because I like studying math and want to make something related to such interest.
This is also a good way to test and expand my programming knowledge.

## User Stories

- As a user, I want to be able to see a graph of a polynomial equation
- As a user, I want to be able to add an equation to a list of equations to graph
- As a user, I want to be able to view a list of the equations of the graphs
- As a user, I want to be able to select an existing graph and change its equation
- As a user, I want to be able to delete an existing graph
- As a user, I want to be promoted to save the current list of equations to a file
when I quit the application 
- As a user, I want to be able to load the equations list file when I start the application

**Phase 4: Task 2**

>Thu Nov 25 20:39:41 PST 2021<br/>
An equation y = tan(x) added to List<br/><br/>
Thu Nov 25 20:39:50 PST 2021<br/>
An equation y = x^2-4 added to List<br/><br/>
Thu Nov 25 20:40:15 PST 2021<br/>
Equation at index 0 updated to: y=2^(-x)<br/><br/>
Thu Nov 25 20:40:22 PST 2021<br/>
Equation at index 1 removed from list.

**Phase4: Task 3**

Looking at the UML diagram, a lot of the classes are dependent on multiple other classes.
I think this will lead to problems if I want to change something major in one class. It 
will also be hard to track down bugs in code. If I had more time to work on the project I would change the following:
- Refactor my classes in `gui` package to reduce the associations to `EquationList`
- Possibly make the classes in `gui` package more independent
- Add more 'specific' classes to decrease coupling
- There should be a way to remove the Observers and still have same functionality
-->
