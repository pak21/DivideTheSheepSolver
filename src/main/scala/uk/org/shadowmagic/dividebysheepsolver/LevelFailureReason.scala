package uk.org.shadowmagic.dividebysheepsolver

object LevelFailureReason extends Enumeration {
  type LevelFailureReason = Value
  val RaftOverloaded, NotEnoughSheep, NotEnoughWolves  = Value
}
