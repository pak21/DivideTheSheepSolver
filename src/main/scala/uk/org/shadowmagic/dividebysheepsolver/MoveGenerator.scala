package uk.org.shadowmagic.dividebysheepsolver

trait MoveGenerator {
  def generateMoves(): Seq[Move]
  def filterMoves(moves: Seq[Level], seen: Set[Level]): Seq[Level]
}
