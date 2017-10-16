package uk.org.shadowmagic.dividebysheepsolver

trait Move {
  val description: String
  def apply(level: Level): Level
}
