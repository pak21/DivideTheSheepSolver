package uk.org.shadowmagic.dividebysheepsolver

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, MustMatchers}

class StateFilterImplSpec extends FlatSpec with MustMatchers with MockFactory {
  val moveSimulator = mock[MoveSimulator]
  val levelEvaluator = mock[LevelEvaluator]

  val stateFilter = new StateFilterImpl(levelEvaluator)

  "A state filter" must "filter previously seen levels" in {
    // Arrange
    val previousLevel = Level(Seq(Island(1)), Seq.empty[Raft])
    val previousState = (previousLevel, Seq.empty[Move])
    val newState = (Level(Seq(Island(2)), Seq.empty[Raft]), Seq.empty[Move])
    val newLevels = Seq(previousState, newState)
    val seen = Set(previousLevel)

    (levelEvaluator.hasFailed _).expects(*).returning(None).once

    // Act
    val viable = stateFilter.filterStates(newLevels, seen)

    // Assert
    viable.size must be (1)
  }

  it must "filter failed levels" in {
    // Arrange
    val newLevels = Seq((Level(Seq.empty[Island], Seq.empty[Raft]), Seq.empty[Move]))
    val seen = Set.empty[Level]

    (levelEvaluator.hasFailed _).expects(*).returning(Some(LevelFailureReason.NotEnoughSheep)).once

    // Act
    val viable = stateFilter.filterStates(newLevels, seen)

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
    val viable = stateFilter.filterStates(states, seen)

    // Assert
    viable.size must be (1)
  }
}
