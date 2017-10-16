package uk.org.shadowmagic.dividebysheepsolver

trait MoveGenerator {
  def generateMoves(level: Level): Seq[Level]
  def filterMoves(moves: Seq[Level], seen: Set[Level]): Seq[Level]
}
