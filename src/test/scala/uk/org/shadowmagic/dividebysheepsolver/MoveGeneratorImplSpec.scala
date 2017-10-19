package uk.org.shadowmagic.dividebysheepsolver

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, MustMatchers}

class MoveGeneratorImplSpec extends FlatSpec with MustMatchers with MockFactory {
  val moveSimulator = mock[MoveSimulator]
  val levelEvaluator = mock[LevelEvaluator]

  val moveGenerator = new MoveGeneratorImpl(moveSimulator)

  "A move generator" must "generate moves" in {
    // Arrange
    /*
    val level = Level(Seq.empty[Island], Seq.empty[Raft])
    (moveSimulator.move _).expects(level, 0, 1).once
    (moveSimulator.move _).expects(level, 0, 3).once
    (moveSimulator.move _).expects(level, 1, 0).once
    (moveSimulator.move _).expects(level, 1, 2).once
    (moveSimulator.move _).expects(level, 1, 4).once
    (moveSimulator.move _).expects(level, 2, 1).once
    (moveSimulator.move _).expects(level, 2, 5).once
    (moveSimulator.move _).expects(level, 3, 0).once
    (moveSimulator.move _).expects(level, 3, 4).once
    (moveSimulator.move _).expects(level, 3, 6).once
    (moveSimulator.move _).expects(level, 4, 1).once
    (moveSimulator.move _).expects(level, 4, 3).once
    (moveSimulator.move _).expects(level, 4, 5).once
    (moveSimulator.move _).expects(level, 4, 7).once
    (moveSimulator.move _).expects(level, 5, 2).once
    (moveSimulator.move _).expects(level, 5, 4).once
    (moveSimulator.move _).expects(level, 5, 8).once
    (moveSimulator.move _).expects(level, 6, 3).once
    (moveSimulator.move _).expects(level, 6, 7).once
    (moveSimulator.move _).expects(level, 7, 4).once
    (moveSimulator.move _).expects(level, 7, 6).once
    (moveSimulator.move _).expects(level, 7, 8).once
    (moveSimulator.move _).expects(level, 8, 5).once
    (moveSimulator.move _).expects(level, 8, 7).once

    (moveSimulator.moveToRaft _).expects(level, 1).once
    */

    // Act
    val moves = moveGenerator.generateMoves()

    // Assert
    moves.length must be (25)
  }
}
