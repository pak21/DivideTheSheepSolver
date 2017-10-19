package uk.org.shadowmagic.dividebysheepsolver

import org.scalatest.{FlatSpec, MustMatchers}

class RaftSpec extends FlatSpec with MustMatchers {
  "A raft" must "return empty if it has no sheep or wolves" in {
    // Arrange
    val raft = Raft()

    // Act
    val empty = raft.isEmpty

    // Assert
    empty mustBe true
  }

  it must "not return empty if it has a sheep" in {
    // Arrange
    val raft = Raft(sheep = 1)

    // Act
    val empty = raft.isEmpty

    // Assert
    empty mustBe false
  }

  it must "return empty if it has a wolf" in {
    // Arrange
    val raft = Raft(wolves = 1)

    // Act
    val empty = raft.isEmpty

    // Assert
    empty mustBe false
  }
}
