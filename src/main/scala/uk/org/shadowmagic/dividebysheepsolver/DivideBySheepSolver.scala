package uk.org.shadowmagic.dividebysheepsolver

object DivideBySheepSolver {
  def main(args: Array[String]) {
    val islands = Seq(
      Island(3, 3),
      Island(4),
      Island(3),

      Island(3),
      Island(4),
      Island(3),

      Island(4),
      Island(5),
      Island(2, 2)
    )

    val level = Level(islands, Seq(Raft(2), Raft(3)))

    val simulator = new MoveSimulatorImpl
    val evaluator = new LevelEvaluatorImpl
    val generator = new MoveGeneratorImpl(simulator, evaluator)
    val searcher = new BreadthFirstSearcherImpl(evaluator, generator)

    val context = searcher.search(level)

    println(s"${context.success} ${context.queue.length}")

    if (context.success) {
      val answer = context.queue.filter(state => evaluator.hasSucceeded(state._1)).head
      val moves = answer._2.reverse
      moves.foreach {
        println(_)
      }
    }
  }
}
