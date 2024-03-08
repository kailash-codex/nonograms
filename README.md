# Nonograms

Application built uses the **model-view-controller** design pattern together with the **JavaFX** UI library for functioning GUI implementation of the single-player logic puzzle **nonograms**.

If you've never heard of nonograms before, I highly suggest starting by checking out [the nonograms Wikipedia page](https://en.wikipedia.org/wiki/Nonogram).

## Running the application

The application uses Maven as a build manager, and JavaFX as a GUI library. Since JavaFX is an external library, it has to be included in the build process in order for the application to successfully run. JavaFX has already been added as a Maven dependency to the POM file.

To run the application with Maven in IntelliJ (or follow similar steps in desired IDE), follow these steps:


1. Click the vertical "Maven" expansion tab which is on the right side of the IntelliJ window.

2. Expand the "Plugins" folder.

3. Expand the "javafx" folder.

4. Double-click "javafx:run" to run the project.


## How it works

Below is a breakdown of the application classes/code structure used in the implementation

### Main class

The `Main` class represents the starting point of your application. When you use Maven to launch your app as described above, Maven will try to run the `Main.main()` method to launch your app.

### PuzzleLibrary

`PuzzleLibrary` exposes a class factory method, `create()`, which instantiates and returns a singleton `List<Clues>` list of `Clues` objects


### Model

All code related to the application's model exists in the `model` package.


### Clues

In the `Clues` interface, each `Clues` instance represents the clues for a single nonograms puzzle.


### Board

A `Clues` instance represents the clues for a puzzle, but doesn't handle the state to track whether individual cells are "shaded" or "eliminated". The `Board` interface is intended to track the array of states for each cell in a puzzle.

### Model

The `Model` interface represents the model of MVC, and therefore contains all data necessary to display the current state of the application. 

### ModelObserver

the `ModelObserver` interface defines a single method, `update()`, and is used together with the `ModelImpl` class to implement the observer design pattern.

### Controller

The controller package in MVC is intended to act as the "glue" between the model and the view.

### View

The `view` package in MVC holds all code related to the GUI.

### AppLauncher

This class is the launching point of your application. The `Main` class is set up to forward to `AppLauncher`, which extends `Application` and therefore launches the JavaFX GUI.

### FXComponent

 The `FXComponent` is used to break up the interface into different smaller components, encapsulating each section of the UI in a separate class.
