package uk.org.shadowmagic.dividebysheepsolver

trait MoveGenerator {
  def generateMoves(): Seq[Move]
  def filterMoves(states: Seq[(Level, Seq[Move])], seen: Set[Level]): Map[Level, Seq[Move]]
}
