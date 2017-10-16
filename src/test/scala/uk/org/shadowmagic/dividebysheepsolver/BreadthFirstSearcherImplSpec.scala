package uk.org.shadowmagic.dividebysheepsolver

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, MustMatchers}

class BreadthFirstSearcherImplSpec extends FlatSpec with MustMatchers with MockFactory {
  val levelEvaluator = mock[LevelEvaluator]
  val moveGenerator = mock[MoveGenerator]

  val breadthFirstSearcher = new BreadthFirstSearcherImpl(levelEvaluator, moveGenerator)

  "A searcher" must "return false if no solution can be found" in {
    // Arrange
    val level = Level(Seq.empty[Island], Seq.empty[Raft])

    (moveGenerator.generateMoves _).expects.returning(Seq.empty[Move]).once
    (moveGenerator.filterMoves _).expects(Seq.empty[(Level, Seq[Move])], *).returning(Seq.empty[(Level, Seq[Move])]).once

    // Act
    val result = breadthFirstSearcher.search(level)

    // Assert
    result.success must be (false)
  }

  it must "return true if a solution can be found" in {
    // Arrange
    val initialState = Level(Seq.empty[Island], Seq.empty[Raft])
    val successState = Level(Seq(Island(1)), Seq.empty[Raft])
    val move = mock[Move]

    (moveGenerator.generateMoves _).expects.returning(Seq(move)).once
    (move.apply _).expects(initialState).returning(successState).once
    (moveGenerator.filterMoves _).expects(*, *).returning(Seq((successState, Seq(move)))).once
    (levelEvaluator.hasSucceeded _).expects(successState).returning(true).once

    // Act
    val result = breadthFirstSearcher.search(initialState)

    // Assert
    result.success must be (true)
  }
}
