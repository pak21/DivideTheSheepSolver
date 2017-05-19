package uk.org.shadowmagic.dividebysheepsolver

import org.scalatest.{FlatSpec, MustMatchers}

class MoveSimulatorImplSpec extends FlatSpec with MustMatchers {
  val moveSimulator = new MoveSimulatorImpl();

  "A move simulator" should "allow sheep to be moved" in {
    // Arrange
    val island0 = Island(3, 3)
    val island1 = Island(3)
    val before = Level(Array(island0, island1), Seq.empty[Raft])

    // Act
    val after = moveSimulator.move(before, 0, 1)

    // Assert
    after.islands(0).sheep must be (0)
    after.islands(1).sheep must be (3)
  }

  it should "keep existing sheep on target island" in {
    // Arrange
    val island0 = Island(3, 1)
    val island1 = Island(3, 1)
    val before = Level(Array(island0, island1), Seq.empty[Raft])

    // Act
    val after = moveSimulator.move(before, 0, 1)

    // Assert
    after.islands(0).sheep must be (0)
    after.islands(1).sheep must be (2)
  }

  it should "discard excess sheep" in {
    // Arrange
    val island0 = Island(3, 3)
    val island1 = Island(3, 2)
    val before = Level(Array(island0, island1), Seq.empty[Raft])

    // Act
    val after = moveSimulator.move(before, 0, 1)

    // Assert
    after.islands(0).sheep must be (0)
    after.islands(1).sheep must be (3)
  }

  it should "be able to move hungry wolves" in {
    // Arrange
    val island0 = Island(3, hungryWolves = 3)
    val island1 = Island(3)
    val before = Level(Array(island0, island1), Seq.empty[Raft])

    // Act
    val after = moveSimulator.move(before, 0, 1)

    // Assert
    after.islands(0).hungryWolves must be (0)
    after.islands(1).hungryWolves must be (3)
  }

  it should "keep existing hungry wolves on target island" in {
    // Arrange
    val island0 = Island(3, hungryWolves = 1)
    val island1 = Island(3, hungryWolves = 1)
    val before = Level(Array(island0, island1), Seq.empty[Raft])

    // Act
    val after = moveSimulator.move(before, 0, 1)

    // Assert
    after.islands(0).hungryWolves must be (0)
    after.islands(1).hungryWolves must be (2)
  }

  it should "discard excess hungry wolves" in {
    // Arrange
    val island0 = Island(3, hungryWolves = 3)
    val island1 = Island(3, hungryWolves = 2)
    val before = Level(Array(island0, island1), Seq.empty[Raft])

    // Act
    val after = moveSimulator.move(before, 0, 1)

    // Assert
    after.islands(0).hungryWolves must be (0)
    after.islands(1).hungryWolves must be (3)
  }

  it should "change hungry wolves to full when they land on sheep" in {
    // Arrange
    val island0 = Island(3, hungryWolves = 2)
    val island1 = Island(3, 3)
    val before = Level(Array(island0, island1), Seq.empty[Raft])

    // Act
    val after = moveSimulator.move(before, 0, 1)

    // Assert
    after.islands(0).hungryWolves must be (0)
    after.islands(1).sheep must be (1)
    after.islands(1).hungryWolves must be (0)
    after.islands(1).fullWolves must be (2)
  }

  it should "change hungry wolves to full when sheep land on them" in {
    // Arrange
    val island0 = Island(3, 2)
    val island1 = Island(3, hungryWolves = 3)
    val before = Level(Array(island0, island1), Seq.empty[Raft])

    // Act
    val after = moveSimulator.move(before, 0, 1)

    // Assert
    after.islands(0).hungryWolves must be (0)
    after.islands(1).sheep must be (0)
    after.islands(1).hungryWolves must be (1)
    after.islands(1).fullWolves must be (2)
  }

  it should "not move full wolves" in {
    // Arrange
    val island0 = Island(3, fullWolves = 3)
    val island1 = Island(3)
    val before = Level(Array(island0, island1), Seq.empty[Raft])

    // Act
    val after = moveSimulator.move(before, 0, 1)

    // Assert
    after.islands(0).fullWolves must be (3)
    after.islands(1).fullWolves must be (0)
  }

  it should "discard sheep if full wolves take up space" in {
    // Arrange
    val island0 = Island(3, 3)
    val island1 = Island(3, fullWolves = 1)
    val before = Level(Array(island0, island1), Seq.empty[Raft])

    // Act
    val after = moveSimulator.move(before, 0, 1)

    // Assert
    after.islands(0).sheep must be (0)
    after.islands(1).sheep must be (2)
    after.islands(1).fullWolves must be (1)
  }

  it should "discard hungry wolves if full wolves take up space" in {
    // Arrange
    val island0 = Island(3, hungryWolves = 3)
    val island1 = Island(3, fullWolves = 1)
    val before = Level(Array(island0, island1), Seq.empty[Raft])

    // Act
    val after = moveSimulator.move(before, 0, 1)

    // Assert
    after.islands(0).hungryWolves must be (0)
    after.islands(1).hungryWolves must be (2)
    after.islands(1).fullWolves must be (1)
  }

  it should "add to full wolves when eating sheep" in {
    // Arrange
    val island0 = Island(3, 1)
    val island1 = Island(3, hungryWolves = 1, fullWolves = 1)
    val before = Level(Array(island0, island1), Seq.empty[Raft])

    // Act
    val after = moveSimulator.move(before, 0, 1)

    // Assert
    after.islands(0).sheep must be (0)
    after.islands(1).sheep must be (0)
    after.islands(1).hungryWolves must be (0)
    after.islands(1).fullWolves must be (2)
  }

  it should "allow sheep to move to a raft" in {
    // Arrange
    val island = Island(3, 3)
    val raft = Raft(3, 4)
    val before = Level(Array(island), Seq(raft))

    // Act
    val after = moveSimulator.moveToRaft(before, 0)

    // Assert
    after.islands(0).sheep must be (0)
    after.rafts(0).sheep must be (1)
  }

  it should "allow sheep to overload a raft" in {
    // Arrange
    val island = Island(3, 3)
    val raft = Raft(3, 2)
    val before = Level(Array(island), Seq(raft))

    // Act
    val after = moveSimulator.moveToRaft(before, 0)

    // Assert
    after.islands(0).sheep must be (0)
    after.rafts.head.sheep must be (-1)
  }

  it should "allow hungry wolves to move to a raft" in {
    // Arrange
    val island = Island(3, hungryWolves = 3)
    val raft = Raft(3, wolves = 4)
    val before = Level(Array(island), Seq(raft))

    // Act
    val after = moveSimulator.moveToRaft(before, 0)

    // Assert
    after.islands(0).hungryWolves must be (0)
    after.rafts.head.wolves must be (0)
  }

  it should "allow hungry wolves to overload a raft" in {
    // Arrange
    val island = Island(3, hungryWolves = 3)
    val raft = Raft(3, wolves = 2)
    val before = Level(Array(island), Seq(raft))

    // Act
    val after = moveSimulator.moveToRaft(before, 0)

    // Assert
    after.islands(0).hungryWolves must be (0)
    after.rafts.head.wolves must be (-1)
  }

  it should "not move full wolves to a raft" in {
    // Arrange
    val island = Island(3, fullWolves = 3)
    val raft = Raft(3, 3)
    val before = Level(Array(island), Seq(raft))

    // Act
    val after = moveSimulator.moveToRaft(before, 0)

    // Assert
    after.islands(0).fullWolves must be (3)
    after.rafts.head.sheep must be (3)
    after.rafts.head.wolves must be (3)
  }
}
