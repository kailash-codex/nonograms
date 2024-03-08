package com.kailash-codex.nonograms.view;

import com.kailash-codex.nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TitleView implements FXComponent {
  private final Controller controller;

  public TitleView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();
    layout.getStyleClass().add("header");

    Label title = new Label("Nonogram");
    title.getStyleClass().add("title");
    layout.getChildren().add(title);

    Label pageNum =
        new Label((controller.getPuzzleIndex() + 1) + " of " + (controller.getPuzzleCount()));
    pageNum.getStyleClass().add("puzzle-Num");
    layout.getChildren().add(pageNum);

    return layout;
  }
}
