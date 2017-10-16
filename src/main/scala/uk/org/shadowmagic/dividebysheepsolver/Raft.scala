package uk.org.shadowmagic.dividebysheepsolver

case class Raft(sheep: Int = 0, wolves: Int = 0) {
  def isEmpty = sheep == 0 && wolves == 0
}
