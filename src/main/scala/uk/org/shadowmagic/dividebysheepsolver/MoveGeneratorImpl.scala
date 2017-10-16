package uk.org.shadowmagic.dividebysheepsolver

class MoveGeneratorImpl(simulator: MoveSimulator, evaluator: LevelEvaluator) extends MoveGenerator {
  override def generateMoves(level: Level) =
    Seq(
      // TODO - pass in a geometry to know which moves to generate

      simulator.move(level, 0, 1),
      simulator.move(level, 0, 3),

      simulator.move(level, 1, 0),
      simulator.move(level, 1, 2),
      simulator.move(level, 1, 4),
      simulator.moveToRaft(level, 1),

      simulator.move(level, 2, 1),
      simulator.move(level, 2, 5),

      simulator.move(level, 3, 0),
      simulator.move(level, 3, 4),
      simulator.move(level, 3, 6),

      simulator.move(level, 4, 1),
      simulator.move(level, 4, 3),
      simulator.move(level, 4, 5),
      simulator.move(level, 4, 7),

      simulator.move(level, 5, 2),
      simulator.move(level, 5, 4),
      simulator.move(level, 5, 8),

      simulator.move(level, 6, 3),
      simulator.move(level, 6, 7),

      simulator.move(level, 7, 4),
      simulator.move(level, 7, 6),
      simulator.move(level, 7, 8),

      simulator.move(level, 8, 5),
      simulator.move(level, 8, 7)
    )

  override def filterMoves(moves: Seq[Level], seen: Set[Level]) = {
    val unseen = moves.filter { !seen.contains(_) }
    val distinct = unseen.distinct
    distinct.filter { evaluator.hasFailed(_).isEmpty }
  }
}
