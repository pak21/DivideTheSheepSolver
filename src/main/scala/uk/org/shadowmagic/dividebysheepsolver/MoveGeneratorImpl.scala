package uk.org.shadowmagic.dividebysheepsolver

class MoveGeneratorImpl(simulator: MoveSimulator) extends MoveGenerator {
  override def generateMoves() =
    Seq(
      // TODO - pass in a geometry to know which moves to generate

      IslandToIslandMove(0, 1, simulator),
      IslandToIslandMove(0, 3, simulator),

      IslandToIslandMove(1, 0, simulator),
      IslandToIslandMove(1, 2, simulator),
      IslandToIslandMove(1, 4, simulator),
      IslandToRaftMove(1, simulator),

      IslandToIslandMove(2, 1, simulator),
      IslandToIslandMove(2, 5, simulator),

      IslandToIslandMove(3, 0, simulator),
      IslandToIslandMove(3, 4, simulator),
      IslandToIslandMove(3, 6, simulator),

      IslandToIslandMove(4, 1, simulator),
      IslandToIslandMove(4, 3, simulator),
      IslandToIslandMove(4, 5, simulator),
      IslandToIslandMove(4, 7, simulator),

      IslandToIslandMove(5, 2, simulator),
      IslandToIslandMove(5, 4, simulator),
      IslandToIslandMove(5, 8, simulator),

      IslandToIslandMove(6, 3, simulator),
      IslandToIslandMove(6, 7, simulator),

      IslandToIslandMove(7, 4, simulator),
      IslandToIslandMove(7, 6, simulator),
      IslandToIslandMove(7, 8, simulator),

      IslandToIslandMove(8, 5, simulator),
      IslandToIslandMove(8, 7, simulator)
    )
}
