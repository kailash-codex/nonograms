package com.kailash-codex.nonograms.view;

import com.kailash-codex.nonograms.controller.Controller;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MessageView implements FXComponent {
  private Controller controller;

  public MessageView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();
    layout.getStyleClass().add("reset");
    layout.setAlignment(Pos.CENTER);

    // Button
    Button reset = new Button();
    reset.setText("Reset Board");
    reset.setOnAction(
        (ActionEvent event) -> {
          controller.clearBoard();
        });
    layout.getChildren().add(reset);

    HBox align = new HBox();
    align.setAlignment(Pos.CENTER);
    // Label Description
    Label stat = new Label("Status:");
    stat.getStyleClass().add("result");
    align.getChildren().add(stat);

    // Label
    Label result = new Label();
    result.getStyleClass().add("result");
    if (controller.isSolved()) {
      result.setText("Complete");
      result.setTextFill(Color.web("#2e8b57"));
    } else {
      result.setTextFill(Color.web("#000000"));
      result.setText("Incomplete");
    }
    align.getChildren().add(result);

    layout.getChildren().add(align);

    return layout;
  }
}
