package uk.org.shadowmagic.dividebysheepsolver

import uk.org.shadowmagic.dividebysheepsolver.LevelFailureReason.LevelFailureReason

trait LevelEvaluator {
  def hasSucceeded(level: Level): Boolean

  def hasFailed(level: Level): Option[LevelFailureReason]
}
