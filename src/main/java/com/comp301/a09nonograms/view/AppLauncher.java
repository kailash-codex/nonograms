package com.kailash-codex.nonograms.view;

import com.kailash-codex.nonograms.PuzzleLibrary;
import com.kailash-codex.nonograms.controller.Controller;
import com.kailash-codex.nonograms.controller.ControllerImpl;
import com.kailash-codex.nonograms.model.Model;
import com.kailash-codex.nonograms.model.ModelImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // Model
    Model _gameModel = new ModelImpl(PuzzleLibrary.create());

    // Controller
    Controller _myRemote = new ControllerImpl(_gameModel);

    // View
    View view = new View(_myRemote);

    // Make Scene
    Scene scene = new Scene(view.render());
    scene.getStylesheets().add("main.css");
    stage.setScene(scene);

    // Refresh Model
    _gameModel.addObserver(
        (Model m) -> {
          scene.setRoot(view.render());
          stage.sizeToScene();
        });

    // Stage setup
    stage.setTitle("Nonogram");
    stage.show();
  }
}
