package com.kailash-codex.nonograms.model;

public class BoardImpl implements Board {

  private Tag[][] _board;
  private int toggleShadedCounter;
  private int toggleEliminatedCounter;

  public BoardImpl(int x, int y) {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException();
    }

    _board = new Tag[x][y];

    for (int i = 0; i < _board.length; i++) {
      for (int j = 0; j < _board[i].length; j++) {
        _board[i][j] = Tag.SPACE;
      }
    }

    toggleShadedCounter = 1;
    toggleEliminatedCounter = 1;
  }

  @Override
  public boolean isShaded(int row, int col) {
    if (row < 0 || col < 0 || row >= _board.length || col >= _board[row].length) {
      throw new IllegalArgumentException();
    }
    if (_board[row][col] == Tag.SHADED) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean isEliminated(int row, int col) {
    if (row < 0 || col < 0 || row >= _board.length || col >= _board[row].length) {
      throw new IllegalArgumentException();
    }

    if (_board[row][col] == Tag.ELIMINATED) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean isSpace(int row, int col) {
    if (row < 0 || col < 0 || row >= _board.length || col >= _board[row].length) {
      throw new IllegalArgumentException();
    }
    if (_board[row][col] == Tag.SPACE) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    if (row < 0 || col < 0 || row >= _board.length || col >= _board[row].length) {
      throw new IllegalArgumentException();
    }

    if (_board[row][col] != Tag.SHADED) {
      _board[row][col] = Tag.SHADED;
    } else {
      _board[row][col] = Tag.SPACE;
    }
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    if (row < 0 || col < 0 || row >= _board.length || col >= _board[row].length) {
      throw new IllegalArgumentException();
    }

    if (_board[row][col] != Tag.ELIMINATED) {
      _board[row][col] = Tag.ELIMINATED;
    } else {
      _board[row][col] = Tag.SPACE;
    }
  }

  @Override
  public void clear() {
    for (int i = 0; i < _board.length; i++) {
      for (int j = 0; j < _board[i].length; j++) {
        _board[i][j] = Tag.SPACE;
      }
    }
  }

  public enum Tag {
    SHADED,
    ELIMINATED,
    SPACE
  }
}
