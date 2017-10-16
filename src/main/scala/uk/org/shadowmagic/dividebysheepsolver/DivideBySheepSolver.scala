package uk.org.shadowmagic.dividebysheepsolver

object DivideBySheepSolver {
  def main(args: Array[String]) {
    val islands = Seq(
      Island(2),
      Island(2),
      Island(2),

      Island(2),
      Island(2),
      Island(2),

      Island(2, 1),
      Island(2),
      Island(2, 1)
    )

    val raft = Raft(2)

    val level = Level(islands, Seq(raft))

    val simulator = new MoveSimulatorImpl
    val evaluator = new LevelEvaluatorImpl
    val generator = new MoveGeneratorImpl(simulator, evaluator)
    val searcher = new BreadthFirstSearcherImpl(evaluator, generator)

    val context = searcher.search(level)

    println(s"${context.success} ${context.queue.length}")
  }
}
