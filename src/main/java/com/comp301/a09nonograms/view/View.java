package com.kailash-codex.nonograms.view;

import com.kailash-codex.nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class View implements FXComponent {
  private final Controller controller;
  private PuzzleView _puzzleComp;
  private Parent _puzzleSection;

  public View(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox mainLayout = new HBox();
    VBox layout = new VBox();
    mainLayout.getChildren().add(layout);
    mainLayout.getStyleClass().add("base");
    layout.getStyleClass().add("base");

    // Title
    TitleView titleComponent = new TitleView(controller);
    layout.getChildren().add(titleComponent.render());

    // Puzzle
    if (_puzzleSection == null) {
      _puzzleComp = new PuzzleView(controller);
      _puzzleSection = _puzzleComp.render();
    }
    layout.getChildren().add(_puzzleComp.render());

    // Message
    MessageView messageComp = new MessageView(controller);
    layout.getChildren().add(messageComp.render());

    // Controls
    ControlView controlComp = new ControlView(controller);
    layout.getChildren().add(controlComp.render());

    return mainLayout;
  }
}
