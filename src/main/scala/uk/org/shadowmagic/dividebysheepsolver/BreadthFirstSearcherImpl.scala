package uk.org.shadowmagic.dividebysheepsolver

import scala.collection.immutable.Queue

class BreadthFirstSearcherImpl(evaluator: LevelEvaluator, generator: MoveGenerator) extends BreadthFirstSearcher {
  override def search(initialState: Level) = {
    val initialQueue = Queue((initialState, Seq.empty[Move]))
    val initialSeen = Map(initialState -> Seq.empty[Move])
    var context = SearchContext(initialQueue, initialSeen, false)

    while (!context.success && context.queue.nonEmpty) {
      val (before, newQueue) = context.queue.dequeue

      val nextMoves = generator.generateMoves()
      val nextLevels = nextMoves.map { move => (move.apply(before._1), move +: before._2) }
      val viable = generator.filterMoves(nextLevels, context.seen.keySet)
      val success = viable.exists { state => evaluator.hasSucceeded(state._1) }

      context = SearchContext(newQueue ++ Queue(viable: _*), context.seen ++ Set(viable: _*), success)
    }

    context
  }
}
