package uk.org.shadowmagic.dividebysheepsolver

case class Island(spaces: Int, sheep: Int = 0, halfSheep: Int = 0, hungryWolves: Int = 0, fullWolves: Int = 0) {
  override def canEqual(that: Any) = that.isInstanceOf[Island]

  override def equals(that: Any) =
    that match {
      case obj: Island => obj.canEqual(this) && this.hashCode == obj.hashCode
      case _ => false
    }

  override def hashCode = {
    val prime = 65521
    var result = 1
    result = prime * result + spaces
    result = prime * result + sheep
    result = prime * result + halfSheep
    result = prime * result + hungryWolves
    result = prime * result + fullWolves
    result
  }
}
