package com.kailash-codex.nonograms.view;

import com.kailash-codex.nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PuzzleView implements FXComponent {
  public static Label[][] setofGrid;
  private final Controller controller;

  public PuzzleView(Controller controller) {
    this.controller = controller;
    setofGrid = new Label[controller.getClues().getWidth()][controller.getClues().getHeight()];
  }

  @Override
  public Parent render() {
    GridPane layout = new GridPane();
    Label base = new Label();
    base.setPrefHeight(60);
    base.setPrefWidth(60);
    layout.add(base, 0, 0);

    // Sets up Col Clues
    for (int k = 0; k < controller.getClues().getWidth(); k++) {
      VBox clueSet = new VBox();
      clueSet.setStyle("-fx-border-color: black;");
      for (int l = 0; l < controller.getClues().getColClues(k).length; l++) {
        if (controller.getClues().getColClues(k)[l] == 0) {
          Label blank = new Label();
          blank.setPrefWidth(28);
          blank.setPrefHeight(28);
          blank.setText(" ");
          blank.setAlignment(Pos.CENTER);
          clueSet.getChildren().add(blank);
        } else if (controller.getClues().getColClues(k)[l] != 0) {
          Label num = new Label();
          num.setPrefWidth(28);
          num.setPrefHeight(28);
          num.setText("" + controller.getClues().getColClues(k)[l]);
          num.setAlignment(Pos.CENTER);
          clueSet.getChildren().add(num);
        }
      }
      layout.add(clueSet, k + 1, 0);
    }

    // Sets up Row Clues
    for (int k = 0; k < controller.getClues().getHeight(); k++) {
      HBox clueSet = new HBox();
      clueSet.setStyle("-fx-border-color: black;");
      for (int l = 0; l < controller.getClues().getRowClues(k).length; l++) {
        if (controller.getClues().getRowClues(k)[l] == 0) {
          Label blank = new Label();
          blank.setPrefWidth(28);
          blank.setPrefHeight(28);
          blank.setText(" ");
          blank.setAlignment(Pos.CENTER);
          clueSet.getChildren().add(blank);
        } else if (controller.getClues().getRowClues(k)[l] != 0) {
          Label num = new Label();
          num.setPrefWidth(28);
          num.setPrefHeight(28);
          num.setText("" + controller.getClues().getRowClues(k)[l]);
          num.setAlignment(Pos.CENTER);
          clueSet.getChildren().add(num);
        }
      }
      layout.add(clueSet, 0, k + 1);
    }

    for (int i = 0; i < controller.getClues().getWidth(); i++) {
      for (int j = 0; j < controller.getClues().getHeight(); j++) {
        Label item = new Label();
        item.setPrefWidth(30);
        item.setPrefHeight(30);
        item.setStyle("-fx-border-color: black;");
        if (setofGrid[i][j] == null) {
          setofGrid[i][j] = item;
        }
        final int ii = i;
        final int jj = j;
        item.setOnMouseClicked(
            (MouseEvent event) -> {
              if (event.getButton() == MouseButton.PRIMARY) {

                controller.toggleShaded(jj, ii);

                if (controller.isShaded(jj, ii)) {
                  setofGrid[ii][jj].setStyle(
                      "-fx-background-color: gray; -fx-text-alignment: center; -fx-border-color: black;");
                } else {
                  setofGrid[ii][jj].setStyle(
                      "-fx-background-color: default; -fx-text-alignment: center; -fx-border-color: black;");
                }
                item.setText(" ");

              } else if (event.getButton() == MouseButton.SECONDARY) {
                controller.toggleEliminated(jj, ii);
                if (controller.isEliminated(jj, ii)) {
                  setofGrid[ii][jj].setTextFill(Color.web("#b22222"));
                  setofGrid[ii][jj].setText("x");
                  setofGrid[ii][jj].setAlignment(Pos.CENTER);
                  setofGrid[ii][jj].setStyle(
                      "-fx-background-color: default; -fx-font-size: 24px; -fx-border-color: black; -fx-text-alignment: center;");

                } else {
                  setofGrid[ii][jj].setStyle(
                      "-fx-background-color: default; -fx-text-alignment: center; -fx-border-color: black;");
                  setofGrid[ii][jj].setText(" ");
                }
              }
            });
        layout.add(setofGrid[ii][jj], i + 1, j + 1);
      }
    }

    return layout;
  }
}
