package com.kailash-codex.nonograms.model;

public class Puzzle {
  private Clues _clue;
  private Board _board;

  public Puzzle(Clues clue) {
    _clue = clue;
    _board = new BoardImpl(_clue.getHeight(), _clue.getWidth());
  }

  public Clues getClue() {
    return _clue;
  }

  public Board getBoard() {
    return _board;
  }
}
