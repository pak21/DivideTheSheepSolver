package uk.org.shadowmagic.dividebysheepsolver

import scala.collection.immutable.Queue

class BreadthFirstSearcherImpl(evaluator: LevelEvaluator, generator: MoveGenerator, filter: StateFilter) extends BreadthFirstSearcher {
  override def search(initialLevel: Level) = {
    val initialState = initialLevel -> Seq.empty[Move]
    val initialQueue = Queue(initialState)
    val initialSeen = Map(initialState)
    var context = SearchContext(initialQueue, initialSeen, false)

    while (!context.success && context.queue.nonEmpty) {
      val (before, newQueue) = context.queue.dequeue

      val moves = generator.generateMoves()
      val newStates = moves.map { move => (move.apply(before._1), move +: before._2) }
      val viableStates = filter.filterStates(newStates, context.seen.keySet)
      val success = viableStates.exists { state => evaluator.hasSucceeded(state._1) }

      context = SearchContext(newQueue ++ Queue(viableStates.toSeq: _*), context.seen ++ viableStates, success)
    }

    context
  }
}
