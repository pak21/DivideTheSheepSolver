package uk.org.shadowmagic.dividebysheepsolver

trait MoveSimulator {
  def move(before: Level, from: Int, to: Int): Level

  def moveToRaft(before: Level, from: Int): Level
}
