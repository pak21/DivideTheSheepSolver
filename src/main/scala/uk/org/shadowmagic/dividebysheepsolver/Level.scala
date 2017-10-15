package uk.org.shadowmagic.dividebysheepsolver

case class Level(islands: Array[Island], rafts: Seq[Raft]) {
  override def canEqual(that: Any) = that.isInstanceOf[Level]

  override def equals(that: scala.Any) =
    that match {
      case obj: Level => obj.canEqual(this) && this.hashCode == obj.hashCode
      case _ => false
    }

  override def hashCode() = {
    val prime = 31
    islands.hashCode * prime + rafts.hashCode
  }
}
