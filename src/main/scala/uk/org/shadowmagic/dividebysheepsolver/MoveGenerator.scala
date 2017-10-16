package uk.org.shadowmagic.dividebysheepsolver

trait MoveGenerator {
  def generateMoves(): Seq[Move]
  def filterMoves(moves: Seq[(Level, Seq[Move])], seen: Set[Level]): Seq[(Level, Seq[Move])]
}
