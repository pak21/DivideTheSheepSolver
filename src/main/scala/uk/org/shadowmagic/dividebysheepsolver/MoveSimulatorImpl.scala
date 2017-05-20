package uk.org.shadowmagic.dividebysheepsolver

class MoveSimulatorImpl extends MoveSimulator {
  override def move(before: Level, from: Int, to: Int) = {
    val newIslands = before.islands.zipWithIndex.map { case (island, i) =>
      i match {
        case `from` => Island(island.spaces, fullWolves = island.fullWolves)
        case `to` => mergeIslands(before.islands(from), island)
        case _ => island
      }
    }
    Level(newIslands, before.rafts)
  }

  override def moveToRaft(before: Level, from: Int) = {
    val newIslands = before.islands.zipWithIndex.map { case (island, i) =>
      i match {
        case `from` => Island(island.spaces, fullWolves = island.fullWolves)
        case _ => island
      }
    }

    val sourceIsland = before.islands(from)
    val targetRaft = before.rafts.head
    val newHeadRaft = Raft(targetRaft.sheep - sourceIsland.sheep, targetRaft.wolves - sourceIsland.hungryWolves)
    val newRafts = newHeadRaft +: before.rafts.drop(1)

    Level(newIslands, newRafts)
  }

  private def mergeIslands(source: Island, target: Island) = {
    val availableSpace = target.spaces - target.fullWolves

    val sheepBeforeEating = Math.min(source.sheep + target.sheep, availableSpace)
    val hungryWolvesBeforeEating = Math.min(source.hungryWolves + target.hungryWolves, availableSpace)

    val sheepEaten = Math.min(sheepBeforeEating, hungryWolvesBeforeEating)

    val sheepAfterEating = sheepBeforeEating - sheepEaten
    val hungryWolvesAfterEating = hungryWolvesBeforeEating - sheepEaten
    val fullWolvesAfterEating = target.fullWolves + sheepEaten

    Island(target.spaces, sheepAfterEating, 0, hungryWolvesAfterEating, fullWolvesAfterEating)
  }
}
