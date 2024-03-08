package com.kailash-codex.nonograms.view;

import com.kailash-codex.nonograms.controller.Controller;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlView implements FXComponent {
  private final Controller controller;

  public ControlView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox layout = new HBox();
    layout.getStyleClass().add("controls-layout");
    layout.setAlignment(Pos.CENTER);
    // Previous
    Button previous = new Button("\u25c4");
    previous.setOnAction(
        (ActionEvent event) -> {
          controller.clearBoard();
          controller.prevPuzzle();
        });
    layout.getChildren().add(previous);

    // Random
    int i = 0;
    Button random = new Button();
    random.setText("Random");
    random.setOnAction(
        (ActionEvent event) -> {
          controller.clearBoard();
          controller.randPuzzle();
        });
    layout.getChildren().add(random);

    // Next
    Button next = new Button("\u25ba");
    next.setOnAction(
        (ActionEvent event) -> {
          controller.clearBoard();
          controller.nextPuzzle();
        });
    layout.getChildren().add(next);

    return layout;
  }
}
