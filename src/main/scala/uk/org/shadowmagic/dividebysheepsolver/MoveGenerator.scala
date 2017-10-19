package uk.org.shadowmagic.dividebysheepsolver

trait MoveGenerator {
  def generateMoves(): Seq[Move]
}
