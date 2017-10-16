package uk.org.shadowmagic.dividebysheepsolver

import scala.collection.immutable.Queue

class BreadthFirstSearcherImpl(evaluator: LevelEvaluator, generator: MoveGenerator) extends BreadthFirstSearcher {
  override def search(initialState: Level) = {
    var context = SearchContext(Queue(initialState), Set(initialState), false)

    while (!context.success && context.queue.nonEmpty) {
      val (before, newQueue) = context.queue.dequeue

      val nextMoves = generator.generateMoves()
      val nextLevels = nextMoves.map { _.apply(before) }
      val viable = generator.filterMoves(nextLevels, context.seen)
      val success = viable.exists { evaluator.hasSucceeded }

      context = SearchContext(newQueue ++ Queue(viable: _*), context.seen ++ Set(viable: _*), success)
    }

    context
  }
}
