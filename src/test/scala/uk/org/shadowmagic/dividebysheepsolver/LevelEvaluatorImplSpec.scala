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

  it should "return false if any rafts remain" in {
    // Arrange
    val level = Level(Array(), Seq(Raft()))

    // Act
    val succeeded = levelEvaluator.hasSucceeded(level)

    // Assert
    succeeded must be (false)
  }
}
