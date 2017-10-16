package uk.org.shadowmagic.dividebysheepsolver

case class IslandToRaftMove(from: Int, moveSimulator: MoveSimulator) extends Move {
  override val description = s"Move from Island {from} to Raft"
  override def apply(level: Level) = moveSimulator.moveToRaft(level, from)
}
