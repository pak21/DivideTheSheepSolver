package uk.org.shadowmagic.dividebysheepsolver

class MoveSimulatorImpl extends MoveSimulator {
  override def move(before: Level, from: Int, to: Int): Level = {
    val newIslands = before.islands.zipWithIndex.map { case (island, i) =>
      i match {
        case `from` => Island(island.spaces)
        case `to` => mergeIslands(before.islands(from), island)
        case _ => island
      }
    }
    Level(newIslands, before.rafts)
  }

  private def mergeIslands(source: Island, target: Island): Island = {
    val newSheep = Math.min(source.sheep + target.sheep, target.spaces)
    val newHungryWolves = Math.min(source.hungryWolves + target.hungryWolves, target.spaces)
    Island(target.spaces, newSheep, 0, newHungryWolves)
  }
}
