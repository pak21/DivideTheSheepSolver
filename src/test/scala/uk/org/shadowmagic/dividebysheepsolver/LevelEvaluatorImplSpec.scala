package uk.org.shadowmagic.dividebysheepsolver

import org.scalatest.{FlatSpec, MustMatchers}

class LevelEvaluatorImplSpec extends FlatSpec with MustMatchers {
  val levelEvaluator = new LevelEvaluatorImpl

  "A level evaluator" should "return success if all rafts have been filled" in {
    // Arrange
    val level = Level(Array(), Seq.empty)

    // Act
    val succeeded = levelEvaluator.hasSucceeded(level)

    // Assert
    succeeded must be (true)
  }

  it should "not return success if any rafts remain" in {
    // Arrange
    val level = Level(Array(), Seq(Raft()))

    // Act
    val succeeded = levelEvaluator.hasSucceeded(level)

    // Assert
    succeeded must be (false)
  }

  it should "not return failed if nothing is wrong" in {
    // Arrange
    val island = Island(3, 3)
    val raft = Raft(3)
    val level = Level(Array(island), Seq(raft))

    // Act
    val failed = levelEvaluator.hasFailed(level)

    // Assert
    failed must be (None)
  }

  it should "return failed if a raft is overloaded with sheep" in {
    // Arrange
    val island = Island(3, 3)
    val raft = Raft(-1)
    val level = Level(Array(island), Seq(raft))

    // Act
    val failed = levelEvaluator.hasFailed(level)

    // Assert
    failed must be (LevelFailureReason.RaftOverloaded)
  }

  it should "return failed if a raft is overloaded with wolves" in {
    // Arrange
    val island = Island(3, 3)
    val raft = Raft(wolves = -1)
    val level = Level(Array(island), Seq(raft))

    // Act
    val failed = levelEvaluator.hasFailed(level)

    // Assert
    failed must be (LevelFailureReason.RaftOverloaded)
  }

  it should "return failed if there are not enough sheep left" in {
    // Arrange
    val island = Island(3, 1)
    val raft = Raft(3)
    val level = Level(Array(island), Seq(raft))

    // Act
    val failed = levelEvaluator.hasFailed(level)

    // Assert
    failed must be (LevelFailureReason.NotEnoughSheep)
  }

  it should "return failed if there are not enough wolves left" in {
    // Arrange
    val island = Island(3, hungryWolves =  1)
    val raft = Raft(wolves = 3)
    val level = Level(Array(island), Seq(raft))

    // Act
    val failed = levelEvaluator.hasFailed(level)

    // Assert
    failed must be (LevelFailureReason.NotEnoughWolves)
  }

  it should "return failed if there are full wolves but not enough hungry wolves" in {
    // Arrange
    val island = Island(3, hungryWolves = 1, fullWolves =  3)
    val raft = Raft(wolves = 3)
    val level = Level(Array(island), Seq(raft))

    // Act
    val failed = levelEvaluator.hasFailed(level)

    // Assert
    failed must be (LevelFailureReason.NotEnoughWolves)
  }
}
