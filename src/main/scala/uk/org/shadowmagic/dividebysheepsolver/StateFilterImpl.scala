package uk.org.shadowmagic.dividebysheepsolver

class StateFilterImpl(evaluator: LevelEvaluator) extends StateFilter {
  override def filterStates(states: Seq[(Level, Seq[Move])], seen: Set[Level]) = {
    val unseen = states.filter { state => !seen.contains(state._1) }
    val distinct = unseen.groupBy(_._1).map(_._2.head).toSeq
    distinct.filter { state => evaluator.hasFailed(state._1).isEmpty }.toMap
  }
}
