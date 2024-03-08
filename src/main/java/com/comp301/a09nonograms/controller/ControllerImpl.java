package com.kailash-codex.nonograms.controller;

import com.kailash-codex.nonograms.model.Clues;
import com.kailash-codex.nonograms.model.Model;

public class ControllerImpl implements Controller {

  private Model _myModel;

  public ControllerImpl(Model model) {
    // Constructor code goes here
    _myModel = model;
  }

  @Override
  public Clues getClues() {
    return _myModel.getClues();
  }

  @Override
  public boolean isSolved() {
    return _myModel.isSolved();
  }

  @Override
  public boolean isShaded(int row, int col) {
    return _myModel.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return _myModel.isEliminated(row, col);
  }

  @Override
  public void toggleShaded(int row, int col) {
    _myModel.toggleCellShaded(row, col);
  }

  @Override
  public void toggleEliminated(int row, int col) {
    _myModel.toggleCellEliminated(row, col);
  }

  @Override
  public void nextPuzzle() {
    int nextPuzzle = getPuzzleIndex();
    nextPuzzle++;
    if (nextPuzzle < getPuzzleCount()) {
      _myModel.setPuzzleIndex(nextPuzzle);
    }
  }

  @Override
  public void prevPuzzle() {
    int prevPuzzle = getPuzzleIndex();
    prevPuzzle--;
    if (prevPuzzle >= 0) {
      _myModel.setPuzzleIndex(prevPuzzle);
    }
  }

  @Override
  public void randPuzzle() {
    int randIndex = -1;

    do {
      randIndex = (int) (Math.random() * 5);
    } while (randIndex == getPuzzleIndex());

    _myModel.setPuzzleIndex(randIndex);
  }

  @Override
  public void clearBoard() {
    _myModel.clear();
  }

  @Override
  public int getPuzzleIndex() {
    return _myModel.getPuzzleIndex();
  }

  @Override
  public int getPuzzleCount() {
    return _myModel.getPuzzleCount();
  }
}
