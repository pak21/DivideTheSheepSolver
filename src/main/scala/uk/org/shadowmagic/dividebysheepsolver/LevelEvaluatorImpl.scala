package uk.org.shadowmagic.dividebysheepsolver

class LevelEvaluatorImpl extends LevelEvaluator {
  override def hasSucceeded(level: Level) = level.rafts.isEmpty
}
