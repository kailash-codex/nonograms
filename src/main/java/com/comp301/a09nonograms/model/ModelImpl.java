package com.kailash-codex.nonograms.model;

import com.kailash-codex.nonograms.view.PuzzleView;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private List<Clues> _cluesList;
  private List<Puzzle> _puzzleList;
  private int _index;
  private List<ModelObserver> _observors;

  public ModelImpl(List<Clues> clues) {
    // Constructor code here
    _cluesList = clues;
    _puzzleList = new ArrayList<>();
    for (Clues clue : _cluesList) {
      _puzzleList.add(new Puzzle(clue));
    }
    _observors = new ArrayList<>();
  }

  @Override
  public int getPuzzleCount() {
    return _puzzleList.size();
  }

  @Override
  public int getPuzzleIndex() {
    return _index;
  }

  @Override
  public void setPuzzleIndex(int index) {
    if (index < 0 || index >= getPuzzleCount()) {
      throw new IllegalArgumentException();
    }

    _index = index;
    PuzzleView.setofGrid = new Label[getClues().getWidth()][getClues().getHeight()];
    notifyObservors();
  }

  @Override
  public void addObserver(ModelObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    }
    _observors.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    }
    _observors.remove(observer);
  }

  @Override
  public boolean isSolved() {
    return (checkRows() && checkColumns());
  }

  private boolean checkColumns() {
    List<List<Integer>> holder = new ArrayList<>();
    for (int i = 0; i < _puzzleList.get(_index).getClue().getWidth(); i++) {
      int shadedCounter = 0;
      List<Integer> columnVals = new ArrayList<>();
      for (int k = 0; k < _puzzleList.get(_index).getClue().getColClues(i).length; k++) {
        if (_puzzleList.get(_index).getClue().getColClues(i)[k] == 0) {
          columnVals.add(0);
        }
      }
      for (int j = 0; j < _puzzleList.get(_index).getClue().getHeight(); j++) {
        if (_puzzleList.get(_index).getBoard().isShaded(j, i)) {
          shadedCounter++;
        } else {
          if (shadedCounter != 0) {
            columnVals.add(shadedCounter);
            shadedCounter = 0;
          }
        }
      }
      if (shadedCounter != 0) {
        columnVals.add(shadedCounter);
        shadedCounter = 0;
      }
      holder.add(columnVals);
    }

    boolean condition = true;

    for (int i = 0; i < holder.size(); i++) {
      for (int j = 0; i < holder.get(i).size(); i++) {
        condition =
            condition
                && (holder.get(i).get(j) == _puzzleList.get(_index).getClue().getColClues(i)[j]);
      }
    }

    return condition;
  }

  private boolean checkRows() {
    List<List<Integer>> holder = new ArrayList<>();
    for (int i = 0; i < _puzzleList.get(_index).getClue().getHeight(); i++) {
      int shadedCounter = 0;
      List<Integer> rowVals = new ArrayList<>();
      for (int k = 0; k < _puzzleList.get(_index).getClue().getRowClues(i).length; k++) {
        if (_puzzleList.get(_index).getClue().getRowClues(i)[k] == 0) {
          rowVals.add(0);
        }
      }
      for (int j = 0; j < _puzzleList.get(_index).getClue().getWidth(); j++) {
        if (_puzzleList.get(_index).getBoard().isShaded(i, j) == true) {
          shadedCounter++;
        } else {
          if (shadedCounter != 0) {
            rowVals.add(shadedCounter);
            shadedCounter = 0;
          }
        }
      }
      if (shadedCounter != 0) {
        rowVals.add(shadedCounter);
        shadedCounter = 0;
      }
      holder.add(rowVals);
    }

    boolean condition = true;

    for (int i = 0; i < holder.size(); i++) {
      if (holder.get(i).size() != _puzzleList.get(_index).getClue().getRowClues(i).length) {
        condition = false;
        break;
      }
      for (int j = 0; j < holder.get(i).size(); j++) {
        condition =
            condition
                && (holder.get(i).get(j) == _puzzleList.get(_index).getClue().getRowClues(i)[j]);
      }
    }

    return condition;
  }

  @Override
  public boolean isShaded(int row, int col) {
    return _puzzleList.get(_index).getBoard().isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return _puzzleList.get(_index).getBoard().isEliminated(row, col);
  }

  @Override
  public boolean isSpace(int row, int col) {
    return _puzzleList.get(_index).getBoard().isSpace(row, col);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    _puzzleList.get(_index).getBoard().toggleCellShaded(row, col);
    notifyObservors();
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    _puzzleList.get(_index).getBoard().toggleCellEliminated(row, col);
    notifyObservors();
  }

  @Override
  public void clear() {
    _puzzleList.get(_index).getBoard().clear();
    PuzzleView.setofGrid = new Label[getClues().getWidth()][getClues().getHeight()];
    notifyObservors();
  }

  @Override
  public int getWidth() {
    return _puzzleList.get(_index).getClue().getWidth();
  }

  @Override
  public int getHeight() {
    return _puzzleList.get(_index).getClue().getHeight();
  }

  @Override
  public int[] getRowClues(int index) {
    return _puzzleList.get(_index).getClue().getRowClues(index);
  }

  @Override
  public int[] getColClues(int index) {
    return _puzzleList.get(_index).getClue().getColClues(index);
  }

  @Override
  public int getRowCluesLength() {
    return _puzzleList.get(_index).getClue().getRowCluesLength();
  }

  @Override
  public int getColCluesLength() {
    return _puzzleList.get(_index).getClue().getColCluesLength();
  }

  public Clues getClues() {
    return _puzzleList.get(_index).getClue();
  }

  public void notifyObservors() {
    for (ModelObserver o : _observors) {
      o.update(this);
    }
  }
}
