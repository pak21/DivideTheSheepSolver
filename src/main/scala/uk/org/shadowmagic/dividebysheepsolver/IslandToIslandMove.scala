package uk.org.shadowmagic.dividebysheepsolver

case class IslandToIslandMove(from: Int, to: Int, moveSimulator: MoveSimulator) extends Move {
  override val description = s"Move from Island {from} to Island {to}"
  override def apply(level: Level) = moveSimulator.move(level, from, to)
}
