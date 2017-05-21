package uk.org.shadowmagic.dividebysheepsolver

class LevelEvaluatorImpl extends LevelEvaluator {
  override def hasSucceeded(level: Level) = level.rafts.isEmpty

  override def hasFailed(level: Level) = {
    isRaftOverloaded(level).orElse(doNotEnoughSheepRemain(level)).orElse(doNotEnoughWolvesRemain(level))
  }

  private def isRaftOverloaded(level: Level) = {
    val raft = level.rafts.head
    if (raft.sheep < 0 || raft.wolves < 0) Some(LevelFailureReason.RaftOverloaded) else None
  }

  private def doNotEnoughSheepRemain(level: Level) = {
    val sheepNeeded = level.rafts.map(_.sheep).sum
    val sheepRemaining = level.islands.map(_.sheep).sum
    if (sheepRemaining < sheepNeeded) Some(LevelFailureReason.NotEnoughSheep) else None
  }

  private def doNotEnoughWolvesRemain(level: Level) = {
    val wolvesNeeded = level.rafts.map(_.wolves).sum
    val wolvesRemaining = level.islands.map(_.hungryWolves).sum
    if (wolvesRemaining < wolvesNeeded) Some(LevelFailureReason.NotEnoughWolves) else None
  }
}

