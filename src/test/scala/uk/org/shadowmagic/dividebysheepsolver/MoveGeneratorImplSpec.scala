package uk.org.shadowmagic.dividebysheepsolver

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, MustMatchers}

class MoveGeneratorImplSpec extends FlatSpec with MustMatchers with MockFactory {
  val moveSimulator = mock[MoveSimulator]
  val levelEvaluator = mock[LevelEvaluator]

  val moveGenerator = new MoveGeneratorImpl(moveSimulator, levelEvaluator)

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

  it must "filter previously seen levels" in {
    // Arrange
    val previousLevel = Level(Seq(Island(1)), Seq.empty[Raft])
    val previousState = (previousLevel, Seq.empty[Move])
    val newState = (Level(Seq(Island(2)), Seq.empty[Raft]), Seq.empty[Move])
    val newLevels = Seq(previousState, newState)
    val seen = Set(previousLevel)

    (levelEvaluator.hasFailed _).expects(*).returning(None).once

    // Act
    val viable = moveGenerator.filterMoves(newLevels, seen)

    // Assert
    viable.size must be (1)
  }

  it must "filter failed levels" in {
    // Arrange
    val newLevels = Seq((Level(Seq.empty[Island], Seq.empty[Raft]), Seq.empty[Move]))
    val seen = Set.empty[Level]

    (levelEvaluator.hasFailed _).expects(*).returning(Some(LevelFailureReason.NotEnoughSheep)).once

    // Act
    val viable = moveGenerator.filterMoves(newLevels, seen)

    // Assert
    viable mustBe empty
  }

  it must "remove duplicate levels with different moves" in {
    // Arrange
    val newLevel = Level(Seq.empty[Island], Seq.empty[Raft])
    val move1 = IslandToIslandMove(0, 1, moveSimulator)
    val move2 = IslandToIslandMove(0, 2, moveSimulator)

    val state1 = (newLevel, Seq(move1))
    val state2 = (newLevel, Seq(move2))
    val states = Seq(state1, state2)

    val seen = Set.empty[Level]

    (levelEvaluator.hasFailed _).expects(*).returning(None).anyNumberOfTimes

    // Act
    val viable = moveGenerator.filterMoves(states, seen)

    // Assert
    viable.size must be (1)
  }
}
