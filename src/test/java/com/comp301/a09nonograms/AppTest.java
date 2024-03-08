package com.kailash-codex.nonograms;

import com.kailash-codex.nonograms.controller.Controller;
import com.kailash-codex.nonograms.controller.ControllerImpl;
import com.kailash-codex.nonograms.model.Model;
import com.kailash-codex.nonograms.model.ModelImpl;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/** Unit test for simple App. */
public class AppTest {
  /** Rigorous Test :-) */
  @Test
  public void shouldAnswerWithTrue() {
    Model test = new ModelImpl(PuzzleLibrary.create());
    Controller remote = new ControllerImpl(test);
    test.setPuzzleIndex(3);
    remote.toggleShaded(0, 0);
    remote.toggleShaded(1, 0);
    remote.toggleShaded(4, 0);
    remote.toggleShaded(0, 1);
    remote.toggleShaded(1, 1);
    remote.toggleShaded(0, 2);
    remote.toggleShaded(1, 2);
    remote.toggleShaded(2, 2);
    remote.toggleShaded(3, 2);
    remote.toggleShaded(1, 3);
    remote.toggleShaded(2, 3);
    remote.toggleShaded(3, 4);
    remote.toggleShaded(2, 4);
    assertTrue(test.isSolved());
  }
}
