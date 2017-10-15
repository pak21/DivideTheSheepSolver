package uk.org.shadowmagic.dividebysheepsolver

case class Raft(sheep: Int = 0, wolves: Int = 0) {
  def isEmpty = sheep == 0 && wolves == 0

  override def canEqual(that: Any) = that.isInstanceOf[Raft]

  override def equals(that: Any) =
    that match {
      case obj: Raft => obj.canEqual(this) && this.hashCode == obj.hashCode
      case _ => false
    }

  override def hashCode() = {
    val prime = 65521
    sheep * prime + wolves
  }
}
